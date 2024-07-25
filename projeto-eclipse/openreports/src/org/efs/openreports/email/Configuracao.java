package org.efs.openreports.email;



public class Configuracao {
	

	private String SmtpHostName;
	private String SmtpAutUsuario;
	private String SmtpAutSenha;
	private String MensagemEmail;
	
	public String getSmtpHostName() {
		return SmtpHostName;
	}
	public void setSmtpHostName(String smtpHostName) {
		SmtpHostName = smtpHostName;
	}
	public String getSmtpAutUsuario() {
		return SmtpAutUsuario;
	}
	public void setSmtpAutUsuario(String smtpAutUsuario) {
		SmtpAutUsuario = smtpAutUsuario;
	}
	public String getSmtpAutSenha() {
		return SmtpAutSenha;
	}
	public void setSmtpAutSenha(String smtpAutSenha) {
		SmtpAutSenha = smtpAutSenha;
	}
	public String getMensagemEmail() {
		return MensagemEmail;
	}
	public void setMensagemEmail(String mensagemEmail) {
		MensagemEmail = mensagemEmail;
	}
	
}
