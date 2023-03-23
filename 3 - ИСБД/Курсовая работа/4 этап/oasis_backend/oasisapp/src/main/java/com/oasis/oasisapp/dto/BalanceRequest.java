package com.oasis.oasisapp.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class BalanceRequest {
    @NotNull(message = "Укажите сумму! От 1$ до 500$")
    @Min(value = 1, message = "Сумма должна быть от 1$ до 500$")
    @Max(value = 500, message = "Сумма должна быть от 1$ до 500$")
    private Double balance;


    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
