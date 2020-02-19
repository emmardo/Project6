package com.openclassrooms.Project6.Models;

import javax.persistence.*;

@Entity
@Table(name = "transaction_type", catalog = "pay_my_buddy")
public class TransactionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_type_id")
    private int id;

    /*5 Types: Incoming(1), Outgoing(2), TopUp(3), Withdrawal(4) and Cancellation(5)*/
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
