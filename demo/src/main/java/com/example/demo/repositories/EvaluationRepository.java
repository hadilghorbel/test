package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.model.entities.Evaluation;

import java.util.List;

public interface EvaluationRepository extends JpaRepository <Evaluation , Long> {

    @Query("select e FROM Evaluation e where e.project.id = :id")
    List<Evaluation> getEvaluationsByProjectID(Long id);

    @Query("SELECT e FROM Evaluation e WHERE e.id= :id")
    Evaluation findByyID(Long id);

    @Query("select e FROM Evaluation e WHERE e.project.id= :idProject AND e.user.id= :idUser")
    List<Evaluation> getEvaluationByProjectIDForUser(Long idProject , Long idUser);

    @Query("select e FROM Evaluation e WHERE e.user.id= :userID")
    List<Evaluation> getEvaluationsByUserID(Long userID);

    @Query("SELECT COUNT(*) FROM Evaluation ")
    int numberOfEvaluations();

    @Query("SELECT e FROM Evaluation e WHERE e.user.id IN :userIds")
    List<Evaluation> findByUserIds(List<Long> userIds);
}
