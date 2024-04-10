import com.fasterxml.jackson.databind.ObjectMapper;
import de.dhbw.softwareengineering.PersonService;
import de.dhbw.softwareengineering.plugins.rest.PersonController;
import de.dhbw.softwareengineering.repositories.PersonRepository;
import de.dhbw.softwareengineering.representations.PersonRepresentation;
import de.dhbw.softwareengineering.values.Address;
import de.dhbw.softwareengineering.values.Gender;
import de.dhbw.softwareengineering.values.Name;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {PersonService.class, PersonController.class, })
class ShoppingListControllerTests {

   private MockMvc mockMvc;

   @Autowired
   PersonRepository personRepository;

   @Mock
   private PersonService personService;
   private PersonController personController;
   private ObjectMapper objectMapper;

   @BeforeEach
   void init(){
       MockitoAnnotations.openMocks(this);
       personController = new PersonController(personService);
       mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
       objectMapper = new ObjectMapper();
   }

   @Test
   void  PersonController_Create_ReturnsCreatedPersonRepresentation() throws Exception {
       PersonRepresentation personRepresentation = PersonRepresentation.builder()
               .id(UUID.randomUUID())
               .name(new Name("firstname", "lastname"))
               .address(new Address("street", "city", "zipCode"))
               .birthDate(LocalDate.now())
               .gender(Gender.MALE)
               .build();

       given(personService.create(ArgumentMatchers.any(PersonRepresentation.class))).willReturn(personRepresentation);

       mockMvc.perform(post("/person/create")
               .contentType(MediaType.APPLICATION_JSON_VALUE)
               .content(objectMapper.writeValueAsString(personRepresentation)))
               .andExpect(status().isCreated());
   }


}
