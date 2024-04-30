package de.dhbw.softwareengineering.mappers;

import de.dhbw.softwareengineering.entities.ShoppingList;
import de.dhbw.softwareengineering.representations.ShoppingListRepresentation;
import org.springframework.stereotype.Component;

@Component
public class ShoppingListMapper {

    public ShoppingListRepresentation toShoppingLingRepresentation(ShoppingList shoppingList){
        return ShoppingListRepresentation.builder()
                .id(shoppingList.getId())
                .shoppingItemList(shoppingList.getShoppingItemList())
                .personUUID(shoppingList.getPersonUUID())
                .build();
    }
}
