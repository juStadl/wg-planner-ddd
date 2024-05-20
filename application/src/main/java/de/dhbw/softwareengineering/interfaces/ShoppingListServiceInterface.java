package de.dhbw.softwareengineering.interfaces;

import de.dhbw.softwareengineering.shoppingList.ShoppingList;
import de.dhbw.softwareengineering.shoppingList.exceptions.ShoppingListNotFoundException;
import de.dhbw.softwareengineering.shoppingList.representation.ShoppingListRepresentation;
import de.dhbw.softwareengineering.shoppingList.values.ShoppingItem;

import java.util.List;
import java.util.UUID;

public interface ShoppingListServiceInterface {
    ShoppingListRepresentation create(UUID personUuid);

    ShoppingListRepresentation getShoppingList(UUID uuid) throws ShoppingListNotFoundException;

    List<ShoppingItem> getShoppingItemList(UUID uuid);

    ShoppingListRepresentation addShoppingItem(UUID listUuid, ShoppingItem shoppingItem);

    void delete(UUID uuid) throws ShoppingListNotFoundException;

    ShoppingList get(UUID uuid);
}
