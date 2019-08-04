package com.exercise.api;

import com.exercise.service.MoneyTransferService;
import com.exercise.domain.Account;
import com.exercise.request.MoneyTransferRequest;
import com.exercise.validation.DifferentAccount;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/transfermoney")
public class MoneyTransferApi {

    private MoneyTransferService moneyTransferService = new MoneyTransferService();

    @POST
    @Produces({ MediaType.APPLICATION_JSON })
    public Response transferMoney(@Valid @DifferentAccount MoneyTransferRequest request) {
        Account account = moneyTransferService.transferMoney(request);
        return Response.ok().entity(account).build();
    }
}
