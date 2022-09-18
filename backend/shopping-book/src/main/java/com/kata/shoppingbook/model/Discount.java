package com.kata.shoppingbook.model;

public class Discount {
    private double displayValue;
    private double value;
    public Discount(double displayValue, double value) {
        this.displayValue = displayValue;
        this.value = value;
    }

    public double getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(double displayValue) {
        this.displayValue = displayValue;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
