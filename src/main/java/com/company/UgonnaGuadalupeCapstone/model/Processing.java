package com.company.UgonnaGuadalupeCapstone.model;

import java.util.Objects;
//processing fee
public class Processing {

    private String productType;
    private double fee;

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Processing that = (Processing) o;
        return Objects.equals(getProductType(), that.getProductType()) && Objects.equals(getFee(), that.getFee());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductType(), getFee());
    }

}
