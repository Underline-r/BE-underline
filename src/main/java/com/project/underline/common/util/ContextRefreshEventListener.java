package com.project.underline.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
@Slf4j
public class ContextRefreshEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private JobExplorer jobExplorer;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    JobOperator jobOperator;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.info("Container restart: restarting 'running' batch jobs");
        List<String> jobs = jobExplorer.getJobNames();
        for (String job : jobs) {
            Set<JobExecution> runningJobs = jobExplorer.findRunningJobExecutions(job);

            for (JobExecution runningJob : runningJobs) {
                try {
                    log.info("Restarting job {} with parameters {}", runningJob.getJobInstance().getJobName(), runningJob.getJobParameters().toString());
                    runningJob.setStatus(BatchStatus.FAILED);
                    runningJob.setEndTime(new Date());
                    jobRepository.update(runningJob);
                    jobOperator.restart(runningJob.getId());
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }
}

