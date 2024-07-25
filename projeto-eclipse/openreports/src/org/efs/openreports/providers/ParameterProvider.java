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

package org.efs.openreports.providers;

import java.util.List;
import java.util.Map;

import org.efs.openreports.objects.*;

public interface ParameterProvider
{
	public ReportParameterValue[] getParamValues(
		ReportParameter reportParameter,
		Map parameters)
		throws ProviderException;
	public void loadReportParameterValues(
		List reportParameters,
		Map parameters)
		throws ProviderException;

	public List getReportParameters(Report report, String type)
		throws ProviderException;

	public Map getReportParametersMap(List reportParameters, Map parameters)
		throws ProviderException;
	public boolean validateParameters(List reportParameters, Map parameters)
		throws ProviderException;

	public ReportParameter getReportParameter(Integer id)
		throws ProviderException;
	public ReportParameter getReportParameter(String name) throws ProviderException;
	public List getReportParameters() throws ProviderException;
	public ReportParameter insertReportParameter(ReportParameter reportParameter)
		throws ProviderException;
	public void updateReportParameter(ReportParameter reportParameter)
		throws ProviderException;
	public void deleteReportParameter(ReportParameter reportParameter)
		throws ProviderException;

	public List getAvailableParameters(Report report) throws ProviderException;

}