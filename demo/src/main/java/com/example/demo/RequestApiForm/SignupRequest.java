package com.example.demo.RequestApiForm;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Setter
@Getter
public class SignupRequest {


    private String globalGroupID;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    @NotBlank
    @Size(max = 50)
    private String firstName;
    @NotBlank
    @Size(max = 50)
    private String lastName;



//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public Set<String> getRole() {
//        return this.role;
//    }
//
//    public void setRole(Set<String> role) {
//        this.role = role;
//    }
//
//    public String getGlobalGroupID() {
//        return globalGroupID;
//    }
//
//    public void setGlobalGroupID(String globalGroupID) {
//        this.globalGroupID = globalGroupID;
//    }
//
//    /**
//     * @return the firstName
//     */
//    public String getFirstName() {
//        return firstName;
//    }
//
//    /**
//     * @param firstName
//     *            the firstName to set
//     */
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    /**
//     * @return the lastName
//     */
//    public String getLastName() {
//        return lastName;
//    }
//
//    /**
//     * @param lastName
//     *            the lastName to set
//     */
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }

}
