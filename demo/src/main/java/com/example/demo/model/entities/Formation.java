package com.example.demo.model.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_Formation")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Formation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Formation_ID")
    Long id;

    @Column(name = "Formation_Titre")
    String titre;

    @Column(name = "Formation_Description")
    String description;

    @ManyToMany
    @JoinTable(name= "Formation_Competences",
            joinColumns = @JoinColumn(name = "competence_id"),
            inverseJoinColumns = @JoinColumn(name= "formation_id"))
    List<Competence> competences = new ArrayList<>();

}
