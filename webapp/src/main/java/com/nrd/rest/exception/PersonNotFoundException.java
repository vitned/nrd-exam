package com.nrd.rest.exception;

public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(final Long id) {
        super("Could not find person with id: " + id);
    }

    public PersonNotFoundException(final String msg) {
        super(msg);
    }
}
