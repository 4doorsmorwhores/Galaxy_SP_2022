/**
 * class Comet extends ObjectSpace
 * @package SpaceObjects
 * @author Andrei Akhramchuk
 * @version 1.0;
 */
package SpaceObjects;

import Datas.Position;
import Datas.Speed;
import Datas.Weight;
import Сalculation.CalculateRadius;

public class Comet extends ObjectSpace{

    /**
     * comet's radius
     */
    private double radius;

    /**
     * constructor for Comet ( children of ObjectSpace )
     * @param name
     * @param type
     * @param position
     * @param speed
     * @param weight
     */
    public Comet(String name, String type, Position position, Speed speed, Weight weight) {
        super(name, type, position, speed, weight);
        this.radius = CalculateRadius.calculateRadius(this);
    }

    /**
     * getr for Comet's radius
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * setr for Comet's radius
     * @param radius
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

}
