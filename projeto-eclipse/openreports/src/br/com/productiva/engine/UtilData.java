package br.com.productiva.engine;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;


public class UtilData {
		public static Date getDataHora(String data) {
			
			Date resposta;
			if(data != null){
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				try {
					resposta = formatter.parse(data);
					return resposta;
				} catch (ParseException e) {
					SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					try {
						resposta = formatter2.parse(data);
						return resposta;
					} catch (ParseException e2) {
						e2.printStackTrace();
					}
				}
			}
			return null;
			
		}
		public static String getDataHora(Date data) {
			String resposta = "";
			if(data != null){
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				resposta = formatter.format(data);
			}
			return resposta;
		}
	
		/**
		 * Retorna a data fornecida como String
		 * @return A data fornecida, considerando o primeiro segundo do dia.
		 */
		public static Date getDataPrimeiroSegundo(String stringData) {
			
			StringTokenizer token = new StringTokenizer(stringData,"/");
			int dia = Integer.parseInt(token.nextToken());
			int mes = Integer.parseInt(token.nextToken());
			int ano = Integer.parseInt(token.nextToken());
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.set(Calendar.DAY_OF_MONTH, dia);
			calendar.set(Calendar.MONTH, mes-1);
			calendar.set(Calendar.YEAR, ano);
			calendar.set(Calendar.HOUR_OF_DAY,0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			return calendar.getTime();
		}
		/**
		 * Retorna a data fornecida como String
		 * @return A data fornecida, considerando o o ultimo segundo do dia.
		 */
		public static Date getDataUltimoSegundo(String stringData) {
			
			StringTokenizer token = new StringTokenizer(stringData,"/");
			int dia = Integer.parseInt(token.nextToken());
			int mes = Integer.parseInt(token.nextToken());
			int ano = Integer.parseInt(token.nextToken());
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.set(Calendar.DAY_OF_MONTH, dia);
			calendar.set(Calendar.MONTH, mes-1);
			calendar.set(Calendar.YEAR, ano);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 999);
			return calendar.getTime();
		}
		
		/**
		 * Retorna a data atual.
		 * @return A data atual, considerando o primeiro segundo do dia.
		 */
		public static Date getDataAtualPrimeiroSegundo() {
			
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(new Date());
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			return calendar.getTime();
		}
		
		/**
		 * Retorna a data atual no formato dd/MM/yyyy
		 * @return A data atual.
		 */
		public static String getDataAtual() {
			Date data = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			return formatter.format(data);
		}
		/**
		 * Retorna a data no formato dd/MM/yyyy
		 * @return A data atual.
		 */
		public static String getData(Date data) {
			String resposta = "";
			if(data != null){
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				resposta = formatter.format(data);
			}
			return resposta;
		}
		/**
		 * Retorna a hora no formato hh:mm:ss
		 * @return A data atual.
		 */
		public static String getHora(BigDecimal valor) {
			if (valor != null) {
				return getHora(valor.intValue());
			}
			return "";
		}
		public static String getHora(int numeroSegundos) {
			int horas = numeroSegundos / 3600;
			int minutos = numeroSegundos % 3600;
			int min = minutos / 60;
			int segundos = minutos % 60;
			return  formatarComZero(horas) + ":" +
					formatarComZero(min) + ":" +
					formatarComZero(segundos);
		}
		
		private static String formatarComZero(int campo) {
			if (campo < 10) {
				return "0"+campo;
			}
			return ""+campo;
		}
		
		public static Date getDate(String data) {
			
			if (UtilString.isVazio(data)) return null;
			return getDataPrimeiroSegundo(data);
		}
		
