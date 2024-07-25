package br.com.productiva.engine;

import java.util.Iterator;
import java.util.List;

/**
 * Entidade que representa o Gráfico Time.
 *  
 * @author tiago.toigo
 */
public class MSLineHighCharts extends MSLine{
	
	private String graph;

	public String getGraph() {
		if (graph == null) {
			graph = "				<script type='text/javascript' src='http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js'></script>"+
				"<script type='text/javascript'>"+
				
		"$(function () {" +
		        "$('#container').highcharts({"+
		            "tooltip: {valueSuffix: ''},"+
		            "legend: {layout: 'vertical',align: 'right',verticalAlign: 'middle',borderWidth: 0},"+
		            "series: [{";
					//int aux = 0;
					
		            for (Iterator iterator = getDataSets().iterator(); iterator.hasNext();) {
		    			
		    			List dataset = (List) iterator.next();
		    			DataSetItem itemZero = (DataSetItem) dataset.get(0);
//		    			String cor = getColour().get(aux);
		    			graph = graph + 
		    			"    name: '"+ itemZero.getNomeSerie() + "',data: [";
		    			for (Iterator lista = dataset.iterator(); lista.hasNext();) {
		    				DataSetItem item = (DataSetItem) lista.next();
		    				graph = graph + item.getValor();
		    				if (lista.hasNext()) {
		    					graph = graph + ",";
		    				}
		    			}
		    			graph = graph + "]}";
		//    			aux = aux + 1;
		    			if (iterator.hasNext() ) {
		    				graph = graph +", {";
		    			}
		    		}
		            graph = graph +"],";
		            
		            graph = graph +
		            "colors: ['#DDDF0D', '#7798BF', '#55BF3B', '#DF5353', '#aaeeee', '#ff0066', '#eeaaee','#55BF3B', '#DF5353', '#7798BF', '#aaeeee'],"+
		   		    "chart: {backgroundColor: {"+
		   		         "linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },"+
		   		         "stops: [[0, 'rgb(96, 96, 96)'],"+
		   		            "[1, 'rgb(16, 16, 16)']]"+
		   		         
		   		      "},"+
		   		      "borderWidth: 0,"+
		   		      "borderRadius: 15,"+
		   		      "plotBackgroundColor: null,"+
		   		      "plotShadow: false,"+
		   		      "plotBorderWidth: 0"+
		   		   "},"+
		   		   "title: {"+
		   			  "text: '"+getReportChart().getName()+"',"+
		   			  "x: -20,"+ 
		   		      "style: {"+
		   		         "color: '#FFF',"+
		   		         "font: '16px Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif'"+
		   		      "}"+
		   		   "},"+
		   		   "subtitle: {"+
		                "text: '"+getReportChart().getDescription()+"',"+
		                "x: -20,"+
		   		      "style: {"+
		   		         "color: '#DDD',"+
		   		         "font: '12px Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif'"+
		   		      "}"+
		   		   "},"+
		   		   "xAxis: {"+
		   			  "categories: [";
		            for (Iterator iterator = getCategories().iterator(); iterator.hasNext();) {
		    			String categoria = ""+iterator.next();
		    			graph = graph + "'"+categoria+ "'";
		    			if (iterator.hasNext()){
		    				graph = graph + ",";
		    			}
		    		}
		            graph = graph + "],"+
		   		      "gridLineWidth: 0,lineColor: '#999',tickColor: '#999',"+
		   		      "labels: {style: {color: '#999',fontWeight: 'bold'}"+
		   		      "},"+
		   		      "title: {style: {color: '#AAA',font: 'bold 12px Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif'}"+
		   		      "}"+
		   		   "},"+
		   		   "yAxis: {alternateGridColor: null,minorTickInterval: null,gridLineColor: 'rgba(255, 255, 255, .1)',"+
		   		      "minorGridLineColor: 'rgba(255,255,255,0.07)',lineWidth: 0,tickWidth: 0,"+
		   		      "labels: {style: {color: '#999',fontWeight: 'bold'}},"+
		   		      "title: {text: '',style: {"+
		   		            "color: '#AAA',font: 'bold 12px Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif'}"+
		   		      "}"+
		   		   "},"+
		   		   "legend: {itemStyle: {color: '#CCC'},itemHoverStyle: {color: '#FFF'},"+
		   		      "itemHiddenStyle: {"+
		   		         "color: '#333'}},"+
		   		   "labels: {"+
		   		      "style: {color: '#CCC'}},"+
		   		   "tooltip: {backgroundColor: {linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },"+
		   		         "stops: [[0, 'rgba(96, 96, 96, .8)'],[1, 'rgba(16, 16, 16, .8)']]"+
		   		      "},"+
		   		      "borderWidth: 0,style: {color: '#FFF'}},"+


		   		   "plotOptions: {series: {shadow: true},line: {dataLabels: {color: '#CCC'},"+
		   		         "marker: {lineColor: '#333'}},spline: {marker: {lineColor: '#333'}},scatter: {marker: {lineColor: '#333'}},"+
		   		      "candlestick: {lineColor: 'white'}},"+

		   		   "toolbar: {itemStyle: {color: '#CCC'}},"+

		   		   "navigation: {buttonOptions: {symbolStroke: '#DDDDDD',hoverSymbolStroke: '#FFFFFF',"+
		   		         "theme: {fill: {linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },stops: [[0.4, '#606060'],[0.6, '#333333']]},stroke: '#000000'}}},"+

		   		   // scroll charts
		   		   "rangeSelector: {buttonTheme: {fill: {linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },stops: [[0.4, '#888'],[0.6, '#555']]},"+
		   		         "stroke: '#000000',style: {color: '#CCC',fontWeight: 'bold'},states: {hover: {fill: {linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },"+
		   		                  "stops: [[0.4, '#BBB'],[0.6, '#888']]},stroke: '#000000',style: {color: 'white'}},"+
		   		            "select: {fill: {linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },stops: [[0.1, '#000'],[0.3, '#333']]},"+
		   		               "stroke: '#000000',style: {color: 'yellow'}}}},"+
		   		      "inputStyle: {backgroundColor: '#333',color: 'silver'},labelStyle: {color: 'silver'}},"+

		   		   "navigator: {handles: {backgroundColor: '#666',borderColor: '#AAA'},outlineColor: '#CCC',"+
		   		      "maskFill: 'rgba(16, 16, 16, 0.5)',series: {color: '#7798BF',lineColor: '#A6C7ED'}},"+

		   		   "scrollbar: {barBackgroundColor: {linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },stops: [[0.4, '#888'],[0.6, '#555']]},"+
		   		      "barBorderColor: '#CCC',buttonArrowColor: '#CCC',buttonBackgroundColor: {linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },"+
		   		            "stops: [[0.4, '#888'],[0.6, '#555']]},"+
		   		      "buttonBorderColor: '#CCC',rifleColor: '#FFF',trackBackgroundColor: {linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },"+
		   		         "stops: [[0, '#000'],[1, '#333']]},trackBorderColor: '#666'},"+

		   		   // special colors for some of the demo examples
		   		   "legendBackgroundColor: 'rgba(48, 48, 48, 0.8)',"+
		   		   "legendBackgroundColorSolid: 'rgb(70, 70, 70)',"+
		   		   "dataLabelsColor: '#444',"+
		   		   "textColor: '#E0E0E0',"+
		   		   "maskColor: 'rgba(255,255,255,0.3)'}"+  
		        ");"+
		    "});"+
		    

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
