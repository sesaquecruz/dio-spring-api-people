package com.people.api.controller;

import com.people.api.dto.PersonDto;
import com.people.api.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/person")
@RequiredArgsConstructor
public class PersonController {
    final private PersonService personService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public List<PersonDto> findAll() {
        return personService.findAll();
    }

    @GetMapping(path = "/{cpf}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public PersonDto findByCpf(@PathVariable @CPF String cpf) {
        return personService.findByCpf(cpf);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public PersonDto create(@RequestBody @Valid PersonDto personDto) {
        return personService.create(personDto);
    }

    @PutMapping(path = "/{cpf}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public PersonDto update(@PathVariable @CPF String cpf, @RequestBody @Valid PersonDto personDto) {
        return personService.update(cpf, personDto);
    }

    @DeleteMapping(path = "/{cpf}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable @CPF String cpf) {
        personService.delete(cpf);
    }
}
