package de.dhbw.softwareengineering.plugins.persistence;

import de.dhbw.softwareengineering.entities.ExerciseList;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface DataExerciseListRepository extends MongoRepository<ExerciseList, UUID> {
}
