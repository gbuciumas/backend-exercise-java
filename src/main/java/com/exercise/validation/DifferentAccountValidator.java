package com.exercise.validation;

import com.exercise.request.MoneyTransferRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DifferentAccountValidator implements
        ConstraintValidator<DifferentAccount, MoneyTransferRequest> {

    @Override
    public void initialize(DifferentAccount differentAccount) {
    }

    @Override
    public boolean isValid(MoneyTransferRequest moneyTransferRequest, ConstraintValidatorContext constraintValidatorContext) {
        if (moneyTransferRequest.getDestinationAccount()!= null) {
            return !moneyTransferRequest.getDestinationAccount().equals(moneyTransferRequest.getSourceAccount());
        }
        return true;
    }
}
