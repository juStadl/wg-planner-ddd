package de.dhbw.softwareengineering.plugins.persistence;

import de.dhbw.softwareengineering.entities.Person;
import de.dhbw.softwareengineering.repositories.PersonRepository;

import java.util.List;
import java.util.UUID;

public class PersonRepositoryBridge implements PersonRepository {
    private DataPersonRepository dataPersonRepository;
    @Override
    public List<Person> findAll() {
        //TODO: implement method
        return null;
    }

    @Override
    public Person save(Person person) {
        //TODO: implement method
        return null;
    }

    @Override
    public Person findByString(UUID personId) {
        //TODO: implement method
        return null;
    }

    @Override
    public void delete(UUID personId) {
        //TODO: implement method
    }
}
