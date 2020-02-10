package com.openclassrooms.Project6.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table
public class TransactionType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transactionTypeId;

    @NotBlank
    private enum transactionType {

        Incoming,
        Outgoing,
        TopUp,
        CashOut,
        Refund
    }

    public int getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(int transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }
}
