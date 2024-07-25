/*
 * Copyright (C) 2002 Erik Swenson - erik@oreports.com
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

import java.util.*;

import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionSupport;

import org.efs.openreports.ORStatics;
import org.efs.openreports.objects.*;
import org.efs.openreports.providers.*;
import org.efs.openreports.util.LocalStrings;

public class ReportDetailAction extends ActionSupport
		implements
			ReportProviderAware,
			ParameterProviderAware,
			DateProviderAware
{
	private Report report;
	private int reportId = Integer.MIN_VALUE;
	
	private String submitType;

	private ParameterProvider parameterProvider;
	private ReportProvider reportProvider;
	private DateProvider dateProvider;

	private List reportParameters;
	private int step = 0;
	
	private boolean displayInline;	

	public String execute()
	{
		try
		{
			ReportUser user = (ReportUser) ActionContext.getContext().getSession().get(
					ORStatics.REPORT_USER);

			report = reportProvider.getReport(new Integer(reportId));			

			if (report == null)
			{
				addActionError(LocalStrings.getString(LocalStrings.ERROR_REPORT_INVALID));
				return ERROR;
			}

			if (!user.isValidReport(report))
			{
				addActionError(LocalStrings.getString(LocalStrings.ERROR_REPORT_NOTAUTHORIZED));
				return ERROR;
			}			

			report.setDisplayInline(displayInline);
			reportParameters = report.getReportParametersByStep(step);

			if (submitType == null)
			{
				// first time through create new map and add standard report parameters
				HashMap newMap = new HashMap();
				newMap.put(ORStatics.USER_ID, user.getId());
				newMap.put(ORStatics.EXTERNAL_ID, user.getExternalId());
				newMap.put(ORStatics.USER_NAME, user.getName());

				ActionContext.getContext().getSession().remove(ORStatics.REPORT_PARAMETERS);
				ActionContext.getContext().getSession().put(ORStatics.REPORT_PARAMETERS, newMap);
				ActionContext.getContext().getSession().put(ORStatics.REPORT, report);

				if (report.getParameters().size() > 0 && report.getParameters().size() != report.getSubReportParameters().size())
				{					
					parameterProvider.loadReportParameterValues(reportParameters, newMap);

					return INPUT;
				}
				else
				{
					return SUCCESS;
				}
			}

			parameterProvider.validateParameters(reportParameters, ActionContext.getContext()
					.getParameters());

			Map map = (Map) ActionContext.getContext().getSession().get(
					ORStatics.REPORT_PARAMETERS);

			Map currentMap = parameterProvider.getReportParametersMap(reportParameters,
					ActionContext.getContext().getParameters());

			map.putAll(currentMap);

			ActionContext.getContext().getSession().put(ORStatics.REPORT_PARAMETERS, map);
			
			step++;

			reportParameters = report.getReportParametersByStep(step);

			if (reportParameters.size() > 0)
			{
				parameterProvider.loadReportParameterValues(reportParameters, map);

				return INPUT;
			}

			return SUCCESS;
		}
		catch (Exception e)
		{			
			Map map = (Map) ActionContext.getContext().getSession().get(
					ORStatics.REPORT_PARAMETERS);

			try
			{
				parameterProvider.loadReportParameterValues(reportParameters, map);
			}
			catch (ProviderException pe)
			{
				addActionError(pe.getMessage());
			}

			addActionError(e.getMessage());
			return INPUT;
		}
	}

	public String getSubmitType()
	{
		return submitType;
	}

	public void setSubmitType(String submitType)
	{
		this.submitType = submitType;
	}

	public int getReportId()
	{
		return reportId;
	}

	public void setReportId(int reportId)
	{
		this.reportId = reportId;
	}

	public void setParameterProvider(ParameterProvider parameterProvider)
	{
		this.parameterProvider = parameterProvider;
	}

	public void setReportProvider(ReportProvider reportProvider)
	{
		this.reportProvider = reportProvider;
	}

	public List getReportParameters()
	{
		return reportParameters;
	}

	public int getStep()
	{
		return step;
	}

	public void setStep(int step)
	{
		this.step = step;
	}
	
	public void setDateProvider(DateProvider dateProvider)
	{
		this.dateProvider = dateProvider;
	}
	
	public String getDateFormat()
	{
		return dateProvider.getDateFormat().toPattern();
	}
	
	public String getDefaultDate()
	{
		return dateProvider.formatDate(new Date());
	}
	
	public Report getReport()
	{
		return report;
	}
	
	public void setReport(Report report)
	{
		this.report = report;
	}

	public boolean isDisplayInline()
	{
		return displayInline;
	}

	public void setDisplayInline(boolean displayInline)
	{
		this.displayInline = displayInline;
	}
}