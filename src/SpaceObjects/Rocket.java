/**
 * class Rocket extends ObjectSpace
 * @package SpaceObjects
 * @author Andrei Akhramchuk
 * @version 1.0;
 */
package SpaceObjects;

import Datas.Position;
import Datas.Speed;
import Datas.Weight;

public class Rocket extends ObjectSpace{

    /**
     * constructor for Rocket ( children of ObjectSpace )
     * @param name
     * @param type
     * @param position
     * @param speed
     * @param weight
     */
    public Rocket(String name, String type, Position position, Speed speed, Weight weight) {
        super(name, type, position, speed, weight);
    }

}
