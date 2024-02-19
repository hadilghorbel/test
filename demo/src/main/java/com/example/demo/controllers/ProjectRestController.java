package com.example.demo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.demo.RequestApiForm.MessageResponse;
import com.example.demo.model.entities.Project;
import com.example.demo.model.entities.User;
import com.example.demo.repositories.ProjectRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.ProjectService;

import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin
public class ProjectRestController {

    @Autowired
    ProjectRepository projectRepository;

    private EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProjectService projectService;



    @Secured(value="ROLE_ADMIN")
    @GetMapping("/retrieve-all-projects")
    public List<Project> getProjects()
    {
        List<Project> list = projectService.retrieveALlprojects();
        return list;
    }

    @Secured(value={"ROLE_MANAGER", "ROLE_ADMIN"})
    @PostMapping("/addProject")
    ResponseEntity<?> addProject(@Valid @RequestBody Project projectToAdd)
    {
        return new ResponseEntity<>(projectService.addProject(projectToAdd), HttpStatus.OK);
    }

    @GetMapping("/retrieve-project/{project-id}")
    public Optional<Project> retrieveProjectById(@PathVariable("project-id") Long projectId, HttpServletRequest request)
    {
        return projectService.findById(projectId);
    }

    @PreAuthorize("#userId == authentication.principal.id")
    @GetMapping("/getMyProjects/{user-id}")
    public List<Project> getMyProjects(@PathVariable("user-id") Long userId)
    {
        return projectService.getMyProjects(userId);
    }

    @Secured(value={"ROLE_MANAGER", "ROLE_ADMIN"})
    @PreAuthorize("#manager.id == authentication.principal.id")
    @PutMapping("/assignProjectToManager/{project-id}")
    public ResponseEntity<?> AssignProjectToManager(@PathVariable("project-id") Long idProject, @RequestBody User manager){
        Project project = projectRepository.findById(idProject).orElse(null);
        assert project !=null;
        project.setChefProjet(manager);
        projectRepository.save(project);
        return ResponseEntity.ok(new MessageResponse("Project assigned successfully!"));
    }

    @Secured(value={"ROLE_MANAGER"})
    @PostMapping("/assignEmployeeToProject/{project-id}")
    public ResponseEntity<?> AssignEmployeeToProject(@PathVariable("project-id") Long idProject, @RequestBody Long idEmployee){
        Project project = projectRepository.findById(idProject).orElse(null);
        User employee = userRepository.findById(idEmployee).orElse(null);
        assert project !=null;
        assert employee !=null;
        project.getEmployees().add(employee);
        entityManager.persist(project);

        return ResponseEntity.ok(new MessageResponse("Employee assigned successfully!"));
    }

    @Secured(value = {"ROLE_MANAGER", "ROLE_ADMIN"})
    @DeleteMapping("/deleteProject/{project-id}")
    public void deleteProject(@PathVariable ("project-id") Long id)
    {
        projectService.deleteById(id);
    }

    @PreAuthorize("#userId == authentication.principal.id")
    @GetMapping("/getEmployeeProjects/{user-id}")
    public List<Project> getEmployeeProjects(@PathVariable("user-id") Long userId)
    {
        return projectRepository.findAllByEmployeeID(userId);
    }

    @GetMapping("/countProjects")
    public int numberOfProjects()
    {
        return projectRepository.numberOfProjects();
    }








}
