package com.company.UgonnaGuadalupeCapstone.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Tshirt implements IItem {
    public static final String ITEM_TYPE = "T-Shirts";
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tshirt tshirt = (Tshirt) o;

        if (getTshirtId() != tshirt.getTshirtId()) return false;
        if (getQuantity() != tshirt.getQuantity()) return false;
        if (!getSize().equals(tshirt.getSize())) return false;
        if (!getColor().equals(tshirt.getColor())) return false;
        if (!getDescription().equals(tshirt.getDescription())) return false;
        return getPrice().equals(tshirt.getPrice());
    }

    @Override
    public int hashCode() {
        int result = getTshirtId();
        result = 31 * result + getSize().hashCode();
        result = 31 * result + getColor().hashCode();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + getPrice().hashCode();
        result = 31 * result + getQuantity();
        return result;
    }

    //
//    public boolean equals(Object object) {
//        if (this == object) return true;
//        if (object == null || getClass() != object.getClass()) return false;
//        if (!super.equals(object)) return false;
//        Tshirt tshirt = (Tshirt) object;
//        return getTshirtId() == tshirt.getTshirtId() && getQuantity() == tshirt.getQuantity() && java.util.Objects.equals(getSize(), tshirt.getSize()) && java.util.Objects.equals(getColor(), tshirt.getColor()) && java.util.Objects.equals(getDescription(), tshirt.getDescription()) && java.util.Objects.equals(getPrice(), tshirt.getPrice());
//    }
//
//    public int hashCode() {
//        return Objects.hash(getTshirtId(), getSize(), getColor(), getDescription(), getPrice(), getQuantity());
//    }
}
