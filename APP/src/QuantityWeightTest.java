package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityWeightTest {

    private static final double EPSILON = 1e-6;

    @Test
    public void testEquality_KilogramToKilogram_SameValue() {
        assertTrue(new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .equals(new QuantityWeight(1.0, WeightUnit.KILOGRAM)));
    }

    @Test
    public void testEquality_KilogramToGram_EquivalentValue() {
        assertTrue(new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .equals(new QuantityWeight(1000.0, WeightUnit.GRAM)));
    }

    @Test
    public void testEquality_KilogramToPound_EquivalentValue() {
        assertTrue(new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .equals(new QuantityWeight(2.20462, WeightUnit.POUND)));
    }

    @Test
    public void testConversion_KilogramToGram() {
        QuantityWeight q = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        assertEquals(1000.0, q.convertTo(WeightUnit.GRAM).convertTo(WeightUnit.GRAM).value, EPSILON);
    }

    @Test
    public void testConversion_PoundToKilogram() {
        QuantityWeight q = new QuantityWeight(2.20462, WeightUnit.POUND);
        assertEquals(1.0, q.convertTo(WeightUnit.KILOGRAM).value, EPSILON);
    }

    @Test
    public void testAddition_SameUnit() {
        QuantityWeight q1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight q2 = new QuantityWeight(2.0, WeightUnit.KILOGRAM);
        assertEquals(3.0, q1.add(q2).value, EPSILON);
    }

    @Test
    public void testAddition_CrossUnit() {
        QuantityWeight q1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight q2 = new QuantityWeight(1000.0, WeightUnit.GRAM);
        assertEquals(2.0, q1.add(q2).value, EPSILON);
    }

    @Test
    public void testAddition_WithTargetUnit() {
        QuantityWeight q1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight q2 = new QuantityWeight(1000.0, WeightUnit.GRAM);
        assertEquals(2000.0, q1.add(q2, WeightUnit.GRAM).value, EPSILON);
    }

    @Test
    public void testNullHandling() {
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityWeight(1.0, null);
        });
    }

    @Test
    public void testNegativeValues() {
        QuantityWeight q1 = new QuantityWeight(5.0, WeightUnit.KILOGRAM);
        QuantityWeight q2 = new QuantityWeight(-2000.0, WeightUnit.GRAM);
        assertEquals(3.0, q1.add(q2).value, EPSILON);
    }
}