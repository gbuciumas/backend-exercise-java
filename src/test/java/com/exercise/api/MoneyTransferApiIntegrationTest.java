package com.exercise.api;

import com.exercise.domain.Account;
import com.exercise.exception.mapper.InsufficientFundsExceptionMapper;
import com.exercise.db.repository.AccountData;
import com.exercise.exception.mapper.AccountNotFoundExceptionMapper;
import com.exercise.request.MoneyTransferRequest;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;

import static com.exercise.request.MoneyTransferRequestBuilder.buildRequest;

public class MoneyTransferApiIntegrationTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(MoneyTransferApi.class, AccountNotFoundExceptionMapper.class, InsufficientFundsExceptionMapper.class);
    }

    @BeforeClass
    public static void insertTestData() {
        AccountData.insertTestData();
    }

    @AfterClass
    public static void deleteTestData() {
        AccountData.deleteInsertedData();
    }

    @Test
    public void failsForDestinationAccountNotFound() {
        MoneyTransferRequest request = buildRequest("111111111", "19999999", 345.78);
        Response response = target("transfermoney").request().put(Entity.entity(request, MediaType.APPLICATION_JSON));
        Assert.assertEquals(400, response.getStatus());
        Assert.assertEquals("Account not found", response.readEntity(String.class));
    }

    @Test
    public void failsForSourceAccountNotFound() {
        MoneyTransferRequest request = buildRequest("10001000", "11111111", 5.78);
        Response response = target("transfermoney").request().put(Entity.entity(request, MediaType.APPLICATION_JSON));
        Assert.assertEquals(400, response.getStatus());
        Assert.assertEquals("Account not found", response.readEntity(String.class));
    }

    @Test
    public void failsIfTheAmountIsNotAvailable() {
        MoneyTransferRequest request = buildRequest("22222222", "11111111", 1010);
        Response response = target("transfermoney").request().put(Entity.entity(request, MediaType.APPLICATION_JSON));
        Assert.assertEquals(400, response.getStatus());
        Assert.assertEquals("Insufficient funds", response.readEntity(String.class));
    }

    @Test
    public void canTransferMoney() {
        MoneyTransferRequest request = buildRequest("12345678", "23456789", 1000);
        Response response = target("transfermoney").request().put(Entity.entity(request, MediaType.APPLICATION_JSON));
        Assert.assertEquals(200, response.getStatus());
        Assert.assertEquals(expectedAccount(), response.readEntity(Account.class));
    }

    private Account expectedAccount() {
        return new Account("12345678", BigDecimal.valueOf(234.56));
    }

}
