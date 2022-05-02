package com.project.csdm_java.exceptions;

import graphql.GraphQLException;
import graphql.kickstart.spring.error.ThrowableGraphQLError;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BookstoreExceptionHandler {

    @ExceptionHandler(GraphQLException.class)
    public ThrowableGraphQLError handler(GraphQLException e) {
        return new ThrowableGraphQLError(e);
    }

    // for hiding implementation from exposure
    @ExceptionHandler(RuntimeException.class)
    public ThrowableGraphQLError handler(RuntimeException e) {
        return new ThrowableGraphQLError(e, "Internal server error!! Call an ambulance!! 🚑");
    }
}
