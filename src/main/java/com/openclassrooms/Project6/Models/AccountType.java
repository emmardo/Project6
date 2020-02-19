package com.openclassrooms.Project6.Models;

import javax.persistence.*;

@Entity
@Table(name = "account_type", catalog = "pay_my_buddy")
public class AccountType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_type_id")
    private int id;

    /*2 Types: Regular(1) and Company(2)*/
    @Column(name = "account_type")
    private String accountType;

    public AccountType(String accountType) {

        this.accountType = accountType;
    }

    public AccountType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
