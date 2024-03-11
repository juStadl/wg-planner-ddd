package de.dhbw.softwareengineering.plugins.persistence;

import de.dhbw.softwareengineering.entities.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface DataPersonRepository extends MongoRepository<Person, UUID> {
}
