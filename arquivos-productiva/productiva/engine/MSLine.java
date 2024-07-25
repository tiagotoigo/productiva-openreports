package br.com.productiva.engine;

import java.util.ArrayList;
import java.util.List;

/**
 * Entidade que representa o Gráfico Time.
 *  
 * @author tiago.toigo
 */
public class MSLine {
	private String envelope;
	private String graph;
	
	private List<String> categories;
	private List<List<String>> dataSets;

	public String getGraph() {
		if (graph == null) {
			graph = " = <graph caption='Daily Visits' subcaption='(from 8/6/2006 to 8/12/2006)' "  
				+ "hovercapbg='FFECAA' hovercapborder='F47E00' formatNumberScale='0' decimalPrecision='0' showvalues='0' numdivlines='3' numVdivlines='0' " 
				+ "yaxisminvalue='1000' "
				+ "yaxismaxvalue='1800' "
				+ "rotateNames='0'> " ;
		}
		return graph;
	}

	public void setGraph(String graph) {
		this.graph = graph;
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
		return envelope;
	}

	public void setEnvelope(String envelope) {
		this.envelope = envelope;
	}

	
}
