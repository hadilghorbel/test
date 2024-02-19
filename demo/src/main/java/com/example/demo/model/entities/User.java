package com.example.demo.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.enums.Gender;
import com.example.demo.model.enums.Status;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity
@Table(name = "T_User")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_id")
    Long id;

    @Column(name="User_GlobalGroupID")
    String globalGroupID;

    @Column(name = "User_First_Name")
    String firstName;

    @Column(name = "User_Last_Name")
    String lastName;

    @Column (name="User_country")
    String country;

    @Column (name= "User_baseLocation")
    String baseLocation;

    @Column (name="User_birthYear")
    int birthYear;

    @Column (name="User_gender")
    Gender gender;

    @Column (name="User_startDate")
    Date startDate;

    @Column (name= "User_localGrade")
    String localGrade;

    @Column (name= "User_localGradeEffDate")
    Date localGradeEffDate;

    @Column (name= "User_roleName")
    String roleName;

    @Column (name= "User_status")
    Status EmployeeStatus;

    @Column (name= "User_engagement")
    String engagement;

    @Column (name= "User_engagementPercentage")
    double engagementPercentage;

    @Column (name= "User_practice")
    String practice;

    @Column (name= "User_email")
    String email;

    @Column (name= "User_password")
    String password;

    @Column (name= "User_actif")
    boolean actif;

    @Column (name = "User_LastLoggedIn")
    Date lastLoggedIn;

    @Column (name = "User_LastLoggedOut")
    Date lastLoggedOut;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> roles = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "User_MangerID")
    private User manager;

    @OneToMany(mappedBy = "manager" , cascade = CascadeType.ALL)
    @JsonIgnore
    private List<User> employees = new ArrayList<User>();

    @OneToMany(mappedBy = "chefProjet")
    @JsonIgnore
    private List<Project> projectsManaged = new ArrayList<Project>();



    
    public User( String GlobalGroupID,String firstName, String lastName,String password,String email, boolean actif) {
        this.globalGroupID= GlobalGroupID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.actif = actif;
    }

    public static User toEntity(UserDto dto){
        return User.builder()
                .id(dto.getId())
                .globalGroupID(dto.getGlobalGroupID())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .actif(dto.isActif())
                .build();
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


}
