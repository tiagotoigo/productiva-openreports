package br.com.productiva.engine;

import java.util.ArrayList;
import java.util.List;

import org.efs.openreports.ORStatics;
import org.efs.openreports.engine.input.ReportEngineInput;
import org.efs.openreports.objects.ReportChart;
import org.efs.openreports.objects.chart.ChartValue;
import org.efs.openreports.objects.chart.TimeChartValue;
import org.efs.openreports.providers.DataSourceProvider;
import org.efs.openreports.providers.DirectoryProvider;
import org.efs.openreports.providers.PropertiesProvider;
import org.efs.openreports.providers.ProviderException;

import com.opensymphony.xwork.ActionContext;

public class ChartReportEngineProductiva extends
		org.efs.openreports.engine.ChartReportEngine {

	public ChartReportEngineProductiva(DataSourceProvider dataSourceProvider,
			DirectoryProvider directoryProvider,
			PropertiesProvider propertiesProvider) {
		super(dataSourceProvider, directoryProvider, propertiesProvider);
		// TODO Auto-generated constructor stub
	}

	
	public void criarRelatorio(ReportEngineInput input)
			throws ProviderException {
		ChartValue[] values = getChartValues(input.getReport().getReportChart(), input.getParameters());
		createChartOutput(input.getReport().getReportChart(), values);
		
	}

	private static void createChartOutput(ReportChart reportChart, ChartValue[] values)
	{

		switch (reportChart.getChartType())
		{
			case ReportChart.BAR_CHART :				
				createBarChart(reportChart, values);				
				break;
			case ReportChart.PIE_CHART :
				createPieChart(reportChart, values);								
				break;
			case ReportChart.XY_CHART :
				createXYChart(reportChart, values);
				break;
			case ReportChart.TIME_CHART :
				createTimeChart(reportChart, values);
				break;
			case ReportChart.RING_CHART :
				createRingChart(reportChart, values);
				break;			
		}
	}
	
	private static void createBarChart(ReportChart reportChart,ChartValue[] values) {

	}

	private static void createPieChart(ReportChart reportChart,ChartValue[] values) {

	}
	
	private static void createXYChart(ReportChart reportChart,ChartValue[] values) {

	}

	private static void createTimeChart(ReportChart reportChart,ChartValue[] values) {
		MSLine msLine = new MSLine();
		obterDadosParaMontarXml(msLine,values);	
		
		//ActionContext.getContext().getSession().put(ORStatics.IMAGES_MAP, msLine.getEnvelope());
		ActionContext.getContext().getSession().put(ORStatics.IMAGES_MAP, "teste");
	}


	private static void obterDadosParaMontarXml(MSLine msLine,ChartValue[] values) {
		
		// A Lógica deve ser a seguinte: Este método recebe a lista já ordenada.
		// Portanto, na 1a iteração abaixo, criam-se as categorias 
		for (int i = 0; i < values.length; i++) {
			TimeChartValue chartValue = (TimeChartValue)values[i];
			String nomeSerie = chartValue.getSeries();
			if (!msLine.getCategories().contains(UtilData.getData(chartValue.getTime()))) {
				msLine.getCategories().add(UtilData.getData(chartValue.getTime()));
			} else {
				System.out.println("Já possui a data");
			}
		}
		// Portanto, na 2a iteração criam-se: series e seus valores
		String nomeSerieAux = null;
		List lista = new ArrayList();
		for (int i = 0; i < values.length; i++) {
			TimeChartValue chartValue = (TimeChartValue)values[i];
			String nomeSerie = chartValue.getSeries();
			DataSetItem item = new DataSetItem();
			item.setTempo(chartValue.getTime());
			item.setValor(chartValue.getValue());
			if (nomeSerieAux == null || nomeSerieAux.equals(nomeSerie)) {
				//se entrou aqui significa que não mudou
				lista.add(item);
			} else {
				msLine.getDataSets().add(lista);
				lista = new ArrayList();
			}
			nomeSerieAux = nomeSerie;
		}
		msLine.getDataSets().add(lista);
	}

	private static void createRingChart(ReportChart reportChart,ChartValue[] values) {

	}
	
}
