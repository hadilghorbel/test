package com.example.demo.services;

import org.springframework.stereotype.Service;
import com.example.demo.model.entities.Competence;

import java.util.List;
import java.util.Optional;

@Service
public interface CompetenceService {
    Competence addComepetence(Competence competence);
    List<Competence> retrieveAllCompetences();
    Competence saveOrUpdate(Competence competence);
    Optional<Competence> findById(Long id);
    void deleteById(Long id);
}
