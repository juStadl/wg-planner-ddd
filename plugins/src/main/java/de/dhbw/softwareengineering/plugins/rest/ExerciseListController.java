package de.dhbw.softwareengineering.plugins.rest;

import de.dhbw.softwareengineering.ExerciseListService;
import de.dhbw.softwareengineering.representations.ExerciseListRepresentation;
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

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciseListRepresentation create()
    {
        return service.create();
    }

    @GetMapping(path = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ExerciseListRepresentation get(
            @PathVariable UUID uuid)
    {
        return service.getObject(uuid);
    }

    @GetMapping(value = "/{uuid}/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Exercise> getList(
            @PathVariable UUID uuid)
    {
        return service.getList(uuid);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(
            @PathVariable UUID uuid)
    {
        service.delete(uuid);
    }


    @PostMapping(value = "/{uuid}/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ExerciseListRepresentation addExercise(
       @PathVariable UUID uuid,
       @RequestBody @Valid Exercise exercise)
    {
        return service.addExercise(uuid, exercise);
    }

    @PutMapping(value = "/{uuid}/updateStatus", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ExerciseListRepresentation updateStatus(
            @PathVariable UUID uuid,
            @RequestBody @Valid Status status)
    {
        return service.updateStatus(uuid, status);
    }

}
