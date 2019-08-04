package com.exercise.db;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class AccountEntity {

    @Id
    @GeneratedValue
    private long id;

    @Version
    @Column(name = "VERSION")
    private long version;

    @Column(name = "ACCOUNT_NUMBER", unique = true)
    private String accountNumber;

    @Column(name = "AMOUNT", scale = 2 )
    private BigDecimal amount;

    public void setVersion(long version) {
        this.version = version;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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
        AccountEntity that = (AccountEntity) o;
        return  version == that.version &&
                accountNumber.equals(that.accountNumber) &&
                amount.equals(that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(version, accountNumber, amount);
    }
}
