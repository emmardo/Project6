package com.openclassrooms.Project6.Models;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table
public class Connection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int connectionId;

    @OneToOne
    private User userId;

    @OneToOne
    @Email
    private User email;

    public int getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(int connectionId) {
        this.connectionId = connectionId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public User getEmail() {
        return email;
    }

    public void setEmail(User email) {
        this.email = email;
    }
}
