package com.exercise.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {

    private String number;
    private BigDecimal amount;

    public Account(String number, BigDecimal amount) {
        this.number = number;
        this.amount = amount;
    }

    public Account() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return number.equals(account.number) &&
                amount.equals(account.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, amount);
    }
}
