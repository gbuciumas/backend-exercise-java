package com.exercise.db;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

public class AccountEntityTest {

    @Test
    public void testEqualsSymmetric() {
        AccountEntity accountEntity1 = buildAccountEntity();
        AccountEntity accountEntity2 = buildAccountEntity();
        assertTrue(accountEntity1.equals(accountEntity2) && accountEntity2.equals(accountEntity1));
        assertTrue(accountEntity1.hashCode() == accountEntity2.hashCode());
    }

    private AccountEntity buildAccountEntity() {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountNumber("11112222");
        accountEntity.setAmount(BigDecimal.valueOf(1234.56));
        return accountEntity;
    }

}
