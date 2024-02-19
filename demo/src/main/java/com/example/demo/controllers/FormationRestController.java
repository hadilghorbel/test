package com.example.demo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.entities.Formation;
import com.example.demo.model.entities.User;
import com.example.demo.services.FormationService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin
public class FormationRestController {

    @Autowired
    FormationService formationService;

    @GetMapping("/retrieve-all-formations")
    public List<Formation> getAllFormations()
    {
        return formationService.retrieveAllFormations();
    }

    @GetMapping("/retrieve-formation/{id}")
    public Optional<Formation> getFormationByID(@PathVariable Long id)
    {
        return formationService.findByID(id);
    }

    @PostMapping("/add-formation")
    public ResponseEntity<?> addFormation(@RequestBody Formation formationToAdd)
    {
        return new ResponseEntity<>(formationService.addFormation(formationToAdd), HttpStatus.OK);
    }

    @DeleteMapping("/deleteFormation/{id}")
    public void deleteFormation(@PathVariable ("id") Long id)
    {
        formationService.deleteFormationByID(id);
    }

    @PostMapping("/users")
    public List<Formation> recommendFormationsForUsers(@RequestBody List<User> users) {
        return formationService.recommendFormationsForGroup(users);
    }

}
