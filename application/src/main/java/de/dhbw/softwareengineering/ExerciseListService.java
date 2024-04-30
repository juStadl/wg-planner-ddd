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
    private static final String EXERCISELIST_NOT_FOUND_MESSAGE = "No exerciseList with such a UUID.";

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
        return exerciseListMapper.toExerciseListRepresentation(validateAndGetExerciseList(uuid));
    }

    @Override
    public List<Exercise> getList(UUID uuid){
        return getObject(uuid).getList();
    }

    @Override
    public ExerciseList getExerciseList(UUID uuid){
        return validateAndGetExerciseList(uuid);
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
        validateAndGetExerciseList(uuid);
        exerciseListRepository.delete(uuid);
    }

    private ExerciseList validateAndGetExerciseList(UUID uuid){
        Optional<ExerciseList> optionalExerciseList = exerciseListRepository.findById(uuid);
        if (optionalExerciseList.isEmpty()){
            throw new ExerciseListNotFoundException(EXERCISELIST_NOT_FOUND_MESSAGE);
        }

        return optionalExerciseList.get();
    }
}
