package br.com.productiva.engine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.efs.openreports.objects.chart.ChartValue;

/**
 * Representa todos os registros de uma consulta de mapa, 
 * que posteriormente poderão ser trabalhados, agregados, etc.
 * 
 * @author tiago.toigo
 *
 */
public class MapValue extends ChartValue {
	
	private double mapCentralLat;
	private double mapCentralLong;
	private double mapHeight;
	private double mapWidth;
	private int mapZoom;
	
	private List<String> listaNomeColuna;
	private List<Integer> listaTipoColuna;
	
	private List<List<Object>> listaValorColuna;

	public List<String> getListaNomeColuna() {
		if (listaNomeColuna == null) {
			listaNomeColuna = new ArrayList<String>();
		}
		return listaNomeColuna;
		
	}

	public List<Integer> getListaTipoColuna() {
		if (listaTipoColuna == null) {
			listaTipoColuna = new ArrayList<Integer>();
		}
		return listaTipoColuna;
	}

	public List<List<Object>> getListaValorColuna() {
		if (listaValorColuna == null) {
			listaValorColuna = new ArrayList<List<Object>>();
		}
		return listaValorColuna;
	}

	/**
	 * A ordem onde é verificado o ponto e a descrição a serem usados no mapa é:
	 * Nome Local, Latitude, Longitude,	Ícone, Campo 1, Campo 2,...
	 * 
	 * As quatro primeiras colunas são reservadas para a criação do mapa.
	 * Podem haver quantos campos forem necessários a partir do 5o campo. 
	 * 
	 * @param campo
	 * @return registro
	 */
	public List<Object> obterRegistro(String nomeLocal){
		List<Object> registro = null;
		for (Iterator iterator = getListaValorColuna().iterator(); iterator.hasNext();) {
			List<Object> type = (List<Object>) iterator.next();
			if (nomeLocal.equals(type.get(0))) {
				return registro;
			}
		}
		return registro;
	}
	
	public double obterLatitude(String nomeLocal){
		List<Object> registro = obterRegistro(nomeLocal);
		return (Double)registro.get(1);
	}
	public double obterLongitude(String nomeLocal){
		List<Object> registro = obterRegistro(nomeLocal);
		return (Double)registro.get(2);
	}
	public String obterDadosHTML(String nomeLocal){
		String dados = "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"  style=\" border: 1px solid #707070; background: none repeat scroll 0 0 #F5F5F5;\" >	<tr>   	   <td colspan=\"10\" align=\"left\"><div style=\"margin-left:2px; margin-top:2px;padding:5px; \">	   <b> <span class=\"tabela_detalhe_mapa\">"+
			nomeLocal +"</span></b>	   </div>	   </td>	</tr>	<tr>	<td align=\"center\" colspan=\"10\" >		" +
			"<table class=\"tabela_detalhe_mapa1\" style=\"border:1px  solid #cccccc; width:200px;\">"+
			"<tbody><tr>";
		int contador = 0;
		for (Iterator iterator = getListaNomeColuna().iterator(); iterator.hasNext();) {
			String type = (String) iterator.next();
			if (contador > 3){
				dados = dados + "<th>"+type+"</th>";
			}
			contador = contador +1;
		}
		dados = dados + "</tr>";
		
		int contador2 = 0;
		for (Iterator iterator = getListaValorColuna().iterator(); iterator.hasNext();) {
			List<Object> type = (List<Object>) iterator.next();
			if (nomeLocal.equals(type.get(0))) {
				dados = dados + "<tr class=\"b\">";
				contador2 = 0;
				for (Iterator iterator2 = type.iterator(); iterator2.hasNext();) {
					Object object = (Object) iterator2.next();
					if (contador2 > 3) {
						dados = dados + "<td class=\"b-even-right\" >" + object + "</td>";
					}
					contador2=contador2+1;
				}
				dados = dados + "</tr>";
			}
		}
		dados = dados +"		</tbody>		</table>		</td>	</tr>	<tr><td colspan=\"10\" valign=\"bottom\" align=\"center\" style=\"padding:4px;\">			     <input type=\"button\" value=\"&nbsp;OK&nbsp;\" onclick=\"fechar();\" class=\"botao\" />			      </td>	</tr></table>";
		return dados;
	}

	public double getMapCentralLat() {
		return mapCentralLat;
	}

	public void setMapCentralLat(double mapCentralLat) {
		this.mapCentralLat = mapCentralLat;
	}

	public double getMapCentralLong() {
		return mapCentralLong;
	}

	public void setMapCentralLong(double mapCentralLong) {
		this.mapCentralLong = mapCentralLong;
	}

	public int getMapZoom() {
		return mapZoom;
	}

	public void setMapZoom(int mapZoom) {
		this.mapZoom = mapZoom;
	}

	public double getMapHeight() {
		return mapHeight;
	}

	public void setMapHeight(double mapHeight) {
		this.mapHeight = mapHeight;
	}

	public double getMapWidth() {
		return mapWidth;
	}

	public void setMapWidth(double mapWidth) {
		this.mapWidth = mapWidth;
	}

}
