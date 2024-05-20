package de.dhbw.softwareengineering.person.mapper;

import de.dhbw.softwareengineering.person.Person;
import de.dhbw.softwareengineering.person.representation.PersonRepresentation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonMapper {

    public List<PersonRepresentation> toPersonRepresentationList(List<Person> personList){
        return personList
                .stream()
                .map(this::toPersonRepresentation)
                .toList();
    }

    public PersonRepresentation toPersonRepresentation(Person person) {
        return PersonRepresentation.builder()
                .id(person.getId())
                .name(person.getName())
                .address(person.getAddress())
                .gender(person.getGender())
                .birthDate(person.getBirthDate())
                .build();
    }
}
