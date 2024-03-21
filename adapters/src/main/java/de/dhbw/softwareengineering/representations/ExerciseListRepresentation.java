package de.dhbw.softwareengineering.representations;

import de.dhbw.softwareengineering.values.Exercise;
import de.dhbw.softwareengineering.values.Status;

import java.util.List;
import java.util.UUID;

public class ExerciseListRepresentation {

    private final UUID id;
    private final List<Exercise> list;
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
