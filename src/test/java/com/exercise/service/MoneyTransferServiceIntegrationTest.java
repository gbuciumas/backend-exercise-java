package com.exercise.service;

import com.exercise.db.repository.AccountData;
import com.exercise.domain.Account;
import com.exercise.exception.AccountNotFoundException;
import com.exercise.exception.InsufficientFundsException;
import org.junit.*;

import java.math.BigDecimal;

import static com.exercise.request.MoneyTransferRequestBuilder.buildRequest;
import static org.junit.Assert.assertEquals;

public class MoneyTransferServiceIntegrationTest {

    private MoneyTransferService moneyTransferService = new MoneyTransferService();;

    @BeforeClass
    public static void insertTestData() {
        AccountData.insertTestData();
    }

    @AfterClass
    public static void deleteTestData() {
        AccountData.deleteInsertedData();
    }

    @Test
    public void canSuccessfullyTransferMoney() {
        Account actualResult = moneyTransferService.transferMoney(buildRequest("12345678", "23456789", 1000));
        assertEquals(expectedAccount("12345678", BigDecimal.valueOf(234.56)), actualResult);
    }

    @Test(expected = AccountNotFoundException.class)
    public void failsIfSourceAccountDoesNotExist() {
        moneyTransferService.transferMoney(buildRequest("10101010", "22222222", 100.10));
    }

    @Test(expected = InsufficientFundsException.class)
    public void failsIfSourceAccountDoesNotHaveSufficientFunds() {
        moneyTransferService.transferMoney( buildRequest("33333333", "44444444", 1999.99));
    }

    private Account expectedAccount(String account, BigDecimal amount) {
        return new Account(account, amount);
    }

}
