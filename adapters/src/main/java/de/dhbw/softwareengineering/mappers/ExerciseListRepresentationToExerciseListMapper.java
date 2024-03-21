package de.dhbw.softwareengineering.mappers;

import com.mongodb.Function;
import com.mongodb.lang.NonNull;
import de.dhbw.softwareengineering.entities.ExerciseList;
import de.dhbw.softwareengineering.representations.ExerciseListRepresentation;
import org.springframework.stereotype.Component;

@Component
public class ExerciseListRepresentationToExerciseListMapper implements Function<ExerciseListRepresentation, ExerciseList> {
    @Override
    @NonNull
    public ExerciseList apply(@NonNull ExerciseListRepresentation exerciseListRepresentation) {
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
