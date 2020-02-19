package com.openclassrooms.Project6.Models;

import javax.persistence.*;

@Entity
@Table(name = "user_modification_type", catalog = "pay_my_buddy")
public class UserModificationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_modification_type_id")
    private int id;

    /*2 Types: Email(1) and Password(2)*/
    @Column(name = "user_modification_type")
    private String  userModificationType;

    public UserModificationType() {
    }

    public UserModificationType(String  userModificationType) {

        this.userModificationType = userModificationType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserModificationType() {
        return userModificationType;
    }

    public void setUserModificationType(String userModificationType) {
        this.userModificationType = userModificationType;
    }
}
