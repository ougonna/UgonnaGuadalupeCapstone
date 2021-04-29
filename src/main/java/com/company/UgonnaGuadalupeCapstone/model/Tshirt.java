package com.company.UgonnaGuadalupeCapstone.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Tshirt implements IItem {
    public static final String ITEM_TYPE = "T-Shirt";
    private int tshirtId;
    private String size;
    private String color;
    private String description;
    private BigDecimal price;
    private int quantity;

    public int getTshirtId() {
        return tshirtId;
    }

    public void setTshirtId(int tshirtId) {
        this.tshirtId = tshirtId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Tshirt tshirt = (Tshirt) object;
        return getTshirtId() == tshirt.getTshirtId() && getQuantity() == tshirt.getQuantity() && java.util.Objects.equals(getSize(), tshirt.getSize()) && java.util.Objects.equals(getColor(), tshirt.getColor()) && java.util.Objects.equals(getDescription(), tshirt.getDescription()) && java.util.Objects.equals(getPrice(), tshirt.getPrice());
    }

    public int hashCode() {
        return Objects.hash(getTshirtId(), getSize(), getColor(), getDescription(), getPrice(), getQuantity());
    }
}
