package com.people.api.service;

import com.people.api.dto.PersonDto;
import com.people.api.exception.PersonAlreadyExistsException;
import com.people.api.exception.PersonNotFoundException;
import com.people.api.mapper.PersonMapper;
import com.people.api.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    final private PersonRepository personRepository;
    final private PersonMapper personMapper;

    public List<PersonDto> findAll() {
        return personRepository.findAll().stream()
                .map(personMapper::toDto)
                .toList();
    }

    public PersonDto findByCpf(String cpf) {
        var personEntity = personRepository.findByCpf(cpf)
                .orElseThrow(PersonNotFoundException::new);
        return personMapper.toDto(personEntity);
    }

    public PersonDto create(PersonDto personDto) {
        if (personRepository.findByCpf(personDto.getCpf()).isPresent())
            throw new PersonAlreadyExistsException();
        var personEntity = personRepository.save(personMapper.toEntity(personDto));
        return personMapper.toDto(personEntity);
    }

    public PersonDto update(String cpf, PersonDto personDto) {
        var personEntitySaved = personRepository.findByCpf(cpf)
                .orElseThrow(PersonNotFoundException::new);
        var personEntityToSave = personMapper.toEntity(personDto);
        personEntityToSave.setId(personEntitySaved.getId());
        return personMapper.toDto(personRepository.save(personEntityToSave));
    }

    public void delete(String cpf) {
        var personEntity = personRepository.findByCpf(cpf)
                .orElseThrow(PersonNotFoundException::new);
        personRepository.delete(personEntity);
    }
}
