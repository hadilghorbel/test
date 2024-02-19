package com.example.demo.services;

import org.springframework.stereotype.Service;
import com.example.demo.model.entities.Competence;
import com.example.demo.model.entities.Evaluation;
import com.example.demo.model.entities.Formation;
import com.example.demo.model.entities.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface FormationService {
    Formation addFormation(Formation formationToAdd);
    List<Formation> retrieveAllFormations();
    Optional <Formation> findByID(Long id);
    Formation saveOrUpdateFormation(Formation formation);
    void deleteFormationByID(Long id);
    public List<Formation> recommendFormationsForGroup(List<User> users);

}
