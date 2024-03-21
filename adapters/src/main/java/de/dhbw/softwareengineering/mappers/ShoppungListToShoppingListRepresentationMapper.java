package de.dhbw.softwareengineering.mappers;

import com.mongodb.Function;
import com.mongodb.lang.NonNull;
import de.dhbw.softwareengineering.entities.ShoppingList;
import de.dhbw.softwareengineering.representations.ShoppingListRepresentation;
import org.springframework.stereotype.Component;

@Component
public class ShoppungListToShoppingListRepresentationMapper implements Function<ShoppingList, ShoppingListRepresentation> {
    @Override
    @NonNull
    public ShoppingListRepresentation apply(@NonNull ShoppingList shoppingList) {
        return map(shoppingList);
    }

    private ShoppingListRepresentation map(ShoppingList shoppingList){
        return new ShoppingListRepresentation(
                shoppingList.getId(),
                shoppingList.getShoppingItemList(),
                shoppingList.getTotalPrice(),
                shoppingList.getPersonUUID()
        );
    }
}
