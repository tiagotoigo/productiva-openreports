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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.efs.openreports.ORStatics;
import org.efs.openreports.objects.ReportAlert;
import org.efs.openreports.objects.ReportGroup;
import org.efs.openreports.objects.ReportUser;
import org.efs.openreports.providers.AlertProvider;
import org.efs.openreports.providers.AlertProviderAware;
import org.efs.openreports.providers.GroupProvider;
import org.efs.openreports.providers.GroupProviderAware;
import org.efs.openreports.providers.ReportProvider;
import org.efs.openreports.providers.ReportProviderAware;
import org.efs.openreports.providers.UserProvider;
import org.efs.openreports.providers.UserProviderAware;
import org.efs.openreports.util.LocalStrings;

import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionSupport;

public class EditUserAction extends ActionSupport implements UserProviderAware, GroupProviderAware, ReportProviderAware, AlertProviderAware
{
	protected static Logger log = Logger.getLogger(EditUserAction.class);

	private String command;
	private String submitType;

	private int id;
	private String name;
	private String password;
	private String passwordConfirm;	
	private String externalId;
	private String email;
	
	private int reportId;
	private Set reports;
	private List userAlerts;
	private List reportGroups;
	private int[] groupIds;
	
	private boolean scheduler;
	private boolean logViewer;
	private boolean rootAdmin;
	private boolean reportAdmin;
	private boolean dataSourceAdmin;
	private boolean parameterAdmin;
	private boolean chartAdmin;
	private boolean userAdmin;
	private boolean groupAdmin;
	private boolean alertAdmin;
	private boolean dashboard;
	private boolean alerts;
	private boolean uploader;
	private boolean schedulerAdmin;
	private boolean advancedScheduler;
	
	private UserProvider userProvider;
	private GroupProvider groupProvider;
	private ReportProvider reportProvider;
	private AlertProvider alertProvider;

