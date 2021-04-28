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

    public String get_state() {
        return _state;
    }

    public void set_state(String _state) {
        this._state = _state;
    }

    public double get_rate() {
        return _rate;
    }

    public void set_rate(double _rate) {
        this._rate = _rate;
    }

    public Tax() {
    }
}
