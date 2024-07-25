package org.efs.openreports.email;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.productiva.engine.UtilString;




public class EnvioEmailUsandoAutenticacao{
	
	public static final String _SMTP_HOST_GMAIL = "smtp.gmail.com",//"smtp.gmail.com",//"webmail.tse.gov.br",
	   						   _SMTP_PORT_GMAIL = "587";//"587";//"25";

	public static void enviarEmail( ArrayList<String> listaEmail, String titulo, String mensagem , Configuracao conf) throws Exception{
		
		// Setando o SMTP e habilitando a autenticação
		Properties props = new Properties();
		props.put("mail.smtp.host", conf.getSmtpHostName());
		props.put("mail.smtp.auth", "true");
		
		//if(conf.getSmtpHostName().equalsIgnoreCase(_SMTP_HOST_GMAIL)){
			props.put("mail.smtp.port", _SMTP_PORT_GMAIL);
			props.put("mail.debug", "true");
			props.put("mail.smtp.starttls.enable", "true");
		//}
			
		Authenticator aut = new SMTPAutenticadorEmail(conf);
		Session session = Session.getDefaultInstance(props, aut);
		session.setDebug(true);
		// Criando a mensagem
		Message msg = new MimeMessage(session);
		
		// Setando o from email
		InternetAddress addressFrom = new InternetAddress(conf.getSmtpAutUsuario());
		msg.setFrom(addressFrom);
		
		//Carregando a lista dos contatos a serem enviados
		InternetAddress[] addressTo = obterListaDestinatarios(listaEmail, msg);
		
		
		msg.setRecipients(Message.RecipientType.TO, addressTo);

		
		//msg.setSubject(titulo);
		msg.setSubject("Productiva");
		msg.setContent(mensagem, "text/html");
		
		Transport tr = session.getTransport("smtp");			
		tr.connect(conf.getSmtpHostName(), conf.getSmtpAutUsuario(), conf.getSmtpAutSenha());
		msg.saveChanges();
		tr.sendMessage(msg, msg.getAllRecipients());
		tr.close();	
	}

	private static InternetAddress[] obterListaDestinatarios(
			ArrayList<String> listaEmail, Message msg) throws AddressException,
			MessagingException {
		List<InternetAddress> emails = new ArrayList<InternetAddress>();
		String email = "";
		for (int i = 0; i < listaEmail.size(); i++) {
			email = (String) listaEmail.get(i);
			if(!UtilString.isVazio(email)){
				if(!email.equals("")){
					if(email.contains(";")){
						String emailsJuntos[] =	email.split(";");
						for(String emailSplit:emailsJuntos){
							if(!UtilString.isVazio(emailSplit)){
								if(!emailSplit.trim().equals("")){
									 emails.add(new InternetAddress(emailSplit));								
								}
							}
						}
					}else{
						emails.add(new InternetAddress(email));
					}
				}
			}
		}
		msg.setSentDate(new Date());
		InternetAddress[] addressTo = new InternetAddress[emails.size()];

		for (int i = 0; i < emails.size(); i++) {
			addressTo[i] = (InternetAddress) emails.get(i); 
		}
		return addressTo;
	}
	
	//USADO PARA TESTE
	protected static final String emailMsgTxt = "Este é um texto de teste de email.";
	protected static final String emailTituloTxt = "Titulo do email";
	
	
	public static void main(String args[]) throws Exception
	{
		try {
			ArrayList<String> listaEmail = new ArrayList<String>();
			listaEmail.add("relatorio@productiva.com.br");
			Configuracao conf = new Configuracao();
			conf.setSmtpHostName(_SMTP_HOST_GMAIL);
			conf.setSmtpAutUsuario("relatorio@gmail.com"); // origem
			conf.setSmtpAutSenha("******");
			EnvioEmailUsandoAutenticacao.enviarEmail(listaEmail, emailTituloTxt, emailMsgTxt, conf);
			System.out.println("Sucessfully Sent mail to All Users");			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

//