package com.openclassrooms.Project6.Models;

import javax.persistence.*;

@Entity
@Table(name = "account_status", catalog = "pay_my_buddy")
public class AccountStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_status_id")
    private int id;

    /*4 Status: Active(1), Iactive(2), NotYetActivated(3) and Deactivated(4)*/
    @Column(name = "account_status")
    private String accountStatus;

    public AccountStatus() {
    }

    public AccountStatus(String accountStatus) {

        this.accountStatus = accountStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }
}
