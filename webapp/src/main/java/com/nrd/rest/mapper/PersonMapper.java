package com.nrd.rest.mapper;

import com.nrd.rest.model.Person;
import com.nrd.rest.model.PersonRequest;

import java.time.LocalDate;

public class PersonMapper {

    private PersonMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Person mapPersonRequestToPerson(PersonRequest personRequest) {
        return Person.builder()
                .firstName(personRequest.getFirstName())
                .lastName(personRequest.getLastName())
                .email(personRequest.getEmail())
                .joinedDate(LocalDate.now())
                .build();
    }
}
