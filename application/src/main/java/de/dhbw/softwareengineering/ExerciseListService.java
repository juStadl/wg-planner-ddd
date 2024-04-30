package de.dhbw.softwareengineering;

import de.dhbw.softwareengineering.entities.ExerciseList;
import de.dhbw.softwareengineering.exceptions.ExerciseListNotFoundException;
import de.dhbw.softwareengineering.interfaces.ExerciseListServiceInterface;
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
public class ExerciseListService implements ExerciseListServiceInterface {

    private final ExerciseListRepository exerciseListRepository;
    private final ExerciseListMapper exerciseListMapper;

    @Autowired
    public ExerciseListService(ExerciseListRepository exerciseListRepository, ExerciseListMapper exerciseListMapper) {
        this.exerciseListRepository = exerciseListRepository;
        this.exerciseListMapper = exerciseListMapper;
    }

    @Override
    public ExerciseListRepresentation create(){
        ExerciseList exerciseList = new ExerciseList();

        return exerciseListMapper.toExerciseListRepresentation(exerciseListRepository.insert(exerciseList));
    }

    @Override
    public ExerciseListRepresentation getObject(UUID uuid) throws ExerciseListNotFoundException {
        return exerciseListMapper.toExerciseListRepresentation(exerciseListRepository.findById(uuid)
                .orElseThrow(() -> new ExerciseListNotFoundException("No exerciselist with such a UUID.")));
    }

    @Override
    public List<Exercise> getList(UUID uuid){
        return getObject(uuid).getList();
    }

    @Override
    public ExerciseList getExerciseList(UUID uuid){
        return exerciseListRepository.findById(uuid)
                .orElseThrow(() -> new ExerciseListNotFoundException("No exerciselist with such a UUID"));
    }

    @Override
    public ExerciseListRepresentation updateStatus(UUID uuid, Status status) throws ExerciseListNotFoundException{
        ExerciseList exerciseList = getExerciseList(uuid);
        exerciseList.setStatus(status);

        return exerciseListMapper.toExerciseListRepresentation(exerciseListRepository.save(exerciseList));
    }

    @Override
    public ExerciseListRepresentation addExercise(UUID uuid, Exercise e){
        ExerciseList exerciseList = getExerciseList(uuid);
        Exercise exercise = new Exercise(e.title(), e.description(), e.personUuid());
        exerciseList.getList().add(exercise);

        return exerciseListMapper.toExerciseListRepresentation(exerciseListRepository.save(exerciseList));
    }

    @Override
    public void delete(UUID uuid) throws ExerciseListNotFoundException{
        Optional<ExerciseList> optionalExerciseList = exerciseListRepository.findById(uuid);

        if(optionalExerciseList.isEmpty()){
            throw new ExerciseListNotFoundException("No exerciselist with such a UUID.");
        }
        exerciseListRepository.delete(uuid);
    }
}
