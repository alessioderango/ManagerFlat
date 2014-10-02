package it.ManagerFlat.project.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

public class DatabaseBackupJob implements Job {

	public void execute(JobExecutionContext jec) throws JobExecutionException {
		// Configuration cfg=new Configuration();
		// cfg.configure("resource/hibernate.cfg.xml")
		// .addPackage("it.ManagerFlat.project.controller.domain")
		// .addAnnotatedClass(Appartamento.class)
		// .addAnnotatedClass(Stanza.class)
		// .addAnnotatedClass(Lettura.class)
		// .addAnnotatedClass(Proprietario.class)
		// .addAnnotatedClass(Administrator.class)
		// .addAnnotatedClass(Inquilino.class)
		// .addAnnotatedClass(Parametro.class);
		// // .addAnnotatedClass(PUT CLASS)
		//
		// SchemaExport se=new SchemaExport(cfg);
		//
		//
		// se.setOutputFile("C:\\prova\\managerflat_db_backup.sql");
		// se.execute(true, true, false, false);
		MailService mail = new MailService();
		Runtime rt = Runtime.getRuntime();
		Process p = null;
		try {
			p = rt.exec("mysqldump -u adminqShamxy --password=DqaE8lDBGwUi --database managerflat_db -P 3306");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		InputStream is = p.getInputStream();
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("mydb_abackup.sql");
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

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
		String dateAsString = simpleDateFormat.format(new Date());
		System.out.println("FACCIO BACKUP");

		try {
			sendMailBackup("admanagerflat@gmail.com", "alessio.derango@gmail.com", "dump database",
					"dump database managerflat_db", dateAsString);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void sendMailBackup(String from, String to, String subject, String text, String date)
			throws AddressException, MessagingException {
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

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(to);
			MimeBodyPart messageBodyPart = new MimeBodyPart();

			Multipart multipart = new MimeMultipart();

			messageBodyPart = new MimeBodyPart();
			String file = "mydb_abackup.sql";
			String fileName = "managerflat_db.sql";
			DataSource source = new FileDataSource(file);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(fileName);
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
