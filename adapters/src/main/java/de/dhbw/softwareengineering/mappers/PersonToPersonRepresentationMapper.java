package de.dhbw.softwareengineering.mappers;

import com.mongodb.Function;
import com.mongodb.lang.NonNull;
import de.dhbw.softwareengineering.entities.Person;
import de.dhbw.softwareengineering.representations.PersonRepresentation;
import org.springframework.stereotype.Component;

@Component
public class PersonToPersonRepresentationMapper implements Function<Person, PersonRepresentation> {
    @Override
    @NonNull
    public PersonRepresentation apply(@NonNull final Person person) {
        return map(person);
    }

    private PersonRepresentation map(final Person person){
        return new PersonRepresentation(
                person.getId(),
                person.getName(),
                person.getAddress(),
                person.getBirthDate(),
                person.getGender());
    }
}
