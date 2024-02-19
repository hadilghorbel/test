package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.model.entities.Competence;

import java.util.List;

public interface CompetenceRepository extends JpaRepository<Competence, Long> {

    @Query("SELECT c FROM Competence c JOIN c.projects p WHERE p.id =:id")
    List<Competence> findCompetenceByProjectID(Long id);

    @Query("select c from Competence as c where c.id =:id")
    Competence findByyId(Long id);

    @Query("SELECT COUNT(*) FROM Competence ")
    int numberOfSkills();
}
