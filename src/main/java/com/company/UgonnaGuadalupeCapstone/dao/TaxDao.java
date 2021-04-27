package com.company.UgonnaGuadalupeCapstone.dao;

public interface TaxDao {

    double getTax(String state);
    void setTax(String state, double rate);
}
