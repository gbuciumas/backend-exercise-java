package com.exercise.exception.mapper;

import com.exercise.exception.InsufficientFundsException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InsufficientFundsExceptionMapper implements ExceptionMapper<InsufficientFundsException> {

    @Override
    public Response toResponse (InsufficientFundsException exception) {
        return Response
                .status(Response.Status.BAD_REQUEST.getStatusCode())
                .type(MediaType.TEXT_PLAIN)
                .entity("Insufficient funds")
                .build();

    }
}
