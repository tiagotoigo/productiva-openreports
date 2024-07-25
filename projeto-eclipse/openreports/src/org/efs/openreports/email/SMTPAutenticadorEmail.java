package org.efs.openreports.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Classe usada para authenticar o servidor de e-mail
 * 
 */
public class SMTPAutenticadorEmail extends Authenticator
{
	private String username;
	private String password; 
	
	public SMTPAutenticadorEmail(Configuracao conf){
		username = conf.getSmtpAutUsuario();
		password = conf.getSmtpAutSenha();
	}
	
	public PasswordAuthentication getPasswordAuthentication(){
		return new PasswordAuthentication(username, password);
	}
}