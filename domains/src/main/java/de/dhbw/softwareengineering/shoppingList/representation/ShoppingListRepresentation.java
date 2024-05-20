package de.dhbw.softwareengineering.shoppingList.representation;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.dhbw.softwareengineering.shoppingList.values.ShoppingItem;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public class ShoppingListRepresentation {

    private final UUID id;

    @JsonProperty("shoppingItemList")
    private final List<ShoppingItem> shoppingItemList;

    @JsonProperty("personUuid")
    private final UUID personUUID;

    public ShoppingListRepresentation(final UUID id, final List<ShoppingItem> shoppingItemList, final UUID personUUID) {
        this.id = id;
        this.shoppingItemList = shoppingItemList;
        this.personUUID = personUUID;
    }

    public UUID getId() {
        return id;
    }

    public List<ShoppingItem> getShoppingItemList() {
        return shoppingItemList;
    }

    public UUID getPersonUUID() {
        return personUUID;
    }
}
