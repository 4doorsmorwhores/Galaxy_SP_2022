/**
 * class MyData
 * @package Calculation
 * @author Andrei Akhramchuk
 * @version 1.0;
 */
package Сalculation;

import Datas.*;
import SpaceObjects.*;

import java.awt.geom.Ellipse2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MyData {

    //AKA CONSTANTS
    /**
     * simulation time tick from .csv
     */
    public static double simulationTimeTick;

    /**
     * gravity const from .csv
     */
    public static double gravityConst;

    //DATA ARRAYS
    /**
     * ArrayList with planet's acceleration
     */
    public static ArrayList<Acceleration> planetAccelerationList = new ArrayList<>();

    /**
     * ArrayList with rocket's acceleration
     */
    public static ArrayList<Acceleration> rocketAccelerationList = new ArrayList<>();

    /**
     * ArrayList with planets
     */
    public static ArrayList<Planet> planetList = new ArrayList<>();

    /**
     * ArrayList with rockets
     */
    public static ArrayList<Rocket> rocketList = new ArrayList<>();


    /**
     * Array of ellipses for checking
     */
    public static Ellipse2D[] ellipseArray;

    /**
     * max coordinate of X in simulated world ( our right border of world)
     */
    public static double maxCoordX ;

    /**
     * min coordinate of X in simulated world ( our left border of world)
     */
    public static double minCoordX ;

    /**
     * max coordinate of Y in simulated world ( our up border of world)
     */
    public static double maxCoordY ;

    /**
     * min coordinate of Y in simulated world ( our down border of world)
     */
    public static double minCoordY ;

    /**
     * world length ( x )
     */
    public static double worldLength ;

    /**
     * world height ( y )
     */
    public static double worldHeight ;

    /**
     * center of world in x position
     */
    public static double centerWorldX ;

    /**
     * center of world in y position
     */
    public static double centerWorldY ;

    /**
     * scale of world
     */
    public static double scale;

    // datas calculation

    /**
     * This method will produce datas, will take array of string, after this will delete all spaces,
     * will initialise constants like time tick, gravity const, also will initialise planetList, and rocketList
     * ( will produce data, for new Planet()... and new Rocket()... )
     * @param path ( path way to our .csv )
     * @throws IOException
     */
    public static void produceData(String path) throws IOException {
        String[] data = Reader.OpenAndRead(path);

        ArrayList<String> dataList = stringArrayToList(data);


        // constant calculation
        String constantsString = dataList.get(0);
        gravityConst = Double.parseDouble(constantsString.substring(0,constantsString.indexOf(",")));
        simulationTimeTick = Double.parseDouble(constantsString.substring(constantsString.indexOf(",")+1,constantsString.length()));

        for (int i = 1; i < dataList.size(); i++) {

            String currentString = dataList.get(i);

                String name;
                String type;
                Position pos = new Position();
                Speed speed = new Speed();
                Weight weight = new Weight();

                name = currentString.substring(0, currentString.indexOf(","));
                currentString = currentString.substring(currentString.indexOf(",") + 1, currentString.length());

                type = currentString.substring(0, currentString.indexOf(","));
                currentString = currentString.substring(currentString.indexOf(",") + 1, currentString.length());

                pos.setX(Double.valueOf(currentString.substring(0, currentString.indexOf(","))));
                currentString = currentString.substring(currentString.indexOf(",") + 1, currentString.length());

                pos.setY(Double.valueOf(currentString.substring(0, currentString.indexOf(","))));
                currentString = currentString.substring(currentString.indexOf(",") + 1, currentString.length());

                speed.setX(Double.valueOf(currentString.substring(0, currentString.indexOf(","))));
                currentString = currentString.substring(currentString.indexOf(",") + 1, currentString.length());

                speed.setY(Double.valueOf(currentString.substring(0, currentString.indexOf(","))));
                currentString = currentString.substring(currentString.indexOf(",") + 1, currentString.length());

                weight.setWeight(Double.valueOf(currentString.substring(0, currentString.length())));


                if (type.equals("Planet")) {

                    planetList.add(new Planet(name,type,pos,speed,weight));
                    planetAccelerationList.add(new Acceleration());
                }

                if (type.equals("Rocket")) {

                    rocketList.add(new Rocket(name,type,pos,speed,weight));
                    rocketAccelerationList.add(new Acceleration());
                }

                if (type.equals("Comet")) {

                    planetList.add(new Planet(name,type,pos,speed,weight));
                    planetAccelerationList.add(new Acceleration());
                }
        }

    }

    /**
     * converter for convert from Array[] to ArrayList
     * @param data - Array[] of String
     * @return outList - final data's ArrayList
     */
    public static ArrayList<String> stringArrayToList(String[] data) {
        ArrayList<String> outList = new ArrayList<>();

        for (int i = 0; i < data.length; i++) {
            outList.add(data[i]);
        }

        outList.removeAll(Collections.singleton(null));
        outList.removeAll(Collections.singleton(""));

        return outList;
    }


}
