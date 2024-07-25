/*
 * Copyright (C) 2003 Erik Swenson - erik@oreports.com
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place - Suite 330, Boston, MA 02111-1307, USA.
 *  
 */

package org.efs.openreports.engine;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.util.JRQueryExecuter;

import org.apache.log4j.Logger;
import org.efs.openreports.engine.input.ReportEngineInput;
import org.efs.openreports.engine.output.ChartEngineOutput;
import org.efs.openreports.engine.output.ReportEngineOutput;
import org.efs.openreports.objects.Report;
import org.efs.openreports.objects.ReportChart;
import org.efs.openreports.objects.ReportDataSource;
import org.efs.openreports.objects.chart.CategoryChartValue;
import org.efs.openreports.objects.chart.ChartValue;
import org.efs.openreports.objects.chart.PieChartValue;
import org.efs.openreports.objects.chart.TimeChartValue;
import org.efs.openreports.objects.chart.XYChartValue;
import org.efs.openreports.providers.DataSourceProvider;
import org.efs.openreports.providers.DirectoryProvider;
import org.efs.openreports.providers.PropertiesProvider;
import org.efs.openreports.providers.ProviderException;
import org.efs.openreports.util.LocalStrings;
import org.efs.openreports.util.ORUtil;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.encoders.EncoderUtil;
import org.jfree.chart.encoders.ImageFormat;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import br.com.productiva.engine.MapValue;

/**
 * ChartReport ReportEngine implementation. Charts are generated using 
 * the JFreeReport library.
 * 
 * @author Erik Swenson
 * 
 */

public class ChartReportEngine extends ReportEngine
{
	protected static Logger log = Logger.getLogger(ChartReportEngine.class.getName());

	public ChartReportEngine(DataSourceProvider dataSourceProvider,
			DirectoryProvider directoryProvider, PropertiesProvider propertiesProvider)
	{
		super(dataSourceProvider, directoryProvider, propertiesProvider);
	}
	
	/**
	 * Generates a ChartEngineOutput from a ReportEngineInput. The output consists 
	 * of a byte[] containing a JPEG image.		
	 */
	public ReportEngineOutput generateReport(ReportEngineInput input)
			throws ProviderException
	{
		ChartValue[] values = getChartValues(input.getReport().getReportChart(), input.getParameters());
		return createChartOutput(input.getReport().getReportChart(), values);
	}	

	/**
	 * Executes the Chart query and builds an array of ChartValues from the results.
	 */
	public ChartValue[] getChartValues(ReportChart reportChart, Map parameters)
			throws ProviderException
	{
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;

		try
		{
			ReportDataSource dataSource = reportChart.getDataSource();
			conn = dataSourceProvider.getConnection(dataSource.getId());

			// Use JasperReports Query logic to parse parameters in chart
			// queries

			JRDesignQuery query = new JRDesignQuery();
			query.setText(reportChart.getQuery());

			// convert parameters to JRDesignParameters so they can be parsed
			Map jrParameters = ORUtil.buildJRDesignParameters(parameters);

			pStmt = JRQueryExecuter.getStatement(query, jrParameters, parameters, conn);

			rs = pStmt.executeQuery();

			Vector v = new Vector();

			int chartType = reportChart.getChartType();
			if (chartType == ReportChart.BAR_CHART)
			{
				while (rs.next())
				{
					CategoryChartValue catValue = new CategoryChartValue();

					catValue.setValue(rs.getDouble(1));
					catValue.setSeries(rs.getString(2));
					catValue.setCategory(rs.getString(3));

					v.add(catValue);
				}
			}
			else if (chartType == ReportChart.PIE_CHART
					|| chartType == ReportChart.RING_CHART)
			{
				while (rs.next())
				{
					PieChartValue pieValue = new PieChartValue();

					pieValue.setValue(rs.getDouble(1));
					pieValue.setKey(rs.getString(2));

					v.add(pieValue);
				}
			}
			else if (chartType == ReportChart.XY_CHART)
			{
				while (rs.next())
				{
					XYChartValue xyValue = new XYChartValue();

					xyValue.setSeries(rs.getString(1));
					xyValue.setValue(rs.getDouble(2));
					xyValue.setSecondValue(rs.getDouble(3));

					v.add(xyValue);
				}
			}
			else if (chartType == ReportChart.TIME_CHART)
			{
				while (rs.next())
				{
					TimeChartValue timeValue = new TimeChartValue();

					timeValue.setSeries(rs.getString(1));
					timeValue.setValue(rs.getDouble(2));
					timeValue.setTime(rs.getTimestamp(3));

					v.add(timeValue);
				}
			}			
			else if (chartType == ReportChart.MAP)
			{
				MapValue mapValue = new MapValue();
				mapValue.setMapZoom(reportChart.getMapZoom());
				mapValue.setMapCentralLat(reportChart.getMapCentralLat());
				mapValue.setMapCentralLong(reportChart.getMapCentralLong());
				mapValue.setMapHeight(reportChart.getMapHeight());
				mapValue.setMapWidth(reportChart.getMapWidth());
				try {  
				    ResultSetMetaData rsmd = rs.getMetaData();  
				    int numColumns = rsmd.getColumnCount();  
				    for (int i=1; i<numColumns+1; i++) {  
				        String columnName = rsmd.getColumnName(i);  
				        Integer columnType = rsmd.getColumnType(i);
				        mapValue.getListaNomeColuna().add(columnName);
				        mapValue.getListaTipoColuna().add(columnType);
				    }
					while (rs.next()) {
						List<Object> listaValores = new ArrayList<Object>();
						for (int i=1; i<numColumns+1; i++) {
							listaValores.add(rs.getObject(i));							
						}
						mapValue.getListaValorColuna().add(listaValores);
					}
				} catch (SQLException e) {  
				    System.out.println("Error: " + e);  
				}
				ChartValue[] values = new ChartValue[1];
				values[0] = mapValue;
				return values;
			}

			ChartValue[] values = new ChartValue[v.size()];
			v.copyInto(values);

			return values;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new ProviderException(LocalStrings
					.getString(LocalStrings.ERROR_CHARTQUERY_INVALID)
					+ ": " + e.toString());
		}
		finally
		{
			try
			{
				if (rs != null) rs.close();
				if (pStmt != null) pStmt.close();
				if (conn != null) conn.close();
			}
			catch (Exception c)
			{
				log.error("Error closing");
			}
		}
	}	
	
