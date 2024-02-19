package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.entities.Competence;
import com.example.demo.model.entities.Formation;

import java.util.List;

public interface FormationRepository extends JpaRepository<Formation , Long> {
    List<Formation> findDistinctByCompetencesIn(List<Competence> competences);
}
