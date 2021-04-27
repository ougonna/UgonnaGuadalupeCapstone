package com.company.UgonnaGuadalupeCapstone.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Tax {
private String _state;
private double _rate;

    public Tax(String _state, double _rate) {
        this._state = _state;
        this._rate = _rate;
    }
}
