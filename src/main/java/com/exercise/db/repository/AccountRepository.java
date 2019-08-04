package com.exercise.db.repository;

import com.exercise.db.AccountEntity;
import com.exercise.db.session.SessionUtil;
import com.exercise.domain.Account;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class AccountRepository {
    static {
        AccountData.insertProdData();
    }

    public AccountEntity updateAccounts(AccountEntity source, AccountEntity destination, Session session){
        Transaction tx = session.beginTransaction();
        AccountEntity result = (AccountEntity) session.merge(source);
        session.merge(destination);
        tx.commit();
        return result;
    }

    public Optional<AccountEntity> getAccount(String accountNumber) {
        Session session = SessionUtil.getSession();
        Optional<AccountEntity> result = getAccount(accountNumber, session);
        session.close();
        return result;
    }

    public Optional<AccountEntity> getAccount(String accountNumber, Session session) {
        Query query = session.createQuery("from AccountEntity where accountNumber = :accountNumber");
        query.setParameter("accountNumber", accountNumber);
        List<AccountEntity> result = (List<AccountEntity>) query.list();
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }
}
