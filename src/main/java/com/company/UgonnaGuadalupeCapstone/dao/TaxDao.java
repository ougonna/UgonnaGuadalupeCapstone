package com.company.UgonnaGuadalupeCapstone.dao;

import java.math.BigDecimal;

public interface TaxDao {

    BigDecimal getTax(String state);
    void setTax(String state, double rate);
}
