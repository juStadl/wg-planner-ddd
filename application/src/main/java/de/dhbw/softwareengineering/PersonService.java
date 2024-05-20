package de.dhbw.softwareengineering;

import de.dhbw.softwareengineering.person.domainService.PersonDomainService;
import de.dhbw.softwareengineering.person.Person;
import de.dhbw.softwareengineering.person.exceptions.PersonNotFoundException;
import de.dhbw.softwareengineering.person.exceptions.ZipCodeException;
import de.dhbw.softwareengineering.interfaces.PersonServiceInterface;
import de.dhbw.softwareengineering.person.mapper.PersonMapper;
import de.dhbw.softwareengineering.person.PersonRepository;
import de.dhbw.softwareengineering.person.representation.PersonRepresentation;
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
    private final PersonDomainService personDomainService;

    @Autowired
    public PersonService(PersonRepository personRepository, PersonMapper personMapper, PersonDomainService personDomainService) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
        this.personDomainService = personDomainService;
    }

    @Override
    public PersonRepresentation create(PersonRepresentation personRepresentation){
        if(personDomainService.validateZipCode(personRepresentation.getAddress().zipCode())){
            Person person = new Person(
                    personRepresentation.getName(),
                    personRepresentation.getAddress(),
                    personRepresentation.getBirthDate(),
                    personRepresentation.getGender()
            );

            return personMapper.toPersonRepresentation(personRepository.insert(person));
        }
        else {
            throw new ZipCodeException("ZipCode has to be five characters");
        }
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
