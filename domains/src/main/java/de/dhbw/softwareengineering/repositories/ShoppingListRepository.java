package de.dhbw.softwareengineering.repositories;

import de.dhbw.softwareengineering.entities.ShoppingList;

import java.util.UUID;

public interface ShoppingListRepository {
    ShoppingList findById(UUID id);
    ShoppingList save(ShoppingList shoppingList);
    void delete(UUID id);
}
