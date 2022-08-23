/**
 * class PlanetHit
 * @package Calculation
 * @author Andrei Akhramchuk
 * @version 1.0;
 */
package Сalculation;

import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import static Main.Galaxy_SP2022.panel;
import static Main.DrawingPanel.*;
import static Сalculation.MyData.*;

public class PlanetHit {

    /**
     * Method that will check if you planet was hit, if yes - return true, if no - return false.
     * @param e ( space object that should be checked)
     * @param x ( coordinate x, that was clicked by mouse)
     * @param y ( coordinate y, that was clicked by mouse)
     * @return true?false
     */
    public static boolean isPlanetHit(Ellipse2D e, double x, double y )
    {
        Area planet = new Area(e);

        double translateX = panel.getWidth()/ 2.0 - (centerWorldX*scale);
        double translateY = panel.getHeight() / 2.0 - (centerWorldY*scale);

        return (planet != null &&
                planet.contains((x - translateX  )/scale, (y - translateY)/scale  ) );
    }

}
