package com.maxis.gamingportal.report;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ReportScheduler {
	
	@Autowired
	JobLauncher jobLauncher;
	
	@Autowired
	@Qualifier("dailyReportJob")
	Job dailyReportJob;
	
	
	@Scheduled(cron="*/20 * * * * ?") 
	public void generteReport(){
		System.out.println("Method executed at every 5 seconds. Current time is :: "+ new Date());
		/*try{
			JobExecution execution = jobLauncher.run(dailyReportJob, new JobParameters());
			System.out.println("Exit Status : " + execution.getStatus());
			
		}catch(Exception ex){
			ex.printStackTrace();
		}*/
		
	}

}
