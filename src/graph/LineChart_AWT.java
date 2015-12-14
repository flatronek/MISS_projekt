package graph;

import org.jfree.chart.ChartPanel;

import java.util.Map;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChart_AWT extends JPanel
{
	ChartPanel chartPanel;
   public LineChart_AWT( String applicationTitle , String chartTitle )
   {
 
      JFreeChart lineChart = ChartFactory.createLineChart(
         chartTitle,
         "Degree","Amount",
         createDataset(),
         PlotOrientation.VERTICAL,
         true,true,false);
         
      chartPanel = new ChartPanel( lineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      add(chartPanel);
    
   }

   private DefaultCategoryDataset createDataset( )
   {
      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
      return dataset;
   }
   


public void createlineChart(Map<Integer, Integer> degreeMap) {
	// TODO Auto-generated method stub
	remove(chartPanel);
    JFreeChart lineChart = ChartFactory.createLineChart(
            "degrees",
            "Degree","Amount",
            createDataset(degreeMap),
            PlotOrientation.VERTICAL,
            true,true,false);
	chartPanel= chartPanel = new ChartPanel( lineChart );
    chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
    add(chartPanel);
}

private CategoryDataset createDataset(Map<Integer, Integer> degreeMap) {
	DefaultCategoryDataset dataset = new DefaultCategoryDataset( );

    for(Integer k : degreeMap.keySet()){
   
    	 dataset.addValue( degreeMap.get(k), "stopien wierz." , k );
    }
    return dataset;
	
}

public void createlineChart(double[] arrayfactors) {
	remove(chartPanel);
    JFreeChart lineChart = ChartFactory.createLineChart(
            "degrees",
            "Degree","Amount",
            createDataset(arrayfactors),
            PlotOrientation.VERTICAL,
            true,true,false);
	chartPanel= chartPanel = new ChartPanel( lineChart );
    chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
    add(chartPanel);
	
}

private CategoryDataset createDataset(double[] arrayfactors) {
	DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
    for(int k =0;k<arrayfactors.length;k++){
    	   
   	 dataset.addValue( (Double)arrayfactors[k], "gronowanie" ,(Integer) k );
   }
   return dataset;
}

}
