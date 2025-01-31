/*
 * Copyright (C) 2006 Erik Swenson - erik@oreports.com
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

package org.efs.openreports.engine.input;

import java.util.Map;

import org.efs.openreports.objects.Report;

/**
 * ReportEngineInput contains information needed to generate a report by the
 * ReportEngine. Report, parameters, and exportType are required.
 * 
 * @author Erik Swenson
 */

public class ReportEngineInput
{		
	private Report report;
	private Map parameters;
	private int exportType;	
	
	// JasperReports only
	private Map imagesMap;	
	private boolean inlineImages;
	//
	
	public ReportEngineInput(Report report, Map parameters)
	{
		this.report = report;
		this.parameters = parameters;
	}

	public Map getParameters()
	{
		return parameters;
	}

	public void setParameters(Map parameters)
	{
		this.parameters = parameters;
	}

	public Report getReport()
	{
		return report;
	}

	public void setReport(Report report)
	{
		this.report = report;
	}	

	public int getExportType()
	{
		return exportType;
	}

	public void setExportType(int exportType)
	{
		this.exportType = exportType;
	}

	public Map getImagesMap()
	{
		return imagesMap;
	}

	public void setImagesMap(Map imagesMap)
	{
		this.imagesMap = imagesMap;
	}

	public boolean isInlineImages()
	{
		return inlineImages;
	}

	public void setInlineImages(boolean inlineImages)
	{
		this.inlineImages = inlineImages;
	}	
}
