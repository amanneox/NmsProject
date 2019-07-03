package com.project.nms.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_list")
public class UserModel extends AuditModel {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 100)
    private String name;

    @NotBlank
    @Size(min = 3)
    private String password;

    @NotBlank
    @Email
    @Column(unique=true)
    private String employeeID;


    @NotBlank
    @Size(max = 20)
    @Column(unique=true)
    private String username;


    @NotBlank
    private  String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getemployeeID() {
        return employeeID;
    }

    public void setemployeeID(String employeeID) {
        this.employeeID = employeeID;
    }
}