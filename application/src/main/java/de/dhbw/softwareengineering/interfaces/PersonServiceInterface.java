package de.dhbw.softwareengineering.interfaces;

import de.dhbw.softwareengineering.entities.Person;
import de.dhbw.softwareengineering.exceptions.PersonNotFoundException;
import de.dhbw.softwareengineering.representations.PersonRepresentation;

import java.util.List;
import java.util.UUID;

public interface PersonServiceInterface {
    PersonRepresentation create(PersonRepresentation personRepresentation);

    PersonRepresentation get(UUID uuid) throws PersonNotFoundException;

    List<PersonRepresentation> getAll();

    void delete(UUID uuid);

    PersonRepresentation update(UUID personUuid, PersonRepresentation updatedPerson);

    Person getPerson(UUID uuid);
}
