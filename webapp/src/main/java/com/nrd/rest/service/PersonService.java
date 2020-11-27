package com.nrd.rest.service;

import com.nrd.rest.exception.PersonNotFoundException;
import com.nrd.rest.model.Person;
import com.nrd.rest.model.PersonRequest;
import com.nrd.rest.model.PersonResponse;
import com.nrd.rest.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.nrd.rest.mapper.PersonMapper.mapPersonRequestToPerson;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonResponse createPerson(final PersonRequest personRequest) {
        Person person = personRepository.save(mapPersonRequestToPerson(personRequest));
        return PersonResponse.successCreatePersonAndNotUpdateCache(person, "Person created successfully!");
    }

    public PersonResponse getAllPeople() {
        List<Person> people = personRepository.findAll();
        if (people.isEmpty()) {
            throw new PersonNotFoundException("No one was found");
        }
        return PersonResponse.displayAllPeople(people, "Display all people. Number of people: " + people.size());
    }

    public PersonResponse getPersonById(final Long personId) {
        final Person person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException(personId));
        return PersonResponse.displayPersonById(person, "Display person by id");
    }

    public PersonResponse updatePerson(final PersonRequest newPerson, final Long personId) {
        final Person person = personRepository.findById(personId)
                .map(p -> {
                    p.setFirstName(newPerson.getFirstName());
                    p.setLastName(newPerson.getLastName());
                    p.setEmail(newPerson.getEmail());
                    return personRepository.save(p);
                }).orElseThrow(() -> new PersonNotFoundException(personId));
        return PersonResponse.successUpdatePersonAndNotUpdateCache(person, "Person updated successfully!");
    }

    public PersonResponse deletePerson(final Long personId) {
        final Person person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException(personId));
        personRepository.delete(person);
        return PersonResponse.successDeletePersonAndNotUpdateCache(person, "Person deleted successfully!");
    }
}