package com.example.demo.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "T_Competence")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Competence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Competence_id")
    Long id;
    @Column(name= "Competence_name")
    String libelle;

    @ManyToMany(mappedBy = "competences")
    @JsonIgnore
    List<Project> projects = new ArrayList<Project>();

    @ManyToMany(mappedBy = "competences")
    @JsonIgnore
    List<Formation> formations = new ArrayList<>();

}
