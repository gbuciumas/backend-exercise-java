package com.exercise.api;

import com.exercise.exception.CannotPerformTransferException;
import com.exercise.exception.mapper.CannotPerformTransferExceptionMapper;
import com.exercise.exception.mapper.ValidationExceptionMapper;
import com.exercise.request.MoneyTransferRequest;
import com.exercise.service.MoneyTransferService;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.exercise.request.MoneyTransferRequestBuilder.buildRequest;
import static org.mockito.Mockito.when;

public class MoneyTransferApiTest extends JerseyTest {
    private static final MoneyTransferApi API = new MoneyTransferApi();

    @Mock
    public MoneyTransferService moneyServiceMock = Mockito.mock(MoneyTransferService.class);

    @Override
    public Application configure() {
        return new ResourceConfig(ValidationExceptionMapper.class, CannotPerformTransferExceptionMapper.class).register(API);
    }

    @Before
    public void initMocks() throws NoSuchFieldException {
        FieldSetter.setField(API, API.getClass().getDeclaredField("moneyTransferService"), moneyServiceMock);
    }

    @Test
    public void failsIfCannotPerformTransferException() {
        MoneyTransferRequest request = buildRequest("12345678", "23456789", 1000);
        when(moneyServiceMock.transferMoney(request)).thenThrow(CannotPerformTransferException.class);
        Response response = target("transfermoney").request().put(Entity.entity(request, MediaType.APPLICATION_JSON));
        Assert.assertEquals(500, response.getStatus());
        Assert.assertEquals("Cannot perform transfer", response.readEntity(String.class));
    }

    @Test
    public void failsIfMissingSourceAccount() {
        MoneyTransferRequest request = buildRequest(null, "111111111", 115.78);
        Response response = target("transfermoney").request().put(Entity.entity(request, MediaType.APPLICATION_JSON));
        Assert.assertEquals(400, response.getStatus());
        Assert.assertEquals("Source account cannot be missing", response.readEntity(String.class));
    }

    @Test
    public void failsIfMissingDestinationAccount() {
        MoneyTransferRequest request = buildRequest("111111111", null, 115.78);
        Response response = target("transfermoney").request().put(Entity.entity(request, MediaType.APPLICATION_JSON));
        Assert.assertEquals(400, response.getStatus());
        Assert.assertEquals("Destination account cannot be missing", response.readEntity(String.class));
    }

    @Test
    public void failsIfMissingAmount() {
        MoneyTransferRequest request = buildRequest("111111111", "222222222", 1556.78);
        request.setAmount(null);
        Response response = target("transfermoney").request().put(Entity.entity(request, MediaType.APPLICATION_JSON));
        Assert.assertEquals(400, response.getStatus());
        Assert.assertEquals("Amount cannot be missing", response.readEntity(String.class));
    }

    @Test
    public void failsIfNegativeAmount() {
        MoneyTransferRequest request = buildRequest("111111111", "222222222", -15.78);
        Response response = target("transfermoney").request().put(Entity.entity(request, MediaType.APPLICATION_JSON));
        Assert.assertEquals(400, response.getStatus());
        Assert.assertEquals("Amount cannot be negative", response.readEntity(String.class));
    }

    @Test
    public void failsForIdenticalAccountNumbers() {
        MoneyTransferRequest request = buildRequest("111111111", "111111111", 5.78);
        Response response = target("transfermoney").request().put(Entity.entity(request, MediaType.APPLICATION_JSON));
        Assert.assertEquals(400, response.getStatus());
        Assert.assertEquals("Account numbers cannot be identical", response.readEntity(String.class));
    }

}
