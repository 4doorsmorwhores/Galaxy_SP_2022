/**
 * class CalculateRadius
 * @package Calculation
 * @author Andrei Akhramchuk
 * @version 1.0;
 */
package Сalculation;

import SpaceObjects.Comet;
import SpaceObjects.Planet;

public class CalculateRadius {

    /**
     * method for calculating radius of our planet
     * @param planet ( for which calculate radius )
     * @return radius
     */
    public static double calculateRadius(Planet planet)
    {
        double mas = planet.getWeight();
        if (mas <= 0 ) mas = 1;
        double radius = Math.pow( ( (3* mas ) / (4*Math.PI) ), ( 1.0/3.0 ) );
        return radius;
    }

    /**
     * method for calculating radius of our comet
     * @param comet ( for which calculate radius )
     * @return radius
     */
    public static double calculateRadius(Comet comet)
    {
        double mas = comet.getWeight();
        if (mas <= 0 ) mas = 1;
        double radius = Math.pow( ( (3* mas ) / (4*Math.PI) ), ( 1.0/3.0 ) );
        return radius;
    }

}
