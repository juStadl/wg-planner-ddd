package de.dhbw.softwareengineering.interfaces;

import de.dhbw.softwareengineering.entities.ExerciseList;
import de.dhbw.softwareengineering.exceptions.ExerciseListNotFoundException;
import de.dhbw.softwareengineering.representations.ExerciseListRepresentation;
import de.dhbw.softwareengineering.values.Exercise;
import de.dhbw.softwareengineering.values.Status;

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
