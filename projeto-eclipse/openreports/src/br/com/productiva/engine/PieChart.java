package br.com.productiva.engine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.efs.openreports.objects.ReportChart;

/**
 * Entidade que representa o Gráfico Time.
 *  
 * @author tiago.toigo
 */
public class PieChart {
	private String graph;
	private List<String> categories;
	private List<List<String>> dataSets;
	private List<String> colour;
	
	private ReportChart reportChart;

	public String getGraph() {
		if (graph == null) {
			graph = "<div style=\"border:1px solid #707070;background-color: white;width:"+(getReportChart().getWidth()+20)+"px;heigth:"+getReportChart().getHeight()+"px;\"> "+
					"<embed type=\"application/x-shockwave-flash\" src=\"fusioncharts/Charts/"+getNomeComponente()+"\" width=\""+(getReportChart().getWidth()+20)+"\" height=\""+getReportChart().getHeight()+"\" id=\"ChartId\" name=\"ChartId\" quality=\"high\" allowscriptaccess=\"always\" "+
					"flashvars=\"chartWidth="+getReportChart().getWidth()+"&chartHeight="+getReportChart().getHeight()+"&debugMode=0&DOMId=ChartId&registerWithJS=0&scaleMode=noScale&lang=EN&dataXML=" +
					"<graph showNames='1' caption='"+getReportChart().getName()+"' subcaption='"+getReportChart().getDescription()+"' "  
				+ "hovercapbg='FFECAA' hovercapborder='F47E00' formatNumberScale='0' decimalPrecision='0' showvalues='0' numdivlines='3' numVdivlines='0' " 
				+ "yaxisminvalue='"+ getReportChart().getXMinimum()+ "' "
				+ "yaxismaxvalue='"+ getReportChart().getXMaximum()+ "' "
				+ "rotateNames='0'> " ;
		}
		return graph;
	}

	protected String getNomeComponente() {
		return "FCF_Pie2D.swf";
	}

	public List<String> getCategories() {
		if (categories == null) {
			categories = new ArrayList<String>();
		}
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public List<List<String>> getDataSets() {
		if (dataSets == null) {
			dataSets = new ArrayList<List<String>>();
		}
		return dataSets;
	}

	public String getEnvelope() {
		String envelope = "";
		String dados = "";
		int aux = 0;
		for (Iterator iterator = getDataSets().iterator(); iterator.hasNext();) {
			List dataset = (List) iterator.next();
			for (Iterator lista = dataset.iterator(); lista.hasNext();) {
				DataSetItem item = (DataSetItem) lista.next();
				dados = dados + "<set name='"+item.getNomeSerie()+"' value='"+item.getValor()+"'/>";
			}
			aux = aux + 1;
		}
		envelope = getGraph() + dados + "</graph>\"/> </div>";
		return envelope;
	}
	
	public double obterMenorValor(){
		Double menorValor = null;
		int aux = 0;
		for (Iterator iterator = getDataSets().iterator(); iterator.hasNext();) {
			
			List dataset = (List) iterator.next();
			DataSetItem itemZero = (DataSetItem) dataset.get(0);
			menorValor = itemZero.getValor();
			for (Iterator lista = dataset.iterator(); lista.hasNext();) {
				DataSetItem item = (DataSetItem) lista.next();
				 if (item.getValor() < menorValor) {
					 menorValor = item.getValor() ;
				 }
			}
			aux = aux + 1;
		}
		return menorValor;
	}
	
	public double obterMaiorValor(){
		Double maiorValor = null;
		int aux = 0;
		for (Iterator iterator = getDataSets().iterator(); iterator.hasNext();) {
			
			List dataset = (List) iterator.next();
			DataSetItem itemZero = (DataSetItem) dataset.get(0);
			maiorValor  = itemZero.getValor();
			for (Iterator lista = dataset.iterator(); lista.hasNext();) {
				DataSetItem item = (DataSetItem) lista.next();
				 if (item.getValor() > maiorValor) {
					 maiorValor = item.getValor() ;
				 }
			}
			aux = aux + 1;
		}
		return maiorValor;
	}
	
	public List<String> getColour() {
		if (colour == null) {
			colour = new ArrayList();
			colour.add("1D8BD1");
			colour.add("F1683C");
			colour.add("2AD62A");
			colour.add("DBDC25");
			colour.add("1D8BD0");
			colour.add("F16830");
			colour.add("2AD620");
			colour.add("DBDC20");
			colour.add("1D8BDF");
			colour.add("F1683F");
			colour.add("2AD62F");
			colour.add("DBDC2F");
			colour.add("1D8BD1");
			colour.add("F16831");
			colour.add("2AD621");
			colour.add("DBDC21");
			colour.add("1D8BD2");
			colour.add("F16832");
			colour.add("2AD622");
			colour.add("DBDC22");
			colour.add("1D83D3");
			colour.add("F16333");
			colour.add("2AD323");
			colour.add("DBD325");
			colour.add("1D83D1");
			colour.add("F1183C");
			colour.add("2A462A");
			colour.add("DBFC25");
			colour.add("1DFBD1");
			colour.add("F1F83C");
			colour.add("2AC62A");
			colour.add("CBDC25");
			colour.add("1D8CD1");
			colour.add("FC683C");
			colour.add("2AD62D");
			colour.add("CBDC25");
		}
		return colour;
	}

	public ReportChart getReportChart() {
		return reportChart;
	}

	public void setReportChart(ReportChart reportChart) {
		this.reportChart = reportChart;
	}
}
