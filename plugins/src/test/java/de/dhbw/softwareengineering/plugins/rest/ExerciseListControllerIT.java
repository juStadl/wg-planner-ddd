package de.dhbw.softwareengineering.plugins.rest;

import de.dhbw.softwareengineering.ExerciseListService;
import de.dhbw.softwareengineering.representations.ExerciseListRepresentation;
import de.dhbw.softwareengineering.values.Exercise;
import de.dhbw.softwareengineering.values.Status;
import net.bytebuddy.description.annotation.AnnotationList.Empty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ExerciseListController.class)
class ExerciseListControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExerciseListService exerciseListService;

    @Test
    void ExerciseLitController_create_ReturnsCreatedExerciseListRepresentation() throws Exception{
        ExerciseListRepresentation createdExerciseList = ExerciseListRepresentation.builder()
                .id(UUID.randomUUID())
                .list(new ArrayList<>())
                .status(Status.TODO)
                .build();

        when(exerciseListService.create()).thenReturn(createdExerciseList);

        mockMvc.perform(post("/exerciseList")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void ExerciseListController_get_ReturnsExerciseListRepresentationWithGivenId() throws Exception {
        UUID uuid = UUID.randomUUID();
        ExerciseListRepresentation exerciseListRepresentation = ExerciseListRepresentation.builder()
                .id(uuid)
                .status(Status.TODO)
                .list(new ArrayList<>())
                .build();

        when(exerciseListService.getObject(any(UUID.class))).thenReturn(exerciseListRepresentation);

        mockMvc.perform(get("/exerciseList/{uuid}", uuid))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(uuid.toString()))
                .andExpect(jsonPath("$.status").value("TODO"))
                .andReturn();
    }

    @Test
    void ExerciseListController_getList_ReturnsListOfExercises() throws Exception{
        List<Exercise> list = Collections.singletonList(new Exercise("testTitle", "testDescription", UUID.randomUUID()));

        when(exerciseListService.getList(any(UUID.class))).thenReturn(list);

        mockMvc.perform(get("/exerciseList/{uuid}/list", UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.length()").value(list.size()))
                .andExpect(jsonPath("$.[0].title").value("testTitle"))
                .andExpect(jsonPath("$.[0].description").value("testDescription"))
                .andExpect(jsonPath("$.[0].description").value("testDescription"))
                .andReturn();
    }

    //TODO: write other test cases too

}
