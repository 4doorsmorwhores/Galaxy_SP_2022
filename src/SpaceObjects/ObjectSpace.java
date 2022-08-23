/**
 * class SpaceObjects - parent class for all space objects
 * @package SpaceObjects
 * @author Andrei Akhramchuk
 * @version 1.0;
 */
package SpaceObjects;

import Datas.Position;
import Datas.Speed;
import Datas.Weight;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class ObjectSpace {

    /**
     * name of space object
     */
    protected String name;

    /**
     * type of space object ( Comet, planet, Rocket )
     */
    protected String type;

    /**
     * position of space object
     */
    protected Position position;

    /**
     * speed of space object
     */
    protected Speed speed;

    /**
     * weight of space object
     */
    protected Weight weight;

    /**
     * variable indicating whether to display planet data in status bar
     */
    protected boolean isHit;

    /**
     * HashMap that contains info about speed in last 30 second of real time
     */
    protected HashMap<Integer,Double> last30secSpeed = new HashMap<>();

    /**
     * Queue that contains info about planet positions in last 1 second (about 62 positions)
     */
    protected Queue<Position> last1secPositions = new LinkedList<>();

    /**
     * constructor for space Object
     * @param name
     * @param type
     * @param position
     * @param speed
     * @param weight
     * parent constructor for all space objects
     */
    public ObjectSpace(String name, String type, Position position, Speed speed, Weight weight) {
        this.name = name;
        this.type = type;
        this.position = position;
        this.speed = speed;
        this.weight = weight;
        this.isHit = false;

    }

    /**
     * getr for space object's name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * getr for space object's type ( Planet, Comet, Rocket )
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * getr for space object's position
     * @return position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * getr for space object's speed
     * @return
     */
    public Speed getSpeed() {
        return speed;
    }

    /**
     * setr for space object's speed in vector X
     * @param x ( speed in vector X )
     */
    public void setXspeed(double x) {
        this.speed.setX(x);
    }

    /**
     * setr for space object's speed in vector Y
     * @param y ( speed in vector Y )
     */
    public void setYspeed(double y) {
        this.speed.setY(y);
    }

    /**
     * getr for sace object's speed in vector X
     * @return speedX
     */
    public double getXspeed() {
        return this.speed.getX();
    }

    /**
     * getr for sace object's speed in vector Y
     * @return speedY
     */
    public double getYspeed() {
        return this.speed.getY();
    }

    /**
     * checker for object hit status
     * @return isHit ( true, false )
     */
    public boolean isHit() {
        return isHit;
    }

    /**
     * method for change object hit status
     * @return isHit ( true, false )
     */
    public void setHit(boolean hit) {
        isHit = hit;
    }

    /**
     * getr fo space object's weight
     * @return
     */
    public double getWeight() {
        return this.weight.getWeight();
    }

    /**
     * NOT USED, BUT WILL, IN THE 2d PART OF SEMESTRAL WORK
     * setr for space object's weight
     * @param weight
     */
    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    /**
     * getr for last 30 second speed
     * @return last30secSpeed
     */
    public HashMap<Integer,Double> getLast30secSpeed() {
        return last30secSpeed;
    }

    /**
     * getr for Queue of last 1 second planet positions
     * @return last1secPositions
     */
    public Queue<Position> getLast1secPositions() {
        return last1secPositions;
    }
}

