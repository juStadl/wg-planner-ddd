package de.dhbw.softwareengineering.plugins.rest;

import de.dhbw.softwareengineering.ExerciseListService;
import de.dhbw.softwareengineering.entities.ExerciseList;
import de.dhbw.softwareengineering.values.Exercise;
import de.dhbw.softwareengineering.values.Status;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/exerciseList")
public class ExerciseListController {
    private final ExerciseListService service;

    @Autowired
    public ExerciseListController(ExerciseListService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ExerciseList create()
    {
        return service.create();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ExerciseList get(
            @PathVariable UUID uuid)
    {
        return service.getObject(uuid);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{uuid}/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Exercise> getList(
            @PathVariable UUID uuid)
    {
        return service.getList(uuid);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{uuid}")
    public void delete(
            @PathVariable UUID uuid)
    {
        service.delete(uuid);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{uuid}/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ExerciseList addExercise(
       @PathVariable UUID uuid,
       @RequestBody @Valid Exercise exercise)
    {
        return service.addExercise(uuid, exercise);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{uuid}/deleteExercise/{exerciseUuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ExerciseList deleteExercise(
            @PathVariable UUID uuid,
            @PathVariable UUID exerciseUuid)
    {
        return service.deleteExercise(uuid, exerciseUuid);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping(value = "/{uuid}/updateStatus", produces = MediaType.APPLICATION_JSON_VALUE)
    public ExerciseList updateStatus(
            @PathVariable UUID uuid,
            @RequestBody @Valid Status status)
    {
        return service.updateStatus(uuid, status);
    }

}
