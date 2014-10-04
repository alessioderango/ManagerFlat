package it.ManagerFlat.project.util;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class DatabaseBackupListener implements ServletContextListener {
	private Scheduler scheduler;

	public void contextInitialized(ServletContextEvent event) {
		try {
			// initialize the scheduler factory
			SchedulerFactory schedulerFactory = new StdSchedulerFactory();
			// get a scheduler
			scheduler = schedulerFactory.getScheduler();
			// configure Database backup job
			JobDetail job = newJob(DatabaseBackupJob.class).withIdentity("databaseBackup", "group").build();
			// create a cron type Trigger and schedule database backup to happen
			// every day at midnight
			CronTrigger trigger = newTrigger().withIdentity("trigger1", "group1")
//					.withSchedule(cronSchedule("0 0 12 * * ?")).build();
					.withSchedule(cronSchedule("0/50 * * * * ?")).build();
			scheduler.scheduleJob(job, trigger);
			scheduler.start();

		} catch (SchedulerException ex) {
			Logger.getLogger(DatabaseBackupListener.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void contextDestroyed(ServletContextEvent event) {
		try {
			scheduler.shutdown();
		} catch (SchedulerException ex) {
			Logger.getLogger(DatabaseBackupListener.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
