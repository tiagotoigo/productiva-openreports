package br.com.productiva.engine;

import java.util.Iterator;
import java.util.List;


public class BarChartHighCharts extends MSLineHighCharts {
	private String graph;

	public String getGraph() {
		if (graph == null) {
			graph = "				<script type='text/javascript' src='http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js'></script>"+
				"<script type='text/javascript'>"+
				"$(function () {"+
				"var colors = Highcharts.getOptions().colors,"+
	            "categories = [";
	            for (Iterator iterator = getCategories().iterator(); iterator.hasNext();) {
					String type = (String) iterator.next();
					graph = graph+"'"+type+"'";
					if (iterator.hasNext()) {
						graph = graph+",";
					}
	            }
	            graph = graph+"], ";
	            graph = graph+" data = [";
	            
	            int aux = 0;
	            List lista = getDataSets().get(0);
	            for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
	            	DataSetItem type = (DataSetItem) iterator.next();
					graph = graph+"{ y:"+ type.getValor() +" , color: colors["+aux+"] }";
					if (iterator.hasNext()) {
						graph = graph+",";
					}
					aux = aux+1;
	            }	            
	            graph = graph+" ];";
	            graph = graph+"var chart = $('#container').highcharts({"+
	            "chart: {  type: 'column',"+
				"	backgroundColor: {  linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 }, stops: [[0, 'rgb(96, 96, 96)'],[1, 'rgb(16, 16, 16)']]	} },"+
	            "title: { text: 'Browser market share, April, 2011', 	style: {color:'#FFFFFF'} },"+
	            "subtitle: {  text: 'Click the columns to view versions. Click again to view brands.',	style: {color:'#FFFFFF'} },"+
	            "xAxis: { categories: categories,	labels: {style: {	color: '#FFFFFF'}}}, "+
	            "yAxis: {  title: {  text: 'Total percent market share',	style: {color:'#FFFFFF'} } },"+
				"legend: {enabled:false},"+
	            "tooltip: {headerFormat: '<span style=\"font-size:10px\">{point.key}</span><table>',pointFormat:  '<tr><td style=\"color:{series.color};padding:0\">Valor: </td>' +"+
	            "        '<td style=\"padding:0\"><b>{point.y:.1f} </b></td></tr>', footerFormat: '</table>', shared: true,  useHTML: true  },"+
	            "series: [{  data: data  }] "+
			"})"+
	       " .highcharts(); "+
	   " });"+

				"</script>"+
		"<script src='highcharts/js/highcharts.js'></script>	<script src='highcharts/js/modules/exporting.js'></script>"+

		"<div id='container' style='max-width: "+(getReportChart().getWidth()+20)+"px; height: "+getReportChart().getHeight()+
		"px; margin: 0 ;'></div>";
				
//				getReportChart().getWidth()+20)+
//					getReportChart().getHeight()+
//					getReportChart().getHeight()+
//					getReportChart().getWidth()+
//					getReportChart().getHeight()+
//					getReportChart().getDescription()+  
//				getReportChart().getXMinimum()+
//				getReportChart().getXMaximum();

		}
		return graph;
	}

	public String getEnvelope() {
		return getGraph();
	}
}
