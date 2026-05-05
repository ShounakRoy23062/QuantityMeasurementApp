public class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null || Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException();
        }
        this.value = value;
        this.unit = unit;
    }

    public Quantity<U> convertTo(U targetUnit) {
        double base = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(base);
        double rounded = Math.round(converted * 100.0) / 100.0;
        return new Quantity<>(rounded, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);
        double sumBase = base1 + base2;
        double result = targetUnit.convertFromBaseUnit(sumBase);
        double rounded = Math.round(result * 100.0) / 100.0;
        return new Quantity<>(rounded, targetUnit);
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
        double base = unit.convertToBaseUnit(value);
        return Double.hashCode(base);
    }

    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}