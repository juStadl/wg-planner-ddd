package de.dhbw.softwareengineering.plugins.rest;

import de.dhbw.softwareengineering.ExerciseListService;
import de.dhbw.softwareengineering.representations.ExerciseListRepresentation;
import de.dhbw.softwareengineering.values.Exercise;
import de.dhbw.softwareengineering.values.Status;
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

    @Test
    void ExerciseListController_delete_DeletesExerciseListWithGivenId() throws Exception{
        UUID uuid = UUID.randomUUID();
        doNothing().when(exerciseListService).delete(uuid);

        mockMvc.perform(delete("/exerciseList/{uuid}", uuid))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andReturn();
    }

    @Test
    void ExerciseListController_addExercise_ReturnsUpdatedExerciseListRepresentation() throws Exception{
        UUID listUuid = UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6");
        UUID personUuid = UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa5");
        ExerciseListRepresentation exerciseListRepresentation = ExerciseListRepresentation.builder()
                .id(listUuid)
                .list(new ArrayList<>())
                .status(Status.TODO)
                .build();

        Exercise e = new Exercise("testTitle", "testDescription", personUuid);
        exerciseListRepresentation.getList().add(e);

        when(exerciseListService.addExercise(any(UUID.class), any(Exercise.class))).thenReturn(exerciseListRepresentation);

        mockMvc.perform(post("/exerciseList/{uuid}/add", listUuid)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"uuid\":\"3fa85f64-5717-4562-b3fc-2c963f66afa4\",\"title\":\"testTitle\",\"description\":\"testDescription\",\"personUuid\":\"3fa85f64-5717-4562-b3fc-2c963f66afa5\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.list.[0].uuid").isNotEmpty())
                .andExpect(jsonPath("$.list.[0].title").value("testTitle"))
                .andExpect(jsonPath("$.list.[0].description").value("testDescription"))
                .andExpect(jsonPath("$.list.[0].personUuid").value(personUuid.toString()))
                .andReturn();
    }

    @Test
    void ExerciseListController_updateStatus_ReturnsExerciseListRepresentationWithUpdatedStatus() throws Exception{
        ExerciseListRepresentation exerciseListRepresentation = ExerciseListRepresentation.builder()
                .id(UUID.randomUUID())
                .status(Status.TODO)
                .list(new ArrayList<>())
                .build();

        ExerciseListRepresentation updatedStatus = ExerciseListRepresentation.builder()
                .id(exerciseListRepresentation.getId())
                .list(exerciseListRepresentation.getList())
                .status(Status.IN_PROGRESS)
                .build();

        when(exerciseListService.updateStatus(any(UUID.class), any(Status.class))).thenReturn(updatedStatus);

        mockMvc.perform(put("/exerciseList/{uuid}/updateStatus", UUID.randomUUID())
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content("\"IN_PROGRESS\""))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"))
                .andReturn();
    }

}
