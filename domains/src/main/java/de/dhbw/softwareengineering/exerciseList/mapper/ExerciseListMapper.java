package de.dhbw.softwareengineering.exerciseList.mapper;

import de.dhbw.softwareengineering.exerciseList.ExerciseList;
import de.dhbw.softwareengineering.exerciseList.representation.ExerciseListRepresentation;
import org.springframework.stereotype.Component;

@Component
public class ExerciseListMapper {

    public ExerciseListRepresentation toExerciseListRepresentation(ExerciseList exerciseList){
        return ExerciseListRepresentation.builder()
                .id(exerciseList.getId())
                .list(exerciseList.getList())
                .status(exerciseList.getStatus())
                .build();
    }
}
