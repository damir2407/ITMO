package com.oasis.oasisapp.model;

import javax.persistence.*;

@Entity
@Table(name = "wallets",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "id")})
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Double balance;

    public Wallet(Double balance) {
        this.balance = balance;
    }

    public Wallet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
