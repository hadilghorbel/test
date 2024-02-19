package com.example.demo.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import com.example.demo.model.entities.Role;
import com.example.demo.model.entities.User;
import com.example.demo.model.enums.Gender;
import com.example.demo.model.enums.Status;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    Long id;
    String globalGroupID;

    String firstName;

    String lastName;

    String email;

    String password;

    boolean actif;

    @ManyToMany(fetch = FetchType.LAZY)
    Set<Role> role = new HashSet<>();


    public UserDto( String GlobalGroupID, String firstName, String lastName,String password,String email, boolean actif) {
        this.globalGroupID= GlobalGroupID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.actif = actif;
    }

    public static UserDto toDto(User entity){
        return UserDto.builder()
                .id(entity.getId())
                .globalGroupID(entity.getGlobalGroupID())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .actif(entity.isActif())
                .build();
    }
}
