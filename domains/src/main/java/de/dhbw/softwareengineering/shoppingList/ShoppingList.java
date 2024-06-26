package de.dhbw.softwareengineering.shoppingList;

import de.dhbw.softwareengineering.shoppingList.values.ShoppingItem;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document(collection = "shoppingList")
public class ShoppingList {
    @Id
    private UUID id;
    private List<ShoppingItem> shoppingItemList;
    private UUID personUUID;

    public ShoppingList() {
    }

    public ShoppingList(UUID personUUID) {
        this.id = UUID.randomUUID();
        this.shoppingItemList = new ArrayList<>();
        this.personUUID = personUUID;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<ShoppingItem> getShoppingItemList() {
        return shoppingItemList;
    }

    public void setShoppingItemList(List<ShoppingItem> shoppingItemList) {
        this.shoppingItemList = shoppingItemList;
    }

    public Double getTotalPrice() {
        double totalPrice = 0.0;
        for(ShoppingItem shoppingItem : shoppingItemList) {
            totalPrice += shoppingItem.price();
        }

        return totalPrice;
    }

    public UUID getPersonUUID() {
        return personUUID;
    }

    public void setPersonUUID(UUID personUUID) {
        this.personUUID = personUUID;
    }
}