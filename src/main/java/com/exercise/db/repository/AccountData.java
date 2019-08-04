package com.exercise.db.repository;

import com.exercise.db.AccountEntity;
import com.exercise.db.session.SessionUtil;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.math.BigDecimal;

public class AccountData {

    public static void insertTestData() {
        Session session = SessionUtil.getSession();
        session.beginTransaction();
        session.save(buildAccountEntity("12345678", BigDecimal.valueOf(1234.56)));
        session.save(buildAccountEntity("23456789", BigDecimal.valueOf(579.08)));
        session.save(buildAccountEntity("11111111", BigDecimal.valueOf(34567.99)));
        session.save(buildAccountEntity("22222222", BigDecimal.valueOf(0.02)));
        session.save(buildAccountEntity("33333333", BigDecimal.valueOf(579.08)));
        session.save(buildAccountEntity("44444444", BigDecimal.valueOf(579.08)));
        session.save(buildAccountEntity("67676767", BigDecimal.valueOf(579.08)));
        session.getTransaction().commit();
        session.close();
    }

    public static void deleteInsertedData() {
        Session session = SessionUtil.getSession();
        session.beginTransaction();
        Query query = session.createQuery("delete AccountEntity where id < 30");
        int result = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public static void insertProdData() {
        Session session = SessionUtil.getSession();
        session.beginTransaction();
        session.save(buildAccountEntity("12121212", BigDecimal.valueOf(1234.56)));
        session.save(buildAccountEntity("98765432", BigDecimal.valueOf(579.08)));
        session.save(buildAccountEntity("55555555", BigDecimal.valueOf(34567.99)));
        session.save(buildAccountEntity("66666666", BigDecimal.valueOf(0.02)));
        session.save(buildAccountEntity("90909090", BigDecimal.valueOf(579.08)));
        session.save(buildAccountEntity("78978978", BigDecimal.valueOf(579.08)));
        session.getTransaction().commit();
        session.close();
    }

    public static AccountEntity buildAccountEntity(String accountNumber, BigDecimal amount) {
        return buildAccountEntity(accountNumber, amount, 0);
    }

    public static AccountEntity buildAccountEntity(String accountNumber, BigDecimal amount, long version) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountNumber(accountNumber);
        accountEntity.setAmount(amount);
        accountEntity.setVersion(version);
        return accountEntity;
    }
}
