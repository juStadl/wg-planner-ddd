package de.dhbw.softwareengineering.exerciseList.values;

import java.util.UUID;

public record Exercise(String title, String description, UUID personUuid) {
}