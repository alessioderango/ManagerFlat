package it.ManagerFlat.project.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import it.ManagerFlat.project.service.MailService;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.core.io.FileSystemResource;

public class DatabaseBackupJob implements Job {

	public void execute(JobExecutionContext jec) throws JobExecutionException {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy");
		String dateAsString = simpleDateFormat.format(new Date());
		String filepath= "/var/lib/openshift/5428839150044694750001eb/app-root/data/";
		String filename= "managerflat_db_backup"+dateAsString+".sql";
		Runtime rt = Runtime.getRuntime();
		Process p = null;
		try {
			//TODO
//			p = rt.exec("mysqldump -u adminqShamxy --password=DqaE8lDBGwUi --database managerflat_db -P 3306");
			p = rt.exec("mysqldump --password=DqaE8lDBGwUi -u adminqShamxy -h 127.6.155.130 -P 3306 --database managerflat_db ");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		InputStream is = p.getInputStream();
		FileOutputStream fos = null;
		try {
			//TODO
			fos = new FileOutputStream(new File(filepath+filename));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int ch;
		try {
			while ((ch = is.read()) != -1) {
				fos.write(ch);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fos.close();
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("----SQL backup file generated: mydb_abackup.sql----");


		try {
			try {
				sendMailBackup("admanagerflat@gmail.com", "alessio.derango@gmail.com", "dump database",
						"dump database managerflat_db", dateAsString,filepath,filename);
				sendMailBackup("admanagerflat@gmail.com", "salvatorederango@gmail.com", "dump database",
						"dump database managerflat_db", dateAsString,filepath,filename);
				sendMailBackup("admanagerflat@gmail.com", "derango@unical.it", "dump database",
						"dump database managerflat_db", dateAsString,filepath,filename);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void sendMailBackup(String from, String to, String subject, String text, String dateAsString, String filepath, String filename)
			throws AddressException, MessagingException, IOException {
		final String username = from;
		final String password = "managerflat57";

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			session.setDebug(true);
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(text);
			MimeBodyPart messageBodyPart = new MimeBodyPart();

			Multipart multipart = new MimeMultipart();

			messageBodyPart = new MimeBodyPart();
			//TODO
			
			FileSystemResource file = new FileSystemResource(filepath+filename);
			messageBodyPart.attachFile(file.getFile());
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);
			System.out.println("Sending");
			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
