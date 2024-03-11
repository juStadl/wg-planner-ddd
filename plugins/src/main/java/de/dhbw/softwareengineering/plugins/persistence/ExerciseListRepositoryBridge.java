package de.dhbw.softwareengineering.plugins.persistence;

import de.dhbw.softwareengineering.entities.ExerciseList;
import de.dhbw.softwareengineering.entities.ShoppingList;
import de.dhbw.softwareengineering.repositories.ExerciseListRepository;
import de.dhbw.softwareengineering.values.Exercise;

import java.util.List;
import java.util.UUID;

public class ExerciseListRepositoryBridge implements ExerciseListRepository {
    private DataExerciseListRepository dataExerciseListRepository;
    @Override
    public ExerciseList findById(UUID id) {
        //TODO: implement method
        return null;
    }

    @Override
    public ExerciseList save(ShoppingList shoppingList) {
        //TODO: implement method
        return null;
    }

    @Override
    public void delete(UUID id) {
        //TODO: implement method
    }

    @Override
    public List<Exercise> findByPerson(UUID personId) {
        //TODO: implement method
        return null;
    }
}
