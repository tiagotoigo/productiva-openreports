package br.com.productiva.engine;

import java.util.Date;

public class DataSetItem {
	
	private String nomeSerie; // esse valor vai ser repetido para uma serie.
	private Date tempo;
	private double valor;
	private double segundoValor;
	
	public Date getTempo() {
		return tempo;
	}
	public void setTempo(Date tempo) {
		this.tempo = tempo;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getNomeSerie() {
		return nomeSerie;
	}
	public void setNomeSerie(String nomeSerie) {
		this.nomeSerie = nomeSerie;
	}
	public double getSegundoValor() {
		return segundoValor;
	}
	public void setSegundoValor(double segundoValor) {
		this.segundoValor = segundoValor;
	}

}
