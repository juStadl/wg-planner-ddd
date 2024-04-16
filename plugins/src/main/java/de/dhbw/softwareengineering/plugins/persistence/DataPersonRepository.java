package de.dhbw.softwareengineering.plugins.persistence;

import de.dhbw.softwareengineering.entities.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface DataPersonRepository extends MongoRepository<Person, UUID> {
}
