package de.dhbw.softwareengineering.exerciseList;

import java.util.Optional;
import java.util.UUID;

public interface ExerciseListRepository {
    ExerciseList insert(ExerciseList exerciseList);
    Optional<ExerciseList> findById(UUID id);
    ExerciseList save(ExerciseList exerciseList);
    void delete(UUID id);
}