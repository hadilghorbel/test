package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entities.User;
import com.example.demo.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@SpringBootApplication(scanBasePackages = "com.exemple.demo")
@SuppressWarnings("ALL")
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User addUser(User userToAdd) {
        return userRepository.save(userToAdd);
    }

    @Override
    public List<User> retrieveAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User saveOrUpdate(User user) {
        return userRepository.save(user);
    }


    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getEmployees(Long id) {
        return userRepository.findAllByManagerID(id);
    }

    @Override
    public User updateUserById(User user, Long id) {
        User found= userRepository.findById(id).orElse(null);
        if(user.getGlobalGroupID()!=null)
        found.setGlobalGroupID(user.getGlobalGroupID());
        if(user.getFirstName()!=null)
        found.setFirstName(user.getFirstName());
        if(user.getLastName()!=null)
        found.setLastName(user.getLastName());
        if(user.getEmail()!=null)
        found.setEmail(user.getEmail());
        found.setActif(user.isActif());
        if(user.getBaseLocation()!=null)
        found.setBaseLocation(user.getBaseLocation());
        userRepository.saveAndFlush(found);
        return found;
    }

    @Override
    public User deleteEmployeeByID(Long id) {
        User found= userRepository.findById(id).orElse(null);

        assert found != null;
        found.setManager(null);
        userRepository.saveAndFlush(found);
        return found;
    }

    @Override
    public List<User> findOnlyManagers() {
        return (List<User>) userRepository.findOnlyManagers();
    }

    @Override
    public boolean checkPassword(User user, String password) {
        // Logic to check if the provided password matches the user's password
        // Implement your own password verification logic here
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public void changePassword(User user, String newPassword) {
        String hashedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(hashedPassword);
    }

}
