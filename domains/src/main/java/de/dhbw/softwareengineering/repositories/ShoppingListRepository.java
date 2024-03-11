package de.dhbw.softwareengineering.repositories;

import de.dhbw.softwareengineering.entities.ShoppingList;

import java.util.Optional;
import java.util.UUID;

public interface ShoppingListRepository {
    Optional<ShoppingList> findById(UUID id);
    ShoppingList save(ShoppingList shoppingList);
    void delete(UUID id);
}
