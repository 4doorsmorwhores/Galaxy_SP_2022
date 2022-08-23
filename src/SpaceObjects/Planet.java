/**
 * class Planet extends ObjectSpace
 * @package SpaceObjects
 * @author Andrei Akhramchuk
 * @version 1.0;
 */
package SpaceObjects;

import Datas.Position;
import Datas.Speed;
import Datas.Weight;
import Сalculation.CalculateRadius;

public class Planet extends ObjectSpace{

    /**
     * Planet's radius
     */
    private double radius;

    /**
     * constructor for Planet ( children of ObjectSpace )
     * @param name
     * @param type
     * @param position
     * @param speed
     * @param weight
     */
    public Planet(String name, String type, Position position, Speed speed, Weight weight) {
        super(name, type, position, speed, weight);
        this.radius = CalculateRadius.calculateRadius(this);
    }

    /**
     * getr for Planet's radius
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * setr for Planet's radius
     * @param radius
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

}