	public String execute()
	{
		ReportUser user = null;				
	
		try
		{
			if (command.equals("edit"))
			{
				user = userProvider.getUser(new Integer(id));
			}
			else
			{
				user = new ReportUser();
			}
			
			reports = user.getReports();
			userAlerts = user.getAlerts();

			if (command.equals("edit") && submitType == null)
			{
				id = user.getId().intValue();
				name = user.getName();
				password = user.getPassword();
				passwordConfirm = user.getPassword();				
				externalId = user.getExternalId();
				email = user.getEmail();				
				reportGroups = user.getGroups();
				scheduler = user.isScheduler();
				advancedScheduler = user.isAdvancedScheduler();
				logViewer = user.isLogViewer();
				rootAdmin= user.isRootAdmin();
				reportAdmin = user.isReportAdmin();
				dataSourceAdmin = user.isDataSourceAdmin();
				parameterAdmin = user.isParameterAdmin();
				userAdmin = user.isUserAdmin();
				groupAdmin = user.isGroupAdmin();
				chartAdmin = user.isChartAdmin();
				alertAdmin = user.isAlertAdmin();
				dashboard = user.isDashboardUser();
				alerts = user.isAlertUser();
				uploader = user.isUploader();
				schedulerAdmin = user.isSchedulerAdmin();
				
				if (user.getDefaultReport() != null)
				{
					reportId = user.getDefaultReport().getId().intValue();
				}
				
				groupIds = null;
			}

			if (submitType == null)
				return INPUT;
			
			if (password == null || password.trim().length() < 1 || !password.equals(passwordConfirm))
			{
				addActionError(LocalStrings.getString(LocalStrings.ERROR_INVALID_PASSWORD));
				return INPUT;
			}

			user.setName(name);
			user.setPassword(password);			
			user.setExternalId(externalId);
			user.setEmail(email);		
			user.setGroups(convertIdsToGroups(groupIds));
			user.setAlertAdmin(alertAdmin);
			user.setChartAdmin(chartAdmin);
			user.setDataSourceAdmin(dataSourceAdmin);
			user.setGroupAdmin(groupAdmin);
			user.setLogViewer(logViewer);
			user.setParameterAdmin(parameterAdmin);
			user.setReportAdmin(reportAdmin);
			user.setRootAdmin(rootAdmin);
			user.setScheduler(scheduler);
			user.setAdvancedScheduler(advancedScheduler);
			user.setUserAdmin(userAdmin);	
			user.setDashboardUser(dashboard);
			user.setAlertUser(alerts);
			user.setUploader(uploader);
			user.setSchedulerAdmin(schedulerAdmin);
			
			if (reportId > 0)
			{
				user.setDefaultReport(reportProvider.getReport(new Integer(reportId)));
			}
			else
			{
				user.setDefaultReport(null);
			}

			if (command.equals("edit"))
			{
				userProvider.updateUser(user);
				
				ReportUser currentUser = (ReportUser) ActionContext.getContext().getSession().get(ORStatics.REPORT_USER);
				if ((currentUser != null) && (currentUser.getId().equals(user.getId())))
				{
					ActionContext.getContext().getSession().put(ORStatics.REPORT_USER, user);
				}
			}

			if (command.equals("add"))
			{
				user = userProvider.insertUser(user);
			}

			return SUCCESS;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			addActionError(e.toString());
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

	public String getPassword()
	{
		return password;
	}

	public String getSubmitType()
	{
		return submitType;
	}

	public String getName()
	{
		return name;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public void setSubmitType(String submitType)
	{
		this.submitType = submitType;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	private List convertIdsToGroups(int[] ids)
	{
		if (ids == null)
			return new ArrayList();

		List groups = new ArrayList();

		try
		{
			for (int i = 0; i < ids.length; i++)
			{
				ReportGroup group =
					groupProvider.getReportGroup(new Integer(ids[i]));
				groups.add(group);
			}
		}
		catch (Exception e)
		{
			addActionError(e.toString());
		}

		return groups;
	}

	public int[] getGroupIds()
	{
		return groupIds;
	}

	public void setGroupIds(int[] groupIds)
	{
		this.groupIds = groupIds;
	}

	public List getReportGroups()
	{
		try
		{
			return groupProvider.getReportGroups();
		}
		catch (Exception e)
		{
			addActionError(e.getMessage());
			return null;
		}
	}

	public List getReportGroupsForUser()
	{
		return reportGroups;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public void setGroupProvider(GroupProvider groupProvider)
	{
		this.groupProvider = groupProvider;
	}

	public void setUserProvider(UserProvider userProvider)
	{
		this.userProvider = userProvider;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getExternalId()
	{
		return externalId;
	}

	public void setExternalId(String externalId)
	{
		this.externalId = externalId;
	}

	public String getPasswordConfirm()
	{
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm)
	{
		this.passwordConfirm = passwordConfirm;
	}

	public int getReportId()
	{
		return reportId;
	}

	public void setReportId(int reportId)
	{
		this.reportId = reportId;
	}

	public Set getReports()
	{
		return reports;
	}

	public void setReportProvider(ReportProvider reportProvider)
	{
		this.reportProvider = reportProvider;
	}

	public boolean isChartAdmin()
	{
		return chartAdmin;
	}

	public void setChartAdmin(boolean chartAdmin)
	{
		this.chartAdmin = chartAdmin;
	}

	public boolean isDataSourceAdmin()
	{
		return dataSourceAdmin;
	}

	public void setDataSourceAdmin(boolean dataSourceAdmin)
	{
		this.dataSourceAdmin = dataSourceAdmin;
	}

	public boolean isGroupAdmin()
	{
		return groupAdmin;
	}

	public void setGroupAdmin(boolean groupAdmin)
	{
		this.groupAdmin = groupAdmin;
	}

	public boolean isLogViewer()
	{
		return logViewer;
	}

	public void setLogViewer(boolean logViewer)
	{
		this.logViewer = logViewer;
	}

	public boolean isParameterAdmin()
	{
		return parameterAdmin;
	}

	public void setParameterAdmin(boolean parameterAdmin)
	{
		this.parameterAdmin = parameterAdmin;
	}

	public boolean isReportAdmin()
	{
		return reportAdmin;
	}

	public void setReportAdmin(boolean reportAdmin)
	{
		this.reportAdmin = reportAdmin;
	}

	public boolean isRootAdmin()
	{
		return rootAdmin;
	}

	public void setRootAdmin(boolean rootAdmin)
	{
		this.rootAdmin = rootAdmin;
	}

	public boolean isScheduler()
	{
		return scheduler;
	}

	public void setScheduler(boolean scheduler)
	{
		this.scheduler = scheduler;
	}

	public boolean isAdvancedScheduler()
	{
		return advancedScheduler;
	}

	public void setAdvancedScheduler(boolean advancedScheduler)
	{
		this.advancedScheduler = advancedScheduler;
	}

	public boolean isUserAdmin()
	{
		return userAdmin;
	}

	public void setUserAdmin(boolean userAdmin)
	{
		this.userAdmin = userAdmin;
	}

	public boolean isAlertAdmin()
	{
		return alertAdmin;
	}

	public void setAlertAdmin(boolean alertAdmin)
	{
		this.alertAdmin = alertAdmin;
	}

	public boolean isDashboard()
	{
		return dashboard;
	}

	public void setDashboard(boolean dashboard)
	{
		this.dashboard = dashboard;
	}

	public boolean isAlerts()
	{
		return alerts;
	}

	public void setAlerts(boolean alerts)
	{
		this.alerts = alerts;
	}

	public boolean isUploader()
	{
		return uploader;
	}

	public void setUploader(boolean uploader)
	{
		this.uploader = uploader;
	}

	public boolean isSchedulerAdmin()
	{
		return schedulerAdmin;
	}

	public void setSchedulerAdmin(boolean schedulerAdmin)
	{
		this.schedulerAdmin = schedulerAdmin;
	}
	
	public String[] getOperators()
	{
		return new String[] {ReportAlert.OPERATOR_EQUAL, ReportAlert.OPERATOR_GREATER, ReportAlert.OPERATOR_LESS};
	}
	
	public List getAllAlerts()
	{
		try
		{
			return alertProvider.getReportAlerts();
		}
		catch (Exception e)
		{
			addActionError(e.toString());
			return null;
		}
	}

	public List getUserAlerts()
	{		
		return userAlerts;
	}

	public void setAlertProvider(AlertProvider alertProvider)
	{
		this.alertProvider = alertProvider;
	}
}