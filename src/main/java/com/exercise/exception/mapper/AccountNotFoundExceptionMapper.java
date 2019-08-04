package com.exercise.exception.mapper;

import com.exercise.exception.AccountNotFoundException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AccountNotFoundExceptionMapper implements ExceptionMapper<AccountNotFoundException> {

    @Override
    public Response toResponse (AccountNotFoundException exception) {
        return Response
                .status(Response.Status.BAD_REQUEST.getStatusCode())
                .type(MediaType.TEXT_PLAIN)
                .entity("Account not found")
                .build();

    }
}
