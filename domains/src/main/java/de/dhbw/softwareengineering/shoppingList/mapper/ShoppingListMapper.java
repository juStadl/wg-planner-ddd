package de.dhbw.softwareengineering.shoppingList.mapper;

import de.dhbw.softwareengineering.shoppingList.ShoppingList;
import de.dhbw.softwareengineering.shoppingList.representation.ShoppingListRepresentation;
import org.springframework.stereotype.Component;

@Component
public class ShoppingListMapper {

    public ShoppingListRepresentation toShoppingListRepresentation(ShoppingList shoppingList){
        return ShoppingListRepresentation.builder()
                .id(shoppingList.getId())
                .shoppingItemList(shoppingList.getShoppingItemList())
                .personUUID(shoppingList.getPersonUUID())
                .build();
    }
}
