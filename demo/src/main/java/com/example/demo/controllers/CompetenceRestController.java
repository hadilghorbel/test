package com.example.demo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.entities.Competence;
import com.example.demo.repositories.CompetenceRepository;
import com.example.demo.services.CompetenceService;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin
@SuppressWarnings("ALL")
public class CompetenceRestController {

    @Autowired
    CompetenceRepository competenceRepository;
    @Autowired
    CompetenceService competenceService;


    @Secured(value = {"ROLE_MANAGER","ROLE_USER","ROLE_ADMIN"})
    @GetMapping("/retrieve-all-competences")
    public List<Competence> getCompetences(){
        return competenceService.retrieveAllCompetences();
    }

    @GetMapping("/retrieve-competence/{competence-id}")
    public Optional<Competence> retrieveCompetenceById(@PathVariable ("competence-id") Long id)
    {
        assert competenceRepository.existsById(id);
        return competenceService.findById(id);
    }

    @Secured(value = {"ROLE_MANAGER", "ROLE_ADMIN"})
    @PostMapping("/addCompetence")
    ResponseEntity<?> addCompetence(@Valid @RequestBody Competence competence)
    {
        return new ResponseEntity<>(competenceService.addComepetence(competence), HttpStatus.OK);
    }

    @Secured(value = { "ROLE_ADMIN"})
    @DeleteMapping("/deleteCompetence/{competence-id}")
    public void deleteCompetence(@PathVariable ("competence-id") Long id)
    {
        competenceService.deleteById(id);
    }

    @GetMapping("/getComptencesProjet/{project-id}")
    public List<Competence> getCompetencesByProjectID (@PathVariable ("project-id") Long projectID)
    {
        return competenceRepository.findCompetenceByProjectID(projectID);
    }

    @GetMapping("/countSkills")
    public int numberOfSkills()
    {
        return competenceRepository.numberOfSkills();
    }


}
