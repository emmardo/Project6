package com.openclassrooms.Project6.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Entity
@Table
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transactionId;

    @OneToOne
    private Account accountId;

    @OneToOne
    private User userId;

    @OneToOne
    private Connection connectionId;

    @OneToOne
    private TransactionType transactionTypeId;

    @NotBlank
    private Timestamp timestamp;

    @NotBlank
    private float moneyAmountVariation;

    @NotBlank
    private float balanceBeforeTransaction;

    private String description;

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Account getAccountId() {
        return accountId;
    }

    public void setAccountId(Account accountId) {
        this.accountId = accountId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Connection getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(Connection connectionId) {
        this.connectionId = connectionId;
    }

    public TransactionType getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(TransactionType transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public float getMoneyAmountVariation() {
        return moneyAmountVariation;
    }

    public void setMoneyAmountVariation(float moneyAmountVariation) {
        this.moneyAmountVariation = moneyAmountVariation;
    }

    public float getBalanceBeforeTransaction() {
        return balanceBeforeTransaction;
    }

    public void setBalanceBeforeTransaction(float balanceBeforeTransaction) {
        this.balanceBeforeTransaction = balanceBeforeTransaction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
