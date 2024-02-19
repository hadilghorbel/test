package com.example.demo.services;

import org.springframework.stereotype.Service;
import com.example.demo.model.entities.Evaluation;

import java.util.List;
import java.util.Optional;

@Service
public interface EvaluationService {
    Evaluation addEvaluation(Evaluation evaluationToAdd);
    List<Evaluation> retrieveAllEvaluations();
    Evaluation saveOrUpdate(Evaluation evaluation);
    Optional<Evaluation> findById(Long id);
    void deleteById(Long id);
}
