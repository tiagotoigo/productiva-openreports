/*  
 * Copyright (C) 2006 by Open Source Software Solutions, LLC and Contributors
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
 * Original Author	:	Roberto Nibali 	- rnibali@pyx.ch 
 * Contributor(s)	:	Erik Swenson 	- erik@oreports.com
 * 
 */

package org.efs.openreports.engine;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.birt.report.engine.api.EngineConstants;
import org.eclipse.birt.report.engine.api.HTMLRenderContext;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.IGetParameterDefinitionTask;
import org.eclipse.birt.report.engine.api.IParameterDefnBase;
import org.eclipse.birt.report.engine.api.IParameterGroupDefn;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.IScalarParameterDefn;
import org.eclipse.birt.report.model.api.OdaDataSourceHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.efs.openreports.engine.input.ReportEngineInput;
import org.efs.openreports.engine.output.ReportEngineOutput;
import org.efs.openreports.objects.Report;
import org.efs.openreports.objects.ReportDataSource;
import org.efs.openreports.objects.ReportParameter;
import org.efs.openreports.providers.BirtProvider;
import org.efs.openreports.providers.DataSourceProvider;
import org.efs.openreports.providers.DirectoryProvider;
import org.efs.openreports.providers.PropertiesProvider;
import org.efs.openreports.providers.ProviderException;

/**
 * Eclipse BIRT ReportEngine implementation. This implementation sets the
 * BIRTHome directory to the 'platform' directory underneath the 'reports'
 * directory. All images created during report generation are stored in the
 * 'reports/images/temp' directory.
 * 
 * @author Roberto Nibali
 * 
 */
public class BirtReportEngine extends ReportEngine
{
	protected static Logger log = Logger.getLogger(BirtReportEngine.class);		

	public BirtReportEngine(DataSourceProvider dataSourceProvider,
			DirectoryProvider directoryProvider, PropertiesProvider propertiesProvider)
	{
		super(dataSourceProvider,directoryProvider, propertiesProvider);
	}
	
	/**
	 * Generates a report from a BIRT report design.
	 */	
	public ReportEngineOutput generateReport(ReportEngineInput input)
			throws ProviderException
	{
		Report report = input.getReport();
		Map parameters = input.getParameters();
		
		ReportEngineOutput output = new ReportEngineOutput();
		ByteArrayOutputStream out = new ByteArrayOutputStream();			

		IReportEngine engine = BirtProvider
				.getBirtEngine(directoryProvider.getReportDirectory() + "platform");		

		// Set options for task
		HTMLRenderOption options = new HTMLRenderOption();
		options.setOutputStream(out);
			   
		HTMLRenderContext renderContext = new HTMLRenderContext();		
		renderContext.setImageDirectory(directoryProvider.getReportImageTempDirectory());
		renderContext.setBaseImageURL("report-images");		
		 
		HashMap contextMap = new HashMap();
		contextMap.put(EngineConstants.APPCONTEXT_HTML_RENDER_CONTEXT, renderContext);
				
		try
		{
			String designFile = directoryProvider.getReportDirectory() + report.getFile();
			
			log.info("Loading BIRT report design: " + report.getFile());
			
			IReportRunnable design = engine.openReportDesign(designFile);
			
			IRunAndRenderTask task = engine.createRunAndRenderTask(design);
			task.setAppContext(contextMap);			
			
			handleDataSourceOverrides(design);				
			
			if (input.getExportType() == ReportEngine.EXPORT_PDF)
			{			
				output.setContentType(ReportEngineOutput.CONTENT_TYPE_PDF);
				options.setOutputFormat(HTMLRenderOption.OUTPUT_FORMAT_PDF);				
			}
			else if (input.getExportType() == ReportEngine.EXPORT_HTML)
			{			
				output.setContentType(ReportEngineOutput.CONTENT_TYPE_HTML);
				options.setOutputFormat(HTMLRenderOption.OUTPUT_FORMAT_HTML);				
			}
			else
			{
				log.error("Export type not yet implemented: " + input.getExportType());
			}
			
			task.setRenderOption(options);
			task.setParameterValues(parameters);
			task.validateParameters();
			
			log.info("Generating BIRT report: " + report.getName());
			
			task.run();						
			task.close();
			
			log.info("Finished Generating BIRT report: " + report.getName());
			
			output.setContent(out.toByteArray());
		}
		catch (Throwable e)
		{
			log.error("generateReport Exception", e);			
			throw new ProviderException(e.toString());
		}		
		
		return output;
	}	
	
