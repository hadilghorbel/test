package com.example.demo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.demo.RequestApiForm.ChangePasswordRequest;
import com.example.demo.RequestApiForm.MessageResponse;
import com.example.demo.RequestApiForm.RequestLogin;
import com.example.demo.RequestApiForm.SignupRequest;
import com.example.demo.exception.API_Request_Exception_NotFound;
import com.example.demo.model.entities.Role;
import com.example.demo.model.entities.User;
import com.example.demo.model.enums.ERole;
import com.example.demo.payload.JwtAuthenticationResponse;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.security.UserPrincipal;
import com.example.demo.services.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.*;

@Slf4j
@RestController
@CrossOrigin
public class UserRestController {

    @Autowired
    UserRepository userRepository;


    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/LoggingTest")
    public String index() {
        log.trace("A TRACE Message");
        log.debug("A DEBUG Message");
        log.info("An INFO Message");
        log.warn("A WARN Message");
        log.error("An ERROR Message");

        return "hi ! Check out the Logs to see the output...";
    }

    /* Get all the users */
    @Secured(value={"ROLE_MANAGER","ROLE_ADMIN"})
    @GetMapping("/retrieve-all-users")
    public List<User> getUsers() {
        return userService.retrieveAllUsers();
    }


    @PreAuthorize("#id == authentication.principal.id")
    @Secured(value="ROLE_MANAGER")
    @GetMapping("/get-employees/{user-id}")
    public List<User> getEmployees(@PathVariable("user-id") Long id){
        return userService.getEmployees(id);
    }

    /* Get user by his identify */
    @PreAuthorize("#userId == authentication.principal.id")
    @GetMapping("/retrieve-user/{user-id}")
    public Optional<User> retrieveUserById(@PathVariable("user-id") Long userId, HttpServletRequest request,
                                             HttpServletResponse response) throws UserPrincipalNotFoundException {
        if (!userRepository.existsById(userId)) {
            throw new API_Request_Exception_NotFound("User not found with this Identify : " + userId);
        }
        return userService.findById(userId);
    }

    /* REGISTRATION */
    @RequestMapping("/signup")
    public ResponseEntity<?> add_user(@Valid @RequestBody SignupRequest signUpRequest) {

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User();
        user.setGlobalGroupID(signUpRequest.getGlobalGroupID());
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setEmail(signUpRequest.getEmail());
        user.setActif(false);

        Set<Role> roles = new HashSet<>();

        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);

        user.setActif(true);
        user.setRoles(roles);

        userRepository.save(user);


        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
    /* END REGISTRATION */

    /* Sign-in */

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider tokenProvider;

    @RequestMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody RequestLogin loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    /* update account Only user with role ADMIN is authorized to use this API */
    @PreAuthorize("#id == authentication.principal.id")
    @PatchMapping("/updateUser/{user-id}")
    public Object updateUser(@RequestBody User user, @PathVariable("user-id") Long id,
                             BindingResult bindingResult) {
        return userService.updateUserById(user,id);

    }

    @Secured(value="ROLE_ADMIN")
    @PutMapping("/assign-employee/{user-id}")
    public ResponseEntity<?> AssignEmployeeToManager(@PathVariable("user-id") Long idEmployee, @RequestBody User manager){
        User employee = userRepository.findById(idEmployee).orElse(null);
        assert employee != null;
        employee.setManager(manager);
        userRepository.save(employee);
        return ResponseEntity.ok(new MessageResponse("Employee assigned successfully!"));
    }


    @Secured(value = "ROLE_MANAGER")
    @PutMapping("/deleteFromTeam/{user-id}")
    public void deleteFromTeam(@PathVariable("user-id") Long idEmployee) {
        userService.deleteEmployeeByID(idEmployee);
    }


    /* Logged out API */
    @PostMapping("/log-out")
    public ResponseEntity<?> logout(HttpSession session, Authentication auth, HttpServletRequest request) {
        log.info("Before logout");

        session.invalidate();
        SecurityContextHolder.getContext().setAuthentication(auth);
        UserPrincipal userDetails = (UserPrincipal) auth.getPrincipal();
        for (Cookie cookie : request.getCookies()) {
            cookie.setMaxAge(0);
        }
        userRepository.updateUserTimeLoggedOut(userDetails.getId(), new Date());
        log.info("time logged out has been updated with user id : " + userDetails.getId());
        return ResponseEntity.ok(new MessageResponse("session logged out"));
    }

    @PreAuthorize("#userID == authentication.principal.id")
    @GetMapping("/get-my-team/{user-id}")
    public List<User> getMyTeam(@PathVariable("user-id") Long userID){
        Long id = userRepository.findManagerById(userID);
        return userService.getEmployees(id);
    }

    @Secured(value = "ROLE_ADMIN")
    @GetMapping("/getManagers")
    public List<User> getOnlyManagers(){
        return userService.findOnlyManagers();
    }

    @GetMapping("/countUsers")
    public int numberOfUsers()
    {
        return userRepository.numberOfUsers();
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestParam Long userID, @RequestBody ChangePasswordRequest request) {
        // Récupérer l'utilisateur courant (vous pouvez utiliser Spring Security pour cela)
        User currentUser = userRepository.findByyId(userID);

        // Vérifier si l'ancien mot de passe correspond à celui de l'utilisateur courant
        if (!userService.checkPassword(currentUser, request.getOldPassword())) {
            return ResponseEntity.badRequest().body("Ancien mot de passe incorrect");
        }

        // Mettre à jour le mot de passe de l'utilisateur courant avec le nouveau mot de passe
        userService.changePassword(currentUser, request.getNewPassword());

        return ResponseEntity.ok("Mot de passe modifié avec succès");
    }



}
