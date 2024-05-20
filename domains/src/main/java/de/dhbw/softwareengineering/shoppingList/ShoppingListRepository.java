package de.dhbw.softwareengineering.shoppingList;

import java.util.Optional;
import java.util.UUID;

public interface ShoppingListRepository {
    Optional<ShoppingList> findById(UUID id);
    ShoppingList save(ShoppingList shoppingList);
    void delete(UUID id);
    ShoppingList insert(ShoppingList shoppingList);
}
