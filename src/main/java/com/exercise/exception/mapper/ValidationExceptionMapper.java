package com.exercise.exception.mapper;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    @Override
    public Response toResponse (ValidationException exception) {
        final StringBuilder strBuilder = new StringBuilder();
        for (ConstraintViolation<?> cv : ((ConstraintViolationException) exception).getConstraintViolations()) {
            strBuilder.append(cv.getMessage());
        }
        return Response
                .status(Response.Status.BAD_REQUEST.getStatusCode())
                .type(MediaType.TEXT_PLAIN)
                .entity(strBuilder.toString())
                .build();

    }
}
