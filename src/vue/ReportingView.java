/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import modele.EFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Maxime
 */
public class ReportingView extends EFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6951151443028082767L;
	private JButton btnDisplayGraph;
	private ChartPanel chartPanel;

	public ReportingView(String title, int height, int width) {
		super(title, height, width);

		// Look and feel natif de l'OS hôte
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Definition of the ReportingView frame

		getContentPane().setLayout(new MigLayout("", "[401.00][grow]", "[31.00][]"));

		JComboBox<String> comboBox = new JComboBox<String>();
		getContentPane().add(comboBox, "cell 0 0,growx");
		comboBox.addItem("Personnes hospitalisées par service");
		comboBox.addItem("Graph2");
		comboBox.addItem("Graph3");
		comboBox.addItem("Graph4");
		comboBox.addItem("Graph5");

		btnDisplayGraph = new JButton("Afficher");
		btnDisplayGraph.addActionListener(this);
		;
		getContentPane().add(btnDisplayGraph, "cell 1 0,growx");
	}
	
	private void chartInit() {
		// Chart init
		XYDataset dataset = createDummyDataset();
		JFreeChart chart = createChart(dataset);
		chartPanel = new ChartPanel(chart);
		//chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
	}

	private XYDataset createDummyDataset() {

		XYSeries series = new XYSeries("2016");
		series.add(18, 567);
		series.add(20, 612);
		series.add(25, 800);
		series.add(30, 980);
		series.add(40, 1410);
		series.add(50, 2350);

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);

		return dataset;
	}

	
	// WIP
	private XYDataset SQLtoDataset (String sqlquery) {
		
		XYSeries series = new XYSeries("2016");
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		return dataset;		
	}
	
	private JFreeChart createChart(XYDataset dataset) {

		JFreeChart chart = ChartFactory.createXYLineChart("Average salary per age", "Age", "Salary (€)", dataset,
				PlotOrientation.VERTICAL, true, true, false);

		XYPlot plot = chart.getXYPlot();

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesStroke(0, new BasicStroke(2.0f));

		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.white);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.BLACK);

		chart.getLegend().setFrame(BlockBorder.NONE);

		chart.setTitle(new TextTitle("Average Salary per Age", new Font("Serif", java.awt.Font.BOLD, 18)));

		return chart;

	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == this.btnDisplayGraph) {
			// Delete the old graph
			this.removeGraph();
			// Add the new graph
			this.addGraph();

			// Refreshing the display
			this.revalidate();
			this.repaint();
		}
	}

	private void removeGraph() {
		if(chartPanel!=null) {
			getContentPane().remove(this.chartPanel);
		}
	}

	private void addGraph() {
		this.chartInit();
		getContentPane().add(this.chartPanel, "cell 0 1 2 1,grow");
	}
}
