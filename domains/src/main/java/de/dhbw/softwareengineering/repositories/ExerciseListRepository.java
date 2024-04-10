package de.dhbw.softwareengineering.repositories;

import de.dhbw.softwareengineering.entities.ExerciseList;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ExerciseListRepository {
    ExerciseList insert(ExerciseList exerciseList);
    Optional<ExerciseList> findById(UUID id);
    ExerciseList save(ExerciseList exerciseList);
    void delete(UUID id);
}