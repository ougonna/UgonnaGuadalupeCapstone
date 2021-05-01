package com.company.UgonnaGuadalupeCapstone.model;

import java.math.BigDecimal;
//Purchasing handler could handle different type of items
//using a unified interface.
public interface IItem {
    BigDecimal getPrice();
    int getQuantity();
    void setQuantity(int quantity);

}
