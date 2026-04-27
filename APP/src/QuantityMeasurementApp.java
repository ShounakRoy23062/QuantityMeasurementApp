package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Feet feet = (Feet) obj;
            return Double.compare(feet.value, value) == 0;
        }
    }

    public static class Inches {
        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Inches inches = (Inches) obj;
            return Double.compare(inches.value, value) == 0;
        }
    }

    public static boolean areFeetEqual(double v1, double v2) {
        return new Feet(v1).equals(new Feet(v2));
    }

    public static boolean areInchesEqual(double v1, double v2) {
        return new Inches(v1).equals(new Inches(v2));
    }

    public static void main(String[] args) {
        System.out.println(areFeetEqual(1.0, 1.0));
        System.out.println(areInchesEqual(1.0, 1.0));
    }
}