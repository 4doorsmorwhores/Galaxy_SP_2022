/**
 * class CalculatePlanets
 * @package Calculation
 * @author Andrei Akhramchuk
 * @version 1.0;
 */
package Сalculation;

import Datas.Acceleration;
import Datas.Inertia;
import Datas.Position;
import Datas.Weight;
import Main.Galaxy_SP2022;
import SpaceObjects.Planet;

import javax.swing.*;

import static Main.Galaxy_SP2022.lasHitedPlanet;

import static java.lang.Double.MAX_VALUE;
import static java.lang.Double.MIN_VALUE;
import static Сalculation.MyData.*;
import static Сalculation.MyData.planetList;

public class CalculatePlanets {

    /**
     * update system method that will update our position and speed of space object,
     * dt_min will count with simulationTimeTick / (10k/ size of planetList Array) that made for optimize program
     * caunting of speed was divided into 2 parts also because of optimizing program
     * @param t ( time since the last update of the system )
     */
    public static void updateSystem(double t) {

        /**
         * our step for optimize system
         */
        double dt_min = MyData.simulationTimeTick / (10000.0/planetList.size());

        while ( t > 0 ) {

            double dt = Math.min( t, dt_min);

            /**
             * call method computePlanetAcceleration for calc. planet's acceleration
             */
            for (int i = 0; i < planetAccelerationList.size(); i++) {
                Acceleration a = computePlanetAccelaration(i);
                planetAccelerationList.set(i, a);
            }

            for (int i = 0; i < planetList.size(); i++) {
                planetList.get(i).setXspeed(planetList.get(i).getXspeed() + ( ( 0.5 * dt ) * planetAccelerationList.get(i).get_aX() ) );
                planetList.get(i).setYspeed(planetList.get(i).getYspeed() + ( ( 0.5 * dt ) * planetAccelerationList.get(i).get_aY() ) );

                planetList.get(i).getPosition().setX(planetList.get(i).getPosition().getX() +  ( dt * planetList.get(i).getXspeed() ) );
                planetList.get(i).getPosition().setY(planetList.get(i).getPosition().getY() +  ( dt * planetList.get(i).getYspeed() ) );

                planetList.get(i).setXspeed(planetList.get(i).getXspeed() + ( ( 0.5 * dt ) * planetAccelerationList.get(i).get_aX() ) );
                planetList.get(i).setYspeed(planetList.get(i).getYspeed() + ( ( 0.5 * dt ) * planetAccelerationList.get(i).get_aY() ) );

            }
            t -= dt;
        }
        /**
         * calling method calculateWorldSize for scale our project
         */
        checker();
        calculateWorldSize();

    }

    /**
     * Method that will compute planet's acceleration.
     * @param i ( the planet for which the acceleration is considered
     * @return a ( acceleration that was computed )
     */
    private static Acceleration computePlanetAccelaration(int i) {

        /**
         * planet for which we compute acceleration
         */
        Planet planetI = planetList.get(i);

        Acceleration a = new Acceleration();

        for (int j = 0; j < planetList.size(); j++)
        {
            /**
             * another planet's that affect the planet on planet I
              */
            Planet planetJ = planetList.get(j);

            // if planetJ and planetI same planets ignore calculation ( thus remove the mistake, the difference in distance will be 0)
            if (j!=i) {
                double dx = planetJ.getPosition().getX() - planetI.getPosition().getX();
                double dy = planetJ.getPosition().getY() - planetI.getPosition().getY();

                double r = Math.sqrt( (dx * dx) + (dy * dy ) );

                a.set_aX(a.get_aX() + planetJ.getWeight() * ( dx / (r*r*r) ) );
                a.set_aY(a.get_aY() + planetJ.getWeight() * ( dy / (r*r*r) ) );

            }
        }

        a.set_aX(a.get_aX() * MyData.gravityConst);
        a.set_aY(a.get_aY() * MyData.gravityConst);

        return a;
    }

