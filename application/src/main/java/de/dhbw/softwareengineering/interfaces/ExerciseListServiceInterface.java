package de.dhbw.softwareengineering.interfaces;

import de.dhbw.softwareengineering.exerciseList.ExerciseList;
import de.dhbw.softwareengineering.exerciseList.exceptions.ExerciseListNotFoundException;
import de.dhbw.softwareengineering.exerciseList.representation.ExerciseListRepresentation;
import de.dhbw.softwareengineering.exerciseList.values.Exercise;
import de.dhbw.softwareengineering.exerciseList.values.Status;

import java.util.List;
import java.util.UUID;

public interface ExerciseListServiceInterface {
    ExerciseListRepresentation create();

    ExerciseListRepresentation getObject(UUID uuid) throws ExerciseListNotFoundException;

    List<Exercise> getList(UUID uuid);

    ExerciseList getExerciseList(UUID uuid);

    ExerciseListRepresentation updateStatus(UUID uuid, Status status) throws ExerciseListNotFoundException;

    ExerciseListRepresentation addExercise(UUID uuid, Exercise e);

    void delete(UUID uuid) throws ExerciseListNotFoundException;
}