	/*
	 * Overrides BIRT DataSources with OpenReports DataSources. In order
	 * for DataSources to be overriden, the name of the DataSource in the
	 * BIRT rptdesign file must match the name of an existing OpenReports
	 * DataSource.
	 */
	private void handleDataSourceOverrides(IReportRunnable design)
	{		
		ReportDesignHandle reportDH = (ReportDesignHandle) design.getDesignHandle();
		
		List birtDataSources = reportDH.getAllDataSources();
		
		if (birtDataSources == null) return;
		
		Iterator iterator = birtDataSources.iterator();
		while (iterator.hasNext())
		{
			OdaDataSourceHandle dataSH = (OdaDataSourceHandle) iterator.next();

			try
			{
				ReportDataSource reportDataSource = dataSourceProvider
						.getDataSource(dataSH.getName());

				if (reportDataSource != null)
				{		
					log.info("Overriding BIRT DataSource: " + dataSH.getName());
					
					log.debug("Original connection properties for: " + dataSH.getName());
					log.debug("URL:    " + dataSH.getStringProperty("odaURL"));
					log.debug("DRIVER:    " + dataSH.getStringProperty("odaDriverClass"));
					log.debug("USER:   " + dataSH.getStringProperty("odaUser"));							

					try
					{
						dataSH.setStringProperty("odaURL", reportDataSource.getUrl());
						dataSH.setStringProperty("odaDriverClass", reportDataSource.getDriverClassName());
						dataSH.setStringProperty("odaUser", reportDataSource.getUsername());
						dataSH.setStringProperty("odaPassword", reportDataSource.getPassword());
					}
					catch (SemanticException e)
					{
						log.error("SemanticException", e);
					}
					
					log.debug("New connection properties for: " + dataSH.getName());
					log.debug("URL:    " + dataSH.getStringProperty("odaURL"));
					log.debug("DRIVER:    " + dataSH.getStringProperty("odaDriverClass"));
					log.debug("USER:   " + dataSH.getStringProperty("odaUser"));					
				}
				else
				{
					log.info("Unknown data source: " + dataSH.getName());
				}
			}
			catch (ProviderException pe)
			{
				log.error("ProviderException", pe);
			}
		}	
		
		design.setDesignHandle(reportDH);
	}
	
	public List buildParameterList(Report report) throws ProviderException	
	{
		IReportEngine engine = BirtProvider
		.getBirtEngine(directoryProvider.getReportDirectory() + "platform");
		
		String designFile = directoryProvider.getReportDirectory() + report.getFile();
		IReportRunnable design = null;
		
		try
		{
			design = engine.openReportDesign(designFile);
		}
		catch (Throwable e)
		{
			log.error("getParameterNames Exception", e);			
			throw new ProviderException(e.toString());
		}	
		
		IGetParameterDefinitionTask task = engine
				.createGetParameterDefinitionTask(design);
		
		Collection params = task.getParameterDefns(true);
		
		ArrayList parameters = new ArrayList();

		Iterator iter = params.iterator();
		// Iterate over all parameters
		while (iter.hasNext())
		{
			IParameterDefnBase param = (IParameterDefnBase) iter.next();
			// Group section found
			if (param instanceof IParameterGroupDefn)
			{
				// Get Group Name
				IParameterGroupDefn group = (IParameterGroupDefn) param;				

				// Get the parameters within a group
				Iterator i2 = group.getContents().iterator();
				while (i2.hasNext())
				{
					IScalarParameterDefn scalar = (IScalarParameterDefn) i2.next();

					ReportParameter rp = new ReportParameter();
					rp.setClassName("java.lang.String");
					rp.setDescription(scalar.getName());
					rp.setName(scalar.getName());
					rp.setType(ReportParameter.TEXT_PARAM);			

					parameters.add(rp);
				}
			}
			else
			{
				// Parameters are not in a group
				IScalarParameterDefn scalar = (IScalarParameterDefn) param;	
				
				ReportParameter rp = new ReportParameter();
				rp.setClassName("java.lang.String");
				rp.setDescription(scalar.getName());
				rp.setName(scalar.getName());
				rp.setType(ReportParameter.TEXT_PARAM);			

				parameters.add(rp);							
			}
		}
		
		task.close();
		
		return parameters;
	}	
}
	
