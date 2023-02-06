package com.people.api.mapper;

import com.people.api.dto.PersonDto;
import com.people.api.entity.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    @Mapping(target = "id", ignore = true)
    PersonEntity toEntity(PersonDto personDto);

    PersonDto toDto(PersonEntity personEntity);
}
