package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.apps.quantitymeasurement.LengthUnit;
import com.apps.quantitymeasurement.QuantityMeasurementApp.Quantity;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    public void testLengthUnitEnum_FeetConstant() {
        assertEquals(1.0, LengthUnit.FEET.getConversionFactor(), EPSILON);
    }

    @Test
    public void testLengthUnitEnum_InchesConstant() {
        assertEquals(1.0 / 12.0, LengthUnit.INCH.getConversionFactor(), EPSILON);
    }

    @Test
    public void testLengthUnitEnum_YardsConstant() {
        assertEquals(3.0, LengthUnit.YARD.getConversionFactor(), EPSILON);
    }

    @Test
    public void testLengthUnitEnum_CentimetersConstant() {
        assertEquals(1.0 / 30.48, LengthUnit.CENTIMETER.getConversionFactor(), EPSILON);
    }

    @Test
    public void testConvertToBaseUnit_InchesToFeet() {
        assertEquals(1.0, LengthUnit.INCH.convertToBaseUnit(12.0), EPSILON);
    }

    @Test
    public void testConvertFromBaseUnit_FeetToInches() {
        assertEquals(12.0, LengthUnit.INCH.convertFromBaseUnit(1.0), EPSILON);
    }

    @Test
    public void testQuantity_Equality() {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);
        assertTrue(q1.equals(q2));
    }

    @Test
    public void testQuantity_ConvertTo() {
        Quantity q = new Quantity(1.0, LengthUnit.FEET);
        assertEquals(12.0, q.convertTo(LengthUnit.INCH).value, EPSILON);
    }

    @Test
    public void testQuantity_Add() {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);
        assertEquals(2.0, q1.add(q2, LengthUnit.FEET).value, EPSILON);
    }

    @Test
    public void testQuantity_AddWithTargetUnit() {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);
        assertEquals(0.666666, q1.add(q2, LengthUnit.YARD).value, EPSILON);
    }

    @Test
    public void testQuantity_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity(1.0, null);
        });
    }

    @Test
    public void testQuantity_InvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity(Double.NaN, LengthUnit.FEET);
        });
    }
}