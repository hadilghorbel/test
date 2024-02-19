package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.model.entities.Project;


import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findByNameIgnoreCase(String name);

    boolean existsByName(String name);
    @Query("select p FROM Project p WHERE p.chefProjet.id = :id")
    List<Project> findAllByManagerID(Long id);

    @Query("select p FROM Project p JOIN p.employees e WHERE e.id = :id ")
    List<Project> findAllByEmployeeID(Long id);
    @Query("select p from Project as p where p.id =:id")
    Project findByyId(Long id);

    @Query("SELECT COUNT(*) FROM Project ")
    int numberOfProjects();
}
