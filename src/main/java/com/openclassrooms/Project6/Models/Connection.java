package com.openclassrooms.Project6.Models;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "connection", catalog = "pay_my_buddy")
@EntityListeners(AuditingEntityListener.class)
public class Connection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*@Column(name = "connection_id")*/
    private int id;

    @ManyToOne
    /*@Column(name = "fk_connection_type_id")*/
    private ConnectionType connectionType;

    @OneToOne
    /*@Column(name = "fk_user_id")*/
    private User user;

    public Connection() {
    }

    public Connection(ConnectionType connectionType, User user) {

        this.connectionType = connectionType;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ConnectionType getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(ConnectionType connectionType) {
        this.connectionType = connectionType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
