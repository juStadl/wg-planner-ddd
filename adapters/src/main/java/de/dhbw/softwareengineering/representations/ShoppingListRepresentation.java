package de.dhbw.softwareengineering.representations;

import de.dhbw.softwareengineering.values.ShoppingItem;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public class ShoppingListRepresentation {

    private final UUID id;
    private final List<ShoppingItem> shoppingItemList;
    private final Double totalPrice;
    private final UUID personUUID;

    public ShoppingListRepresentation(final UUID id, final List<ShoppingItem> shoppingItemList, final Double totalPrice, final UUID personUUID) {
        this.id = id;
        this.shoppingItemList = shoppingItemList;
        this.totalPrice = totalPrice;
        this.personUUID = personUUID;
    }

    public UUID getId() {
        return id;
    }

    public List<ShoppingItem> getShoppingItemList() {
        return shoppingItemList;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public UUID getPersonUUID() {
        return personUUID;
    }
}
