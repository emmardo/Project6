package com.openclassrooms.Project6.Models;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "iban", catalog = "pay_my_buddy")
@EntityListeners(AuditingEntityListener.class)
public class Iban {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*@Column(name = "iban_id")*/
    private int id;

    @ManyToOne
    /*@Column(name = "fk_account_id")*/
    private Account account;

    @NotNull
    @NotBlank
    private String iban;

    public Iban() {
    }

    public Iban(Account account, String iban) {

        this.account = account;
        this.iban = iban;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}
