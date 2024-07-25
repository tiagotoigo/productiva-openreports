package br.com.productiva.engine;

import java.util.Iterator;
import java.util.List;

public class GoogleMap {
	public static String getEnvelopeMapa(MapValue mapa) {
		String html =    
			"<script>"+
			"function initialize() {   var mapOptions = {     zoom: "+mapa.getMapZoom()+",     center: new google.maps.LatLng("+mapa.getMapCentralLat()+","+mapa.getMapCentralLong()+")   };"+
			"var map = new google.maps.Map(document.getElementById('map-canvas'),       mapOptions);";
			String aux = ""; 
			int ind = 1;
			for (Iterator iterator = mapa.getListaValorColuna().iterator(); iterator.hasNext();) {
				List<Object> type = (List<Object>) iterator.next();
				if (!aux.equals(type.get(0).toString())) {
					html = html + "var marker"+ind+" = new google.maps.Marker({     position: new google.maps.LatLng("+type.get(1).toString()+","+type.get(2).toString()+"),     map: map,     title: '"+type.get(0).toString()+"', 	icon: 'ico/mapas/"+type.get(3)+"'   });"+
						"google.maps.event.addListener(marker"+ind+" , 'click', function() {"+
						"showMsg('"+type.get(0).toString()+"','"+mapa.obterDadosHTML(type.get(0).toString())+"','false','false','true');"+
						"});";
					aux = type.get(0).toString();
					ind = ind + 1;
				}
			}
			html = html + 
			"}"+
			"google.maps.event.addDomListener(window, 'load', initialize);"+
			"</script>"+
			"<div id=\"map-canvas\"></div><span class=\"tabela_detalhe_mapa\">Clique nos pontos para visualizar os dados</span>"+
			"<br/>"+getEnvelopeResultados(mapa);
		return html;
	}
	
	public static String getEnvelopeResultados(MapValue mapa) {
		String html = "<br/>"+
			"<table class=\"tabela_detalhe_mapa1\" >"+
			"<tbody><tr>";
		for (Iterator iterator = mapa.getListaNomeColuna().iterator(); iterator.hasNext();) {
			String type = (String) iterator.next();
			html = html + "<th>"+type+"</th>";
		}
		html = html + "</tr>";
		
		for (Iterator iterator = mapa.getListaValorColuna().iterator(); iterator.hasNext();) {
			List<Object> type = (List<Object>) iterator.next();
			html = html + "<tr class=\"b\">";
			int ind = 0;
			for (Iterator iterator2 = type.iterator(); iterator2.hasNext();) {
				Object object = (Object) iterator2.next();
				if (object == null ) object = "";
				if (ind == 0 || ind == 3) {
					html = html + "<td class=\"b-even\" >" + object + "</td>";
				} else {
					html = html + "<td class=\"b-even-right\" >" + object + "</td>";
				}
				ind = ind + 1;
			}
			html = html+"</tr>";
		}
		html = html + "</tbody></table>";
		return html;
	}

}
