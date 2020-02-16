package com.openclassrooms.Project6.Models;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "account", catalog = "pay_my_buddy")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;

    @OneToOne
    private User userId;

    @NotBlank
    private float balance;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
