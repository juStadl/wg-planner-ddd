package de.dhbw.softwareenginerring;

import de.dhbw.softwareengineering.ExerciseListService;
import de.dhbw.softwareengineering.exerciseList.ExerciseList;
import de.dhbw.softwareengineering.exerciseList.exceptions.ExerciseListNotFoundException;
import de.dhbw.softwareengineering.exerciseList.mapper.ExerciseListMapper;
import de.dhbw.softwareengineering.exerciseList.ExerciseListRepository;
import de.dhbw.softwareengineering.exerciseList.representation.ExerciseListRepresentation;
import de.dhbw.softwareengineering.exerciseList.values.Exercise;
import de.dhbw.softwareengineering.exerciseList.values.Status;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExerciseListServiceTests {

    @Mock
    ExerciseListRepository exerciseListRepository;

    @Mock
    ExerciseListMapper exerciseListMapper;

    @InjectMocks
    ExerciseListService exerciseListService;

    @Test
    void ExerciseListService_Create_ReturnsExerciseListRepresentation(){
        ExerciseList exerciseList = new ExerciseList();

        ExerciseListRepresentation exerciseListRepresentation = ExerciseListRepresentation.builder()
                .id(exerciseList.getId())
                .list(exerciseList.getList())
                .status(exerciseList.getStatus())
                .build();

        when(exerciseListRepository.insert(Mockito.any(ExerciseList.class))).thenReturn(exerciseList);
        when(exerciseListMapper.toExerciseListRepresentation(Mockito.any(ExerciseList.class))).thenReturn(exerciseListRepresentation);

        ExerciseListRepresentation savedExerciseList = exerciseListService.create();

        Assertions.assertThat(savedExerciseList).isNotNull();
        Assertions.assertThat(savedExerciseList.getId()).isNotNull();
    }

    @Test
    void ExerciseListService_getObject_ReturnsExerciseListRepresentation()
            throws ExerciseListNotFoundException{

        ExerciseList exerciseList = new ExerciseList();
        UUID uuid = exerciseList.getId();

        when(exerciseListRepository.findById(uuid)).thenReturn(Optional.of(exerciseList));

        ExerciseListRepresentation exerciseListRepresentation = ExerciseListRepresentation.builder()
                .id(exerciseList.getId())
                .list(exerciseList.getList())
                .status(exerciseList.getStatus())
                .build();

        when(exerciseListMapper.toExerciseListRepresentation(exerciseList)).thenReturn(exerciseListRepresentation);

        ExerciseListRepresentation retrievedExerciseList = exerciseListService.getObject(uuid);

        Assertions.assertThat(retrievedExerciseList).isNotNull();
        Assertions.assertThat(retrievedExerciseList.getId()).isEqualTo(uuid);
    }

    @Test
    void ExerciseListService_getObject_ThrowsExerciseListNotFoundException(){
        UUID notExistingUuid = UUID.randomUUID();

        when(exerciseListRepository.findById(notExistingUuid)).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> exerciseListService.getObject(notExistingUuid))
                .isInstanceOf(ExerciseListNotFoundException.class)
                .hasMessageContaining("No exerciseList with such a UUID.");
    }

    @Test
    void ExerciseListService_getExerciseList_ReturnsExerciseList() {
        ExerciseList exerciseList = new ExerciseList();
        UUID exerciseListUuid = exerciseList.getId();

        when(exerciseListRepository.findById(exerciseListUuid)).thenReturn(Optional.of(exerciseList));

        ExerciseList result = exerciseListService.getExerciseList(exerciseListUuid);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isEqualTo(exerciseListUuid);
    }

    @Test
    void ExerciseListService_updateStatus_ReturnsUpdatedExerciseListRepresentation() {
        UUID uuid = UUID.randomUUID();

        ExerciseList exerciseList = new ExerciseList();

        Status newStatus = Status.IN_PROGRESS;

        when(exerciseListRepository.findById(uuid)).thenReturn(Optional.of(exerciseList));

        when(exerciseListRepository.save(exerciseList)).thenReturn(exerciseList);

        ExerciseListRepresentation expectedRepresentation = ExerciseListRepresentation.builder()
                .id(uuid)
                .list(exerciseList.getList())
                .status(newStatus)
                .build();

        when(exerciseListMapper.toExerciseListRepresentation(exerciseList)).thenReturn(expectedRepresentation);

        ExerciseListRepresentation result = exerciseListService.updateStatus(uuid, newStatus);

        Assertions.assertThat(result).isNotNull().isEqualTo(expectedRepresentation);

        Assertions.assertThat(result.getStatus()).isEqualTo(newStatus);

        verify(exerciseListRepository).save(exerciseList);
    }

    @Test
    void updateStatus_ThrowsExerciseListNotFoundException() {
        UUID uuid = UUID.randomUUID();

        when(exerciseListRepository.findById(uuid)).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> exerciseListService.updateStatus(uuid, Status.IN_PROGRESS))
                        .isInstanceOf(ExerciseListNotFoundException.class)
                                .hasMessageContaining("No exerciseList with such a UUID.");
    }

    @Test
    void ExerciseListService_addExercise_ReturnsExerciseListRepresentation(){
        ExerciseList exerciseList = new ExerciseList();

        UUID uuid = UUID.randomUUID();

        Exercise exercise = new Exercise("Title1", "Description1", UUID.randomUUID());

        when(exerciseListRepository.findById(uuid)).thenReturn(Optional.of(exerciseList));

        when(exerciseListRepository.save(exerciseList)).thenReturn(exerciseList);

        List<Exercise> list = new ArrayList<>();
        list.add(exercise);

        ExerciseListRepresentation expectedExerciseList = ExerciseListRepresentation.builder()
                .id(uuid)
                .list(list)
                .status(exerciseList.getStatus())
                .build();

        when(exerciseListMapper.toExerciseListRepresentation(exerciseList)).thenReturn(expectedExerciseList);

        ExerciseListRepresentation result = exerciseListService.addExercise(uuid, exercise);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getList().size()).isEqualByComparingTo(1);
        verify(exerciseListRepository).save(exerciseList);
    }

    @Test
    void ExerciseListService_delete_ExistingPerson_DeletesPerson(){
        UUID uuid = UUID.randomUUID();
        ExerciseList exerciseList = new ExerciseList();

        when(exerciseListRepository.findById(uuid)).thenReturn(Optional.of(exerciseList));

        exerciseListService.delete(uuid);

        verify(exerciseListRepository).delete(uuid);
    }

    @Test
    void ExerciseListService_delete_NonExistingPerson_ThrowsException(){
        UUID randomUUID = UUID.randomUUID();

        when(exerciseListRepository.findById(randomUUID)).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> exerciseListService.delete(randomUUID))
                .isInstanceOf(ExerciseListNotFoundException.class)
                .hasMessageContaining("No exerciseList with such a UUID.");
    }
}
