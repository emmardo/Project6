package com.openclassrooms.Project6.Models;

import javax.persistence.*;

@Entity
@Table
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int id;

    /*3 Roles: SuperUser(1), Admin(2) and Regular(3)*/
    private String role;

    public Role() {
    }

    public Role(String role) {

        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
