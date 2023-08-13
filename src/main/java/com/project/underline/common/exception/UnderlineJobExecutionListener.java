package com.project.underline.common.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
@RequiredArgsConstructor
@Slf4j
public class UnderlineJobExecutionListener implements JobExecutionListener {

    private final JobExplorer jobExplorer;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        String jobName = jobExecution.getJobInstance().getJobName();
        Set<JobExecution> runningJobs = jobExplorer.findRunningJobExecutions(jobName);

        if (!runningJobs.isEmpty()) {
            log.info("이미 실행중인 작업 {}", jobName);
            jobExecution.setStatus(BatchStatus.ABANDONED);
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
    }

}
