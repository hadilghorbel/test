package com.example.demo.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import com.example.demo.model.dto.EvaluationDTO;
import com.example.demo.repositories.ProjectRepository;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "T_Evaluation")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Evaluation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Evaluation_id")
    Long id;

    @Column(name= "Evaluation_value")
    int value;

    @Column(name= "Competence_Libelle")
    String competenceLibelle;

    @Column(name = "Evaluation_Note_Manager")
    Long noteManager;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name= "User_id")
    User user;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name= "Project_id")
    Project project;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name= "Competence_id")
    Competence competence;

}
