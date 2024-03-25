package de.dhbw.softwareengineering.mappers;

import com.mongodb.lang.NonNull;
import de.dhbw.softwareengineering.entities.ExerciseList;
import de.dhbw.softwareengineering.representations.ExerciseListRepresentation;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ExerciseListToExerciseListRepresentationMapper implements Function<ExerciseList, ExerciseListRepresentation> {

    @Override
    @NonNull
    public ExerciseListRepresentation apply(@NonNull final ExerciseList exerciseList) {
        return map(exerciseList);
    }

    private ExerciseListRepresentation map(ExerciseList exerciseList){
        return new ExerciseListRepresentation(
                exerciseList.getId(),
                exerciseList.getList(),
                exerciseList.getStatus()
        );
    }
}
