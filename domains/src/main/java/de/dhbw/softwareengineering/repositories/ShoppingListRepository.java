package de.dhbw.softwareengineering.repositories;

import de.dhbw.softwareengineering.entities.ShoppingList;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ShoppingListRepository {
    Optional<ShoppingList> findById(UUID id);
    ShoppingList save(ShoppingList shoppingList);
    void delete(UUID id);
    ShoppingList insert(ShoppingList shoppingList);
}