	private static ChartEngineOutput createChartOutput(ReportChart reportChart, ChartValue[] values)
	{
		JFreeChart chart = null;

		switch (reportChart.getChartType())
		{
			case ReportChart.BAR_CHART :				
				chart = createBarChart(reportChart, values);				
				break;
			case ReportChart.PIE_CHART :
				chart = createPieChart(reportChart, values);								
				break;
			case ReportChart.XY_CHART :
				chart = createXYChart(reportChart, values);
				break;
			case ReportChart.TIME_CHART :
				chart = createTimeChart(reportChart, values);
				break;
			case ReportChart.RING_CHART :
				chart = createRingChart(reportChart, values);
				break;			
		}

		if (chart == null) return null;		
		
		chart.setBackgroundPaint(Color.WHITE);
		
		ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());

		BufferedImage bufferedImage =  chart.createBufferedImage(reportChart.getWidth(), reportChart.getHeight(),info);
		byte[] image = null;
		
		try
		{
			//image = EncoderUtil.encode(bufferedImage, ImageFormat.JPEG);
			image = EncoderUtil.encode(bufferedImage, ImageFormat.PNG);
		}
		catch(IOException ioe)
		{
			log.warn(ioe);
		}
			
		ChartEngineOutput chartOutput = new ChartEngineOutput();
		chartOutput.setContent(image);	
		//chartOutput.setContentType(ReportEngineOutput.CONTENT_TYPE_JPEG);
		chartOutput.setContentType(ReportEngineOutput.CONTENT_TYPE_PNG);
		chartOutput.setChartRenderingInfo(info);
		chartOutput.setChartValues(values);
		