		public static Date getDataAtualUltimoSegundo() {
			
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(new Date());
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 999);
			return calendar.getTime();
		}
		
		public static String getDataRelatorio(String dataUsuario) {
			
			if (!UtilString.isVazio(dataUsuario)) {
				StringTokenizer token = new StringTokenizer(dataUsuario,"/");
				int dia = Integer.parseInt(token.nextToken());
				int mes = Integer.parseInt(token.nextToken());
				int ano = Integer.parseInt(token.nextToken());
				return mes + "/" + dia + "/" + ano;
			} else {
				return null;
			}
		}
		
		public static java.sql.Date getSqlDate(Date dataInicio) {
			
			return new java.sql.Date(dataInicio.getTime());
		}
		
		public static BigDecimal obterHora(String horario) {
			int numeroSegundos = 0;
			if (!UtilString.isVazio(horario)) {
				StringTokenizer token = new StringTokenizer(horario,":");
				int hor = Integer.parseInt(token.nextToken());
				int min = Integer.parseInt(token.nextToken());
				int seg = Integer.parseInt(token.nextToken());
				hor = hor * 3600;
				min = min * 60;
				numeroSegundos = hor + min + seg;
			}
			return new BigDecimal(numeroSegundos);
		}
		
		public static String getDia(Date data) {
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(data);
			String diaDaSemana = "";
			switch (calendar.get(Calendar.DAY_OF_WEEK)) {
				case Calendar.SUNDAY:
					diaDaSemana  = "Domingo";
					break;
				case Calendar.MONDAY:
					diaDaSemana  = "Segunda";
					break;
				case Calendar.TUESDAY:
					diaDaSemana = "Terça";
					break;
				case Calendar.WEDNESDAY:
					diaDaSemana = "Quarta";
					break;
				case Calendar.THURSDAY:
					diaDaSemana = "Quinta";
					break;
				case Calendar.FRIDAY:
					diaDaSemana = "Sexta";
					break;
				case Calendar.SATURDAY:
					diaDaSemana = "Sábado";
					break;
			}
			return diaDaSemana;
		}
		
		public static String getDataMesmoDiaMesSeguinte(Date data, int diaFixo, 
			int numeroMes) {
			GregorianCalendar calendar = new GregorianCalendar();		
			calendar.setTime(data);
			calendar.add(Calendar.MONTH, numeroMes);
			calendar.set(Calendar.DAY_OF_MONTH , diaFixo);
			return getData(calendar.getTime());
		}

		public static String subtrairDiasDaData(Date data, int dias){
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(data);
			calendar.add(Calendar.DAY_OF_MONTH, -dias);
			return getData(calendar.getTime());
		}
		
		public static String getDiaAbreviado(Date data) {
			
			return getDia(data).substring(0,3);
		}
		
		public static void main(String[] args) {
//			System.out.println(UtilData.obterHora("23:59:59"));
//			System.out.println(UtilData.obterHora("12:12:12"));
//			System.out.println(UtilData.getHora(86399));
//			System.out.println(UtilData.getHora(43932));
			//Date data1 = UtilData.getDataPrimeiroSegundo("03/01/2009");
			//System.out.println(getComparaData("25/01/2010", "25/01/2009"));
			//System.out.println(UtilData.getDataComIntervaloDia(data1, 30, 1));
		//	System.out.println(UtilData.adicionarDias(data1,20));
			Date data1 = UtilData.getDataHora("01/01/2008 18:00");
			Date data2 = UtilData.getDataHora("1/1/2009 18:55");
			Date data3 = UtilData.getDataHora("10/1/2009 18:10:05");
			System.out.println(getDataHora(data1));
			System.out.println(getDataHora(data2));
			System.out.println(getDataHora(data3));
		}
		
		public static int getQuantidadeDiasDoMes(String stringData) {
			Date data = getDataPrimeiroSegundo(stringData);
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(data);
			return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		}
		
		public static Date adicionarDias(Date data,int dias) {
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(data);
			calendar.add(Calendar.DAY_OF_MONTH, dias);
			return calendar.getTime();
		}
		
		public static Date setarDia(Date data,int dia) {
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(data);
			calendar.set(Calendar.DAY_OF_MONTH, dia);
			return calendar.getTime();
		}
		
		public static int getDiaDoMes(Date dataFim) {
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(dataFim);
			return calendar.get(Calendar.DAY_OF_MONTH);
		}
		
		/**
		 * Converte a String de data dd/mm/yyyy 
		 * para mm/dd/yyyy
		 * @param  String 31/01/2010 - DDMMYYYY
		 * @return String 01/31/2010 - MMDDYYYY
		 */
		public static String getDateMesDiaAno(String data){
			if (!UtilString.isVazio(data)) {
				char d[] = data.toCharArray();
				String dia = d[0]+"" + d[1]+"";
				String mes = d[3]+"" + d[4]+"";
				String ano = d[6]+"" + d[7]+"" + d[8]+"" + d[9]+"";
				return  mes+"/"+dia+"/"+ano;
			} else {
				return null;
			}
			
		}
		
		/**
		 * Compara se a data1 é menor que a data2 
		 * @param sData1 String dd/mm/yyyy
		 * @param sData2 String dd/mm/yyyy
		 * @return boolean
		 */
		public static boolean getComparaData(String sData1, String sData2){
			Date data1 = getDataPrimeiroSegundo(sData1);
			Date data2 = getDataPrimeiroSegundo(sData2);
			return data1.before(data2);
		}
		
		public static String obterDiferencaDias(Date data1, Date data2) {
			long milisegundos = data1.getTime() - data2.getTime();
			long dias = milisegundos / 1000 / 60 / 60 / 24; 
			return String.valueOf(dias);
		}

	}


