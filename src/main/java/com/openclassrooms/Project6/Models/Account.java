package com.openclassrooms.Project6.Models;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "account", catalog = "pay_my_buddy")
@EntityListeners(AuditingEntityListener.class)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    /*@Column(name = "fk_user_id")*/
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_account_type_id")
    /*@Column(name = "fk_account_type_id")*/
    private AccountType accountType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_account_status_id")
    /*@Column(name = "fk_account_status_id")*/
    private AccountStatus accountStatus;

    @OneToOne
    @JoinColumn(name = "connection_id", referencedColumnName = "connection_id")
    /*@Column(name = "fk_conection_id")*/
    private Connection connection;

    @NotNull
    /*@Column(name = "current_balance")*/
    private float currentBalance;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "account")
    private List<Iban> ibans;

    public Account() {
    }

    public Account(User user, AccountType accountType, AccountStatus accountStatus, Connection connection, float currentBalance) {

        this.user = user;
        this.accountType = accountType;
        this.accountStatus = accountStatus;
        this.connection = connection;
        this.currentBalance = currentBalance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public float getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(float currentBalance) {
        this.currentBalance = currentBalance;
    }
}
