package de.dhbw.softwareengineering;

import de.dhbw.softwareengineering.entities.ExerciseList;
import de.dhbw.softwareengineering.exceptions.ExerciseListNotFoundException;
import de.dhbw.softwareengineering.mappers.ExerciseListMapper;
import de.dhbw.softwareengineering.repositories.ExerciseListRepository;
import de.dhbw.softwareengineering.representations.ExerciseListRepresentation;
import de.dhbw.softwareengineering.values.Exercise;
import de.dhbw.softwareengineering.values.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExerciseListService {

    private final ExerciseListRepository exerciseListRepository;
    private final ExerciseListMapper exerciseListMapper;

    @Autowired
    public ExerciseListService(ExerciseListRepository exerciseListRepository, ExerciseListMapper exerciseListMapper) {
        this.exerciseListRepository = exerciseListRepository;
        this.exerciseListMapper = exerciseListMapper;
    }

    public ExerciseListRepresentation create(){
        ExerciseList exerciseList = new ExerciseList();

        return exerciseListMapper.toExerciseListRepresentation(exerciseListRepository.insert(exerciseList));
    }

    public ExerciseListRepresentation getObject(UUID uuid) throws ExerciseListNotFoundException {
        return exerciseListMapper.toExerciseListRepresentation(exerciseListRepository.findById(uuid)
                .orElseThrow(() -> new ExerciseListNotFoundException("No exerciselist with such a UUID.")));
    }

    public List<Exercise> getList(UUID uuid){
        return getObject(uuid).getList();
    }

    public ExerciseList getExerciseList(UUID uuid){
        return exerciseListRepository.findById(uuid)
                .orElseThrow(() -> new ExerciseListNotFoundException("No exerciselist with such a UUID"));
    }

    public ExerciseListRepresentation updateStatus(UUID uuid, Status status) throws ExerciseListNotFoundException{
        ExerciseList exerciseList = getExerciseList(uuid);
        exerciseList.setStatus(status);

        return exerciseListMapper.toExerciseListRepresentation(exerciseListRepository.save(exerciseList));
    }

    public ExerciseListRepresentation addExercise(UUID uuid, Exercise e){
        ExerciseList exerciseList = getExerciseList(uuid);
        Exercise exercise = new Exercise(e.getTitle(), e.getDescription(), e.getPersonUuid());
        exerciseList.getList().add(exercise);

        return exerciseListMapper.toExerciseListRepresentation(exerciseListRepository.save(exerciseList));
    }

    public void delete(UUID uuid) throws ExerciseListNotFoundException{
        Optional<ExerciseList> optionalExerciseList = exerciseListRepository.findById(uuid);

        if(optionalExerciseList.isEmpty()){
            throw new ExerciseListNotFoundException("No exerciselist with such a UUID.");
        }
        exerciseListRepository.delete(uuid);
    }
}
