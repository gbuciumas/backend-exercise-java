package com.exercise.domain;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

public class AccountTest {

    @Test
    public void testEqualsSymmetric() {
        Account account1 = new Account("11112222", BigDecimal.valueOf(1234.56));
        Account account2 = new Account("11112222", BigDecimal.valueOf(1234.56));
        assertTrue(account1.equals(account2) && account2.equals(account1));
        assertTrue(account1.hashCode() == account2.hashCode());
    }
}
