/*
 * Copyright (C) 2006 Erik Swenson - erik@oreports.com
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

package org.efs.openreports.actions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.efs.openreports.ORStatics;
import org.efs.openreports.engine.ChartReportEngine;
import org.efs.openreports.engine.input.ReportEngineInput;
import org.efs.openreports.engine.output.ChartEngineOutput;
import org.efs.openreports.objects.Report;
import org.efs.openreports.objects.ReportLog;
import org.efs.openreports.objects.ReportUser;
import org.efs.openreports.objects.chart.ChartValue;
import org.efs.openreports.util.LocalStrings;
import org.jfree.chart.imagemap.ImageMapUtilities;

import br.com.productiva.engine.ChartReportEngineProductiva;

import com.opensymphony.xwork.ActionContext;

public class ChartReportAction extends QueryReportAction 
{
	protected static Logger log = Logger.getLogger(ChartReportAction.class);
	
	private String imageMap;
	private ChartValue[] chartValues;

	public String execute()
	{		
		ReportUser user = (ReportUser) ActionContext.getContext().getSession().get(
				ORStatics.REPORT_USER);

		report = (Report) ActionContext.getContext().getSession().get(ORStatics.REPORT);

		Map reportParameters = getReportParameterMap(user);

		ReportLog reportLog = new ReportLog(user, report, new Date());

		try
		{
			log.debug("Starting Chart Report: " + report.getName());			

			reportLogProvider.insertReportLog(reportLog);				
			
			ChartReportEngine chartReportEngine = new ChartReportEngineProductiva(
					dataSourceProvider, directoryProvider, propertiesProvider);

			ReportEngineInput input = new ReportEngineInput(report, reportParameters);

			ChartEngineOutput chartOutput = (ChartEngineOutput) chartReportEngine
					.generateReport(input);	
			
			chartValues = chartOutput.getChartValues();
			if (chartValues.length == 0)
			{
				addActionError(LocalStrings.getString(LocalStrings.ERROR_REPORT_EMPTY));
			}
			
			imageMap = ImageMapUtilities.getImageMap("chart", chartOutput.getChartRenderingInfo());						
			
			HashMap imagesMap = new HashMap();
			imagesMap.put("ChartImage", chartOutput.getContent());			
			
			ActionContext.getContext().getSession().put(ORStatics.IMAGES_MAP, imagesMap);			
						
			reportLog.setEndTime(new Date());
			reportLog.setStatus(ReportLog.STATUS_SUCCESS);
			reportLogProvider.updateReportLog(reportLog);

			log.debug("Finished Chart Report: " + report.getName());
		}
		catch (Exception e)
		{
			addActionError(e.getMessage());

			log.error(e.toString());

			reportLog.setMessage(e.getMessage());
			reportLog.setStatus(ReportLog.STATUS_FAILURE);

			reportLog.setEndTime(new Date());

			try
			{
				reportLogProvider.updateReportLog(reportLog);
			}
			catch (Exception ex)
			{
				log.error("Unable to create ReportLog: " + ex.getMessage());
			}

			return ERROR;
		}		

		return SUCCESS;
	}	

	public String getImageMap()
	{
		return imageMap;
	}

	public void setImageMap(String imageMap)
	{
		this.imageMap = imageMap;
	}

	public ChartValue[] getChartValues()
	{
		return chartValues;
	}		
}