		return chartOutput;
	}	
	
	private static JFreeChart createBarChart(ReportChart reportChart, ChartValue[] values)
	{
		CategoryDataset dataset = createCategoryDataset(values);

		PlotOrientation orientation = PlotOrientation.VERTICAL;
		if (reportChart.getPlotOrientation() == ReportChart.HORIZONTAL)
		{
			orientation = PlotOrientation.HORIZONTAL;
		}
		
		JFreeChart chart = ChartFactory.createBarChart3D(reportChart.getTitle(),
				reportChart.getXAxisLabel(), reportChart.getYAxisLabel(), dataset,
				orientation, reportChart.isShowLegend(), true, false);		

		return chart;
	}	

	private static JFreeChart createPieChart(ReportChart reportChart, ChartValue[] values)
	{
		DefaultPieDataset dataset = createPieDataset(values);

		JFreeChart chart = ChartFactory.createPieChart3D(reportChart.getTitle(),
				dataset, reportChart.isShowLegend(), true, false);
		chart.setBackgroundPaint(Color.WHITE);
		
//		
//		 chart.getRenderingHints().put
//		 	(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		PiePlot3D plot = (PiePlot3D)chart.getPlot(); 
        plot.setBackgroundPaint(Color.WHITE);
        plot.setStartAngle(0.30);
        plot.setInteriorGap(0.10);
        plot.setDepthFactor(0.10);
        plot.setForegroundAlpha(0.70f);
        // Specify the colors here 
//        Color[] colors = {Color.blue, Color.yellow, Color.green}; 
//        PieRenderer renderer = new PieRenderer(colors); 
//        renderer.setColor(plot, dataset); 
        
		return chart;
	}	
	
	private static JFreeChart createRingChart(ReportChart reportChart, ChartValue[] values)
	{
		PieDataset dataset = createPieDataset(values);

		JFreeChart chart = ChartFactory.createRingChart(reportChart.getTitle(),
				dataset, reportChart.isShowLegend(), true, false);

		return chart;
	}

	private static JFreeChart createXYChart(ReportChart reportChart, ChartValue[] values)
	{
		XYDataset dataset = createXYDataset(values);
		
		PlotOrientation orientation = PlotOrientation.VERTICAL;
		if (reportChart.getPlotOrientation() == ReportChart.HORIZONTAL)
		{
			orientation = PlotOrientation.HORIZONTAL;
		}

		JFreeChart chart = ChartFactory.createXYLineChart(reportChart.getTitle(),
				reportChart.getXAxisLabel(), reportChart.getYAxisLabel(), dataset,
				orientation, reportChart.isShowLegend(), true, false);

		return chart;
	}

	private static JFreeChart createTimeChart(ReportChart reportChart, ChartValue[] values)
	{
		XYDataset dataset = createTimeDataset(values);

		JFreeChart chart = ChartFactory.createTimeSeriesChart(reportChart
				.getTitle(), reportChart.getXAxisLabel(), reportChart
				.getYAxisLabel(), dataset, reportChart.isShowLegend(), true, false);

		return chart;
	}	

	private static CategoryDataset createCategoryDataset(ChartValue[] values)
	{
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (int i = 0; i < values.length; i++)
		{
			CategoryChartValue value = (CategoryChartValue) values[i];
			dataset.addValue(value.getValue(), value.getSeries(), value.getCategory());
		}

		return dataset;
	}

	private static DefaultPieDataset createPieDataset(ChartValue[] values)
	{
		DefaultPieDataset dataset = new DefaultPieDataset();

		for (int i = 0; i < values.length; i++)
		{
			PieChartValue value = (PieChartValue) values[i];
			dataset.setValue(value.getKey(), value.getValue());
		}

		return dataset;
	}

	private static XYDataset createXYDataset(ChartValue[] values)
	{
		XYSeries series = null;
		XYSeriesCollection seriesCollection = new XYSeriesCollection();

		for (int i = 0; i < values.length; i++)
		{
			XYChartValue value = (XYChartValue) values[i];

			if (series == null || !series.getKey().equals(value.getSeries()))
			{
				if (series != null)
				{
					seriesCollection.addSeries(series);
				}

				series = new XYSeries(value.getSeries());
			}

			series.add(value.getValue(), value.getSecondValue());
		}

		seriesCollection.addSeries(series);

		return seriesCollection;
	}

	private static XYDataset createTimeDataset(ChartValue[] values)
	{
		TimeSeries series = null;
		TimeSeriesCollection seriesCollection = new TimeSeriesCollection();

		for (int i = 0; i < values.length; i++)
		{
			TimeChartValue value = (TimeChartValue) values[i];

			if (series == null || !series.getKey().equals(value.getSeries()))
			{
				if (series != null)
				{
					seriesCollection.addSeries(series);
				}

				series = new TimeSeries(value.getSeries(), Second.class);
			}

			series.add(new Second(value.getTime()), value.getValue());
		}

		seriesCollection.addSeries(series);

		return seriesCollection;
	}
	
	public List buildParameterList(Report report) throws ProviderException
	{
		throw new ProviderException("ChartReportEngine: buildParameterList not implemented.");
	}	
	
	public static class PieRenderer 
    { 
        private Color[] color; 
        
        public PieRenderer(Color[] color) 
        { 
            this.color = color; 
        }        
        
        public void setColor(PiePlot plot, DefaultPieDataset dataset) 
        { 
            List <Comparable> keys = dataset.getKeys(); 
            int aInt; 
            
            for (int i = 0; i < keys.size(); i++) 
            { 
                aInt = i % this.color.length; 
                plot.setSectionPaint(keys.get(i), this.color[aInt]); 
            } 
        } 
    } 
}