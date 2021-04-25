package com.company.UgonnaGuadalupeCapstone.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Processing {

    private String productType;
    private BigDecimal fee;

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
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
