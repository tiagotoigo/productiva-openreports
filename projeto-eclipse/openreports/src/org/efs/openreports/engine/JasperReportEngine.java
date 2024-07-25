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

package org.efs.openreports.engine;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRQueryExecuter;

import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.RowSetDynaClass;
import org.apache.log4j.Logger;
import org.efs.openreports.engine.input.ReportEngineInput;
import org.efs.openreports.engine.output.JasperReportEngineOutput;
import org.efs.openreports.engine.output.ReportEngineOutput;
import org.efs.openreports.objects.Report;
import org.efs.openreports.objects.ReportDataSource;
import org.efs.openreports.objects.ReportExportOption;
import org.efs.openreports.objects.ReportParameter;
import org.efs.openreports.objects.ReportParameterMap;
import org.efs.openreports.providers.DataSourceProvider;
import org.efs.openreports.providers.DirectoryProvider;
import org.efs.openreports.providers.PropertiesProvider;
import org.efs.openreports.providers.ProviderException;
import org.efs.openreports.util.ORUtil;

/**
 * JasperReports ReportEngine implementation. Report generation is separated
 * into fillReport and exportReport methods in order to provide direct access 
 * to the JasperPrint object when required.
 * 
 * @author Erik Swenson
 * 
 */
public class JasperReportEngine extends ReportEngine
{
	protected static Logger log = Logger.getLogger(JasperReportEngine.class.getName());	
	
	public JasperReportEngine(DataSourceProvider dataSourceProvider,
			DirectoryProvider directoryProvider, PropertiesProvider propertiesProvider)
	{
		super(dataSourceProvider,directoryProvider, propertiesProvider);
	}
	
	public ReportEngineOutput generateReport(ReportEngineInput input)
			throws ProviderException
	{		
		JasperPrint jasperPrint = fillReport(input);

		ReportEngineOutput engineOutput = exportReport(jasperPrint,
				input.getExportType(), input.getReport().getReportExportOption(), input
						.getImagesMap(), input.isInlineImages());
		
		return engineOutput;
	}	
	
	public JasperPrint fillReport(ReportEngineInput input) throws ProviderException
	{
		Connection conn = null;

		Report report = input.getReport();
		Map parameters = input.getParameters();
		
		ReportDataSource dataSource = report.getDataSource();

		try
		{
			JasperReport jr = null;

			if (report.isQueryReport()) return fillQueryReport(report, parameters, input.getExportType());

			jr = (JasperReport) JRLoader
					.loadObject(directoryProvider.getReportDirectory() + report.getFile());

			List subReports = report.getSubReportParameters();
			if (subReports != null && subReports.size() > 0)
			{
				Iterator iterator = report.getSubReportParameters().iterator();
				while (iterator.hasNext())
				{
					ReportParameterMap rpMap = (ReportParameterMap) iterator.next();

					JasperReport subReport = (JasperReport) JRLoader.loadObject(directoryProvider
							.getReportDirectory()
							+ rpMap.getReportParameter().getData());

					parameters.put(rpMap.getReportParameter().getName(), subReport);
				}
			}

			JasperPrint jp = null;
			// Tiago
			parameters.put("SUBREPORT_DIR", "c:\\stage\\gerador-tomcat\\reports\\");
			// create new HashMap to send to JasperReports in order to
			// fix serialization problems
			Map jasperReportMap = new HashMap(parameters);

			if (dataSource == null)
			{

				jp = JasperFillManager.fillReport(jr, jasperReportMap, new JREmptyDataSource());
			}
			else
			{
				conn = dataSourceProvider.getConnection(dataSource.getId());
				jp = JasperFillManager.fillReport(jr, jasperReportMap, conn);
			}

			if (jp == null || jp.getPages().size() < 1) throw new ProviderException("Report Empty");

			return jp;
		}
		catch (Exception e)
		{
			String errorMessage;
			if (e.getCause() instanceof java.io.InvalidClassException)
			{
				errorMessage = "Error creating report: " + report.getName()
						+ " was compiled with a different version of JasperReports.";
			}
			else
			{
				errorMessage = "Error creating report: " + e.toString();
			}

			log.error(errorMessage, e);
			throw new ProviderException(errorMessage);

		}
		finally
		{
			try
			{
				if (conn != null) conn.close();
			}
			catch (Exception ex)
			{
				log.error("Error closing connection: " + ex.getMessage());
			}
		}
	}	
	
