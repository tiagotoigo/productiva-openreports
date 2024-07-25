package br.com.productiva.engine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.util.JRQueryExecuter;

import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.RowSetDynaClass;
import org.efs.openreports.ORStatics;
import org.efs.openreports.engine.input.ReportEngineInput;
import org.efs.openreports.engine.output.QueryEngineOutput;
import org.efs.openreports.engine.output.ReportEngineOutput;
import org.efs.openreports.objects.Report;
import org.efs.openreports.objects.ReportChart;
import org.efs.openreports.objects.ReportDataSource;
import org.efs.openreports.objects.chart.CategoryChartValue;
import org.efs.openreports.objects.chart.ChartValue;
import org.efs.openreports.objects.chart.PieChartValue;
import org.efs.openreports.objects.chart.TimeChartValue;
import org.efs.openreports.objects.chart.XYChartValue;
import org.efs.openreports.providers.DataSourceProvider;
import org.efs.openreports.providers.DirectoryProvider;
import org.efs.openreports.providers.PropertiesProvider;
import org.efs.openreports.providers.ProviderException;
import org.efs.openreports.util.DisplayProperty;
import org.efs.openreports.util.ORUtil;

import java.math.BigDecimal;
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

	private  void createChartOutput(ReportChart reportChart, ChartValue[] values)
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
			case ReportChart.MAP :
				createMap(reportChart, values);
				break;	
		}
	}
	
	public void createMap(ReportChart reportChart, ChartValue[] values) {
		
		String htmlMapa = obterDadosParaMontarHTMLMapa(values[0]);
		String htmlResultados = obterDadosParaMontarHTMLTabelaResultados(values[0]);
    	ActionContext.getContext().getSession().put(ORStatics.IMAGES_MAP, htmlMapa);
    	ActionContext.getContext().getSession().put(ORStatics.QUERY_REPORT_RESULTS, htmlResultados);
	}


	private String obterDadosParaMontarHTMLTabelaResultados(
			ChartValue chartValue) {
		GoogleMap googlemap = new GoogleMap();
		return googlemap.getEnvelopeResultados((MapValue) chartValue);
	}


	private String obterDadosParaMontarHTMLMapa(ChartValue chartValue) {
		GoogleMap googlemap = new GoogleMap();
		return googlemap.getEnvelopeMapa((MapValue) chartValue);
	}


	private static void createBarChart(ReportChart reportChart,ChartValue[] values) {
		MSLine msLine = new BarChart();
		if (reportChart.getEngineGrafica() == 2) {
			msLine = new BarChartHighCharts();
			msLine.setReportChart(reportChart);
			obterDadosParaMontarXmlBarChartHighCharts(msLine,(ChartValue[])values);
		} else {
			msLine.setReportChart(reportChart);
			obterDadosParaMontarXmlBarChart(msLine,(ChartValue[])values);
		}
		String xml = msLine.getEnvelope();
    	ActionContext.getContext().getSession().put(ORStatics.IMAGES_MAP, xml);
	}

	private static void createPieChart(ReportChart reportChart,ChartValue[] values) {
		PieChart pieChart = new PieChart();
		if (reportChart.getEngineGrafica() == 2) {
			pieChart = new PieChartHighCharts();
		}
		pieChart.setReportChart(reportChart);
		obterDadosParaMontarXml(pieChart,values);
		String xml = pieChart.getEnvelope();
    	ActionContext.getContext().getSession().put(ORStatics.IMAGES_MAP, xml);	
    }
	
	private static void createXYChart(ReportChart reportChart,ChartValue[] values) {
		MSLine msLine = new XYChart();
		if (reportChart.getEngineGrafica() == 2) {
			msLine = new MSLineHighCharts();
		}
		msLine.setReportChart(reportChart);
		obterDadosParaMontarXmlXYChart(msLine,(ChartValue[])values);
		String xml = msLine.getEnvelope();
    	ActionContext.getContext().getSession().put(ORStatics.IMAGES_MAP, xml);
	}

	private static void createTimeChart(ReportChart reportChart,ChartValue[] values) {
		MSLine msLine = new MSLine();
		if (reportChart.getEngineGrafica() == 2) {
			msLine = new MSLineHighCharts();
		}
		msLine.setReportChart(reportChart);
		obterDadosParaMontarXml(msLine,values);
		String xml = msLine.getEnvelope();
    	ActionContext.getContext().getSession().put(ORStatics.IMAGES_MAP, xml);
	}


	private static void obterDadosParaMontarXml(MSLine msLine,ChartValue[] values) {
		
		// Na 1a iteração abaixo, criam-se as categorias 
		for (int i = 0; i < values.length; i++) {
			TimeChartValue chartValue = (TimeChartValue)values[i];
			if (!msLine.getCategories().contains(UtilData.getData(chartValue.getTime()))) {
				msLine.getCategories().add(UtilData.getData(chartValue.getTime()));
			}
		}
		ordenarListaCategorias(msLine);
		// Na 2a iteração criam-se: series e seus valores
		String nomeSerieAux = null;
		List lista = new ArrayList();
		for (int i = 0; i < values.length; i++) {
			TimeChartValue chartValue = (TimeChartValue)values[i];
			String nomeSerie = chartValue.getSeries();
			DataSetItem item = new DataSetItem();
			item.setNomeSerie(nomeSerie);
			item.setTempo(chartValue.getTime());
			item.setValor(chartValue.getValue());
			if (nomeSerieAux == null || nomeSerieAux.equals(nomeSerie)) {
				//se entrou aqui significa que não mudou
				lista.add(item);
			} else {
				msLine.getDataSets().add(lista);
				lista = new ArrayList();
				lista.add(item);
			}
			nomeSerieAux = nomeSerie;
		}
		msLine.getDataSets().add(lista);

	}
	
	private static void obterDadosParaMontarXmlBarChart(MSLine msLine,ChartValue[] values) {
		
		// Nesta iteração criam-se: series e seus valores
		String nomeSerieAux = null;
		List lista = new ArrayList();
		for (int i = 0; i < values.length; i++) {
			CategoryChartValue chartValue = (CategoryChartValue)values[i];
			String nomeSerie = chartValue.getCategory();
			DataSetItem item = new DataSetItem();
			item.setNomeSerie(nomeSerie);
			item.setValor(chartValue.getValue());
			lista.add(item);
		}
		msLine.getDataSets().add(lista);

	}
	
	private static void obterDadosParaMontarXml(PieChart pieChart,ChartValue[] values) {
		
		// Nesta iteração criam-se: series e seus valores
		List lista = new ArrayList();
		for (int i = 0; i < values.length; i++) {
			PieChartValue chartValue = (PieChartValue)values[i];
			String nomeSerie = chartValue.getKey();
			DataSetItem item = new DataSetItem();
			item.setNomeSerie(nomeSerie);
			item.setValor(chartValue.getValue());
			lista.add(item);
		}
		pieChart.getDataSets().add(lista);
	}

	private static void obterDadosParaMontarXmlXYChart(MSLine msLine,ChartValue[] values) {
		
		// Na 1a iteração abaixo, criam-se as categorias 
		for (int i = 0; i < values.length; i++) {
			XYChartValue chartValue = (XYChartValue)values[i];
			if (!msLine.getCategories().contains(""+chartValue.getValue())) {
				msLine.getCategories().add(""+chartValue.getValue());
			}
		}
		ordenarListaCategoriasXYChart(msLine);
		// Na 2a iteração criam-se: series e seus valores
		String nomeSerieAux = null;
		List lista = new ArrayList();
		for (int i = 0; i < values.length; i++) {
			XYChartValue chartValue = (XYChartValue)values[i];
			String nomeSerie = chartValue.getSeries();
			DataSetItem item = new DataSetItem();
			item.setNomeSerie(nomeSerie);
			item.setSegundoValor(chartValue.getValue());
			item.setValor(chartValue.getSecondValue());
			if (nomeSerieAux == null || nomeSerieAux.equals(nomeSerie)) {
				//se entrou aqui significa que não mudou
				lista.add(item);
			} else {
				msLine.getDataSets().add(lista);
				lista = new ArrayList();
				lista.add(item);
			}
			nomeSerieAux = nomeSerie;
		}
		msLine.getDataSets().add(lista);
	}
	
	private static void obterDadosParaMontarXmlBarChartHighCharts(MSLine msLine,ChartValue[] values) {
		
		//Na 1a iteração criam-se: categorias
		for (int i = 0; i < values.length; i++) {
			CategoryChartValue chartValue = (CategoryChartValue)values[i];
			if (!msLine.getCategories().contains(""+chartValue.getCategory())) {
				msLine.getCategories().add(""+chartValue.getCategory());
			}
		}
		// Na 2a iteração criam-se: series e seus valores
		List lista = new ArrayList();
		for (int i = 0; i < values.length; i++) {
			CategoryChartValue chartValue = (CategoryChartValue)values[i];
			String nomeSerie = chartValue.getSeries();
			DataSetItem item = new DataSetItem();
			item.setNomeSerie(nomeSerie);
			item.setValor(chartValue.getValue());
			lista.add(item);
		}
		msLine.getDataSets().add(lista);
	}

	private static void ordenarListaCategorias(MSLine msLine) {
		ArrayList categorias = new ArrayList(msLine.getCategories());
		Object[] array = (Object[])categorias.toArray();
		
		String eleito = "";
		for (int i = 0; i < array.length; i++) {
			eleito = (String)array[i];
			int j; 
			for (j = i-1; (j >= 0) && (UtilData.getComparaData(eleito,(String)array[j] )); j--) {
				array[j+1] = array[j];
			}
			array[j+1] = eleito;
		}
		ArrayList categoriasOrdenadas = new ArrayList();
		for (int i = 0; i < array.length; i++) {
			categoriasOrdenadas.add((String)array[i]);
		}
		msLine.setCategories(categoriasOrdenadas);
		
	}
	private static void ordenarListaCategoriasXYChart(MSLine msLine) {
		ArrayList categorias = new ArrayList(msLine.getCategories());
		Object[] array = (Object[])categorias.toArray();
		BigDecimal[] array2 = new BigDecimal[categorias.size()];
		for (int i = 0; i < array.length; i++) {
			array2[i] = new BigDecimal((String)array[i]);
		}
		BigDecimal eleito;
		for (int i = 0; i < array2.length; i++) {
			eleito = array2[i];
			int j; 
			for (j = i-1; (j >= 0) && (eleito.compareTo(array2[j]) < 0 ); j--) {
				array[j+1] = array[j];
			}
			array[j+1] = eleito;
		}
		ArrayList categoriasOrdenadas = new ArrayList();
		for (int i = 0; i < array2.length; i++) {
			categoriasOrdenadas.add(array2[i]);
		}
		msLine.setCategories(categoriasOrdenadas);
		
	}

	private static void createRingChart(ReportChart reportChart,ChartValue[] values) {
		PieChart pieChart = new RingChart();
		if (reportChart.getEngineGrafica() == 2) {
			pieChart = new RingChartHighCharts();
		}
		pieChart.setReportChart(reportChart);
		obterDadosParaMontarXml(pieChart,values);
		String xml = pieChart.getEnvelope();
    	ActionContext.getContext().getSession().put(ORStatics.IMAGES_MAP, xml);
	}
	

}
