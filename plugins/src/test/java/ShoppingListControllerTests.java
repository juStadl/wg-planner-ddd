import com.fasterxml.jackson.databind.ObjectMapper;
import de.dhbw.softwareengineering.PersonService;
import de.dhbw.softwareengineering.plugins.persistence.PersonRepositoryBridge;
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

class ShoppingListControllerTests {

}
