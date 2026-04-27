package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.apps.quantitymeasurement.QuantityMeasurementApp.LengthUnit;
import com.apps.quantitymeasurement.QuantityMeasurementApp.Quantity;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    public void testAddition_SameUnit_FeetPlusFeet() {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(2.0, LengthUnit.FEET);
        assertEquals(3.0, q1.add(q2).convertTo(LengthUnit.FEET).value, EPSILON);
    }

    @Test
    public void testAddition_SameUnit_InchPlusInch() {
        Quantity q1 = new Quantity(6.0, LengthUnit.INCH);
        Quantity q2 = new Quantity(6.0, LengthUnit.INCH);
        assertEquals(12.0, q1.add(q2).convertTo(LengthUnit.INCH).value, EPSILON);
    }

    @Test
    public void testAddition_CrossUnit_FeetPlusInches() {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);
        assertEquals(2.0, q1.add(q2).value, EPSILON);
    }

    @Test
    public void testAddition_CrossUnit_InchPlusFeet() {
        Quantity q1 = new Quantity(12.0, LengthUnit.INCH);
        Quantity q2 = new Quantity(1.0, LengthUnit.FEET);
        assertEquals(24.0, q1.add(q2).value, EPSILON);
    }

    @Test
    public void testAddition_CrossUnit_YardPlusFeet() {
        Quantity q1 = new Quantity(1.0, LengthUnit.YARD);
        Quantity q2 = new Quantity(3.0, LengthUnit.FEET);
        assertEquals(2.0, q1.add(q2).value, EPSILON);
    }

    @Test
    public void testAddition_CrossUnit_CentimeterPlusInch() {
        Quantity q1 = new Quantity(2.54, LengthUnit.CENTIMETER);
        Quantity q2 = new Quantity(1.0, LengthUnit.INCH);
        assertEquals(5.08, q1.add(q2).value, EPSILON);
    }

    @Test
    public void testAddition_Commutativity() {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);
        double r1 = q1.add(q2).convertTo(LengthUnit.FEET).value;
        double r2 = q2.add(q1).convertTo(LengthUnit.FEET).value;
        assertEquals(r1, r2, EPSILON);
    }

    @Test
    public void testAddition_WithZero() {
        Quantity q1 = new Quantity(5.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(0.0, LengthUnit.INCH);
        assertEquals(5.0, q1.add(q2).value, EPSILON);
    }

    @Test
    public void testAddition_NegativeValues() {
        Quantity q1 = new Quantity(5.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(-2.0, LengthUnit.FEET);
        assertEquals(3.0, q1.add(q2).value, EPSILON);
    }

    @Test
    public void testAddition_NullSecondOperand() {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> {
            q1.add(null);
        });
    }

    @Test
    public void testAddition_LargeValues() {
        Quantity q1 = new Quantity(1e6, LengthUnit.FEET);
        Quantity q2 = new Quantity(1e6, LengthUnit.FEET);
        assertEquals(2e6, q1.add(q2).value, EPSILON);
    }

    @Test
    public void testAddition_SmallValues() {
        Quantity q1 = new Quantity(0.001, LengthUnit.FEET);
        Quantity q2 = new Quantity(0.002, LengthUnit.FEET);
        assertEquals(0.003, q1.add(q2).value, EPSILON);
    }
}