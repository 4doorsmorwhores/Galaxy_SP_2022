/**
 * class Acceleration
 * @package Datas
 * @author Andrei Akhramchuk
 * @version 1.0;
 */
package Datas;

/**
 * class for planet's acceleration ( acceleration was divided on 2 vectors ( x and y)).
 */
public class Acceleration {

    /**
     * acceleration ( vector x)
     */
    private double aX;

    /**
     * acceleration ( vector y)
     */
    private double aY;

    /**
     * contsructor without parameters for acceleration, all acceleration vectors set on 0
     * ( default constructor )
     */
    public Acceleration() {
        aX = 0;
        aY = 0;
    }

    /**
     * getr for aX
     * @return aX ( acceleration in vector X )
     */
    public double get_aX() {
        return aX;
    }

    /**
     * setr for aX
     * @param aX
     */
    public void set_aX(double aX) {
        this.aX = aX;
    }

    /**
     * getr for aY
     * @return Ay ( acceleration in vector Y )
     */
    public double get_aY() {
        return aY;
    }

    /**
     * setr for aY
     * @param aY
     */
    public void set_aY(double aY) {
        this.aY = aY;
    }

}
