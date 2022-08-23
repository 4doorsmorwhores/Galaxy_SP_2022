/**
 * class Position
 * @package Datas
 * @author Andrei Akhramchuk
 * @version 1.0;
 */
package Datas;

import java.text.DecimalFormat;

public class Position {

    /**
     * position on coordinate X
     */
    private double x;

    /**
     * position on coordinate Y
     */
    private double y;

    /**
     * contsructor without parameters for position, all positions set on 0
     * ( default constructor )
     */
    public Position() {
        this.x = 0;
        this.y = 0;
    }

    public Position(double x,double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * getr for position X
     * @return x
     */
    public double getX() {
        return x;
    }

    /**
     * setr for position X
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * getr for position Y
     * @return x
     */
    public double getY() {
        return y;
    }


    /**
     * setr for position Y
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * toString of position that made for output position in our status bar
     * @return data-string
     */
    @Override
    public String toString() {
        return "[" +
                "x=" + new DecimalFormat("#0.00").format(x) +
                ", y=" + new DecimalFormat("#0.00").format(y) +
                ']';
    }
}
