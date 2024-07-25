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

package org.efs.openreports.actions.admin;

import java.util.List;

import com.opensymphony.xwork.ActionSupport;

import org.efs.openreports.objects.ReportLog;
import org.efs.openreports.providers.*;
import org.efs.openreports.providers.ProviderException;
import org.efs.openreports.providers.ReportLogProvider;
import org.efs.openreports.providers.ReportLogProviderAware;

public class ViewReportLogsAction extends ActionSupport
		implements
			ReportLogProviderAware,
			ReportProviderAware,
			UserProviderAware,
			AlertProviderAware
{
	private List reportLogs;
	private List users;
	private List reports;
	private List alerts;
	private String status;
	private Integer userId;
	private Integer reportId;
	private Integer alertId;

	private boolean submitOk = false;

	private String[] statuses = new String[] { ReportLog.STATUS_SUCCESS,
			ReportLog.STATUS_EMPTY, ReportLog.STATUS_FAILURE, ReportLog.STATUS_TRIGGERED,
			ReportLog.STATUS_NOT_TRIGGERED };

	private ReportLogProvider reportLogProvider;
	private ReportProvider reportProvider;
	private UserProvider userProvider;
	private AlertProvider alertProvider;

	public List getReportLogs()
	{
		return reportLogs;
	}

	public String execute()
	{
		try
		{
			reports = reportProvider.getReports();			
			users = userProvider.getUsers();
			alerts = alertProvider.getReportAlerts();

			if (submitOk)
			{
				if (status != null && status.length() < 1) status = null;

				reportLogs = reportLogProvider.getReportLogs(status, userId, reportId, alertId, 500);
			}
		}
		catch (ProviderException pe)
		{
			addActionError(pe.getMessage());
			return ERROR;
		}

		return SUCCESS;
	}

	public void setSubmitOk(String submitOk)
	{
		if (submitOk != null) this.submitOk = true;
	}

	public boolean isResultAvailable()
	{
		return submitOk;
	}
	
	public List getReports()
	{
		return reports;
	}

	public String[] getStatuses()
	{
		return statuses;
	}

	public List getUsers()
	{
		return users;
	}

	public Integer getReportId()
	{
		return reportId;
	}

	public void setReportId(Integer reportId)
	{
		this.reportId = reportId;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public Integer getUserId()
	{
		return userId;
	}

	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}

	public Integer getAlertId()
	{
		return alertId;
	}

	public void setAlertId(Integer alertId)
	{
		this.alertId = alertId;
	}

	public void setReportLogProvider(ReportLogProvider reportLogProvider)
	{
		this.reportLogProvider = reportLogProvider;
	}

	public void setReportProvider(ReportProvider reportProvider)
	{
		this.reportProvider = reportProvider;
	}

	public void setUserProvider(UserProvider userProvider)
	{
		this.userProvider = userProvider;
	}

	public void setAlertProvider(AlertProvider alertProvider)
	{
		this.alertProvider = alertProvider;
	}

	public List getAlerts()
	{
		return alerts;
	}	
}