package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.demo.model.dto.ProjectDto;
import com.example.demo.model.entities.Project;
import com.example.demo.model.entities.User;
import com.example.demo.model.enums.PStatus;
import com.example.demo.repositories.ProjectRepository;
import com.example.demo.repositories.UserRepository;

import java.time.LocalDate;
import java.util.*;

@SpringBootApplication(scanBasePackages = "com.exemple.demo")
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Project addProject(Project projectToAdd) {
        Project project = new Project();
        List<User> employees = new ArrayList<>();
        for (int i = 0 ; i < projectToAdd.getEmployees().size() ; i++)
        {
            Long id = projectToAdd.getEmployees().get(i).getId();
            User employee = userRepository.findByyId(id);
            employees.add(employee);
        }
        project.setName(projectToAdd.getName());
        project.setDescription(projectToAdd.getDescription());
        project.setStartDate(projectToAdd.getStartDate());
        project.setDeadline(projectToAdd.getDeadline());
        project.setChefProjet(projectToAdd.getChefProjet());
        project.setEmployees(employees);
        project.setCompetences(projectToAdd.getCompetences());
        LocalDate now= LocalDate.now();
        Date date = java.sql.Date.valueOf(now.plusDays(1));
        project.setCreatedTime(date);
        project.setStatus(PStatus.EARLY);

        return projectRepository.save(project);
    }

    @Override
    public List<Project> retrieveALlprojects() {
        return projectRepository.findAll();
    }

    @Override
    public ProjectDto saveOrUpdate(Project project) {
        return null;
    }

    @Override
    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public List<Project> getMyProjects(Long userId) {
        return projectRepository.findAllByManagerID(userId);
    }

    @Override
    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }
}
