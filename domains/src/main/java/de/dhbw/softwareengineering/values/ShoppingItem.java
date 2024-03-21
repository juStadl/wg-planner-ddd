package de.dhbw.softwareengineering.values;

import java.util.Objects;
import java.util.UUID;

public final class ShoppingItem {
    private UUID uuid;
    private String title;
    private Integer quantity;
    private Double price;

    public ShoppingItem() {
    }

    public ShoppingItem(String title, Integer quantity, Double price) {
        this.uuid = UUID.randomUUID();
        this.title = title;
        this.quantity = quantity;
        this.price = price;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingItem that = (ShoppingItem) o;
        return Objects.equals(uuid, that.uuid) && Objects.equals(title, that.title) && Objects.equals(quantity, that.quantity) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, title, quantity, price);
    }
}