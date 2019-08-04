package com.exercise.service;

import com.exercise.db.AccountEntity;
import com.exercise.db.repository.AccountRepository;
import com.exercise.exception.CannotPerformTransferException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.FieldSetter;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.OptimisticLockException;

import java.math.BigDecimal;

import static com.exercise.request.MoneyTransferRequestBuilder.buildRequest;
import static java.util.Optional.of;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MoneyTransferServiceTest {

    @Mock
    private AccountRepository accountRepositoryMock;
    private MoneyTransferService moneyTransferService = new MoneyTransferService();

    @Test(expected = CannotPerformTransferException.class)
    public void willThrowCannotPerformTransferException() throws NoSuchFieldException {
        FieldSetter.setField(moneyTransferService, moneyTransferService.getClass().getDeclaredField("accountRepository"), accountRepositoryMock);
        when(accountRepositoryMock.updateAccounts(any(), any(), any())).thenThrow(OptimisticLockException.class);
        AccountEntity source = new AccountEntity();
        source.setAccountNumber("123");
        source.setAmount(new BigDecimal(100));
        when(accountRepositoryMock.getAccount(any(), any())).thenReturn(of(source));
        moneyTransferService.transferMoney(buildRequest("123", "456", 10.00));
    }

}
