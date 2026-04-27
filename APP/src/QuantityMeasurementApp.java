package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(0.0328084);

        private final double toFeet;

        LengthUnit(double toFeet) {
            this.toFeet = toFeet;
        }

        public double toBase(double value) {
            return value * toFeet;
        }

        public double fromBase(double baseValue) {
            return baseValue / toFeet;
        }
    }

    public static class Quantity {
        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            if (unit == null || !Double.isFinite(value)) throw new IllegalArgumentException();
            this.value = value;
            this.unit = unit;
        }

        public Quantity convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) throw new IllegalArgumentException();
            double base = unit.toBase(value);
            double converted = targetUnit.fromBase(base);
            return new Quantity(converted, targetUnit);
        }

        private double toBase() {
            return unit.toBase(value);
        }

        private static Quantity addInternal(Quantity q1, Quantity q2, LengthUnit targetUnit) {
            double base1 = q1.toBase();
            double base2 = q2.toBase();
            double sumBase = base1 + base2;
            double result = targetUnit.fromBase(sumBase);
            return new Quantity(result, targetUnit);
        }

        public Quantity add(Quantity other) {
            if (other == null || other.unit == null) throw new IllegalArgumentException();
            return addInternal(this, other, this.unit);
        }

        public Quantity add(Quantity other, LengthUnit targetUnit) {
            if (other == null || targetUnit == null) throw new IllegalArgumentException();
            return addInternal(this, other, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Quantity other = (Quantity) obj;
            return Double.compare(this.toBase(), other.toBase()) == 0;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    public static double convert(double value, LengthUnit source, LengthUnit target) {
        if (source == null || target == null || !Double.isFinite(value)) throw new IllegalArgumentException();
        double base = source.toBase(value);
        return target.fromBase(base);
    }

    public static Quantity add(Quantity q1, Quantity q2, LengthUnit targetUnit) {
        if (q1 == null || q2 == null || targetUnit == null) throw new IllegalArgumentException();
        return q1.add(q2, targetUnit);
    }

    public static Quantity add(double v1, LengthUnit u1, double v2, LengthUnit u2, LengthUnit target) {
        if (u1 == null || u2 == null || target == null || !Double.isFinite(v1) || !Double.isFinite(v2))
            throw new IllegalArgumentException();
        double base1 = u1.toBase(v1);
        double base2 = u2.toBase(v2);
        double sumBase = base1 + base2;
        double result = target.fromBase(sumBase);
        return new Quantity(result, target);
    }

    public static void main(String[] args) {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);

        System.out.println(q1.add(q2, LengthUnit.FEET));
        System.out.println(q1.add(q2, LengthUnit.INCH));
        System.out.println(q1.add(q2, LengthUnit.YARD));
    }
}