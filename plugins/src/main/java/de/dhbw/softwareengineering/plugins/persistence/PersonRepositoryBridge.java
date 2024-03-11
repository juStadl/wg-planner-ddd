package de.dhbw.softwareengineering.plugins.persistence;

import de.dhbw.softwareengineering.entities.Person;
import de.dhbw.softwareengineering.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PersonRepositoryBridge implements PersonRepository {
    private final DataPersonRepository dataPersonRepository;

    @Autowired
    public PersonRepositoryBridge(DataPersonRepository dataPersonRepository) {
        this.dataPersonRepository = dataPersonRepository;
    }

    @Override
    public List<Person> findAll() {
        return dataPersonRepository.findAll();
    }

    @Override
    public Person save(Person person) {
        return dataPersonRepository.save(person);
    }

    @Override
    public Optional<Person> findByUuid(UUID personId) {
        return dataPersonRepository.findById(personId);
    }

    @Override
    public void delete(UUID personId) {
        dataPersonRepository.deleteById(personId);
    }
}
