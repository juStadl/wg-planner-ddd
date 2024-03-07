package de.dhbw.softwareengineering.values;

import java.util.Objects;
import java.util.UUID;

public final class Exercise {
    private final UUID uuid;
    private final String title;
    private final String description;
    private final UUID personUuid;

    public Exercise(String title, String description, UUID personUuid) {
        this.uuid = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.personUuid = personUuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public UUID getPersonUuid() {
        return personUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exercise exercise = (Exercise) o;
        return Objects.equals(uuid, exercise.uuid) && Objects.equals(title, exercise.title) && Objects.equals(description, exercise.description) && Objects.equals(personUuid, exercise.personUuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, title, description, personUuid);
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "uuid=" + uuid +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", personUuid=" + personUuid +
                '}';
    }
}