package it.ManagerFlat.project.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;

import it.ManagerFlat.project.service.MailService;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class DatabaseBackupJob implements Job {

    public void execute(JobExecutionContext jec) throws JobExecutionException {
        Configuration cfg=new Configuration();
        cfg.configure("resource/hibernate.cfg.xml");
       
        SchemaExport se=new SchemaExport(cfg);
       
        
        se.setOutputFile("managerflat_db_backup.sql");
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("dd/MM/yy");
        String dateAsString = simpleDateFormat.format(new Date());
        MailService mailService = new MailService();
        try {
			mailService.sendMailBackup("alessio.derangoògmail.com", "alessio.derangoògmail.com", "dump database", "dump database managerflat_db", dateAsString);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        se.execute(true, true, false, false);
    }
    
}
