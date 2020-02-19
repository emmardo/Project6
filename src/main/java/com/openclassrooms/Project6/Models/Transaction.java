package com.openclassrooms.Project6.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "transaction", catalog = "pay_my_buddy")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int id;

    @ManyToOne
    @Column(name = "fk_transaction_type_id")
    private TransactionType transactionType;

    @ManyToOne
    @Column(name = "fk_account_id")
    private Account account;

    @ManyToOne
    @Column(name = "fk_connection_id")
    private Connection connection;

    @ManyToOne
    @Column(name = "fk_iban_id")
    private Iban iban;

    @NotBlank
    private float balanceBeforeTransaction;

    @NotBlank
    private float moneyAmountVariation;

    @NotBlank
    private Date madeAt;

    private String origin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Iban getIban() {
        return iban;
    }

    public void setIban(Iban iban) {
        this.iban = iban;
    }

    public float getBalanceBeforeTransaction() {
        return balanceBeforeTransaction;
    }

    public void setBalanceBeforeTransaction(float balanceBeforeTransaction) {
        this.balanceBeforeTransaction = balanceBeforeTransaction;
    }

    public float getMoneyAmountVariation() {
        return moneyAmountVariation;
    }

    public void setMoneyAmountVariation(float moneyAmountVariation) {
        this.moneyAmountVariation = moneyAmountVariation;
    }

    public Date getMadeAt() {
        return madeAt;
    }

    public void setMadeAt(Date madeAt) {
        this.madeAt = madeAt;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
