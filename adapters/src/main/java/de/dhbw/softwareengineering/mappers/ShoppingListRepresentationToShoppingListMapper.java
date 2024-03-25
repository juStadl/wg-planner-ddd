package de.dhbw.softwareengineering.mappers;

import com.mongodb.lang.NonNull;
import de.dhbw.softwareengineering.entities.ShoppingList;
import de.dhbw.softwareengineering.representations.ShoppingListRepresentation;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ShoppingListRepresentationToShoppingListMapper implements Function<ShoppingListRepresentation, ShoppingList> {
    @Override
    @NonNull
    public ShoppingList apply(@NonNull final ShoppingListRepresentation shoppingListRepresentation) {
        return map(shoppingListRepresentation);
    }

    private ShoppingList map(ShoppingListRepresentation shoppingListRepresentation){
        ShoppingList s = new ShoppingList();
        s.setId(shoppingListRepresentation.getId());
        s.setShoppingItemList(shoppingListRepresentation.getShoppingItemList());
        s.setTotalPrice(shoppingListRepresentation.getTotalPrice());
        s.setPersonUUID(shoppingListRepresentation.getPersonUUID());

        return s;
    }
}
