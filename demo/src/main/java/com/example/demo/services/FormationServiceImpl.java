package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.demo.model.entities.Competence;
import com.example.demo.model.entities.Evaluation;
import com.example.demo.model.entities.Formation;
import com.example.demo.model.entities.User;
import com.example.demo.repositories.EvaluationRepository;
import com.example.demo.repositories.FormationRepository;

import java.util.*;

@SpringBootApplication(scanBasePackages = "com.exemple.demo")
public class FormationServiceImpl implements FormationService{

    @Autowired
    FormationRepository formationRepository;

    @Autowired
    EvaluationRepository evaluationRepository;
    @Override
    public Formation addFormation(Formation formationToAdd) {
        return formationRepository.save(formationToAdd);
    }

    @Override
    public List<Formation> retrieveAllFormations() {
        return formationRepository.findAll();
    }

    @Override
    public Optional<Formation> findByID(Long id) {
        return formationRepository.findById(id);
    }

    @Override
    public Formation saveOrUpdateFormation(Formation formation) {
        return formationRepository.save(formation);
    }

    @Override
    public void deleteFormationByID(Long id) {
        formationRepository.deleteById(id);
    }

    @Override
    public List<Formation> recommendFormationsForGroup(List<User> users) {
        List<Formation> recommendedFormations = new ArrayList<>();

        // Récupérer les évaluations pour les utilisateurs du groupe
        List<Long> userIds = new ArrayList<>();
        for (User user : users) {
            userIds.add(user.getId());
        }
        List<Evaluation> evaluations = evaluationRepository.findByUserIds(userIds);

        // Récupérer les compétences évaluées avec une note élevée
        Map<Competence, List<Evaluation>> evaluationsByCompetence = new HashMap<>();
        for (Evaluation evaluation : evaluations) {
            if (evaluation.getValue() <= 4) {
                List<Evaluation> competenceEvaluations = evaluationsByCompetence.getOrDefault(evaluation.getCompetence(), new ArrayList<>());
                competenceEvaluations.add(evaluation);
                evaluationsByCompetence.put(evaluation.getCompetence(), competenceEvaluations);
            }
        }

        List<Competence> lowRatedCompetences = new ArrayList<>();
        for (Map.Entry<Competence, List<Evaluation>> entry : evaluationsByCompetence.entrySet()) {
            List<Evaluation> competenceEvaluations = entry.getValue();
            double averageValue = competenceEvaluations.stream()
                    .mapToDouble(Evaluation::getValue)
                    .average()
                    .orElse(0.0); // Valeur par défaut si aucune évaluation n'est présente

            if (averageValue < 3.0) {
                lowRatedCompetences.add(entry.getKey());
            }
        }

        // Récupérer les formations liées aux compétences évaluées
        recommendedFormations = formationRepository.findDistinctByCompetencesIn(lowRatedCompetences);

        return recommendedFormations;
    }


}
