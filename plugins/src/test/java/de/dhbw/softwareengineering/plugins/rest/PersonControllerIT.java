package de.dhbw.softwareengineering.plugins.rest;

import de.dhbw.softwareengineering.PersonService;
import de.dhbw.softwareengineering.representations.PersonRepresentation;
import de.dhbw.softwareengineering.values.Address;
import de.dhbw.softwareengineering.values.Gender;
import de.dhbw.softwareengineering.values.Name;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PersonController.class)
class PersonControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @Test
    void PersonController_create_ReturnsCreatedPersonRepresentation() throws Exception {
        PersonRepresentation inputPerson = PersonRepresentation.builder()
                .name(new Name("name", "lastname"))
                .address(new Address("street", "city", "zipcode"))
                .birthDate(LocalDate.of(2024, 4,16))
                .gender(Gender.MALE)
                .build();

        PersonRepresentation createdPerson = PersonRepresentation.builder()
                .id(UUID.randomUUID())
                .name(inputPerson.getName())
                .address(inputPerson.getAddress())
                .birthDate(inputPerson.getBirthDate())
                .gender(inputPerson.getGender())
                .build();

        when(personService.create(any(PersonRepresentation.class))).thenReturn(createdPerson);

        mockMvc.perform(post("/person/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"name\":{\"firstName\":\"name\",\"lastName\":\"lastname\"},\"address\":{\"street\":\"street\",\"city\":\"city\",\"zipCode\":\"zipcode\"},\"birthdate\":\"2024-04-16\",\"gender\":\"MALE\"}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.name.firstName").value("name"))
                .andExpect(jsonPath("$.name.lastName").value("lastname"))
                .andExpect(jsonPath("$.address.street").value("street"))
                .andExpect(jsonPath("$.address.city").value("city"))
                .andExpect(jsonPath("$.address.zipCode").value("zipcode"))
                .andExpect(jsonPath("$.birthdate").value("2024-04-16"))
                .andExpect(jsonPath("$.gender").value("MALE"))
                .andReturn();
    }

    @Test
    void PersonController_getAll_ReturnsListOfPersonRepresentation() throws Exception {
        List<PersonRepresentation> personRepresentationList = Arrays.asList(
                new PersonRepresentation(UUID.randomUUID(), new Name("name1", "lastname1"), new Address("street1", "city1", "12345"), LocalDate.of(2002, 9, 10), Gender.MALE),
                new PersonRepresentation(UUID.randomUUID(), new Name("name2", "lastname2"), new Address("street2", "city2", "12345"), LocalDate.of(1995, 2, 2), Gender.FEMALE)
        );

        when(personService.getAll()).thenReturn(personRepresentationList);

        mockMvc.perform(get("/person")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name.firstName").value("name1"))
                .andExpect(jsonPath("$[0].name.lastName").value("lastname1"))
                .andExpect(jsonPath("$[1].name.firstName").value("name2"))
                .andExpect(jsonPath("$[1].name.lastName").value("lastname2"))
                .andExpect(jsonPath("$[0].address.street").value("street1"))
                .andExpect(jsonPath("$[1].address.street").value("street2"))
                .andReturn();
    }

    @Test
    void PersonController_get_ReturnsPersonRepresentationWithMatchingId() throws Exception {
        UUID uuid = UUID.randomUUID();
        PersonRepresentation person = PersonRepresentation.builder()
                .id(uuid)
                .name(new Name("name", "lastname"))
                .address(new Address("street", "city", "zipcode"))
                .birthDate(LocalDate.of(2024, 4,16))
                .gender(Gender.MALE)
                .build();

        when(personService.get(any(UUID.class))).thenReturn(person);

        mockMvc.perform(get("/person/{id}", uuid))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(person.getId().toString()))
                .andExpect(jsonPath("$.name.firstName").value("name"))
                .andExpect(jsonPath("$.address.street").value("street"))
                .andReturn();
    }

    @Test
    void PersonController_update_ReturnsUpdatedPersonRepresentation() throws Exception {
        UUID uuid = UUID.fromString("accaefdf-970a-4881-9145-203805bf8cbe");
        PersonRepresentation person = PersonRepresentation.builder()
                .id(uuid)
                .name(new Name("name", "lastname"))
                .address(new Address("street", "city", "zipcode"))
                .birthDate(LocalDate.of(2024, 4,16))
                .gender(Gender.MALE)
                .build();

        PersonRepresentation updatedPerson = PersonRepresentation.builder()
                .id(uuid)
                .name(new Name("updatedName", "updatedLastname"))
                .address(new Address("updatedStreet", "city", "zipcode"))
                .birthDate(person.getBirthDate())
                .gender(Gender.FEMALE)
                .build();

        when(personService.update(any(UUID.class), any(PersonRepresentation.class))).thenReturn(updatedPerson);

        mockMvc.perform(put("/person/{id}", uuid)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"id\":\"accaefdf-970a-4881-9145-203805bf8cbe\",\"name\":{\"firstName\":\"updatedName\",\"lastName\":\"updatedLastname\"},\"address\":{\"street\":\"updatedStreet\",\"city\":\"city\",\"zipCode\":\"zipcode\"},\"birthdate\":\"2024-04-16\",\"gender\":\"FEMALE\"}"))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id").value("accaefdf-970a-4881-9145-203805bf8cbe"))
                .andExpect(jsonPath("$.name.firstName").value("updatedName"))
                .andExpect(jsonPath("$.name.lastName").value("updatedLastname"))
                .andExpect(jsonPath("$.address.street").value("updatedStreet"))
                .andExpect(jsonPath("$.gender").value("FEMALE"))
                .andReturn();

    }

    @Test
    void PersonController_delete_DeletesPersonWithGivenId() throws Exception {
        UUID uuid = UUID.randomUUID();
        doNothing().when(personService).delete(uuid);

        mockMvc.perform(delete("/person/{id}", uuid))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andReturn();

    }
}
