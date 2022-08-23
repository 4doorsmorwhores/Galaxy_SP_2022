/**
 * class Galaxy_SP2022(BasicDrawing)
 * @package Main
 * @author Andrei Akhramchuk
 * @version 1.0;
 */
package Main;

import Datas.Position;
import SpaceObjects.Planet;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import Сalculation.MyData;
import Сalculation.PlanetHit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import java.util.Timer;

import static Сalculation.MyData.*;

public class Galaxy_SP2022 {

	/**
	 * Drawing panel
	 */
	public static DrawingPanel panel = new DrawingPanel();

	/**
	 * checker for pause or unpause simulatuin
	 */
	public static boolean isSpacePressed = false;

	/**
	 * cheker for space object, that was last hited
	 */
	public static Planet lasHitedPlanet = null;

	public static Planet currentHitedPlanet;

	public static Planet currentPlanetWithOpenedWindow = null;

	public static String randomJokeInfo;

	/**
	 * main method for start projcet
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		// our path for simulation
		String path = args[0];

		//produce our data from csv to array and after that to data for visualisation
		MyData.produceData(path);

		JFrame okno = new JFrame();
		okno.setTitle("Andrei Akhramchuk, A21B0074P");
		okno.setSize(800, 600);

		okno.add(panel, BorderLayout.CENTER); //prida komponentu
		okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		okno.setLocationRelativeTo(null); //vycentrovat na obrazovce

		okno.setVisible(true);

		/**
		 * Mouse listener for took info about planet
		 * if our planet was hit, lasHitedPlanet will take the id of this planet
		 * and next time if another planet will be selected, our lasSelectedPlanet our checker will be cleared that means that
		 * our planet will be repaint with blue and status bar will be cleared and new planet will be added
		 * to our status bar and will be repainted with red.
		 */
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				if (lasHitedPlanet != null) {
					lasHitedPlanet.setHit(false);
					if (currentPlanetWithOpenedWindow != null) {
						ChartPanel.newWindow.dispose();
						currentPlanetWithOpenedWindow = null;
					}
				}

				for (int i = 0; i < ellipseArray.length; i++) {
					if (PlanetHit.isPlanetHit( ellipseArray[i], e.getX(),e.getY())) {
						if (!planetList.get(i).isHit()) {
							planetList.get(i).setHit(true);
							currentHitedPlanet = planetList.get(i);
							lasHitedPlanet = currentHitedPlanet;
							if (ChartPanel.newWindow != null)
							ChartPanel.newWindow.dispose();
							Main.ChartPanel.createChartWindow();
							break;
						}
					}
				}

			}
		});

		/**
		 * KeyBoardListener that will check if space was pressed if no our spacePressed checker will be false -
		 * our simulation is going on, if space will be pressed our simulation will stop, if space will pressed secondly
		 * our simulation will continue from last stop point.
		 */
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
			@Override
			public boolean dispatchKeyEvent(KeyEvent e) {
				if (e.getID() == KeyEvent.KEY_PRESSED
						&& e.getKeyCode() == KeyEvent.VK_SPACE) {
					if (!isSpacePressed) {
						isSpacePressed = true;
						DrawingPanel.pause = true;
					randomJokeInfo	= Informer.generateRandomJokeInfo();
					} else {
						isSpacePressed = false;
						DrawingPanel.pause = false;
					}
				}
				return true;
			}
		});

		/**
		 * timer was set on period 10 not 16, because my monitor provide 144Hz and make my semestral for 100 fps
		 */
		Timer tm = new Timer();
		tm.schedule(new TimerTask() {
			@Override
			public void run() {
				panel.repaint();
				if (ChartPanel.chartPanel != null) {
					Main.ChartPanel.updateChartPanel();
				}
			}
		},0,10);

	}

}
