/*
 * Copyright (C) 2002 Erik Swenson - eswenson@opensourcesoft.net
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 */

package org.efs.openreports.objects;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class ReportDataSource extends BasicDataSource implements Serializable
{	private static final String CONTEXTO = "call admcandidato.pc_contexto.pr_set_eleicao(2010,1,'T',0,'T')";
	private static final String CONTEXTO_CAND = "call admelegeral.pc_contexto.set_eleicao(2010,1, 'S', 0, NULL, 'T')";
	private static final long serialVersionUID = 7990031344563814988L;
	
	private Integer id;
	private String name;
	private String contexto;
	private boolean jndi;
	
	public ReportDataSource() {}
	 
	public boolean isJndi()
	{
		return jndi;
	}

	public void setJndi(boolean jndi)
	{
		this.jndi = jndi;
	}	
	
	public void setId(Integer id)
	{
		this.id = id;
	}	

	public String toString()
	{
		return name;
	} 

	public Integer getId()
	{		
		return id;		
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public Connection getConnection() throws SQLException {
		Connection conn = super.getConnection();
		CallableStatement cs = null;
//		if (getName() != null && getName().indexOf("Cand Oficial") != -1) {
//			callProcedureCand(conn, cs);
//		} else {
//			callProcedure(conn, cs);
//		}
		return conn;
	}

	public Connection getConnectionCsv() throws SQLException, ClassNotFoundException {
		//addConnectionProperty("fileExtension", ".txt");
		//addConnectionProperty("raiseUnsupportedOperationException", "false");
		//addConnectionProperty("separator", ";");
		addConnectionProperty("suppressHeaders", "true");
		addConnectionProperty("columnTypes", "String,String,String,String,String");
		//addConnectionProperty("separator","|");                // separator is a bar
		//addConnectionProperty("suppressHeaders","true");       // first line contains data
		//addConnectionProperty("fileExtension",".txt");         // file extension is .txt
		//addConnectionProperty("charset","ISO-8859-2");         // file encoding is "ISO-8859-2"
		//addConnectionProperty("maxFileSize",10000);            // max size of files in bytes.
		//addConnectionProperty("create","true");                // driver will create directory(s)
		//addConnectionProperty("lineBreakEscape","ELB");        // all line breaks will be replaces with ELB
		//addConnectionProperty("carriageReturnEscape","ECR");   // all carriage return will be replaces with ECR
		//addConnectionProperty("useQuotes","true");             // all values in output file will be wrapped with double quotes ( " )
		//Connection conn = super.getConnection();
		Class.forName("org.relique.jdbc.csv.CsvDriver");
		Connection conn = DriverManager.getConnection(getUrl(), connectionProperties);
		return conn;
	}
	/**
	 * @param conn
	 * @param cs
	 */
	private void callProcedure(Connection conn, CallableStatement cs) {
		try {
			// chamando a procedure
			cs = conn.prepareCall(CONTEXTO);
			cs.execute();
			System.out.println("333OK");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("333ERRO");
		}
	}
	private void callProcedureCand(Connection conn, CallableStatement cs) {
		try {
			// chamando a procedure
			cs = conn.prepareCall(CONTEXTO_CAND);
			cs.execute();
			System.out.println("222OK");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("222ERRO");
		}
	}	
	public String getContexto() {
		return contexto;
	}

	public void setContexto(String contexto) {
		this.contexto = contexto;
	}
	

}