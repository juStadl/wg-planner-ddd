package de.dhbw.softwareengineering;

import de.dhbw.softwareengineering.entities.Person;
import de.dhbw.softwareengineering.exceptions.PersonNotFoundException;
import de.dhbw.softwareengineering.mappers.PersonMapper;
import de.dhbw.softwareengineering.repositories.PersonRepository;
import de.dhbw.softwareengineering.representations.PersonRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private static final String EXCEPTION_MESSAGE = "No person with such a UUID.";

    @Autowired
    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    public PersonRepresentation create(PersonRepresentation personRepresentation){
        Person person = new Person(
                personRepresentation.getId(),
                personRepresentation.getName(),
                personRepresentation.getAddress(),
                personRepresentation.getBirthDate(),
                personRepresentation.getGender()
        );

        return personMapper.toPersonRepresentation(personRepository.insert(person));
    }
    public PersonRepresentation get(UUID uuid) throws PersonNotFoundException{
        return personMapper.toPersonRepresentation(personRepository.findByUuid(uuid)
                .orElseThrow(() -> new PersonNotFoundException(EXCEPTION_MESSAGE)));
    }

    public List<PersonRepresentation> getAll(){

        return personMapper.toPersonRepresentationList(personRepository.findAll());
    }

    public void delete(UUID uuid){
        Optional<Person> optionalPerson = personRepository.findByUuid(uuid);

        if(optionalPerson.isEmpty()){
            throw new PersonNotFoundException(EXCEPTION_MESSAGE);
        }
        personRepository.delete(uuid);
    }

    public PersonRepresentation update(UUID personUuid, PersonRepresentation updatedPerson){
        Person person = getPerson(personUuid);

        person.setName(updatedPerson.getName());
        person.setAddress(updatedPerson.getAddress());
        person.setBirthDate(updatedPerson.getBirthDate());
        person.setGender(updatedPerson.getGender());

        return personMapper.toPersonRepresentation(personRepository.save(person));
    }

    private Person getPerson(UUID uuid){
        return personRepository.findByUuid(uuid)
                .orElseThrow(() -> new PersonNotFoundException(EXCEPTION_MESSAGE));
    }
}
