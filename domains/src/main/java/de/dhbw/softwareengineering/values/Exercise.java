package de.dhbw.softwareengineering.values;

import java.util.UUID;

public record Exercise(String title, String description, UUID personUuid) {
}