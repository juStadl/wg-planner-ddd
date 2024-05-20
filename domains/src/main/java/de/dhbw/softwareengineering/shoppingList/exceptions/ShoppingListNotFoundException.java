package de.dhbw.softwareengineering.shoppingList.exceptions;

import de.dhbw.softwareengineering.NotFoundException;

public class ShoppingListNotFoundException extends NotFoundException {
    public ShoppingListNotFoundException(String message) {
        super(message);
    }
}