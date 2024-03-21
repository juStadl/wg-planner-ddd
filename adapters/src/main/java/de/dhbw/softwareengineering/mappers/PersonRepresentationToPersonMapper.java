package de.dhbw.softwareengineering.mappers;

import com.mongodb.Function;
import com.mongodb.lang.NonNull;
import de.dhbw.softwareengineering.entities.Person;
import de.dhbw.softwareengineering.representations.PersonRepresentation;
import org.springframework.stereotype.Component;

@Component
public class PersonRepresentationToPersonMapper implements Function<PersonRepresentation, Person> {

    @Override
    @NonNull
    public Person apply(@NonNull PersonRepresentation personRepresentation) {
        return map(personRepresentation);
    }

    private Person map(PersonRepresentation personRepresentation){
        Person p = new Person(
                personRepresentation.getName(),
                personRepresentation.getAddress(),
                personRepresentation.getBirthDate(),
                personRepresentation.getGender()
        );
        p.setId(personRepresentation.getId());
        return p;
    }
}
