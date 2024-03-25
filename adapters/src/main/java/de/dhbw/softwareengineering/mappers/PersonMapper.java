package de.dhbw.softwareengineering.mappers;

import de.dhbw.softwareengineering.entities.Person;
import de.dhbw.softwareengineering.representations.PersonRepresentation;
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
