package de.dhbw.softwareengineering.plugins.persistence;

import de.dhbw.softwareengineering.shoppingList.ShoppingList;
import de.dhbw.softwareengineering.shoppingList.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class ShoppingListRepositoryBridge implements ShoppingListRepository {
    private final DataShoppingListRepository dataShoppingListRepository;

    @Autowired
    public ShoppingListRepositoryBridge(DataShoppingListRepository dataShoppingListRepository) {
        this.dataShoppingListRepository = dataShoppingListRepository;
    }

    @Override
    public Optional<ShoppingList> findById(UUID id) {
        return dataShoppingListRepository.findById(id);
    }

    @Override
    public ShoppingList save(ShoppingList shoppingList) {
        return dataShoppingListRepository.save(shoppingList);
    }

    @Override
    public void delete(UUID id) {
        dataShoppingListRepository.deleteById(id);
    }

    @Override
    public ShoppingList insert(ShoppingList shoppingList) {
        return dataShoppingListRepository.insert(shoppingList);
    }
}
