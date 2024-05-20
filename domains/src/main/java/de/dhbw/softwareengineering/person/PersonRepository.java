package de.dhbw.softwareengineering.person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonRepository {
    List<Person> findAll();
    Person save(Person person);
    Optional<Person> findByUuid(UUID personId);
    void delete(UUID personId);
    Person insert(Person person);
}