    /**
     * this method calculate world size ( find min coord(x,y) and max coord(x,y) - ends of our world ),
     * also will calculate center,length and height of world. It also reacts to the radius of the planets.
     */
    public static void calculateWorldSize() {

        maxCoordX = MIN_VALUE; ;
        minCoordX = MAX_VALUE ;

        maxCoordY = MIN_VALUE ;
        minCoordY = MAX_VALUE ;

        planetList.forEach( planet -> {

            double radius = planet.getRadius();

            if (radius > 1e6) radius = 3e10;

            if ((planet.getPosition().getX() + radius  ) > maxCoordX) maxCoordX = (planet.getPosition().getX() + radius) ;

            if ((planet.getPosition().getX() - radius ) < minCoordX) minCoordX = (planet.getPosition().getX() - radius) ;

            if ((planet.getPosition().getY() + radius ) > maxCoordY) maxCoordY = (planet.getPosition().getY() + radius);

            if ((planet.getPosition().getY() - radius ) < minCoordY) minCoordY = (planet.getPosition().getY() - radius);
        } );

        worldLength = maxCoordX - minCoordX ;
        worldHeight = maxCoordY - minCoordY;

        centerWorldX = (minCoordX + maxCoordX) / 2.0;
        centerWorldY = (minCoordY + maxCoordY) / 2.0;

    }

    /**
     * This method will check if collision should be started ( if yes will call method startCollision,
     * if not will just repeat checking)
     */
    public static void checker(){
        for (int i = 0; i< planetList.size(); i++) {
            for (int j = i+1; j < planetList.size(); j++) {
                double dx = planetList.get(j).getPosition().getX() - planetList.get(i).getPosition().getX();
                double dy = planetList.get(j).getPosition().getY() - planetList.get(i).getPosition().getY();

                double r = Math.sqrt( (dx * dx) + (dy * dy ) );
                if ( r < (planetList.get(i).getRadius() + planetList.get(j).getRadius()) )
                startCollison(i,j);
            }
        }
    }

    /**
     * This method will start collision
     * ( observing the laws of physics, the connection will occur on the momentum ( mass, speed, acceleration nowhere ).
     * The planet with the smallest mass will be removed, the planet with the largest mass will change characteristics.)
     * @param i - planet on pos i
     * @param j - planet on pos j
     */
    private static void startCollison(int i, int j) {

        Inertia inI = new Inertia(
                planetList.get(i).getWeight()*planetList.get(i).getXspeed(),
                planetList.get(i).getWeight()*planetList.get(i).getYspeed());
        Inertia inJ = new Inertia(
                planetList.get(j).getWeight()*planetList.get(j).getXspeed(),
                planetList.get(j).getWeight()*planetList.get(j).getYspeed());

        double sumWeight = planetList.get(i).getWeight() + planetList.get(j).getWeight();

        double speedX = Inertia.inertionalSum(inI.getX(),inJ.getX())/sumWeight;
        double speedY = Inertia.inertionalSum(inI.getY(),inJ.getY())/sumWeight;

        if (planetList.get(i).getWeight() < planetList.get(j).getWeight()) {

            planetList.get(j).setWeight(new Weight(sumWeight));
            planetList.get(j).setRadius(CalculateRadius.calculateRadius(planetList.get(j)));
            planetList.get(j).setYspeed(speedY);
            planetList.get(j).setXspeed(speedX);

            if (planetList.get(i).isHit()) {
                changeHitStatus(i,planetList.get(j));
            }

            planetList.remove(i);
            planetAccelerationList.remove(i);
        } else {

            planetList.get(i).setWeight(new Weight(sumWeight));
            planetList.get(i).setRadius(CalculateRadius.calculateRadius(planetList.get(i)));
            planetList.get(i).setYspeed(speedY);
            planetList.get(i).setXspeed(speedX);

            if (planetList.get(j).isHit()) {
                changeHitStatus(j,planetList.get(i));
            }

            planetList.remove(j);
            planetAccelerationList.remove(j);
        }
    }

    /**
     * This method will update hit status of planet ( if planet was hit and chart was opened
     * char will be closen and also info about our planet will deleted -> not drawin ).
     * @param toFalse
     * @param remainingPlanet
     */
    public static void changeHitStatus(int toFalse, Planet  remainingPlanet) {
        planetList.get(toFalse).setHit(false);
        remainingPlanet.setHit(false);
        lasHitedPlanet = remainingPlanet;
        Galaxy_SP2022.currentPlanetWithOpenedWindow = Galaxy_SP2022.lasHitedPlanet;
    }

    /**
     * This method will add to Queue info about positions of our planet ( if our time > 1s. )
     * method will delte 1 position from Queue and will add to Queue another one.
     * @param time
     */
    public static void updateLastPositionsInfo(double time) {
        planetList.forEach( planet -> {
            if (time>1) {
                planet.getLast1secPositions().poll();
            }
            planet.getLast1secPositions().add(
                    new Position(planet.getPosition().getX(),
                            planet.getPosition().getY()));
        });
    }

}
