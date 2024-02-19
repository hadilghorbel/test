package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.demo.model.entities.Evaluation;
import com.example.demo.repositories.EvaluationRepository;

import java.util.List;
import java.util.Optional;

@SpringBootApplication(scanBasePackages = "com.exemple.demo")
public class EvaluationServiceImpl implements EvaluationService{

    @Autowired
    EvaluationRepository evaluationRepository;

    @Override
    public Evaluation addEvaluation(Evaluation evaluationToAdd) {
        return evaluationRepository.save(evaluationToAdd);
    }

    @Override
    public List<Evaluation> retrieveAllEvaluations() {
        return evaluationRepository.findAll();
    }

    @Override
    public Evaluation saveOrUpdate(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

    @Override
    public Optional<Evaluation> findById(Long id) {
        return evaluationRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        evaluationRepository.deleteById(id);

    }

}
