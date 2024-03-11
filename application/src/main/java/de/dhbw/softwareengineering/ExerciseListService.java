package de.dhbw.softwareengineering;

import de.dhbw.softwareengineering.entities.ExerciseList;
import de.dhbw.softwareengineering.exceptions.ExerciseListNotFoundException;
import de.dhbw.softwareengineering.repositories.ExerciseListRepository;
import de.dhbw.softwareengineering.values.Exercise;
import de.dhbw.softwareengineering.values.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ExerciseListService {

    private final ExerciseListRepository exerciseListRepository;

    @Autowired
    public ExerciseListService(ExerciseListRepository exerciseListRepository) {
        this.exerciseListRepository = exerciseListRepository;
    }

    public ExerciseList create(){
        ExerciseList exerciseList = new ExerciseList(new ArrayList<>());

        return exerciseListRepository.save(exerciseList);
    }

    public ExerciseList getObject(UUID uuid) throws ExerciseListNotFoundException {
        return exerciseListRepository.findById(uuid)
                .orElseThrow(() -> new ExerciseListNotFoundException("No exerciselist object with such an UUID."));
    }

    public List<Exercise> getList(UUID uuid){
        return getObject(uuid).getList();
    }

    public ExerciseList updateStatus(UUID uuid, Status status) throws ExerciseListNotFoundException{
        ExerciseList exerciseList = getObject(uuid);
        exerciseList.setStatus(status);

        return exerciseListRepository.save(exerciseList);
    }

    public ExerciseList addExercise(UUID uuid, Exercise exercise){
        ExerciseList exerciseList = getObject(uuid);
        exerciseList.getList().add(exercise);

        return exerciseListRepository.save(exerciseList);
    }

    public ExerciseList deleteExercise(UUID listObjectUuid, UUID exerciseUuid) {
        ExerciseList exerciseList = getObject(listObjectUuid);
        exerciseList.getList().removeIf(exercise -> exercise.getUuid().equals(exerciseUuid));

        return exerciseListRepository.save(exerciseList);
    }

    public void delete(UUID uuid){
        exerciseListRepository.delete(uuid);
    }
}
