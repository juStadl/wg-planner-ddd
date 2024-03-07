package de.dhbw.softwareengineering.exceptions;

public class ShoppingListNotFoundException extends NotFoundException {
    public ShoppingListNotFoundException(String message) {
        super(message);
    }
}