	public ReportEngineOutput exportReport(JasperPrint jasperPrint, int exportType,
			ReportExportOption exportOptions, Map imagesMap, boolean inlineImages) throws ProviderException
	{		
		JasperReportEngineOutput engineOutput = new JasperReportEngineOutput();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();		
		
		JRAbstractExporter exporter = null;		
		
		try
		{
			if (exportType == ReportEngine.EXPORT_PDF)
			{
				engineOutput.setContentType(ReportEngineOutput.CONTENT_TYPE_PDF);
				
				exporter = new JRPdfExporter();				
			}
			else if (exportType == ReportEngine.EXPORT_XLS
					|| exportType == ReportEngine.EXPORT_EXCEL)
			{
				engineOutput.setContentType(ReportEngineOutput.CONTENT_TYPE_XLS);
				
				if (exportType == ReportEngine.EXPORT_XLS)
				{
					exporter = new JRXlsExporter();
				}
				else if (exportType == ReportEngine.EXPORT_EXCEL)
				{
					exporter = new JExcelApiExporter();
				}

				exporter.setParameter(
						JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
						new Boolean(exportOptions.isXlsRemoveEmptySpaceBetweenRows()));

				exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
						new Boolean(exportOptions.isXlsOnePagePerSheet()));

				exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
						new Boolean(exportOptions.isXlsAutoDetectCellType()));

				exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
						new Boolean(exportOptions.isXlsWhitePageBackground()));
			}
			else if (exportType == ReportEngine.EXPORT_CSV)
			{
				engineOutput.setContentType(ReportEngineOutput.CONTENT_TYPE_CSV);
				
				exporter = new JRCsvExporter();
			}
			else if (exportType == ReportEngine.EXPORT_TEXT)
			{
				engineOutput.setContentType(ReportEngineOutput.CONTENT_TYPE_TEXT);
				
				exporter = new JRTextExporter();
				exporter.setParameter(JRTextExporterParameter.CHARACTER_WIDTH,
						new Integer(10));
				exporter.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT,
						new Integer(10));
			}
			else if (exportType == ReportEngine.EXPORT_RTF)
			{
				engineOutput.setContentType(ReportEngineOutput.CONTENT_TYPE_RTF);
				
				exporter = new JRRtfExporter();
			}
			else
			{
				engineOutput.setContentType(ReportEngineOutput.CONTENT_TYPE_HTML);
				
				exporter = new JRHtmlExporter();

				exporter.setParameter(
						JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
						new Boolean(exportOptions.isHtmlRemoveEmptySpaceBetweenRows()));

				exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
						new Boolean(exportOptions.isHtmlUsingImagesToAlign()));

				exporter.setParameter(JRHtmlExporterParameter.IS_WHITE_PAGE_BACKGROUND,
						new Boolean(exportOptions.isHtmlWhitePageBackground()));

				exporter.setParameter(JRHtmlExporterParameter.IS_WRAP_BREAK_WORD,
						new Boolean(exportOptions.isHtmlWrapBreakWord()));
				
				if (imagesMap == null) imagesMap = new HashMap();
				exporter.setParameter(JRHtmlExporterParameter.IMAGES_MAP, imagesMap);
				
				if (inlineImages)
				{
					exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, "cid:");
				}
				else
				{
					//see ImageLoaderAction for more information
					exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,
						"imageLoader.action?imageName=");
				}
			}
			exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "ISO-8859-1");
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
			exporter.exportReport();
		}
		catch (Exception e)
		{
			throw new ProviderException(e.toString());
		}
		
		engineOutput.setImagesMap(imagesMap);
		engineOutput.setContent(outputStream.toByteArray());
		
		return engineOutput;
	}	
	
	/*
	 * Creates a default JasperPrint from a QueryReport. This method is used when
	 * a scheduled QueryReport is executed.
	 */
	private JasperPrint fillQueryReport(Report report, Map map, int exportType) throws Exception
	{
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;

		// create new HashMap to send to JasperReports in order to
		// fix serialization problems
		Map parameters = new HashMap(map);
		
		List results = null;
		DynaProperty[] properties = null;

		try
		{
			ReportDataSource dataSource = report.getDataSource();
			conn = dataSourceProvider.getConnection(dataSource.getId());

			if (parameters == null || parameters.isEmpty())
			{
				pStmt = conn.prepareStatement(report.getQuery());
			}
			else
			{
				// Use JasperReports Query logic to parse parameters in chart
				// queries

				JRDesignQuery query = new JRDesignQuery();
				query.setText(report.getQuery());

				// convert parameters to JRDesignParameters so they can be
				// parsed
				Map jrParameters = ORUtil.buildJRDesignParameters(parameters);

				pStmt = JRQueryExecuter.getStatement(query, jrParameters, parameters, conn);
			}

			rs = pStmt.executeQuery();

			RowSetDynaClass rowSetDynaClass = new RowSetDynaClass(rs);

			results = rowSetDynaClass.getRows();
			properties = rowSetDynaClass.getDynaProperties();

			rs.close();
		}
		catch (Exception e)
		{
			throw new ProviderException("Error executing report query: " + e.getMessage());
		}
		finally
		{
			try
			{
				if (pStmt != null) pStmt.close();
				if (conn != null) conn.close();
			}
			catch (Exception c)
			{
				log.error("Error closing");
			}
		}

		JasperDesign jasperDesign = new JasperDesign();
		jasperDesign.setName(report.getName().replaceAll(" ", "_"));
		
		int width = jasperDesign.getPageWidth();
		int height = jasperDesign.getPageHeight();
		
		jasperDesign.setOrientation(JasperDesign.ORIENTATION_LANDSCAPE);		
		jasperDesign.setPageHeight(width);
		jasperDesign.setPageWidth(height);

		for (int i = 0; i < properties.length; i++)
		{

			JRDesignField field = new JRDesignField();
			field.setName((String) properties[i].getName());
			field.setValueClass((Class) properties[i].getType());

			try
			{
				jasperDesign.addField(field);
			}
			catch (Exception e)
			{
				log.warn(e);
			}
		}

		if (exportType == ReportEngine.EXPORT_PDF)
		{
			// add title
			JRDesignStaticText sText = new JRDesignStaticText();
			sText.setX(0);
			sText.setY(0);
			sText.setWidth(jasperDesign.getPageHeight());
			sText.setHeight(50);
			sText.setText(jasperDesign.getName());
			sText.setFontSize(16);
			sText.setBold(true);

			JRDesignBand band = new JRDesignBand();
			band.setHeight(50);
			band.addElement(sText);

			jasperDesign.setTitle(band);
		
			// add page footer for page numbers
			band = new JRDesignBand();
			band.setHeight(15);

			sText = new JRDesignStaticText();
			sText.setX(0);
			sText.setY(0);
			sText.setHeight(15);
			sText.setWidth(40);
			sText.setText("Page:");

			band.addElement(sText);

			JRDesignExpression exp = new JRDesignExpression();
			exp.addVariableChunk("PAGE_NUMBER");
			exp.setValueClass(Integer.class);

			JRDesignTextField txt = new JRDesignTextField();
			txt.setExpression(exp);
			txt.setX(40);
			txt.setY(0);
			txt.setHeight(15);
			txt.setWidth(100);

			band.addElement(txt);

			jasperDesign.setPageFooter(band);
		}

		JRDesignBand emptyBand = new JRDesignBand();
		emptyBand.setHeight(0);
		jasperDesign.setPageHeader(emptyBand);
		jasperDesign.setColumnFooter(emptyBand);
		jasperDesign.setSummary(emptyBand);

		JRField[] fields = jasperDesign.getFields();

		// add column header and detail bands
		JRDesignBand bandDetail = new JRDesignBand();
		bandDetail.setHeight(20);

		JRDesignBand bandHeader = new JRDesignBand();
		bandHeader.setHeight(20);
		
		int fieldWidth = (jasperDesign.getPageWidth() - jasperDesign.getLeftMargin()
				- jasperDesign.getRightMargin() - (fields.length - 1) * jasperDesign.getColumnSpacing())
				/ fields.length;	

		for (int i = 0; i < fields.length; i++)
		{
			try
			{
				JRField field = fields[i];

				JRDesignExpression exp = new JRDesignExpression();
				exp.addFieldChunk(field.getName());
				
				if (field.getValueClassName().equals("java.sql.Date"))
				{
					// JasperReports does not support java.sql.Date in text field expression
					exp.setValueClass(java.util.Date.class);
				}
				else
				{
					exp.setValueClass(field.getValueClass());
				}

				JRDesignTextField txt = new JRDesignTextField();
				txt.setExpression(exp);
				txt.setX(i * fieldWidth);
				txt.setY(0);
				txt.setHeight(20);
				txt.setWidth(fieldWidth);

				if (field.getValueClass().equals(Double.class))
				{
					txt.setPattern("0.00");
				}

				bandDetail.addElement(txt);

				JRDesignStaticText sText = new JRDesignStaticText();
				sText.setX(i * fieldWidth);
				sText.setY(0);
				sText.setHeight(20);
				sText.setWidth(fieldWidth);
				sText.setText(field.getName());
				sText.setUnderline(true);

				bandHeader.addElement(sText);
			}
			catch (Exception e)
			{
				log.warn(e);
			}
		}

		if (exportType == ReportEngine.EXPORT_PDF) jasperDesign.setColumnHeader(bandHeader);
		jasperDesign.setDetail(bandDetail);
		
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
				new JRBeanCollectionDataSource(results));

		return jasperPrint;
	}	
	
	public List buildParameterList(Report report) throws ProviderException
	{
		try
		{
			JasperReport jasperReport = (JasperReport) JRLoader
			.loadObject(directoryProvider.getReportDirectory() + report.getFile());
			
			ArrayList parameters = new ArrayList();
			
			JRParameter[] jrParameters = jasperReport.getParameters();			
			for (int i=0; i < jrParameters.length; i++)
			{
				if (!jrParameters[i].isSystemDefined())
				{
					ReportParameter rp = new ReportParameter();
					rp.setClassName(jrParameters[i].getValueClassName());
					rp.setDescription(jrParameters[i].getName());
					rp.setName(jrParameters[i].getName());
					rp.setType(ReportParameter.TEXT_PARAM);			

					parameters.add(rp);
				}
			}
			
			return parameters;
		}
		catch(JRException e)
		{
			throw new ProviderException(e);
		}
	}
}