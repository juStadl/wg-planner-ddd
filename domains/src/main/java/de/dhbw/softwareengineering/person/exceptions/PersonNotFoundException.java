package de.dhbw.softwareengineering.person.exceptions;

import de.dhbw.softwareengineering.NotFoundException;

public class PersonNotFoundException extends NotFoundException {
    public PersonNotFoundException(String message) {
        super(message);
    }
}