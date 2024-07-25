/*
 * Copyright (C) 2002 Erik Swenson - eswenson@opensourcesoft.net
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

package org.efs.openreports.actions;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionSupport;

import org.efs.openreports.objects.ReportUser;

public class ReportGroupAction extends ActionSupport
{
	private List reportGroups;

	private int id;

	public String execute()	{
		
		ReportUser user = (ReportUser) ActionContext.getContext().getSession().get("user");
		ActionContext.getContext().getSession().put("listaDeGrupos", user.getGroups());
		reportGroups = user.getGroups();		
		Collections.sort(reportGroups);
		return INPUT;
	}	

	public List getReportGroups()
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
}