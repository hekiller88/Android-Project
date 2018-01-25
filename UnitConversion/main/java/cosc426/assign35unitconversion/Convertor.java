package cosc426.assign35unitconversion;

/**
 * Created by lhe on 11/11/17.
 */

public class Convertor {

    public static String mile2kilometer(float n)
    {
        return String.format("%.2f", n * 1.60934);
    }

    public static String kilometer2mile(float n)
    {
        return String.format("%.2f", n * 0.621371);
    }

    public static String feet2meter(float n)
    {
        return String.format("%.2f", n * 0.3048);
    }

    public static String meter2feet(float n)
    {
        return String.format("%.2f", n * 3.28084);
    }

    public static String inch2centimeter(float n)
    {
        return String.format("%.2f", n * 2.54);
    }

    public static String centimeter2inch(float n)
    {
        return String.format("%.2f", n * 0.393701);
    }
}
