package de.dhbw.softwareengineering.repositories;

import de.dhbw.softwareengineering.entities.ExerciseList;
import de.dhbw.softwareengineering.entities.ShoppingList;
import de.dhbw.softwareengineering.values.Exercise;

import java.util.List;
import java.util.UUID;

public interface ExerciseListRepository {
    ExerciseList findById(UUID id);
    ExerciseList save(ShoppingList shoppingList);
    void delete(UUID id);
    List<Exercise> findByPerson(UUID personId);
}