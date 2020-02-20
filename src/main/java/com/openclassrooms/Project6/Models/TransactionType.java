package com.openclassrooms.Project6.Models;

import javax.persistence.*;

@Entity
@Table(name = "transaction_type", catalog = "pay_my_buddy")
public class TransactionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_type_id")
    private int id;

    /*4 Types: Regular(1), TopUp(2), Withdrawal(3) and Cancellation(4)*/
    @Column(name = "transaction_type")
    private String transactionType;

    public TransactionType() {
    }

    public TransactionType(String transactionType) {

        this.transactionType = transactionType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
