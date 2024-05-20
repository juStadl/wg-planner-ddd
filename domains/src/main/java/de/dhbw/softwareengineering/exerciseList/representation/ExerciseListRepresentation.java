package de.dhbw.softwareengineering.exerciseList.representation;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.dhbw.softwareengineering.exerciseList.values.Exercise;
import de.dhbw.softwareengineering.exerciseList.values.Status;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public class ExerciseListRepresentation {

    private final UUID id;

    @JsonProperty("exerciseList")
    private final List<Exercise> list;

    @JsonProperty("status")
    private final Status status;

    public ExerciseListRepresentation(UUID id, final List<Exercise> list, final Status status) {
        this.id = id;
        this.list = list;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public List<Exercise> getList() {
        return list;
    }

    public Status getStatus() {
        return status;
    }
}
