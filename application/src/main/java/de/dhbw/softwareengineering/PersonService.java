package de.dhbw.softwareengineering;

import de.dhbw.softwareengineering.entities.Person;
import de.dhbw.softwareengineering.exceptions.PersonNotFoundException;
import de.dhbw.softwareengineering.interfaces.PersonServiceInterface;
import de.dhbw.softwareengineering.mappers.PersonMapper;
import de.dhbw.softwareengineering.repositories.PersonRepository;
import de.dhbw.softwareengineering.representations.PersonRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService implements PersonServiceInterface {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private static final String PERSON_NOT_FOUND_MESSAGE = "No person with such a UUID.";

    @Autowired
    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    @Override
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
    @Override
    public PersonRepresentation get(UUID uuid) throws PersonNotFoundException{
        return personMapper.toPersonRepresentation(validateAndGetPerson(uuid));
    }

    @Override
    public List<PersonRepresentation> getAll(){

        return personMapper.toPersonRepresentationList(personRepository.findAll());
    }

    @Override
    public void delete(UUID uuid){
        validateAndGetPerson(uuid);
        personRepository.delete(uuid);
    }

    @Override
    public PersonRepresentation update(UUID personUuid, PersonRepresentation updatedPerson){
        Person person = getPerson(personUuid);

        person.setName(updatedPerson.getName());
        person.setAddress(updatedPerson.getAddress());
        person.setBirthDate(updatedPerson.getBirthDate());
        person.setGender(updatedPerson.getGender());

        return personMapper.toPersonRepresentation(personRepository.save(person));
    }

    @Override
    public Person getPerson(UUID uuid) {
        return validateAndGetPerson(uuid);
    }

    private Person validateAndGetPerson(UUID uuid){
        Optional<Person> optionalPerson = personRepository.findByUuid(uuid);
        if (optionalPerson.isEmpty()){
            throw new PersonNotFoundException(PERSON_NOT_FOUND_MESSAGE);
        }
        return optionalPerson.get();
    }

}
