package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.apps.quantitymeasurement.QuantityMeasurementApp.LengthUnit;
import com.apps.quantitymeasurement.QuantityMeasurementApp.Quantity;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    public void testConversion_FeetToInches() {
        assertEquals(12.0, QuantityMeasurementApp.convert(1.0, LengthUnit.FEET, LengthUnit.INCH), EPSILON);
    }

    @Test
    public void testConversion_InchesToFeet() {
        assertEquals(2.0, QuantityMeasurementApp.convert(24.0, LengthUnit.INCH, LengthUnit.FEET), EPSILON);
    }

    @Test
    public void testConversion_YardsToInches() {
        assertEquals(36.0, QuantityMeasurementApp.convert(1.0, LengthUnit.YARD, LengthUnit.INCH), EPSILON);
    }

    @Test
    public void testConversion_InchesToYards() {
        assertEquals(2.0, QuantityMeasurementApp.convert(72.0, LengthUnit.INCH, LengthUnit.YARD), EPSILON);
    }

    @Test
    public void testConversion_CentimetersToInches() {
        assertEquals(1.0, QuantityMeasurementApp.convert(2.54, LengthUnit.CENTIMETER, LengthUnit.INCH), EPSILON);
    }

    @Test
    public void testConversion_FeetToYard() {
        assertEquals(2.0, QuantityMeasurementApp.convert(6.0, LengthUnit.FEET, LengthUnit.YARD), EPSILON);
    }

    @Test
    public void testConversion_RoundTrip_PreservesValue() {
        double value = 5.0;
        double result = QuantityMeasurementApp.convert(
                QuantityMeasurementApp.convert(value, LengthUnit.FEET, LengthUnit.INCH),
                LengthUnit.INCH,
                LengthUnit.FEET
        );
        assertEquals(value, result, EPSILON);
    }

    @Test
    public void testConversion_ZeroValue() {
        assertEquals(0.0, QuantityMeasurementApp.convert(0.0, LengthUnit.FEET, LengthUnit.INCH), EPSILON);
    }

    @Test
    public void testConversion_NegativeValue() {
        assertEquals(-12.0, QuantityMeasurementApp.convert(-1.0, LengthUnit.FEET, LengthUnit.INCH), EPSILON);
    }

    @Test
    public void testConversion_InvalidUnit_Throws() {
        assertThrows(IllegalArgumentException.class, () -> {
            QuantityMeasurementApp.convert(1.0, null, LengthUnit.FEET);
        });
    }

    @Test
    public void testConversion_NaNOrInfinite_Throws() {
        assertThrows(IllegalArgumentException.class, () -> {
            QuantityMeasurementApp.convert(Double.NaN, LengthUnit.FEET, LengthUnit.INCH);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            QuantityMeasurementApp.convert(Double.POSITIVE_INFINITY, LengthUnit.FEET, LengthUnit.INCH);
        });
    }

    @Test
    public void testConversion_PrecisionTolerance() {
        double result = QuantityMeasurementApp.convert(1.0, LengthUnit.CENTIMETER, LengthUnit.INCH);
        assertEquals(0.393701, result, EPSILON);
    }

    @Test
    public void testInstanceConversion() {
        Quantity q = new Quantity(1.0, LengthUnit.FEET);
        Quantity converted = q.convertTo(LengthUnit.INCH);
        assertEquals(12.0, converted.convertTo(LengthUnit.INCH).value, EPSILON);
    }
}