package br.com.productiva.actions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.efs.openreports.ORStatics;
import org.efs.openreports.actions.QueryReportAction;
import org.efs.openreports.engine.ChartReportEngine;
import org.efs.openreports.engine.QueryReportEngine;
import org.efs.openreports.engine.input.ReportEngineInput;
import org.efs.openreports.engine.output.ChartEngineOutput;
import org.efs.openreports.engine.output.QueryEngineOutput;
import org.efs.openreports.objects.Report;
import org.efs.openreports.objects.ReportLog;
import org.efs.openreports.objects.ReportUser;
import org.efs.openreports.objects.chart.ChartValue;
import org.efs.openreports.providers.ProviderException;
import org.efs.openreports.util.LocalStrings;
import org.jfree.chart.imagemap.ImageMapUtilities;

import br.com.productiva.engine.ChartReportEngineProductiva;

import com.opensymphony.xwork.ActionContext;

public class ChartReportActionProductiva extends QueryReportAction 
{
	protected static Logger log = Logger.getLogger(ChartReportActionProductiva.class);
	
	private String imageMap;
	private String fusionChart;
	private ChartValue[] chartValues;
	public String altura;
	public String largura;
	
	private static final String SUCCESS2 = "success2"; 

	public String execute()
	{		
		ReportUser user = (ReportUser) ActionContext.getContext().getSession().get(
				ORStatics.REPORT_USER);

		report = (Report) ActionContext.getContext().getSession().get(ORStatics.REPORT);

		Map reportParameters = getReportParameterMap(user);

		ReportLog reportLog = new ReportLog(user, report, new Date());

		try
		{
			log.debug("Starting Chart Report: " + report.getName());			

			reportLogProvider.insertReportLog(reportLog);				
			
			ReportEngineInput input = new ReportEngineInput(report, reportParameters);
			
			if (report.getReportChart().getChartType()  == 5) {
				//Mapas
				geraGoogleMap(input);
				setAltura(report.getReportChart().getMapHeight()+"px");
				setLargura(report.getReportChart().getMapWidth()+"px");
				return SUCCESS2;
			} else if (report.getReportChart().getEngineGrafica() == 1 || report.getReportChart().getEngineGrafica() == 2) {
				geraGraficoJFreeChart(input);
				geraGraficoFusionChart(input);
			} else {
				geraGraficoJFreeChart(input);
			}
			reportLog.setEndTime(new Date());
			reportLog.setStatus(ReportLog.STATUS_SUCCESS);
			reportLogProvider.updateReportLog(reportLog);

			log.debug("Finished Chart Report: " + report.getName());
		}
		catch (Exception e)
		{
			addActionError(e.getMessage());

			log.error(e.toString());

			reportLog.setMessage(e.getMessage());
			reportLog.setStatus(ReportLog.STATUS_FAILURE);

			reportLog.setEndTime(new Date());

			try
			{
				reportLogProvider.updateReportLog(reportLog);
			}
			catch (Exception ex)
			{
				log.error("Unable to create ReportLog: " + ex.getMessage());
			}

			return ERROR;
		}		

		return SUCCESS;
	}

	private void geraGoogleMap(ReportEngineInput input) {
		ChartReportEngineProductiva chartReportEngine = new ChartReportEngineProductiva(
				dataSourceProvider, directoryProvider, propertiesProvider);
		try {
			chartReportEngine.criarRelatorio(input);
			imageMap = (String)ActionContext.getContext().getSession().get(ORStatics.IMAGES_MAP);
		}catch (ProviderException e){
			log.error("Erro ao criar FusionChart: " + e.getMessage());
		}
	}

	/**
	 * Usado para criar tanto FusionCharts quanto HighCharts
	 * @param input
	 */
	protected void geraGraficoFusionChart(ReportEngineInput input) {
		ChartReportEngineProductiva chartReportEngine = new ChartReportEngineProductiva(
				dataSourceProvider, directoryProvider, propertiesProvider);
		try {
			chartReportEngine.criarRelatorio(input);
			imageMap = (String)ActionContext.getContext().getSession().get(ORStatics.IMAGES_MAP);
		}catch (ProviderException e){
			log.error("Erro ao criar FusionChart: " + e.getMessage());
		}
	}

	private void geraGraficoJFreeChart(ReportEngineInput input)
			throws ProviderException {
		ChartReportEngine chartReportEngine = new ChartReportEngine(
				dataSourceProvider, directoryProvider, propertiesProvider);

		ChartEngineOutput chartOutput = (ChartEngineOutput) chartReportEngine
				.generateReport(input);	
		
		chartValues = chartOutput.getChartValues();
		if (chartValues.length == 0)
		{
			addActionError(LocalStrings.getString(LocalStrings.ERROR_REPORT_EMPTY));
		}
		
		imageMap = ImageMapUtilities.getImageMap("chart", chartOutput.getChartRenderingInfo());						
		
		HashMap imagesMap = new HashMap();
		imagesMap.put("ChartImage", chartOutput.getContent());			
		
		ActionContext.getContext().getSession().put(ORStatics.IMAGES_MAP, imagesMap);
	}	

	public String getImageMap()
	{
		return imageMap;
	}

	public void setImageMap(String imageMap)
	{
		this.imageMap = imageMap;
	}

	public ChartValue[] getChartValues()
	{
		return chartValues;
	}

	public String getFusionChart() {
		return fusionChart;
	}

	public void setFusionChart(String fusionChart) {
		this.fusionChart = fusionChart;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public String getLargura() {
		return largura;
	}

	public void setLargura(String largura) {
		this.largura = largura;
	}		
}