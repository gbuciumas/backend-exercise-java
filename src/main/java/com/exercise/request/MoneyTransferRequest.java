package com.exercise.request;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class MoneyTransferRequest {

    @NotNull(message = "Source account cannot be missing")
    private String sourceAccount;

    @NotNull(message = "Destination account cannot be missing")
    private String destinationAccount;

    @NotNull(message = "Amount cannot be missing")
    @DecimalMin(value = "0.00", message="Amount cannot be negative")
    private BigDecimal amount;

    public String getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public String getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(String destinationAccount) {
        this.destinationAccount = destinationAccount;
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
        MoneyTransferRequest that = (MoneyTransferRequest) o;
        return Objects.equals(sourceAccount, that.sourceAccount) &&
                Objects.equals(destinationAccount, that.destinationAccount) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceAccount, destinationAccount, amount);
    }
}
