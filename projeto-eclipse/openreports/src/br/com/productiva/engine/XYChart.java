package br.com.productiva.engine;

import java.util.Iterator;
import java.util.List;

public class XYChart extends MSLine {

	@Override
	public String getEnvelope() {
		String envelope = "";
		String dados = "<categories>";
		for (Iterator iterator = getCategories().iterator(); iterator.hasNext();) {
			String categoria = iterator.next()+"";
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

}
