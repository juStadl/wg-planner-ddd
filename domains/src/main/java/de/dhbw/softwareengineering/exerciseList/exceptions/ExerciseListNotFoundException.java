package de.dhbw.softwareengineering.exerciseList.exceptions;

import de.dhbw.softwareengineering.NotFoundException;

public class ExerciseListNotFoundException extends NotFoundException {
    public ExerciseListNotFoundException(String message) {
        super(message);
    }
}