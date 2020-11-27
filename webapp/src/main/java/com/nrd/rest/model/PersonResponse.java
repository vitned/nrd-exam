package com.nrd.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nrd.rest.model.enums.CacheResponseStatus;
import com.nrd.rest.model.enums.PersonResponseStatus;
import lombok.Getter;

import java.util.List;

import static com.nrd.rest.model.enums.CacheResponseStatus.CACHE_NOT_UPDATED;
import static com.nrd.rest.model.enums.PersonResponseStatus.*;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class PersonResponse {

    private final PersonResponseStatus personResponseStatus;
    private final CacheResponseStatus cacheResponseStatus;
    private final String responseMessage;
    private final Person person;
    private final List<Person> people;

    private PersonResponse(final PersonResponseStatus personResponseStatus, final CacheResponseStatus cacheResponseStatus, final String responseMessage, final Person person, List<Person> people) {
        this.personResponseStatus = personResponseStatus;
        this.cacheResponseStatus = cacheResponseStatus;
        this.responseMessage = responseMessage;
        this.person = person;
        this.people = people;
    }

    public static PersonResponse successCreatePersonAndNotUpdateCache(final Person person, final String responseMessage) {
        return new PersonResponse(CREATED, CACHE_NOT_UPDATED, responseMessage, person, null);
    }

    public static PersonResponse successUpdatePersonAndNotUpdateCache(final Person person, final String responseMessage) {
        return new PersonResponse(UPDATED, CACHE_NOT_UPDATED, responseMessage, person, null);
    }

    public static PersonResponse successDeletePersonAndNotUpdateCache(final Person person, final String responseMessage) {
        return new PersonResponse(DELETED, CACHE_NOT_UPDATED, responseMessage, person, null);
    }

    public static PersonResponse displayPersonById(Person person, String responseMessage) {
        return new PersonResponse(NO_ACTIONS, null, responseMessage, person, null);
    }

    public static PersonResponse displayAllPeople(List<Person> people, String responseMessage) {
        return new PersonResponse(NO_ACTIONS, null, responseMessage, null , people);
    }
}