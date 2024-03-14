package de.dhbw.softwareengineering;

import de.dhbw.softwareengineering.entities.Person;
import de.dhbw.softwareengineering.exceptions.PersonNotFoundException;
import de.dhbw.softwareengineering.repositories.PersonRepository;
import de.dhbw.softwareengineering.values.Address;
import de.dhbw.softwareengineering.values.Gender;
import de.dhbw.softwareengineering.values.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person create(Name name, Address address, LocalDate date, Gender gender){
        Person person = new Person(name, address, date, gender);

        return personRepository.save(person);
    }
    public Person get(UUID uuid) throws PersonNotFoundException{
        return personRepository.findByUuid(uuid)
                .orElseThrow(() -> new PersonNotFoundException("No person with such a UUID."));
    }

    public List<Person> getAll(){
        return personRepository.findAll();
    }

    public void delete(UUID uuid) throws PersonNotFoundException {
        personRepository.delete(uuid);
    }

    public Person update(UUID uuid, Person updatedPerson){
        Person person = get(uuid);

        person.setName(updatedPerson.getName());
        person.setGender(updatedPerson.getGender());
        person.setAddress(updatedPerson.getAddress());
        person.setBirthDate(updatedPerson.getBirthDate());

        return personRepository.save(person);
    }
}
