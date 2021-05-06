package com.company.UgonnaGuadalupeCapstone.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PurchaseRequest {

    @NotEmpty(message = "You must enter your name")
    private String name;
    @NotEmpty(message = "You must enter your street")
    private String street;
    @NotEmpty(message = "You must enter your city")
    private String city;
    @NotEmpty(message = "You must enter your state abbreviation")
    @Size(min = 2, max = 2)
    private String state;
    @NotEmpty(message = "You must enter your Zipcode")
    @Size(min = 5, max = 5)
    private String zip;
    @NotEmpty(message = "You must enter your itemType")
    private String itemType;

    private int itemID;
    private int quantity;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
