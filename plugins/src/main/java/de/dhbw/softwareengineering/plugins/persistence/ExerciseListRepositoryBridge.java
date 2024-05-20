package de.dhbw.softwareengineering.plugins.persistence;

import de.dhbw.softwareengineering.exerciseList.ExerciseList;
import de.dhbw.softwareengineering.exerciseList.ExerciseListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class ExerciseListRepositoryBridge implements ExerciseListRepository {
    private final DataExerciseListRepository dataExerciseListRepository;

    @Autowired
    public ExerciseListRepositoryBridge(DataExerciseListRepository dataExerciseListRepository) {
        this.dataExerciseListRepository = dataExerciseListRepository;
    }

    @Override
    public ExerciseList insert(ExerciseList exerciseList) {
        return dataExerciseListRepository.insert(exerciseList);
    }

    @Override
    public Optional<ExerciseList> findById(UUID id) {
        return dataExerciseListRepository.findById(id);
    }

    @Override
    public ExerciseList save(ExerciseList exerciseList) {
        return dataExerciseListRepository.save(exerciseList);
    }

    @Override
    public void delete(UUID id) {
        dataExerciseListRepository.deleteById(id);
    }
}
