package com.oasis.oasisapp.dto;

public class BalanceResponse {
    private Double balance;

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public BalanceResponse(Double balance) {
        this.balance = balance;
    }
}
