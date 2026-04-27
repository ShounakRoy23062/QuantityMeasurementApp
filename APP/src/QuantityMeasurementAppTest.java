package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.apps.quantitymeasurement.QuantityMeasurementApp.Quantity;
import com.apps.quantitymeasurement.QuantityMeasurementApp.LengthUnit;

public class QuantityMeasurementAppTest {

    @Test
    public void testEquality_YardToYard_SameValue() {
        Quantity q1 = new Quantity(1.0, LengthUnit.YARD);
        Quantity q2 = new Quantity(1.0, LengthUnit.YARD);
        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_YardToYard_DifferentValue() {
        Quantity q1 = new Quantity(1.0, LengthUnit.YARD);
        Quantity q2 = new Quantity(2.0, LengthUnit.YARD);
        assertFalse(q1.equals(q2));
    }

    @Test
    public void testEquality_YardToFeet_EquivalentValue() {
        Quantity q1 = new Quantity(1.0, LengthUnit.YARD);
        Quantity q2 = new Quantity(3.0, LengthUnit.FEET);
        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_FeetToYard_EquivalentValue() {
        Quantity q1 = new Quantity(3.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(1.0, LengthUnit.YARD);
        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_YardToInches_EquivalentValue() {
        Quantity q1 = new Quantity(1.0, LengthUnit.YARD);
        Quantity q2 = new Quantity(36.0, LengthUnit.INCH);
        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_InchesToYard_EquivalentValue() {
        Quantity q1 = new Quantity(36.0, LengthUnit.INCH);
        Quantity q2 = new Quantity(1.0, LengthUnit.YARD);
        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_YardToFeet_NonEquivalentValue() {
        Quantity q1 = new Quantity(1.0, LengthUnit.YARD);
        Quantity q2 = new Quantity(2.0, LengthUnit.FEET);
        assertFalse(q1.equals(q2));
    }

    @Test
    public void testEquality_CentimetersToInches_EquivalentValue() {
        Quantity q1 = new Quantity(1.0, LengthUnit.CENTIMETER);
        Quantity q2 = new Quantity(0.393701, LengthUnit.INCH);
        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_CentimetersToFeet_NonEquivalentValue() {
        Quantity q1 = new Quantity(1.0, LengthUnit.CENTIMETER);
        Quantity q2 = new Quantity(1.0, LengthUnit.FEET);
        assertFalse(q1.equals(q2));
    }

    @Test
    public void testEquality_MultiUnit_TransitiveProperty() {
        Quantity q1 = new Quantity(1.0, LengthUnit.YARD);
        Quantity q2 = new Quantity(3.0, LengthUnit.FEET);
        Quantity q3 = new Quantity(36.0, LengthUnit.INCH);
        assertTrue(q1.equals(q2));
        assertTrue(q2.equals(q3));
        assertTrue(q1.equals(q3));
    }

    @Test
    public void testEquality_NullComparison() {
        Quantity q1 = new Quantity(1.0, LengthUnit.YARD);
        assertFalse(q1.equals(null));
    }

    @Test
    public void testEquality_SameReference() {
        Quantity q1 = new Quantity(1.0, LengthUnit.YARD);
        assertTrue(q1.equals(q1));
    }

    @Test
    public void testEquality_InvalidUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity(1.0, null);
        });
    }
}