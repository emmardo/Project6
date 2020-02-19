package com.openclassrooms.Project6.Models;

import javax.persistence.*;

@Entity
@Table(name = "connection_type", catalog = "pay_my_buddy")
public class ConnectionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "connection_type_id")
    private int id;

    /*2 Types: Regular(1) and Company(2)*/
    @Column(name = "connection_type")
    private String connectionType;

    public ConnectionType() {
    }

    public ConnectionType(String connectionType) {

        this.connectionType = connectionType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }
}
