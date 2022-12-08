package br.fatec.vidapet.service;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.fatec.vidapet.dto.EmailDTO;

@Service
public class EmailService {
	
	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String sender;
		
	public String htmlFromTemplate(EmailDTO email) {
		Context context = new Context();
		context.setVariable("email", email);
		context.setVariable("imagem", "/home/anabeatriz/Documentos/projeto_backend_VidaPet/src/main/resources/templates/imagens");
		return templateEngine.process("email", context);
	}
	
	public void sendHtmlEmail(EmailDTO email) throws MessagingException {
		MimeMessage message = prepareHtmlEmailMessage(email);
		javaMailSender.send(message);
	}	

	private MimeMessage prepareHtmlEmailMessage(EmailDTO email) throws MessagingException {
		MimeMessage msg = javaMailSender.createMimeMessage();
		prepareMimeMessageHelper(msg, email);
		return msg;
	}
	
	private MimeMessageHelper prepareMimeMessageHelper(MimeMessage msg, EmailDTO email) throws MessagingException{
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		helper.setTo(email.getTo());
		helper.setFrom(sender);
		helper.setSubject(email.getSubject());
		helper.setSentDate(new Date(System.currentTimeMillis()));
		helper.setText(htmlFromTemplate(email), true);
		return helper;
	}
}
