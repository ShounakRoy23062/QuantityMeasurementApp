package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

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
            double base = unit.convertToBaseUnit(value);
            double converted = targetUnit.convertFromBaseUnit(base);
            return new Quantity(converted, targetUnit);
        }

        private double toBase() {
            return unit.convertToBaseUnit(value);
        }

        private static Quantity addInternal(Quantity q1, Quantity q2, LengthUnit targetUnit) {
            double base1 = q1.toBase();
            double base2 = q2.toBase();
            double sumBase = base1 + base2;
            double result = targetUnit.convertFromBaseUnit(sumBase);
            return new Quantity(result, targetUnit);
        }

        public Quantity add(Quantity other) {
            if (other == null) throw new IllegalArgumentException();
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
        double base = source.convertToBaseUnit(value);
        return target.convertFromBaseUnit(base);
    }

    public static Quantity add(Quantity q1, Quantity q2, LengthUnit targetUnit) {
        if (q1 == null || q2 == null || targetUnit == null) throw new IllegalArgumentException();
        return q1.add(q2, targetUnit);
    }

    public static Quantity add(double v1, LengthUnit u1, double v2, LengthUnit u2, LengthUnit target) {
        if (u1 == null || u2 == null || target == null || !Double.isFinite(v1) || !Double.isFinite(v2))
            throw new IllegalArgumentException();
        double base1 = u1.convertToBaseUnit(v1);
        double base2 = u2.convertToBaseUnit(v2);
        double sumBase = base1 + base2;
        double result = target.convertFromBaseUnit(sumBase);
        return new Quantity(result, target);
    }

    public static void main(String[] args) {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);

        System.out.println(q1.convertTo(LengthUnit.INCH));
        System.out.println(q1.add(q2, LengthUnit.FEET));
        System.out.println(q2.equals(new Quantity(1.0, LengthUnit.YARD)));
    }
}