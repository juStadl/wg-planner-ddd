package de.dhbw.softwareengineering.entities;

import de.dhbw.softwareengineering.values.Exercise;
import de.dhbw.softwareengineering.values.Status;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document(collection = "exerciseList")
public class ExerciseList {
    @Id
    private UUID id;
    private List<Exercise> list;
    private Status status;

    public ExerciseList() {
    }

    public ExerciseList(List<Exercise> list) {
        this.id = UUID.randomUUID();
        this.list = list;
        this.status = Status.TODO;
    }

    public void setList(List<Exercise> list) {
        this.list = list;
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

    public void setStatus(Status status) {
        this.status = status;
    }
}