package com.exercise.service;

import com.exercise.db.AccountEntity;
import com.exercise.db.repository.AccountRepository;
import com.exercise.db.session.SessionUtil;
import com.exercise.domain.Account;
import com.exercise.exception.AccountNotFoundException;
import com.exercise.exception.CannotPerformTransferException;
import com.exercise.exception.InsufficientFundsException;
import com.exercise.request.MoneyTransferRequest;
import org.hibernate.Session;

import javax.persistence.OptimisticLockException;
import java.math.BigDecimal;

import static java.util.Optional.of;

public class MoneyTransferService {

    private AccountRepository accountRepository = new AccountRepository();

    public Account transferMoney(MoneyTransferRequest request) {
        return of(transferMoneyWithRetries(request.getSourceAccount(), request.getDestinationAccount(), request.getAmount(), 3)).map(this::mapAccount).get();
    }

    private AccountEntity transferMoneyWithRetries(String sourceAccountNumber, String destinationAccountNumber, BigDecimal amount, int retries) {
        for (int i = 0; i <= retries; i++) {
            try {
                return updateAccountsInSession(sourceAccountNumber, destinationAccountNumber, amount);
            } catch (OptimisticLockException ex) {
            }
        }
        throw new CannotPerformTransferException();
    }

    private AccountEntity updateAccountsInSession(String sourceAccountNumber, String destinationAccountNumber, BigDecimal amount) {
        Session session = SessionUtil.getSession();
        AccountEntity sourceAccountEntity = accountRepository.getAccount(sourceAccountNumber, session).orElseThrow(AccountNotFoundException::new);
        AccountEntity destinationAccountEntity = accountRepository.getAccount(destinationAccountNumber, session).orElseThrow(AccountNotFoundException::new);

        if (sourceAccountEntity.getAmount().compareTo(amount) < 0) {
            throw new InsufficientFundsException();
        }

        sourceAccountEntity.setAmount(sourceAccountEntity.getAmount().add(amount.negate()));
        destinationAccountEntity.setAmount(destinationAccountEntity.getAmount().add(amount));

        accountRepository.updateAccounts(sourceAccountEntity, destinationAccountEntity, session);
        session.close();
        return sourceAccountEntity;
    }

    private Account mapAccount(AccountEntity entity) {
        return new Account(entity.getAccountNumber(), entity.getAmount());
    }
}

