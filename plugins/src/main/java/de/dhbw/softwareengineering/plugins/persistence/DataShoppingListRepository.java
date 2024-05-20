package de.dhbw.softwareengineering.plugins.persistence;

import de.dhbw.softwareengineering.shoppingList.ShoppingList;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface DataShoppingListRepository extends MongoRepository<ShoppingList, UUID> {
}
