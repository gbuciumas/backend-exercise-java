package com.exercise.db.repository;

import com.exercise.db.AccountEntity;
import com.exercise.db.repository.AccountData;
import com.exercise.db.repository.AccountRepository;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class AccountRepositoryTest {
    private final AccountEntity AE1 = AccountData.buildAccountEntity("12345678", BigDecimal.valueOf(1234.56));

    @BeforeClass
    public static void insertTestData() {
        AccountData.insertTestData();
    }

    @AfterClass
    public static void deleteTestData() {
        AccountData.deleteInsertedData();
    }

    @Test
    public void canRetrieveExistingAccount() {
        AccountRepository repo = new AccountRepository();
        assertEquals(Optional.of(AE1), repo.getAccount("12345678"));
    }

    @Test
    public void accountNotFound() {
        AccountRepository repo = new AccountRepository();
        assertEquals(Optional.empty(), repo.getAccount("11112"));
    }
}
