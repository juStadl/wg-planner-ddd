package de.dhbw.softwareengineering.mappers;

import com.mongodb.lang.NonNull;
import de.dhbw.softwareengineering.entities.ExerciseList;
import de.dhbw.softwareengineering.representations.ExerciseListRepresentation;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ExerciseListRepresentationToExerciseListMapper implements Function<ExerciseListRepresentation, ExerciseList> {
    @Override
    @NonNull
    public ExerciseList apply(@NonNull final ExerciseListRepresentation exerciseListRepresentation) {
        return map(exerciseListRepresentation);
    }

    private ExerciseList map(ExerciseListRepresentation exerciseListRepresentation){
        ExerciseList e = new ExerciseList();
        e.setList(exerciseListRepresentation.getList());
        e.setStatus(exerciseListRepresentation.getStatus());
        e.setId(exerciseListRepresentation.getId());

        return e;
    }
}
