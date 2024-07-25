package br.com.productiva.engine;

import java.util.Iterator;
import java.util.List;


public class BarChart extends XYChart{
	
	private String graph;
	
	public String getGraph() {
		if (graph == null) {
			graph = "<div style=\"border:1px solid #707070;background-color: white;width:"+(getReportChart().getWidth()+20)+"px;heigth:"+getReportChart().getHeight()+"px;\"> "+
					"<embed type=\"application/x-shockwave-flash\" src=\"fusioncharts/Charts/FCF_Column3D.swf\" width=\""+(getReportChart().getWidth()+20)+"\" height=\""+getReportChart().getHeight()+"\" id=\"ChartId\" name=\"ChartId\" quality=\"high\" allowscriptaccess=\"always\" "+
					"flashvars=\"chartWidth="+getReportChart().getWidth()+"&chartHeight="+getReportChart().getHeight()+"&debugMode=0&DOMId=ChartId&registerWithJS=0&scaleMode=noScale&lang=EN&dataXML=" +
					"<graph caption='"+getReportChart().getName()+"' subcaption='"+getReportChart().getDescription()+"' "  
				+ "hovercapbg='FFECAA' hovercapborder='F47E00' formatNumberScale='0' decimalPrecision='0' showvalues='0' numdivlines='3' numVdivlines='0' " 
				+ "yaxisminvalue='"+ getReportChart().getXMinimum()+ "' "
				+ "yaxismaxvalue='"+ getReportChart().getXMaximum()+ "' "
				+ "rotateNames='0'> " ;
			
			
		}
		return graph;
	}
	public String getEnvelope() {
		String envelope = "";
		String dados = "";
		int aux = 0;
		for (Iterator iterator = getDataSets().iterator(); iterator.hasNext();) {
			List dataset = (List) iterator.next();
			for (Iterator lista = dataset.iterator(); lista.hasNext();) {
				DataSetItem item = (DataSetItem) lista.next();
				String cor = getColour().get(aux);
				dados = dados + "<set name='"+item.getNomeSerie()+"' value='"+item.getValor()+"' color='" + cor +"' />";
				aux = aux + 1;
			}
		}
		envelope = getGraph() + dados + "</graph>\"/> </div>";
		return envelope;
	}

}
