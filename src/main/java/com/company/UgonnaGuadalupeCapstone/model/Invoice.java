package com.company.UgonnaGuadalupeCapstone.model;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Objects;

public class Invoice {

    private int invoiceID;
    @NotEmpty(message = "OOF")
    private String name;
    @NotEmpty(message = "OOF")
    private String street;
    @NotEmpty(message = "OOF")
    private String city;
    @NotEmpty(message = "OOF")
    private String state;
    @NotEmpty(message = "OOF")
    private String zipcode;
    @NotEmpty(message = "OOF")
    private String itemType;
    private int itemId;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;
    private BigDecimal tax;
    private BigDecimal processing_fee;
    private BigDecimal total;

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

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

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getProcessingFee() {
        return processing_fee;
    }

    public void setProcessingFee(BigDecimal processing_fee) {
        this.processing_fee = processing_fee;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    //hash code


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return getInvoiceID() == invoice.getInvoiceID() && getItemId() == invoice.getItemId() && getQuantity() == invoice.getQuantity() && Objects.equals(getName(), invoice.getName()) && Objects.equals(getStreet(), invoice.getStreet()) && Objects.equals(getCity(), invoice.getCity()) && Objects.equals(getState(), invoice.getState()) && Objects.equals(getZipcode(), invoice.getZipcode()) && Objects.equals(getItemType(), invoice.getItemType()) && Objects.equals(getUnitPrice(), invoice.getUnitPrice()) && Objects.equals(getSubtotal(), invoice.getSubtotal()) && Objects.equals(getTax(), invoice.getTax()) && Objects.equals(processing_fee, invoice.processing_fee) && Objects.equals(getTotal(), invoice.getTotal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvoiceID(), getName(), getStreet(), getCity(), getState(), getZipcode(), getItemType(), getItemId(), getQuantity(), getUnitPrice(), getSubtotal(), getTax(), processing_fee, getTotal());
    }
}
