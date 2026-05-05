public class QuantityMeasurementApp {

    public static <U extends IMeasurable> void demonstrateEquality(Quantity<U> q1, Quantity<U> q2) {
        System.out.println(q1.equals(q2));
    }

    public static <U extends IMeasurable> void demonstrateConversion(Quantity<U> q, U target) {
        System.out.println(q.convertTo(target));
    }

    public static <U extends IMeasurable> void demonstrateAddition(Quantity<U> q1, Quantity<U> q2, U target) {
        System.out.println(q1.add(q2, target));
    }

    public static <U extends IMeasurable> void demonstrateSubtraction(Quantity<U> q1, Quantity<U> q2, U target) {
        System.out.println(q1.subtract(q2, target));
    }

    public static <U extends IMeasurable> void demonstrateDivision(Quantity<U> q1, Quantity<U> q2) {
        System.out.println(q1.divide(q2));
    }

    public static void main(String[] args) {

        Quantity<LengthUnit> l1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(6.0, LengthUnit.INCHES);

        demonstrateSubtraction(l1, l2, LengthUnit.FEET);
        demonstrateDivision(l1, new Quantity<>(2.0, LengthUnit.FEET));

        Quantity<WeightUnit> w1 = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(5000.0, WeightUnit.GRAM);

        demonstrateSubtraction(w1, w2, WeightUnit.KILOGRAM);
        demonstrateDivision(w1, new Quantity<>(5.0, WeightUnit.KILOGRAM));

        Quantity<VolumeUnit> v1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        demonstrateSubtraction(v1, v2, VolumeUnit.LITRE);
        demonstrateDivision(v1, new Quantity<>(10.0, VolumeUnit.LITRE));
    }
}