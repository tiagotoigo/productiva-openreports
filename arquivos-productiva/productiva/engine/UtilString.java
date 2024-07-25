package br.com.productiva.engine;

import java.math.BigDecimal;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.hibernate.lob.ClobImpl;

/**
 * Classe utilitária para a manipulação de strings
 * @author Tiago Toigo
 */
public class UtilString {

	/**
	 * Construtor.
	 *
	 */
	private UtilString() {
	}

    public static boolean isVazio(String string) {
    	
    	return string == null || string.trim().equals("");
    }
    
   	/**
   	 * Retorna o StackTrace como um String.
   	 * @param ex a exceção que será exibida
   	 * @return String
   	 */
   	public static String getStackTrace(Exception ex) {
        
        StringBuffer buf = new StringBuffer();
        
        if (ex != null) {
            StackTraceElement[] traces = ex.getStackTrace();
            
            //cabecalho do erro
            buf.append(ex.toString());
            
            if (ex.getMessage() != null) {
                buf.append(": ".concat(ex.getMessage().concat("\r\n")));
            }
            
            //stacktrace do erro
            for (int i = 0; i < traces.length; i++) {
                buf.append("\t".concat(traces[i].toString().concat("\r\n")));
            }
            
            Throwable causa = ex.getCause();
            
            while (causa != null) {
                buf.append("\n\nCausa:\n\n");
                buf.append(causa);
                causa = causa.getCause();
            }
        }
        
        return buf.toString();
    }
   
    /**
     * Retorna o valor que foi fornecido como parâmetro se este é diferente de
     * nulo, do contrário, retorna uma string vazia.
     * @param valor O valor que quer testar
     * @return A string diferente de null
     */
    public static final String obterValorDiferenteNull(String valor) {
    	String parametro = "";
    	if(valor != null){
    		parametro = valor; 
    	}
    	return parametro;
    }

	public static String completarAEsquerda(String valor, String string, int i){
		String novoValor = valor.trim();
		int tamanho = novoValor.length();
		if (tamanho >= i) {
			novoValor = novoValor.substring(0, i);
		} else {
			while (tamanho < i) {
				novoValor = string + novoValor;
				int tam = novoValor.length();
					if (tam >= i) {
						novoValor = novoValor.substring(tam - i, tam);
					}
				tamanho++;
			}
		}
		return novoValor;
	}

	/**
	 * Retorna uma String passando um Objeto Clob
	 * @param valor
	 * @return
	 */	
	public static String getStringDoClob(Clob clob){
		String resposta = "";
		try{
			if(clob != null){
				resposta = clob.getSubString(Long.parseLong("1"), 
						Integer.parseInt(String.valueOf(clob.length())));
			}
			return resposta;
		}catch (Exception e) {
			return resposta;
		}
	}
	public static Clob obterClob(String string){
		Clob resposta = null;
		try{
			if(string != null){
				resposta = new ClobImpl(string);
			}
			return resposta;
		}catch (Exception e) {
			return resposta;
		}
	}

	public static char obterSimNao(String valor) {

		char retorno = Constantes.NAO;
		if (!isVazio(valor) && 
				valor.charAt(0) == Constantes.SIM) {
			retorno = Constantes.SIM;
		} 
		return retorno;
	}

	public static String obterSimNao(Character valor) {
		return obterSimNao(String.valueOf(valor))+"";
	}

	public static BigDecimal obterBigDecimal(String string) {
		if (isVazio(string)) {
			return new BigDecimal(0);
		}
		string = string.replaceAll("[.]", "");
		return new BigDecimal(string.replace(',', '.'));
	}

	public static String obterTrueFalse(char ngcRascunho) {
		
		if (ngcRascunho == Constantes.SIM){
			return Constantes.TRUE;
		}
		return Constantes.FALSE;
	}

	public static String obterValorInteiro(BigDecimal valor) {
		if (valor == null) {
			return "0";
		}
		String string = valor.toString();
		int ponto = string.lastIndexOf('.');
		String stringSemCasasDecimais = string.substring(0, ponto);
		return stringSemCasasDecimais;
	}
	
	/**
	 * Retorna um char ao passar uma string
	 * @param string
	 * @return
	 */
	public static char obterChar(String string){
		if (isVazio(string)) {
			return ' ';
		}
		char ret[];
		ret = string.toCharArray();
		return ret[0];
	}
	
	public static char obterCharNS (Object obj){
		char retorno = Constantes.NAO;
		if(obj!=null){
			String obj2 = (String) obj;
			if(obj2.equalsIgnoreCase("") || obj2.equalsIgnoreCase(
					Constantes.NAO+"")){
				
			}else if(obj2.equalsIgnoreCase("S")){
				 retorno = Constantes.SIM;
			}
		}
		return retorno;
	}
	
	public static void main(String[] teste){
		String n = null;
		System.out.println(obterCharNS(n));
	}
	
	public static List<String> split(String valor,String token){
		StringTokenizer tokenizer = new StringTokenizer(valor,token);
		List<String> listaStrings = new ArrayList<String>();
		while (tokenizer.hasMoreTokens()) {
			listaStrings.add(tokenizer.nextToken().trim());
		}
		return listaStrings;
	}
	
	public static String obterValorZeroSeNull(String valor) {
		if (valor == null) {
			return Constantes.VALOR_ZERO;
		}
			return valor;
	}
}

