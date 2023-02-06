package com.people.api.service;

import com.people.api.exception.PersonAlreadyExistsException;
import com.people.api.exception.PersonNotFoundException;
import com.people.api.mapper.PersonMapper;
import com.people.api.mapper.PersonMapperImpl;
import com.people.api.repository.PersonRepository;
import com.people.api.utils.PersonDataTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
    private final PersonMapper personMapper = new PersonMapperImpl();
    @Mock
    private PersonRepository personRepository;
    private PersonService personService;

    @BeforeEach
    void setup() {
        personService = new PersonService(personRepository, personMapper);
    }

    @Test
    void findAll() {
        var personEntityList = PersonDataTest.personEntityList();

        when(personRepository.findAll()).thenReturn(personEntityList);

        var expetedPersonDtoList = PersonDataTest.personDtoList();
        var actualPersonDtoList = personService.findAll();

        assertThat(actualPersonDtoList.size()).isEqualTo(expetedPersonDtoList.size());

        IntStream.range(0, actualPersonDtoList.size())
                .forEach(i -> assertThat(actualPersonDtoList.get(i)).isEqualTo(expetedPersonDtoList.get(i)));
    }

    @Test
    void findByCpf() {
        var personEntity = PersonDataTest.personEntityList().get(0);

        when(personRepository.findByCpf(personEntity.getCpf())).thenReturn(Optional.of(personEntity));

        var expectedPersonDto = personService.findByCpf(personEntity.getCpf());
        var actualPersonDto = PersonDataTest.personDtoList().get(0);

        assertThat(actualPersonDto).isEqualTo(expectedPersonDto);

        when(personRepository.findByCpf("")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> personService.findByCpf(""))
                .isInstanceOf(PersonNotFoundException.class);
    }

    @Test
    void create() {
        var personEntity = PersonDataTest.personEntityList().get(0);
        var personDto = PersonDataTest.personDtoList().get(0);

        when(personRepository.findByCpf(personDto.getCpf())).thenReturn(Optional.empty());
        when(personRepository.save(personMapper.toEntity(personDto))).thenReturn(personEntity);

        var expectedPersonDto = personDto;
        var actualPersonDto = personService.create(personDto);

        assertThat(actualPersonDto).isEqualTo(expectedPersonDto);

        when(personRepository.findByCpf(personDto.getCpf())).thenReturn(Optional.of(personEntity));

        assertThatThrownBy(() -> personService.create(personDto))
                .isInstanceOf(PersonAlreadyExistsException.class);
    }

    @Test
    void update() {
        var personEntity = PersonDataTest.personEntityList().get(0);
        var personDto = PersonDataTest.personDtoList().get(0);

        when(personRepository.findByCpf(personDto.getCpf())).thenReturn(Optional.of(personEntity));
        when(personRepository.save(personEntity)).thenReturn(personEntity);

        var expectedPersonDto = personDto;
        var actualPersonDto = personService.update(personDto.getCpf(), personDto);

        assertThat(actualPersonDto).isEqualTo(expectedPersonDto);

        when(personRepository.findByCpf(personDto.getCpf())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> personService.update(personDto.getCpf(), personDto))
                .isInstanceOf(PersonNotFoundException.class);
    }

    @Test
    void delete() {
        var personEntity = PersonDataTest.personEntityList().get(0);

        when(personRepository.findByCpf(personEntity.getCpf())).thenReturn(Optional.of(personEntity));
        doNothing().when(personRepository).delete(personEntity);

        assertThatCode(() -> personService.delete(personEntity.getCpf())).doesNotThrowAnyException();

        when(personRepository.findByCpf(personEntity.getCpf())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> personService.delete(personEntity.getCpf()))
                .isInstanceOf(PersonNotFoundException.class);
    }
}