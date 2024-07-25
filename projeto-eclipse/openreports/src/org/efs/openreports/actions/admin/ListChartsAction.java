/*
 * Copyright (C) 2003 Erik Swenson - eswenson@opensourcesoft.net
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opensymphony.xwork.ActionSupport;

import org.efs.openreports.objects.ReportChart;
import org.efs.openreports.providers.ChartProvider;
import org.efs.openreports.providers.ChartProviderAware;
import org.efs.openreports.providers.ProviderException;

public class ListChartsAction extends ActionSupport implements ChartProviderAware
{
	private List reportCharts;

	private ChartProvider chartProvider;

	public List getReportCharts()
	{		
		return reportCharts;		
	}

	public String execute()
	{
		try
		{			
			List reportChartsAux = chartProvider.getReportCharts();
			reportCharts = new ArrayList();
			for (Iterator iterator = reportChartsAux.iterator(); iterator.hasNext();) {
				ReportChart type = (ReportChart) iterator.next();
				if (type.getChartType() != 5) {
					reportCharts.add(type);
				}
			}
		}
		catch(ProviderException pe)
		{
			addActionError(pe.getMessage());
			return ERROR;	
		}	

		return SUCCESS;
	}	

	public void setChartProvider(ChartProvider chartProvider)
	{
		this.chartProvider = chartProvider;
	}

}