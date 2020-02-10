package com.openclassrooms.Project6.Models;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table
public class BankAccountDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bankAccountDetailsIs;

    @ManyToOne
    private User userId;

    @NotBlank
    private String iban;

    public int getBankAccountDetailsIs() {
        return bankAccountDetailsIs;
    }

    public void setBankAccountDetailsIs(int bankAccountDetailsIs) {
        this.bankAccountDetailsIs = bankAccountDetailsIs;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}
