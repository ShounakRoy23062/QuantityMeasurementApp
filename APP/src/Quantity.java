public class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        this.value = value;
        this.unit = unit;
    }
}