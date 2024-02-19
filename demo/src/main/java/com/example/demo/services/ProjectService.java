package com.example.demo.services;

import org.springframework.stereotype.Service;
import com.example.demo.model.dto.ProjectDto;
import com.example.demo.model.entities.Project;

import java.util.List;
import java.util.Optional;

@Service
public interface ProjectService {

    Project addProject(Project projectToAdd);

    List<Project> retrieveALlprojects();

    ProjectDto saveOrUpdate(Project project);

    Optional<Project> findById(Long id);

    List<Project> getMyProjects(Long userId);

    void deleteById(Long id);

}
