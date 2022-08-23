/**
 * class DrawingPanel
 * @package Main
 * @author Andrei Akhramchuk
 * @version 1.0;
 */
package Main;

import SpaceObjects.Planet;
import Сalculation.CalculatePlanets;
import Сalculation.MyData;

import static Main.Galaxy_SP2022.randomJokeInfo;
import static Сalculation.MyData.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.swing.*;


public class DrawingPanel extends JPanel {

	public DrawingPanel() {
		this.setPreferredSize(new Dimension(800, 600));
	}

	/**
	 * start time of our simulation
	 */
	private long startTime = System.currentTimeMillis();

	/**
	 * start time for count local time
	 */
	private long startTimeForLocalTime = System.currentTimeMillis();

	/**
	 * checker for local time of simulation ( adding stop time )
	 */
	public boolean isAdDD = true;

	/**
	 * checker for pause simulation
	 */
	public static boolean pause;

	/**
	 * curent local time of simulation
	 */
	private double currentTimeAnimation = 0;

	/**
	 * elapsed time from last update of simulation
	 */
	private long elapsedTime;

	/**
	 * elapsed time during pause
	 */
	private long elapsedTimeIn;

	/**
	 * stop time
	 */
	private long stopTime;
	private long myStopTime;



	/**
	 * Method for draw simulation
	 * @param g
	 */
	@Override
	public void paint(Graphics g) {

		//adding all objects in ellipse array
		ellipseArray = new Ellipse2D[planetList.size()];

		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.BLACK);

		g2.fillRect(0,0,this.getWidth(),this.getHeight() );

		g2.setColor(Color.GREEN);
		//font for drawing system time info
		Font stringFont = new Font( "SansSerif", Font.PLAIN, 18 );

		//font for drawing "pause string"
		Font stopFont = new Font( "SansSerif", Font.PLAIN, 48 );
		g2.setFont( stringFont );

		//stoping simulation during pause
		if (pause)
		{
			elapsedTimeIn = System.currentTimeMillis();
			startTime = System.currentTimeMillis();
			stopTime = (elapsedTimeIn - elapsedTime);
			isAdDD = false;
		} else {
			// adding elapsed time during pauses
			if (!isAdDD) {
				myStopTime += stopTime;
				isAdDD = true;
			}
			elapsedTime = System.currentTimeMillis();
			long curTime = System.currentTimeMillis();

			int currentIntTimeFromStart = (int) (((curTime - startTimeForLocalTime) - myStopTime ) / 1000.0);
			double time = ((curTime - startTimeForLocalTime) - myStopTime ) / 1000.0;

			ChartPanel.updateChartInfo(planetList, currentIntTimeFromStart);
			CalculatePlanets.updateLastPositionsInfo(time);




			currentTimeAnimation = MyData.simulationTimeTick * ((curTime - startTimeForLocalTime) - myStopTime ) / 1000.0 ;

			double elapsed = (curTime - startTime) / 1000.0;

			// update simulation
			CalculatePlanets.updateSystem(MyData.simulationTimeTick * elapsed);
			startTime = curTime;
		}
		final String length = String.valueOf(currentTimeAnimation);
		g2.drawString("Simulation Time: " + currentTimeAnimation,
				this.getWidth() - (g2.getFontMetrics().stringWidth("Simulation time: " + length) + 20),
				20);


		// calculating scale
			double scaleX = this.getWidth()/worldLength;
			double scaleY = this.getHeight()/worldHeight;
			scale = Math.min(scaleX,scaleY);


		AffineTransform transformBeforeScaling = g2.getTransform();

			// translate center of world in the center of our screen
			g2.translate(this.getWidth()/ 2.0 - (centerWorldX*scale) ,this.getHeight() / 2.0 - (centerWorldY*scale) );
			g2.scale(scale,scale);

		AffineTransform transformAfterScaling = g2.getTransform();
		//drawPlanetsPath(g2);
			// calling drawing planets
			for (int i = 0; i < planetList.size(); i++) {
				drawPlanet(planetList.get(i),g2,i);
			}


		g2.setTransform(transformBeforeScaling);

		//writing hit planet info
		for (int i = 0; i < planetList.size(); i++) {
			if(planetList.get(i).isHit())
			{
				g2.setColor(Color.GREEN);
				g2.drawString("Planet name: " + planetList.get(i).getName(), 0, 20);
				g2.drawString("Planet position: " + planetList.get(i).getPosition().toString(), 0, 40);
				g2.drawString("Planet speed: " + planetList.get(i).getSpeed().toString(),0,60);
				g2.setColor(Color.CYAN);
			}
		}

		//writing simulation pause info

		if (pause) {
			g2.setColor(Color.YELLOW);
			g2.setFont(stopFont);
			g2.drawString("Simulation is paused!", this.getWidth() / 2 - (g2.getFontMetrics().stringWidth("Simulation is paused!") / 2), this.getHeight() / 2);
			g2.setFont(stringFont);
			g2.setColor(Color.WHITE);
			g2.drawString(randomJokeInfo, this.getWidth() / 2 - (g2.getFontMetrics().stringWidth(randomJokeInfo) / 2),this.getHeight() / 2 + 40);
			g2.setColor(Color.CYAN);
		}

		g2.setTransform(transformAfterScaling);

	}



	/**
	 * Method for drawing planet ( if planet was hit planet draw with red, if wasn't hit - CYAN )
	 * @param planet
	 * @param g2
	 * @param i
	 */
	private void drawPlanet(Planet planet, Graphics2D g2, int i) {
		if(planetList.get(i).isHit())
		{
			g2.setColor(Color.RED);
		} else g2.setColor(Color.CYAN);
		double radius = planet.getRadius();
		if (radius*scale < 2) radius = 3/scale ;
		Ellipse2D e = new Ellipse2D.Double( (planet.getPosition().getX() - radius ),
											(planet.getPosition().getY() - radius ),
				                           radius * 2,
				                           radius  * 2);
		drawPath(g2,planet,e);
		ellipseArray[i] = e;
		g2.fill(e);
	}

	/**
	 * Method that will draw planet path, this path will be changed by color and also scale
	 * if planet was hi path will draw with red, if not with cyan
	 */
	private void drawPath(Graphics2D g2, Planet planet, Ellipse2D elipse) {

		if (planet.isHit())
			g2.setColor(new Color(153,0,0));
		else
			g2.setColor(new Color(0,128,128));

		AtomicInteger i = new AtomicInteger(0);
		AtomicReference<Double> scope = new AtomicReference<>(0.00);
		int step = planet.getLast1secPositions().size();
		planet.getLast1secPositions().forEach( position -> {
			if (planet.isHit())
				g2.setColor(new Color(153 + i.incrementAndGet(),10 + i.get(),0 + i.get()));
			else
				g2.setColor(new Color(0,40 + i.incrementAndGet(),40 + i.incrementAndGet()));
			if (scope.get()<1) {
				scope.updateAndGet(v -> (v + 1.0/step));
			} else {
				scope.updateAndGet(v -> (1.0));
			}
			Ellipse2D e = new Ellipse2D.Double( (position.getX() - elipse.getWidth()/2 * scope.get()) ,
					(position.getY() - elipse.getHeight()/2 * scope.get()) ,
					elipse.getWidth() * scope.get() ,
					elipse.getHeight() * scope.get() ) ;

			g2.fill(e);
		});
		if (!planet.isHit()) {
			g2.setColor(Color.CYAN);
		} else g2.setColor(Color.RED);
	}

}
