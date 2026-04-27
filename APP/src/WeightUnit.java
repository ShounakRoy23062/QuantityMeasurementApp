package com.apps.quantitymeasurement;

public enum WeightUnit {
    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double toKilogram;

    WeightUnit(double toKilogram) {
        this.toKilogram = toKilogram;
    }

    public double convertToBaseUnit(double value) {
        return value * toKilogram;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / toKilogram;
    }

    public double getConversionFactor() {
        return toKilogram;
    }
}