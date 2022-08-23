/**
 * class Speed
 * @package Datas
 * @author Andrei Akhramchuk
 * @version 1.0;
 */
package Datas;

import java.text.DecimalFormat;

public class Speed {

    /**
     * speed in vector X
     */
    private double x;

    /**
     * speed in vector Y
     */
    private double y;

    /**
     * contsructor without parameters for speed, all speed in vectors set on 0
     * ( default constructor )
     */
    public Speed() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * getr for speed in vector X
     * @return x
     */
    public double getX() {
        return x;
    }

    /**
     * setr for spped in vector X
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * getr for speed in vector Y
     * @return y
     */
    public double getY() {
        return y;
    }

    /**
     * setr for spped in vector Y
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * toString of speed that made for output speed in our status bar
     * @return data-string
     */
    @Override
    public String toString() {
        return "[" +
                "Vx=" + new DecimalFormat("#0.000").format(x) +
                ", Vy=" + new DecimalFormat("#0.000").format(y) +
                ']';
    }
}
