package de.dhbw.softwareengineering.repositories;

import de.dhbw.softwareengineering.entities.ExerciseList;

import java.util.Optional;
import java.util.UUID;

public interface ExerciseListRepository {
    ExerciseList insert(ExerciseList exerciseList);
    Optional<ExerciseList> findById(UUID id);
    ExerciseList save(ExerciseList exerciseList);
    void delete(UUID id);
}