package com.example.demo.model.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import com.example.demo.model.enums.ERole;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "T_User_Role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Role_id")
    Long id;


    @Enumerated(EnumType.STRING)
    @Column(name = "User_Role_Name", length = 20)
    ERole name;
}
