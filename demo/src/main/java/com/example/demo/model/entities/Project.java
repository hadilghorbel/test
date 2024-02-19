package com.example.demo.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import com.example.demo.model.dto.ProjectDto;
import com.example.demo.model.enums.PStatus;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "T_Project")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_ID")
    Long id;

    @Column(name= "project_Name")
    String name;

    @Column(name= "project_Description")
    String description;

    @Column(name = "project_startDate")
    Date startDate;

    @Column(name= "project_Deadline")
    Date deadline;

    @Column(name= "project_Status")
    PStatus status;

    @Column(name="project_CreatedTime")
    Date createdTime;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name= "Chef_Projet")
    private User chefProjet;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name= "Employee_Projects",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name= "project_id"))
    List<User> employees = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name= "Project_Competences",
            joinColumns = @JoinColumn(name = "competence_id"),
            inverseJoinColumns = @JoinColumn(name= "project_id"))
    List<Competence> competences = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    @JsonIgnore
    List<Evaluation> evaluations;


    public static Project toEntity (ProjectDto dto){

        return Project.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .startDate(dto.getStartDate())
                .deadline(dto.getDeadline())
                .status(dto.getStatus())
                .build();
    }





}
