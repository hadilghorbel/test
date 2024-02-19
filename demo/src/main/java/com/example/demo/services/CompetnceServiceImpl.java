package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import com.example.demo.model.entities.Competence;
import com.example.demo.repositories.CompetenceRepository;

import java.util.List;
import java.util.Optional;

@SpringBootApplication(scanBasePackages = "com.exemple.demo")
public class CompetnceServiceImpl implements CompetenceService{

    @Autowired
    CompetenceRepository competenceRepository;

    @Override
    public Competence addComepetence(Competence competence) {
        return competenceRepository.save(competence);
    }

    @Override
    public List<Competence> retrieveAllCompetences() {
        return competenceRepository.findAll();
    }

    @Override
    public Competence saveOrUpdate(Competence competence) {
        return competenceRepository.save(competence);
    }

    @Override
    public Optional<Competence> findById(Long id) {
        return competenceRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        competenceRepository.deleteById(id);
    }

}
