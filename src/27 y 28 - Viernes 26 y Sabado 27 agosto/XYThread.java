package jSqlViewer;

import java.awt.Color;

import javax.swing.JFrame;

import org.knowm.xchart.DialChart;
import org.knowm.xchart.DialChartBuilder;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.XYStyler;
import org.knowm.xchart.style.markers.SeriesMarkers;

public class XYThread extends Thread {
	
	private double[] xData ;
	private double[][] yData;
	private String[] serieNames;
	
	public XYThread(String[][] data,int[] rows,int[] cols,String[] serieNames)
	{
		this.serieNames = serieNames;
		xData = new double[rows.length];
		for( int i=0 ; i<xData.length ; i++ )
			xData[i] = rows[i];
		
		yData = new double[cols.length][rows.length];
		for( int s=0 ; s<cols.length ; s++ )
			for( int r=0 ; r<rows.length ; r++ )
			{
				String val = data[rows[r]][cols[s]];
				if( val != null)
					yData[s][r] = Double.parseDouble(val);
				else
					yData[s][r] = 0.0;
			}
	}
	@Override
	public void run()
	{
//		XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "yData[0]", xData, yData[0]);
		
		XYChart chart = new XYChartBuilder().width(800).height(600).build();
		chart.setTitle("data Viewer");
		chart.setXAxisTitle("X");
		for(int i=0 ; i<yData.length ; i++ )
		{
//			XYSeries serie = chart.addSeries(String.format("yData[%d]",i), xData, yData[i]);
			XYSeries serie = chart.addSeries(serieNames[i], xData, yData[i]);
			chart.setYAxisGroupTitle(i,serieNames[i]);
			XYStyler style = chart.getStyler();
			Color[] serieColors = style.getSeriesColors();
			style.setYAxisGroupTitleColor(i,serieColors[i]);
			style.setYAxisGroupTickMarksColorMap(i, serieColors[i]);
			style.setYAxisGroupTickLabelsColorMap(i, serieColors[i]);
			System.out.println(i + "  " + serie.getLineColor());
//			chart.getStyler().setYAxisTitleColor(serie.getLineColor());
			serie.setYAxisGroup(i);
			serie.setMarker(SeriesMarkers.NONE);
		}
		
		var winChart = new SwingWrapper(chart).displayChart();
		winChart.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
	}
}
