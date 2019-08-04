package com.exercise.request;

import java.math.BigDecimal;

public final class MoneyTransferRequestBuilder {

    private MoneyTransferRequestBuilder() {
    }

    public static MoneyTransferRequest buildRequest(String sourceAccount, String destinationAccount, double amount) {
        MoneyTransferRequest request = new MoneyTransferRequest();
        request.setSourceAccount(sourceAccount);
        request.setDestinationAccount(destinationAccount);
        request.setAmount(BigDecimal.valueOf(amount));
        return request;
    }
}
