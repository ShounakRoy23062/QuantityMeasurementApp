package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.apps.quantitymeasurement.QuantityMeasurementApp.Quantity;
import com.apps.quantitymeasurement.QuantityMeasurementApp.LengthUnit;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    public void testAddition_ExplicitTargetUnit_Feet() {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);
        assertEquals(2.0, q1.add(q2, LengthUnit.FEET).value, EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Inches() {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);
        assertEquals(24.0, q1.add(q2, LengthUnit.INCH).value, EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Yards() {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);
        assertEquals(0.666666, q1.add(q2, LengthUnit.YARD).value, EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Centimeters() {
        Quantity q1 = new Quantity(1.0, LengthUnit.INCH);
        Quantity q2 = new Quantity(1.0, LengthUnit.INCH);
        assertEquals(5.08, q1.add(q2, LengthUnit.CENTIMETER).value, EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {
        Quantity q1 = new Quantity(2.0, LengthUnit.YARD);
        Quantity q2 = new Quantity(3.0, LengthUnit.FEET);
        assertEquals(3.0, q1.add(q2, LengthUnit.YARD).value, EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {
        Quantity q1 = new Quantity(2.0, LengthUnit.YARD);
        Quantity q2 = new Quantity(3.0, LengthUnit.FEET);
        assertEquals(9.0, q1.add(q2, LengthUnit.FEET).value, EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Commutativity() {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);
        double r1 = q1.add(q2, LengthUnit.YARD).value;
        double r2 = q2.add(q1, LengthUnit.YARD).value;
        assertEquals(r1, r2, EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_WithZero() {
        Quantity q1 = new Quantity(5.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(0.0, LengthUnit.INCH);
        assertEquals(1.666666, q1.add(q2, LengthUnit.YARD).value, EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_NegativeValues() {
        Quantity q1 = new Quantity(5.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(-2.0, LengthUnit.FEET);
        assertEquals(36.0, q1.add(q2, LengthUnit.INCH).value, EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_NullTargetUnit() {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);
        assertThrows(IllegalArgumentException.class, () -> {
            q1.add(q2, null);
        });
    }

    @Test
    public void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
        Quantity q1 = new Quantity(1000.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(500.0, LengthUnit.FEET);
        assertEquals(18000.0, q1.add(q2, LengthUnit.INCH).value, EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SmallToLargeScale() {
        Quantity q1 = new Quantity(12.0, LengthUnit.INCH);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);
        assertEquals(0.666666, q1.add(q2, LengthUnit.YARD).value, EPSILON);
    }
}