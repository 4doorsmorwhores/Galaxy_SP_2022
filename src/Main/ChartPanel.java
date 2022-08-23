/**
 * class ChartPanel
 * @package Main
 * @author Andrei Akhramchuk
 * @version 2.0;
 */
package Main;

import SpaceObjects.Planet;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import static Сalculation.MyData.planetList;

public class ChartPanel {
    public static org.jfree.chart.ChartPanel chartPanel;

    /**
     * window that contain chart
     */
    public static JFrame newWindow;

    /**
     * method that will update chart panel ( will call method that will update chart info, and after that
     * will create new chart and will set this char to charPanel and after that will repaint it).
     */
    public static void updateChartPanel() {
        JFreeChart lineChartObject = ChartFactory.createLineChart(
                "Chart of planet speed","Time [S.]",
                "Speed [KM/H]",
                createDataSet(), PlotOrientation.VERTICAL,
                true,true,false);
        chartPanel.setChart(lineChartObject);
        chartPanel.repaint();
    }

    /**
     * method that create data set of last 30 second speed ( will create chart about planet that was hited)
     * @return line_chart_dataset (dataset of last 30 second speed of hited planet)
     */
    public static DefaultCategoryDataset createDataSet() {
        DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
        try {
            planetList.get(planetList.indexOf(Galaxy_SP2022.currentHitedPlanet)).getLast30secSpeed().forEach((integer, acceleration) -> {
                line_chart_dataset.addValue(acceleration, Galaxy_SP2022.currentHitedPlanet.getName(), String.valueOf(integer));
            });
        } catch (Exception e) { newWindow.dispose();}

        return line_chart_dataset;
    }

    /**
     * Method that will create new Window [(650px x 400px) - setAlwaysOnTop(true)] for our chart
     */
    public static void createChartWindow() {
        newWindow = new JFrame();
        JFreeChart lineChartObject = ChartFactory.createLineChart(
                "Chart of planet speed","Time [S.]",
                "Speed [KM/H]",
                createDataSet(), PlotOrientation.VERTICAL,
                true,true,false);

        chartPanel = new org.jfree.chart.ChartPanel(lineChartObject);

        newWindow.add(chartPanel);

        newWindow.setLocationRelativeTo(null);
        newWindow.setSize(650, 400);
        newWindow.setVisible(true);
        newWindow.setAlwaysOnTop(true);
        newWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    Galaxy_SP2022.currentHitedPlanet.setHit(false);
                    Galaxy_SP2022.lasHitedPlanet = Galaxy_SP2022.currentHitedPlanet;
                } catch (Exception ex){}
            }
        });
    }

    /**
     * Method that will update our Queue every 1 second of real time
     * @param planetList
     * @param index
     */
    public static void updateChartInfo(ArrayList<Planet> planetList, int index) {
        planetList.forEach( planet -> {
            double sX = planetList.get(planetList.indexOf(planet)).getXspeed();
            double sY = planetList.get(planetList.indexOf(planet)).getYspeed();
            double currentPlanetSpeed = (Math.sqrt((sX * sX) + (sY * sY))) * 3.6;
            if (index >= 30) {
                planet.getLast30secSpeed().remove(index - 30);
                planet.getLast30secSpeed().put(index, currentPlanetSpeed);
            } else planet.getLast30secSpeed().put(index, currentPlanetSpeed);
        });
    }
}
