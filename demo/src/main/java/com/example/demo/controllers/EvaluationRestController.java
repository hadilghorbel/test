package com.example.demo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.dto.EvaluationDTO;
import com.example.demo.model.entities.Evaluation;
import com.example.demo.repositories.CompetenceRepository;
import com.example.demo.repositories.EvaluationRepository;
import com.example.demo.repositories.ProjectRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.EvaluationService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin
public class EvaluationRestController {

    @Autowired
    EvaluationService evaluationService;

    @Autowired
    EvaluationRepository evaluationRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CompetenceRepository competenceRepository;

    public void EvaluationList(EvaluationDTO evaluationDTO, Evaluation evaluationToADD) {

        evaluationToADD.setCompetenceLibelle(evaluationDTO.getCompetenceLibelle());
        evaluationToADD.setValue(evaluationDTO.getValue());
        evaluationToADD.setNoteManager(evaluationDTO.getNoteManager());
        evaluationToADD.setProject(projectRepository.findByyId(evaluationDTO.getProjectID()));
        evaluationToADD.setUser(userRepository.findByyId(evaluationDTO.getUserID()));
        evaluationToADD.setCompetence(competenceRepository.findByyId(evaluationDTO.getCompetenceID()));
        evaluationService.saveOrUpdate(evaluationToADD);
    }

    @Secured(value = "ROLE_ADMIN")
    @GetMapping("/retrieveEvaluations")
    public List<Evaluation> getEvaluations()
    {
        return evaluationService.retrieveAllEvaluations();
    }


    @Secured(value = "ROLE_USER")
    @PostMapping("/addEvaluation")
    ResponseEntity<?> addEvaluation(@RequestBody List<EvaluationDTO> evaluation)
    {
        for (EvaluationDTO evaluationDTO : evaluation) {
            Evaluation evaluationToADD = new Evaluation();
            EvaluationList(evaluationDTO, evaluationToADD);
        }
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @Secured(value= {"ROLE_MANAGER","ROLE_USER"})
    @PutMapping("/updateEvaluationManager")
    ResponseEntity<?> updateEvaluationManager(@RequestBody List<EvaluationDTO> evaluation)
    {
        for (EvaluationDTO evaluationDTO : evaluation) {
            Evaluation evaluationToADD = new Evaluation();
            evaluationToADD.setId(evaluationDTO.getId());
            EvaluationList(evaluationDTO, evaluationToADD);
        }
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @Secured(value = {"ROLE_MANAGER","ROLE_USER"})
    @GetMapping("/getEvaluationsByProjectID/{id}")
    public List<Evaluation> getEvaluationByProjectID(@PathVariable("id") Long id)
    {
        System.out.println(id);
        return evaluationRepository.getEvaluationsByProjectID(id);
    }

    @GetMapping("/getEvaluationsByProjectIdForUser")
    public List<Evaluation> getEvaluationdByProjectIdForUser(@RequestParam ("idProject") Long idProject , @RequestParam ("idUser") Long idUser)
    {
        return evaluationRepository.getEvaluationByProjectIDForUser(idProject,idUser);
    }

    @GetMapping("/countEvaluations")
    public int numberOfEvaluations()
    {
        return evaluationRepository.numberOfEvaluations();
    }

    @GetMapping("/my-evaluations/{id}")
    public List<Evaluation> getEvaluationsByUserID(@PathVariable("id") Long userID)
    {
        return evaluationRepository.getEvaluationsByUserID(userID);
    }



}
