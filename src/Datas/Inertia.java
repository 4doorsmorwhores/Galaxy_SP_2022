/**
 * class Inertia
 * @package Datas
 * @author Andrei Akhramchuk
 * @version 2.0;
 */
package Datas;

public class Inertia {

    /**
     * inertia in vector X
     */
    private double x;

    /**
     * inertia in vector Y
     */
    private double y;

    /**
     * Constructor thar create inertia
     * @param x
     * @param y
     */
    public Inertia(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * A method that summarizes two inertia vectors on the same axis
     * @param x1
     * @param x2
     * @return 2 inertia amount
     */
    public static double inertionalSum(double x1, double x2) {
        return x1+x2;
    }

    /**
     * getr for Inertia in vector X
     * @return x
     */
    public double getX() {
        return x;
    }

    /**
     * getr for Inertia in vector Y
     * @return y
     */
    public double getY() {
        return y;
    }

}
