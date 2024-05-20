package de.dhbw.softwareenginerring;

import de.dhbw.softwareengineering.PersonService;
import de.dhbw.softwareengineering.domainservice.PersonDomainService;
import de.dhbw.softwareengineering.entities.Person;
import de.dhbw.softwareengineering.exceptions.PersonNotFoundException;
import de.dhbw.softwareengineering.mappers.PersonMapper;
import de.dhbw.softwareengineering.repositories.PersonRepository;
import de.dhbw.softwareengineering.representations.PersonRepresentation;
import de.dhbw.softwareengineering.values.Address;
import de.dhbw.softwareengineering.values.Gender;
import de.dhbw.softwareengineering.values.Name;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceTests {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonMapper personMapper;
    @Mock
    private PersonDomainService personDomainService;

    @InjectMocks
    private PersonService personService;

    @Test
    void PersonService_Create_ReturnsPersonRepresentation(){
       Person person = Person.builder()
               .name(new Name("testName", "testLastname"))
               .address(new Address("testStreet", "testCity", "12345"))
               .birthDate(LocalDate.now())
               .gender(Gender.MALE)
               .build();

       PersonRepresentation personRepresentation = PersonRepresentation.builder()
               .name(new Name("testName", "testLastname"))
               .address(new Address("testStreet", "testCity", "12345"))
               .birthDate(LocalDate.now())
               .gender(Gender.MALE)
               .build();

        when(personDomainService.validateZipCode(personRepresentation.getAddress().zipCode())).thenReturn(true);
        when(personRepository.insert(Mockito.any(Person.class))).thenReturn(person);
        when(personMapper.toPersonRepresentation(Mockito.any(Person.class))).thenReturn(personRepresentation);

        PersonRepresentation savedPerson = personService.create(personRepresentation);

        Assertions.assertThat(savedPerson).isNotNull();
    }

    @Test
    void PersonService_Get_ReturnsPersonRepresentation() throws PersonNotFoundException {
        UUID uuid = UUID.randomUUID();
        Person person = Person.builder()
                .id(uuid)
                .name(new Name("testName", "testLastname"))
                .address(new Address("testStreet", "testCity", "12345"))
                .birthDate(LocalDate.now())
                .gender(Gender.MALE)
                .build();

        when(personRepository.findByUuid(uuid)).thenReturn(Optional.of(person));

        PersonRepresentation personRepresentation = PersonRepresentation.builder()
                .id(uuid)
                .name(new Name("testName", "testLastname"))
                .address(new Address("testStreet", "testCity", "12345"))
                .birthDate(LocalDate.now())
                .gender(Gender.MALE)
                .build();

        when(personMapper.toPersonRepresentation(person)).thenReturn(personRepresentation);

        PersonRepresentation retrievedPerson = personService.get(uuid);

        Assertions.assertThat(retrievedPerson).isNotNull();
        Assertions.assertThat(retrievedPerson.getId()).isEqualTo(uuid);
    }

    @Test
    void PersonService_Get_ThrowsPersonNotFoundException() {
        UUID notExistingUuid = UUID.randomUUID();

        when(personRepository.findByUuid(notExistingUuid)).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> personService.get(notExistingUuid))
                .isInstanceOf(PersonNotFoundException.class)
                .hasMessageContaining("No person with such a UUID.");
    }

    @Test
    void PersonService_GetAll_ReturnsPersonRepresentationList(){

        Person person1 = Person.builder()
                .id(UUID.randomUUID())
                .name(new Name("testName1", "testLastname1"))
                .address(new Address("testStreet1", "testCity1", "12345"))
                .birthDate(LocalDate.now())
                .gender(Gender.MALE)
                .build();

        Person person2 = Person.builder()
                .id(UUID.randomUUID())
                .name(new Name("testName2", "testLastname2"))
                .address(new Address("testStreet2", "testCity2", "67890"))
                .birthDate(LocalDate.now())
                .gender(Gender.FEMALE)
                .build();

        PersonRepresentation personRepresentation1 = PersonRepresentation.builder()
                .id(person1.getId())
                .name(new Name("testName1", "testLastname1"))
                .address(new Address("testStreet1", "testCity1", "12345"))
                .birthDate(LocalDate.now())
                .gender(Gender.MALE)
                .build();

        PersonRepresentation personRepresentation2 = PersonRepresentation.builder()
                .id(person2.getId())
                .name(new Name("testName2", "testLastname2"))
                .address(new Address("testStreet2", "testCity2", "67890"))
                .birthDate(LocalDate.now())
                .gender(Gender.FEMALE)
                .build();

        when(personRepository.findAll()).thenReturn(Arrays.asList(person1, person2));

        when(personMapper.toPersonRepresentationList(Arrays.asList(person1, person2)))
                .thenReturn(Arrays.asList(personRepresentation1, personRepresentation2));

        List<PersonRepresentation> allPersons = personService.getAll();

        Assertions.assertThat(allPersons).isNotNull().hasSize(2);
    }

    @Test
    void PersonService_Delete_ExistingPerson_DeletesPerson() {
        UUID uuid = UUID.randomUUID();

        Person existingPerson = Person.builder()
                .id(uuid)
                .name(new Name("testName", "testLastname"))
                .address(new Address("testStreet", "testCity", "12345"))
                .birthDate(LocalDate.now())
                .gender(Gender.MALE)
                .build();

        when(personRepository.findByUuid(uuid)).thenReturn(Optional.of(existingPerson));

        personService.delete(uuid);

        verify(personRepository).delete(uuid);
    }

    @Test
    void PersonService_Delete_NonExistingPerson_ThrowsException() {
        UUID uuid = UUID.randomUUID();

        when(personRepository.findByUuid(uuid)).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> personService.delete(uuid))
                .isInstanceOf(PersonNotFoundException.class)
                .hasMessageContaining("No person with such a UUID.");
    }

}
