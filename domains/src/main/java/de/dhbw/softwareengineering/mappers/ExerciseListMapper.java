package de.dhbw.softwareengineering.mappers;

import de.dhbw.softwareengineering.entities.ExerciseList;
import de.dhbw.softwareengineering.representations.ExerciseListRepresentation;
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
