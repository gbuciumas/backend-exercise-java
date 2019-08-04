package com.exercise.exception.mapper;

import com.exercise.exception.AccountNotFoundException;
import com.exercise.exception.CannotPerformTransferException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CannotPerformTransferExceptionMapper implements ExceptionMapper<CannotPerformTransferException> {

    @Override
    public Response toResponse (CannotPerformTransferException exception) {
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode())
                .type(MediaType.TEXT_PLAIN)
                .entity("Cannot perform transfer")
                .build();

    }
}
