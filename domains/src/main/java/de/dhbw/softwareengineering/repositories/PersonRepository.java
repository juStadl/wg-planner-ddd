package de.dhbw.softwareengineering.repositories;

import de.dhbw.softwareengineering.entities.Person;

import java.util.List;
import java.util.UUID;

public interface PersonRepository {
    List<Person> findAll();
    Person save(Person person);
    Person findByString(UUID personId);
    void delete(UUID personId);

}