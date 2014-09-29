package it.ManagerFlat.project.service;

import java.io.File;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailService {
    
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private SimpleMailMessage alertMailMessage;
    
    public void sendMail(String from, String to, String subject, String body,String data) throws MessagingException {
        
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);
//        mailSender.send(simpleMailMessage);
		MimeMessage message = mailSender.createMimeMessage();
		 
		 	MimeMessageHelper helper = new MimeMessageHelper(message, true);
	 
			helper.setFrom(simpleMailMessage.getFrom());
			helper.setTo(simpleMailMessage.getTo());
			helper.setSubject(simpleMailMessage.getSubject());
			helper.setText(String.format(
				simpleMailMessage.getText()));
	 
			FileSystemResource file = new FileSystemResource("C:\\prova\\ConsumiStanza1_"+ data +".pdf");
			FileSystemResource file1 = new FileSystemResource("C:\\prova\\ConsumiStanza2_"+ data +".pdf");
			FileSystemResource file2 = new FileSystemResource("C:\\prova\\ConsumiStanza3_"+ data +".pdf");
			FileSystemResource file3 = new FileSystemResource("C:\\prova\\ConsumiStanza4_"+ data +".pdf");
			helper.addAttachment(file.getFilename(), file);
			helper.addAttachment(file.getFilename(), file1);
			helper.addAttachment(file.getFilename(), file2);
			helper.addAttachment(file.getFilename(), file3);
	 
		    
		     mailSender.send(message);
//        File stanza1= new File("C:\\prova\\ConsumiStanza1_"+ dateFormat.format(date) +".pdf");
//        File stanza2= new File("C:\\prova\\ConsumiStanza2_"+ dateFormat.format(date) +".pdf");
//        File stanza3= new File("C:\\prova\\ConsumiStanza3_"+ dateFormat.format(date) +".pdf");
//        File stanza4= new File("C:\\prova\\ConsumiStanza4_"+ dateFormat.format(date) +".pdf");
		
    }
    
    public void sendAlertMail(String alert) {
        
        SimpleMailMessage mailMessage = new SimpleMailMessage(alertMailMessage);
        mailMessage.setText(alert);
        mailSender.send(mailMessage);
        
    }
    
}