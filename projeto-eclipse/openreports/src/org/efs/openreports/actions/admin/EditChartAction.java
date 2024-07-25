/*
 * Copyright (C) 2003 Erik Swenson - erik@oreports.com
 * 
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place - Suite 330, Boston, MA 02111-1307, USA.
 *  
 */

package org.efs.openreports.actions.admin;

import java.util.*;

import br.com.productiva.engine.ChartReportEngineProductiva;

import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionSupport;

import org.apache.log4j.Logger;

import org.efs.openreports.ORStatics;
import org.efs.openreports.engine.ChartReportEngine;
import org.efs.openreports.engine.QueryReportEngine;
import org.efs.openreports.engine.input.ReportEngineInput;
import org.efs.openreports.engine.output.QueryEngineOutput;
import org.efs.openreports.objects.Report;
import org.efs.openreports.objects.ReportChart;
import org.efs.openreports.objects.ReportParameter;
import org.efs.openreports.objects.ReportUser;
import org.efs.openreports.objects.chart.ChartValue;
import org.efs.openreports.providers.*;
import org.efs.openreports.util.ORUtil;

public class EditChartAction extends ActionSupport
		implements
			DataSourceProviderAware,
			ChartProviderAware,
			ParameterProviderAware,
			ReportProviderAware,
			DirectoryProviderAware,
			PropertiesProviderAware
{
	private String results;
	
	protected static Logger log =
		Logger.getLogger(EditChartAction.class);

	private String command;
	
	private boolean submitOk;
	private boolean submitValidate;

	private int id;
	private String name;
	private String description;
	private String query;
	private int dataSourceId = Integer.MIN_VALUE;	
	private int reportId = Integer.MIN_VALUE;
	private int chartType;
	private int width = 600;
	private int height = 400;
	private int xMinimum = 0;
	private int xMaximum = 1000;
	private String xAxisLabel;
	private String yAxisLabel;
	private int orientation;
	private boolean showLegend;
	private boolean showTitle;
	private boolean showValues;
	private int engineGrafica;
	private int mapZoom = 4;
	private double mapCentralLat = -15.798889;
	private double mapCentralLong = -55.866667;
	private int mapHeight = 490;
	private int mapWidth  = 540;

	private ReportChart reportChart;		
	private ChartValue[] chartValues;

	private DataSourceProvider dataSourceProvider;
	private ChartProvider chartProvider;
	private ParameterProvider parameterProvider;
	private ReportProvider reportProvider;	
	private PropertiesProvider propertiesProvider;
	private DirectoryProvider directoryProvider;

	public String execute()
	{
		try
		{
			if (command.equals("edit"))
			{
				reportChart =
					chartProvider.getReportChart(new Integer(id));
			}
			else
			{
				reportChart = new ReportChart();
			}

			if (command.equals("edit") && !submitOk && !submitValidate)
			{
				name = reportChart.getName();
				description = reportChart.getDescription();
				query = reportChart.getQuery();				
				chartType = reportChart.getChartType();
				width = reportChart.getWidth();
				height = reportChart.getHeight();
				xMinimum = reportChart.getXMinimum();
				xMaximum = reportChart.getXMaximum();
				mapHeight = reportChart.getMapHeight();
				mapWidth = reportChart.getMapWidth();
				mapCentralLat = reportChart.getMapCentralLat();
				mapCentralLong = reportChart.getMapCentralLong();
				mapZoom = reportChart.getMapZoom();
				xAxisLabel = reportChart.getXAxisLabel();
				yAxisLabel = reportChart.getYAxisLabel();
				orientation = reportChart.getPlotOrientation();
				showLegend = reportChart.isShowLegend();
				showTitle = reportChart.isShowTitle();
				showValues = reportChart.isShowValues();
				id = reportChart.getId().intValue();
				engineGrafica = reportChart.getEngineGrafica().intValue();
				
				if (reportChart.getDataSource() != null)
				{
					dataSourceId =
						reportChart.getDataSource().getId().intValue();
				}	
				
				if (reportChart.getDrillDownReport() != null)
				{
					reportId = reportChart.getDrillDownReport().getId().intValue();
				}
				
			}

			if (!submitOk && !submitValidate)
				return INPUT;

			reportChart.setName(name);
			reportChart.setDescription(description);
			reportChart.setQuery(query);		
			reportChart.setChartType(chartType);
			reportChart.setWidth(width);
			reportChart.setHeight(height);
			reportChart.setXMinimum(xMinimum);
			reportChart.setXMaximum(xMaximum);
			reportChart.setXAxisLabel(xAxisLabel);
			reportChart.setYAxisLabel(yAxisLabel);
			reportChart.setMapHeight(mapHeight);
			reportChart.setMapWidth(mapWidth);
			reportChart.setMapCentralLat(mapCentralLat);
			reportChart.setMapCentralLong(mapCentralLong);
			reportChart.setMapZoom(mapZoom);
			reportChart.setShowLegend(new Boolean(showLegend));
			reportChart.setShowTitle(new Boolean(showTitle));
			reportChart.setShowValues(new Boolean(showValues));
			reportChart.setPlotOrientation(new Integer(orientation));
			reportChart.setEngineGrafica(new Integer(engineGrafica));
			
			if (dataSourceId != -1) reportChart.setDataSource(dataSourceProvider
					.getDataSource(new Integer(dataSourceId)));
			
			if (reportId != -1)
			{
				reportChart.setDrillDownReport(reportProvider.getReport(new Integer(reportId)));	
			}
			else
			{
				reportChart.setDrillDownReport(null);
			}					
			
			if (submitValidate)
			{
				Map map = new HashMap();
				if (query.toUpperCase().indexOf("$P") > -1)
				{
					ReportUser reportUser = (ReportUser) ActionContext.getContext().getSession().get(ORStatics.REPORT_USER);
					map = ORUtil.buildQueryParameterMap(reportUser, query, parameterProvider);
				}
				
				ChartReportEngine chartReportEngine;				
				
				if (reportChart.getChartType() != 5) {
					chartReportEngine = new ChartReportEngine(
								dataSourceProvider, directoryProvider, propertiesProvider);
					chartValues = chartReportEngine.getChartValues(reportChart, map);
				} else {
					chartReportEngine = new ChartReportEngineProductiva(
							dataSourceProvider, directoryProvider, propertiesProvider);
					chartValues = chartReportEngine.getChartValues(reportChart, map);
					((ChartReportEngineProductiva)chartReportEngine).createMap(reportChart, chartValues);
				}

				return INPUT;
			}			

			if (command.equals("edit"))
			{
				chartProvider.updateReportChart(reportChart);
			}

			if (command.equals("add"))
			{
				chartProvider.insertReportChart(reportChart);
			}

			return SUCCESS;
		}
		catch (Exception e)
		{
			addActionError(e.getMessage());
			return INPUT;
		}
	}

	/**
	 * Obter valores
	 * @return
	 */
	public String getResults()
	{
		results = (String)ActionContext.getContext().getSession().get(ORStatics.QUERY_REPORT_RESULTS);
		return results;
	}
	
	protected Map getReportParameterMap(ReportUser user)
	{
		Map reportParameters = new HashMap();

		if (ActionContext.getContext().getSession().get(ORStatics.REPORT_PARAMETERS) != null)
		{
			reportParameters = (Map) ActionContext.getContext().getSession().get(
					ORStatics.REPORT_PARAMETERS);
		}

		// add standard report parameters
		reportParameters.put(ORStatics.USER_ID, user.getId());
		reportParameters.put(ORStatics.EXTERNAL_ID, user.getExternalId());
		reportParameters.put(ORStatics.USER_NAME, user.getName());

		return reportParameters;
	}
	public String getCommand()
	{
		return command;
	}

	public void setCommand(String command)
	{
		this.command = command;
	}	
	
	public void setSubmitOk(String submitOk)
	{
		if (submitOk != null) this.submitOk = true;
	}
	
	public void setSubmitValidate(String submitValidate)
	{
		if (submitValidate != null) this.submitValidate = true;
	}
	
	public int getDataSourceId()
	{
		return dataSourceId;
	}

	public String getName()
	{
		return name;
	}

	public void setDataSourceId(int dataSourceId)
	{
		this.dataSourceId = dataSourceId;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List getDataSources()
	{
		try
		{
			return dataSourceProvider.getDataSources();
		}
		catch (Exception e)
		{
			addActionError(e.getMessage());
			return null;
		}
	}
	
	public List getReports()
	{	
		try
		{
			return reportProvider.getReports();		
		}
		catch (Exception e)
		{
			addActionError(e.getMessage());
			return null;
		}		
	}

	public String[] getTypes()
	{
		return ReportParameter.TYPES;
	}

	public String[] getClassNames()
	{
		return ReportParameter.CLASS_NAMES;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}	

	public void setDataSourceProvider(DataSourceProvider dataSourceProvider)
	{
		this.dataSourceProvider = dataSourceProvider;
	}	

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}	

	public void setChartProvider(ChartProvider chartProvider)
	{
		this.chartProvider = chartProvider;
	}

	public String getQuery()
	{
		return query;
	}

	public void setQuery(String query)
	{
		this.query = query;
	}	

	public int getChartType()
	{
		return chartType;
	}

	public void setChartType(int chartType)
	{
		this.chartType = chartType;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public ChartValue[] getChartValues()
	{
		return chartValues;
	}

	public String getXAxisLabel()
	{
		return xAxisLabel;
	}

	public void setXAxisLabel(String axisLabel)
	{
		xAxisLabel = axisLabel;
	}

	public String getYAxisLabel()
	{
		return yAxisLabel;
	}

	public void setYAxisLabel(String axisLabel)
	{
		yAxisLabel = axisLabel;
	}

	public void setParameterProvider(ParameterProvider parameterProvider)
	{
		this.parameterProvider = parameterProvider;
	}

	public int getOrientation()
	{
		return orientation;
	}

	public void setOrientation(int orientation)
	{
		this.orientation = orientation;
	}

	public boolean isShowLegend()
	{
		return showLegend;
	}

	public void setShowLegend(boolean showLegend)
	{
		this.showLegend = showLegend;
	}

	public boolean isShowTitle()
	{
		return showTitle;
	}

	public void setShowTitle(boolean showTitle)
	{
		this.showTitle = showTitle;
	}

	public int getReportId()
	{
		return reportId;
	}

	public void setReportId(int reportId)
	{
		this.reportId = reportId;
	}

	public void setReportProvider(ReportProvider reportProvider)
	{
		this.reportProvider = reportProvider;
	}

	public boolean isShowValues()
	{
		return showValues;
	}

	public void setShowValues(boolean showValues)
	{
		this.showValues = showValues;
	}

	public void setDirectoryProvider(DirectoryProvider directoryProvider)
	{
		this.directoryProvider = directoryProvider;
	}

	public void setPropertiesProvider(PropertiesProvider propertiesProvider)
	{
		this.propertiesProvider = propertiesProvider;
	}

	public int getEngineGrafica() {
		return engineGrafica;
	}

	public void setEngineGrafica(int engineGrafica) {
		this.engineGrafica = engineGrafica;
	}

	public int getxMinimum() {
		return xMinimum;
	}

	public void setxMinimum(int xMinimum) {
		this.xMinimum = xMinimum;
	}

	public int getxMaximum() {
		return xMaximum;
	}

	public void setxMaximum(int xMaximum) {
		this.xMaximum = xMaximum;
	}

	public void setResults(String results) {
		this.results = results;
	}

	public int getMapZoom() {
		return mapZoom;
	}

	public void setMapZoom(int mapZoom) {
		this.mapZoom = mapZoom;
	}

	public double getMapCentralLat() {
		return mapCentralLat;
	}

	public void setMapCentralLat(double mapCentralLat) {
		this.mapCentralLat = mapCentralLat;
	}

	public double getMapCentralLong() {
		return mapCentralLong;
	}

	public void setMapCentralLong(double mapCentralLong) {
		this.mapCentralLong = mapCentralLong;
	}

	public int getMapHeight() {
		return mapHeight;
	}

	public void setMapHeight(int mapHeight) {
		this.mapHeight = mapHeight;
	}

	public int getMapWidth() {
		return mapWidth;
	}

	public void setMapWidth(int mapWidth) {
		this.mapWidth = mapWidth;
	}
}