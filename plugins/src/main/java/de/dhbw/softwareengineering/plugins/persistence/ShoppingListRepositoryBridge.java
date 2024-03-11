package de.dhbw.softwareengineering.plugins.persistence;

import de.dhbw.softwareengineering.entities.ShoppingList;
import de.dhbw.softwareengineering.repositories.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ShoppingListRepositoryBridge implements ShoppingListRepository {
    private DataShoppingListRepository dataShoppingListRepository;
    @Override
    public ShoppingList findById(UUID id) {
        //TODO: implement method
        return null;
    }

    @Override
    public ShoppingList save(ShoppingList shoppingList) {
        //TODO: implement method
        return null;
    }

    @Override
    public void delete(UUID id) {
        // TODO document why this method is empty
    }
}
