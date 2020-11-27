package com.nrd.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nrd.rest.model.PersonRequest;
import com.nrd.rest.model.PersonResponse;
import com.nrd.rest.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/nrd", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class PersonController {

    private static final Logger log = LoggerFactory.getLogger(PersonController.class);

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping(value = "/person")
    public ResponseEntity<PersonResponse> createPerson(@RequestBody final PersonRequest personRequest) {
        log.info("Create a person");
        return ResponseEntity.ok(personService.createPerson(personRequest));
    }

    @GetMapping(value = "/people")
    public ResponseEntity<PersonResponse> getAllPeople() {
        log.info("Receive all people");
        return ResponseEntity.ok(personService.getAllPeople());
    }

    @GetMapping(value = "/person/{personId}")
    public ResponseEntity<PersonResponse> getPersonById(@PathVariable final Long personId) {
        log.info("Receive a person. Id: {}", personId);
        return ResponseEntity.ok(personService.getPersonById(personId));
    }

    @PutMapping(value = "/person/{personId}")
    public ResponseEntity<PersonResponse> updatePerson(@RequestBody final PersonRequest newPerson, @PathVariable final Long personId) {
        log.info("Update a person. Id: {}", personId);
        return ResponseEntity.ok(personService.updatePerson(newPerson, personId));
    }

    @DeleteMapping(value = "/person/{personId}")
    public ResponseEntity<PersonResponse> deletePerson(@PathVariable final Long personId) {
        log.info("Delete a person. Id: {}", personId);
        return ResponseEntity.ok(personService.deletePerson(personId));
    }
}