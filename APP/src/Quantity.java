public class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;

    private enum ArithmeticOperation {
        ADD {
            public double compute(double a, double b) { return a + b; }
        },
        SUBTRACT {
            public double compute(double a, double b) { return a - b; }
        },
        DIVIDE {
            public double compute(double a, double b) {
                if (b == 0.0) throw new ArithmeticException();
                return a / b;
            }
        };

        public abstract double compute(double a, double b);
    }

    public Quantity(double value, U unit) {
        if (unit == null || Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException();
        }
        this.value = value;
        this.unit = unit;
    }

    private void validate(Quantity<U> other, U targetUnit, boolean needTarget) {
        if (other == null) throw new IllegalArgumentException();
        if (!this.unit.getClass().equals(other.unit.getClass())) throw new IllegalArgumentException();
        if (Double.isNaN(other.value) || Double.isInfinite(other.value)) throw new IllegalArgumentException();
        if (needTarget && targetUnit == null) throw new IllegalArgumentException();
    }

    private double performBase(Quantity<U> other, ArithmeticOperation op) {
        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);
        return op.compute(base1, base2);
    }

    private double round(double v) {
        return Math.round(v * 100.0) / 100.0;
    }

    public Quantity<U> convertTo(U targetUnit) {
        double base = unit.convertToBaseUnit(value);
        double result = targetUnit.convertFromBaseUnit(base);
        return new Quantity<>(round(result), targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validate(other, targetUnit, true);
        double base = performBase(other, ArithmeticOperation.ADD);
        double result = targetUnit.convertFromBaseUnit(base);
        return new Quantity<>(round(result), targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validate(other, targetUnit, true);
        double base = performBase(other, ArithmeticOperation.SUBTRACT);
        double result = targetUnit.convertFromBaseUnit(base);
        return new Quantity<>(round(result), targetUnit);
    }

    public double divide(Quantity<U> other) {
        validate(other, null, false);
        return performBase(other, ArithmeticOperation.DIVIDE);
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity<?>)) return false;

        Quantity<?> other = (Quantity<?>) obj;

        if (!this.unit.getClass().equals(other.unit.getClass())) return false;

        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = ((IMeasurable) other.unit).convertToBaseUnit(other.value);

        return Double.compare(base1, base2) == 0;
    }

    public int hashCode() {
        return Double.hashCode(unit.convertToBaseUnit(value));
    }

    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}