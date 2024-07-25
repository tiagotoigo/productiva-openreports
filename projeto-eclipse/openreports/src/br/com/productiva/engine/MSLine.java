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
public class MSLine {
	
	private String graph;
	private List<String> categories;
	private List<List<String>> dataSets;
	private List<String> colour;
	
	private ReportChart reportChart;

	public String getGraph() {
		if (graph == null) {
			graph = "<div style=\"border:1px solid #707070;background-color: white;width:"+(getReportChart().getWidth()+20)+"px;heigth:"+getReportChart().getHeight()+"px;\"> "+
					"<embed type=\"application/x-shockwave-flash\" src=\"fusioncharts/Charts/FCF_MSLine.swf\" width=\""+(getReportChart().getWidth()+20)+"\" height=\""+getReportChart().getHeight()+"\" id=\"ChartId\" name=\"ChartId\" quality=\"high\" allowscriptaccess=\"always\" "+
					"flashvars=\"chartWidth="+getReportChart().getWidth()+"&chartHeight="+getReportChart().getHeight()+"&debugMode=0&DOMId=ChartId&registerWithJS=0&scaleMode=noScale&lang=EN&dataXML=" +
					"<graph caption='"+getReportChart().getName()+"' subcaption='"+getReportChart().getDescription()+"' "  
				+ "hovercapbg='FFECAA' hovercapborder='F47E00' formatNumberScale='0' decimalPrecision='0' showvalues='0' numdivlines='3' numVdivlines='0' " 
				+ "yaxisminvalue='"+ getReportChart().getXMinimum()+ "' "
				+ "yaxismaxvalue='"+ getReportChart().getXMaximum()+ "' "
				+ "rotateNames='0'> " ;
		}
		return graph;
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
		String dados = "<categories>";
		for (Iterator iterator = getCategories().iterator(); iterator.hasNext();) {
			String categoria = (String) iterator.next();
			dados = dados + "<category name='"+ categoria + "'/>";
		}
		dados = dados+"</categories>";
		int aux = 0;
		for (Iterator iterator = getDataSets().iterator(); iterator.hasNext();) {
			
			List dataset = (List) iterator.next();
			DataSetItem itemZero = (DataSetItem) dataset.get(0);
			String cor = getColour().get(aux);
			dados = dados + "<dataset seriesName='" + itemZero.getNomeSerie() + "' color='" + cor + "' anchorBorderColor='"+cor+"' anchorBgColor='"+cor+"'>";
			for (Iterator lista = dataset.iterator(); lista.hasNext();) {
				DataSetItem item = (DataSetItem) lista.next();
				dados = dados + "<set value='"+item.getValor()+"'/>";
			}
			dados = dados + "</dataset>";
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
			colour.add("AFD8F8");
			colour.add("F6BD0F");
			colour.add("8BBA00");
			colour.add("FF8E46");
			colour.add("008E8E");
			colour.add("D64646");
			colour.add("8E468E");
			colour.add("588526");
			colour.add("B3AA00");
			colour.add("008ED6");
			colour.add("9D080D");
			colour.add("A186BE");
			colour.add("AFD8F8");
			colour.add("F6BD0F");
			colour.add("8BBA00");
			colour.add("FF8E46");
			colour.add("008E8E");
			colour.add("D64646");
			colour.add("8E468E");
			colour.add("588526");
			colour.add("B3AA00");
			colour.add("008ED6");
			colour.add("9D080D");
			colour.add("A186BE");
			colour.add("AFD8F8");
			colour.add("F6BD0F");
			colour.add("8BBA00");
			colour.add("FF8E46");
			colour.add("008E8E");
			colour.add("D64646");
			colour.add("8E468E");
			colour.add("588526");
			colour.add("B3AA00");
			colour.add("008ED6");
			colour.add("9D080D");
			colour.add("A186BE");
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
