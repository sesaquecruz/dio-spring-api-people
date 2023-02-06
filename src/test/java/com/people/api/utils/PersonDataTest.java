package com.people.api.utils;

import com.people.api.dto.PersonDto;
import com.people.api.entity.PersonEntity;

import java.time.LocalDate;
import java.util.List;

public class PersonDataTest {
    private static final long PERSON_1_ID = 1;
    private static final String PERSON_1_CPF = "26592727780";
    private static final LocalDate PERSON_1_BIRTH_DATE = LocalDate.of(2000, 1,10);
    private static final String PERSON_1_FIRST_NAME = "Tom";
    private static final String PERSON_1_LAST_NAME = "Gaus";
    private static final String PERSON_1_PHONE = "0012345678";
    private static final String PERSON_1_EMAIL = "tom@mail.com";

    private static final long PERSON_2_ID = 2;
    private static final String PERSON_2_CPF = "47502154574";
    private static final LocalDate PERSON_2_BIRTH_DATE = LocalDate.of(2001, 2,11);
    private static final String PERSON_2_FIRST_NAME = "Liz";
    private static final String PERSON_2_LAST_NAME = "Finn";
    private static final String PERSON_2_PHONE = "1112345678";
    private static final String PERSON_2_EMAIL = "liz@mail.com";

    private static final long PERSON_3_ID = 3;
    private static final String PERSON_3_CPF = "31462703232";
    private static final LocalDate PERSON_3_BIRTH_DATE = LocalDate.of(2002, 3,12);
    private static final String PERSON_3_FIRST_NAME = "Bruce";
    private static final String PERSON_3_LAST_NAME = "Halt";
    private static final String PERSON_3_PHONE = "2212345678";
    private static final String PERSON_3_EMAIL = "bruce@mail.com";

    public static List<PersonEntity> personEntityList() {
        return List.of(
                PersonEntity.builder()
                        .id(PERSON_1_ID)
                        .cpf(PERSON_1_CPF)
                        .birthDate(PERSON_1_BIRTH_DATE)
                        .firstName(PERSON_1_FIRST_NAME)
                        .lastName(PERSON_1_LAST_NAME)
                        .phone(PERSON_1_PHONE)
                        .email(PERSON_1_EMAIL)
                        .build(),
                PersonEntity.builder()
                        .id(PERSON_2_ID)
                        .cpf(PERSON_2_CPF)
                        .birthDate(PERSON_2_BIRTH_DATE)
                        .firstName(PERSON_2_FIRST_NAME)
                        .lastName(PERSON_2_LAST_NAME)
                        .phone(PERSON_2_PHONE)
                        .email(PERSON_2_EMAIL)
                        .build(),
                PersonEntity.builder()
                        .id(PERSON_3_ID)
                        .cpf(PERSON_3_CPF)
                        .birthDate(PERSON_3_BIRTH_DATE)
                        .firstName(PERSON_3_FIRST_NAME)
                        .lastName(PERSON_3_LAST_NAME)
                        .phone(PERSON_3_PHONE)
                        .email(PERSON_3_EMAIL)
                        .build()
        );
    }

    public static List<PersonDto> personDtoList() {
        return List.of(
                PersonDto.builder()
                        .cpf(PERSON_1_CPF)
                        .birthDate(PERSON_1_BIRTH_DATE)
                        .firstName(PERSON_1_FIRST_NAME)
                        .lastName(PERSON_1_LAST_NAME)
                        .phone(PERSON_1_PHONE)
                        .email(PERSON_1_EMAIL)
                        .build(),
                PersonDto.builder()
                        .cpf(PERSON_2_CPF)
                        .birthDate(PERSON_2_BIRTH_DATE)
                        .firstName(PERSON_2_FIRST_NAME)
                        .lastName(PERSON_2_LAST_NAME)
                        .phone(PERSON_2_PHONE)
                        .email(PERSON_2_EMAIL)
                        .build(),
                PersonDto.builder()
                        .cpf(PERSON_3_CPF)
                        .birthDate(PERSON_3_BIRTH_DATE)
                        .firstName(PERSON_3_FIRST_NAME)
                        .lastName(PERSON_3_LAST_NAME)
                        .phone(PERSON_3_PHONE)
                        .email(PERSON_3_EMAIL)
                        .build()
        );
    }
}
