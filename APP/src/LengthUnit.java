package com.apps.quantitymeasurement;

public enum LengthUnit {
    FEET(1.0),
    INCH(1.0 / 12.0),
    YARD(3.0),
    CENTIMETER(1.0 / 30.48);

    private final double toFeet;

    LengthUnit(double toFeet) {
        this.toFeet = toFeet;
    }

    public double convertToBaseUnit(double value) {
        return value * toFeet;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / toFeet;
    }

    public double getConversionFactor() {
        return toFeet;
    }
}