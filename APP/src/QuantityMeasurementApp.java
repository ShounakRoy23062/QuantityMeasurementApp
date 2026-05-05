public class QuantityMeasurementApp {

    public static <U extends IMeasurable> void show(String msg, Object val) {
        System.out.println(msg + " : " + val);
    }

    public static void main(String[] args) {

        Quantity<LengthUnit> l1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(6.0, LengthUnit.INCHES);

        show("Length Add", l1.add(l2));
        show("Length Sub", l1.subtract(l2));
        show("Length Div", l1.divide(new Quantity<>(2.0, LengthUnit.FEET)));

        Quantity<WeightUnit> w1 = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(5000.0, WeightUnit.GRAM);

        show("Weight Add", w1.add(w2));
        show("Weight Sub", w1.subtract(w2));
        show("Weight Div", w1.divide(new Quantity<>(5.0, WeightUnit.KILOGRAM)));

        Quantity<VolumeUnit> v1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        show("Volume Add", v1.add(v2));
        show("Volume Sub", v1.subtract(v2));
        show("Volume Div", v1.divide(new Quantity<>(10.0, VolumeUnit.LITRE)));
    }
}