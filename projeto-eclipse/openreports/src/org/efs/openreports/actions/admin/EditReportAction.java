/*
 * Copyright (C) 2002 Erik Swenson - erik@oreports.com
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

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionSupport;

import org.apache.log4j.Logger;
import org.efs.openreports.ORStatics;
import org.efs.openreports.objects.*;
import org.efs.openreports.providers.*;
import org.efs.openreports.util.LocalStrings;
import org.efs.openreports.util.ORUtil;

public class EditReportAction
	extends ActionSupport
	implements ReportProviderAware, DataSourceProviderAware, ChartProviderAware, ParameterProviderAware
{
	protected static Logger log = Logger.getLogger(EditReportAction.class);

	private String command;
	
	private boolean submitOk;
	private boolean submitValidate;

	private int id;
	private String name;
	private String description;
	private String file;
	private String query;
	private int dataSourceId;
	private int reportChartId;

	private boolean pdfExportEnabled;
	private boolean htmlExportEnabled;
	private boolean csvExportEnabled;
	private boolean xlsExportEnabled;
	private boolean rtfExportEnabled;
	private boolean textExportEnabled;
	private boolean excelExportEnabled;
	private boolean virtual;
	private boolean hidden;
	
	private Report report;

	private ReportProvider reportProvider;
	private DataSourceProvider dataSourceProvider;
	private ChartProvider chartProvider;
	private ParameterProvider parameterProvider;
	
	private ReportParameterValue[] parameterValues;
	
	private String reportUrl;

	private Integer tipoGrafico;

	public String getReportUrl() {
		return reportUrl;
	}

	public void setReportUrl(String reportUrl) {
		this.reportUrl = reportUrl;
	}

	public String execute()
	{
		try
		{
			if (command.equals("edit") || command.equals("edit, edit")) 
			{
				report = reportProvider.getReport(new Integer(id));
			}
			else
			{
				report = new Report();
			}

			if ((command.equals("edit") || command.equals("edit, edit")) && !submitOk && !submitValidate)
			{
				name = report.getName();
				description = report.getDescription();
				file = report.getFile();
				query = report.getQuery();
				id = report.getId().intValue();
				pdfExportEnabled = report.isPdfExportEnabled();
				csvExportEnabled = report.isCsvExportEnabled();
				htmlExportEnabled = report.isHtmlExportEnabled();
				xlsExportEnabled = report.isXlsExportEnabled();
				rtfExportEnabled = report.isRtfExportEnabled();
				textExportEnabled = report.isTextExportEnabled();
				excelExportEnabled = report.isExcelExportEnabled();
				virtual = report.isVirtualizationEnabled();
				hidden = report.isHidden();
				if (report.getDataSource() != null)
				{
					dataSourceId = report.getDataSource().getId().intValue();
				}
				if (report.getReportChart() != null)
				{
					reportChartId = report.getReportChart().getId().intValue();
				}

			}

			if (!submitOk && !submitValidate)
				return INPUT;
			
			if (name == null || name.trim().length() < 1)
			{
				addActionError(LocalStrings.getString(LocalStrings.ERROR_REPORT_INVALID));
				return INPUT;
			}

			report.setName(name);
			report.setDescription(description);
			report.setFile(file);
			report.setQuery(query);
			report.setCsvExportEnabled(csvExportEnabled);
			report.setHtmlExportEnabled(htmlExportEnabled);
			report.setPdfExportEnabled(pdfExportEnabled);
			report.setXlsExportEnabled(xlsExportEnabled);
			report.setRtfExportEnabled(new Boolean(rtfExportEnabled));
			report.setTextExportEnabled(new Boolean(textExportEnabled));
			report.setExcelExportEnabled(new Boolean(excelExportEnabled));
			report.setVirtualizationEnabled(new Boolean(virtual));
			report.setHidden(new Boolean(hidden));
			report.setTipoGrafico(tipoGrafico);
			
			if ((query == null || query.trim().length() < 1)
				&& !csvExportEnabled
				&& !htmlExportEnabled
				&& !pdfExportEnabled
				&& !xlsExportEnabled
				&& !rtfExportEnabled
				&& !textExportEnabled
				&& !excelExportEnabled)
			{
				report.setPdfExportEnabled(true);
			}

			if (dataSourceId != -1)
			{
				report.setDataSource(
					dataSourceProvider.getDataSource(
						new Integer(dataSourceId)));
			}
			else
			{
				report.setDataSource(null);
			}

			if (reportChartId != -1)
			{
				report.setReportChart(
					chartProvider.getReportChart(new Integer(reportChartId)));
			}
			else
			{
				report.setReportChart(null);
			}			

			if (submitValidate)
			{
				if (report.getQuery() == null || report.getQuery().length() < 1) return INPUT;
				
				Map map = new HashMap();
				if (query.toUpperCase().indexOf("$P") > -1)
				{
					ReportUser reportUser = (ReportUser) ActionContext.getContext().getSession().get(ORStatics.REPORT_USER);						
					map = ORUtil.buildQueryParameterMap(reportUser, query, parameterProvider);
				}
				
				//FIXME validate query as parameter...
				ReportParameter reportParameter = new ReportParameter();
				reportParameter.setData(query);
				reportParameter.setType(ReportParameter.QUERY_PARAM);
				reportParameter.setDataSource(report.getDataSource());
				reportParameter.setClassName("java.lang.String");
				
				parameterValues = parameterProvider.getParamValues(reportParameter, map);

				return INPUT;
			}			

			if (command.equals("edit"))
			{
				reportProvider.updateReport(report);				
			}

			if (command.equals("add"))
			{
				reportProvider.insertReport(report);				
			}
			
			return SUCCESS;
		}
		catch (Exception e)
		{
			addActionError(e.getMessage());
			return INPUT;
		}
	}

	public String getCommand()
	{
		return command;
	}

	public void setCommand(String command)
	{
		this.command = command;
	}	

	public int getDataSourceId()
	{
		return dataSourceId;
	}

	public String getDescription()
	{
		return description;
	}

	public String getFile()
	{
		return file;
	}

	public String getName()
	{
		return name;
	}

	public void setDataSourceId(int dataSourceId)
	{
		this.dataSourceId = dataSourceId;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setFile(String file)
	{
		this.file = file;
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
			addActionError(e.toString());
			return null;
		}
	}

	public List getReportFileNames()
	{
		try
		{
			return reportProvider.getReportFileNames();
		}
		catch (Exception e)
		{
			addActionError(e.toString());
			return null;
		}
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public List getParametersInReport()
	{
		if (report == null || report.getParameters() == null)
			return null;

		List list = report.getParameters();
		Collections.sort(list);

		return list;
	}

	public List getReportParameters()
	{
		try
		{
			return parameterProvider.getAvailableParameters(report);
		}
		catch (Exception e)
		{
			addActionError(e.toString());
			return null;
		}
	}
	
	public Report getReport()
	{
		return report;
	}
	
	public void setDataSourceProvider(DataSourceProvider dataSourceProvider)
	{
		this.dataSourceProvider = dataSourceProvider;
	}

	public void setReportProvider(ReportProvider reportProvider)
	{
		this.reportProvider = reportProvider;
	}

	public int getReportChartId()
	{
		return reportChartId;
	}

	public void setReportChartId(int reportChartId)
	{
		this.reportChartId = reportChartId;
	}

	public List getReportCharts()
	{
		try
		{
			return chartProvider.getReportCharts();
		}
		catch (Exception e)
		{
			addActionError(e.toString());
			return null;
		}
	}

	public void setChartProvider(ChartProvider chartProvider)
	{
		this.chartProvider = chartProvider;
	}

	public boolean isCsvExportEnabled()
	{
		return csvExportEnabled;
	}

	public void setCsvExportEnabled(boolean csvExportEnabled)
	{
		this.csvExportEnabled = csvExportEnabled;
	}

	public boolean isHtmlExportEnabled()
	{
		return htmlExportEnabled;
	}

	public void setHtmlExportEnabled(boolean htmlExportEnabled)
	{
		this.htmlExportEnabled = htmlExportEnabled;
	}

	public boolean isPdfExportEnabled()
	{
		return pdfExportEnabled;
	}

	public void setPdfExportEnabled(boolean pdfExportEnabled)
	{
		this.pdfExportEnabled = pdfExportEnabled;
	}

	public boolean isXlsExportEnabled()
	{
		return xlsExportEnabled;
	}

	public void setXlsExportEnabled(boolean xlsExportEnabled)
	{
		this.xlsExportEnabled = xlsExportEnabled;
	}

	public String getQuery()
	{
		return query;
	}

	public void setQuery(String query)
	{
		this.query = query;
	}
	
	public void setSubmitOk(String submitOk)
	{
		if (submitOk != null) this.submitOk = true;
	}
	
	public void setSubmitValidate(String submitValidate)
	{
		if (submitValidate != null) this.submitValidate = true;
	}

	public void setParameterProvider(ParameterProvider parameterProvider)
	{
		this.parameterProvider = parameterProvider;
	}
	
	public ReportParameterValue[] getParameterValues()
	{
		return parameterValues;
	}

	public boolean isExcelExportEnabled()
	{
		return excelExportEnabled;
	}

	public void setExcelExportEnabled(boolean excelExportEnabled)
	{
		this.excelExportEnabled = excelExportEnabled;
	}

	public boolean isRtfExportEnabled()
	{
		return rtfExportEnabled;
	}

	public void setRtfExportEnabled(boolean rtfExportEnabled)
	{
		this.rtfExportEnabled = rtfExportEnabled;
	}

	public boolean isTextExportEnabled()
	{
		return textExportEnabled;
	}

	public void setTextExportEnabled(boolean textExportEnabled)
	{
		this.textExportEnabled = textExportEnabled;
	}
	
	public boolean isHidden()
	{
		return hidden;
	}

	public void setHidden(boolean hidden)
	{
		this.hidden = hidden;
	}	

	public boolean isVirtual()
	{
		return virtual;
	}

	public void setVirtual(boolean virtual)
	{
		this.virtual = virtual;
	}

	public Integer getTipoGrafico() {
		return tipoGrafico;
	}

	public void setTipoGrafico(Integer tipoGrafico) {
		this.tipoGrafico = tipoGrafico;
	}
}