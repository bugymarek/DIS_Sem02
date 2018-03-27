/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Bugy
 */
public class Chart {
    private final XYSeriesCollection data;
    private final JFreeChart chart;
    
    public Chart(JPanel panel){
        data = new XYSeriesCollection();
        XYSeries seriesA = new XYSeries("Pripad A");
        XYSeries seriesB = new XYSeries("Pripad B");
        data.addSeries(seriesA);                     
        data.addSeries(seriesB);
        chart = ChartFactory.createXYLineChart(
            "Úspešne vyhratie automobilu",
                "Počet replikácií", // x-axis Label
                "Pravdepodobnosť", // y-axis Label
                data, // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
        );
        XYPlot plot = (XYPlot)chart.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        // "0" is the line plot
        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(0, false);

        // "1" is the scatter plot
        renderer.setSeriesLinesVisible(1, true);
        renderer.setSeriesShapesVisible(1, false);

        plot.setRenderer(renderer);
        ChartPanel chartPanel = new ChartPanel(chart); 
        panel.removeAll();
        panel.setLayout(new java.awt.BorderLayout());        
        panel.add(chartPanel, BorderLayout.CENTER);
        panel.validate();
    }
    
    public void addValueToSeries(int index, double x, double y){
        data.getSeries(index).add(x, y);
    }

    void changeRange(double minValue, double maxValue) {
        chart.getXYPlot().getRangeAxis().setRange(minValue, maxValue);
    }
}
