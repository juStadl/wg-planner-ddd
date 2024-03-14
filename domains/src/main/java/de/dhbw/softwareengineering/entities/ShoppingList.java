package de.dhbw.softwareengineering.entities;

import de.dhbw.softwareengineering.values.ShoppingItem;
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
    private Double totalPrice;
    private UUID personUUID;

    public ShoppingList() {
    }

    public ShoppingList(UUID personUUID) {
        this.id = UUID.randomUUID();
        this.shoppingItemList = new ArrayList<>();
        this.totalPrice = 0.0;
        this.personUUID = personUUID;
    }

    public UUID getId() {
        return id;
    }

    public List<ShoppingItem> getShoppingItemList() {
        return shoppingItemList;
    }

    public void setShoppingItemList(List<ShoppingItem> shoppingItemList) {
        this.shoppingItemList = shoppingItemList;
    }

    public Double getTotalPrice() {
        for(ShoppingItem shoppingItem : shoppingItemList){
            totalPrice += shoppingItem.getPrice();
        }
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public UUID getPersonUUID() {
        return personUUID;
    }

    public void setPersonUUID(UUID personUUID) {
        this.personUUID = personUUID;
    }
}