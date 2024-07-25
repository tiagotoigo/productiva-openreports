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

package org.efs.openreports.objects;

import java.io.Serializable;

public class ReportChart implements Serializable
{	
	private static final long serialVersionUID = 7406441909199255551L;
	
	public static final int BAR_CHART = 0;
	public static final int PIE_CHART = 1;
	public static final int XY_CHART = 2;
	public static final int TIME_CHART = 3;
	public static final int RING_CHART = 4;	
	public static final int MAP = 5;
	
	public static final int HORIZONTAL = 1;
	public static final int VERTICAL = 2;
	
	public static final String DRILLDOWN_PARAMETER = "DrillDown";
	
	private Integer id;
	private String name;
	private String description;
	private String query;
	
	private int chartType;
	private int width = 400;
	private int height = 400;
	private int XMinimum = 0;
	private int XMaximum = 1000;
	
	private Integer mapWidth = 540;
	private Integer mapHeight = 490;
	private Double mapCentralLat = -15.798889;
	private Double mapCentralLong = -55.866667;
	private Integer mapZoom = 4;
	
	private String xAxisLabel;
	private String yAxisLabel;
	
	private boolean showLegend;	
	private boolean showTitle;
	private boolean showValues;
	private int plotOrientation;

	private ReportDataSource dataSource;
	
	private Report drillDownReport;

	private Integer engineGrafica;
	
	public ReportChart()
	{
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String toString()
	{
		return name;
	}

	public String getDescription()
	{
		return description;
	}

	public String getTitle()
	{
		if (showTitle) return description;
		return null;
	}
	
	public Integer getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int compareTo(Object object)
	{
		ReportChart reportChart = (ReportChart) object;
		return name.compareTo(reportChart.getName());
	}

	public ReportDataSource getDataSource()
	{
		return dataSource;
	}

	public void setDataSource(ReportDataSource dataSource)
	{
		this.dataSource = dataSource;
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

	public boolean isShowLegend()
	{
		return showLegend;
	}

	public void setShowLegend(Boolean showLegend)
	{
		if (showLegend == null) showLegend = new Boolean(false);
		this.showLegend = showLegend.booleanValue();
	}
	
	public boolean isShowTitle()
	{
		return showTitle;
	}

	public void setShowTitle(Boolean showTitle)
	{
		if (showTitle == null) showTitle = new Boolean(false);
		this.showTitle = showTitle.booleanValue();
	}		

	public int getPlotOrientation()
	{
		return plotOrientation;
	}

	public void setPlotOrientation(Integer plotOrientation)
	{
		if (plotOrientation == null) plotOrientation = new Integer(VERTICAL);
		this.plotOrientation = plotOrientation.intValue();
	}

	public Report getDrillDownReport()
	{
		return drillDownReport;
	}

	public void setDrillDownReport(Report drillDownReport)
	{
		this.drillDownReport = drillDownReport;
	}

	public boolean isShowValues()
	{
		return showValues;
	}

	public void setShowValues(Boolean showValues)
	{
		if (showValues == null) showValues = new Boolean(false);
		this.showValues = showValues.booleanValue();
	}

	public Integer getEngineGrafica() {
		return engineGrafica;
	}

	public void setEngineGrafica(Integer engineGrafica) {
		this.engineGrafica = engineGrafica;
	}

	public int getXMinimum() {
		return XMinimum;
	}

	public void setXMinimum(int xMinimum) {
		XMinimum = xMinimum;
	}

	public int getXMaximum() {
		return XMaximum;
	}

	public void setXMaximum(int xMaximum) {
		XMaximum = xMaximum;
	}

	public Integer getMapWidth() {
		return mapWidth;
	}

	public void setMapWidth(Integer mapWidth) {
		this.mapWidth = mapWidth;
	}

	public Integer getMapHeight() {
		return mapHeight;
	}

	public void setMapHeight(Integer mapHeight) {
		this.mapHeight = mapHeight;
	}

	public Double getMapCentralLat() {
		return mapCentralLat;
	}

	public void setMapCentralLat(Double mapCentralLat) {
		this.mapCentralLat = mapCentralLat;
	}

	public Double getMapCentralLong() {
		return mapCentralLong;
	}

	public void setMapCentralLong(Double mapCentralLong) {
		this.mapCentralLong = mapCentralLong;
	}

	public Integer getMapZoom() {
		return mapZoom;
	}

	public void setMapZoom(Integer mapZoom) {
		this.mapZoom = mapZoom;
	}


	
}