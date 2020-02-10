package com.openclassrooms.Project6.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roleId;

    @NotBlank
    private enum role {

        ADMIN,
        USER
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
