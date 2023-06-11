package com.project.underline.batch.config;

import com.project.underline.batch.processor.PostTempToPostViewProcessor;
import com.project.underline.batch.processor.PostViewItemWriter;
import com.project.underline.batch.redis.RedisItemReader;
import com.project.underline.post.entity.PostTemp;
import com.project.underline.post.entity.PostView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

//@Slf4j
//@RequiredArgsConstructor
//@EnableScheduling
//@EnableBatchProcessing
//@Configuration
//public class BatchConfiguration {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//    private final JobLauncher jobLauncher;
//
//    private final PostViewItemWriter postViewItemWriter;
//    private final PostTempToPostViewProcessor postTempToPostViewProcessor;
//    private final RedisItemReader redisItemReader;
//
//    private static final int chunkSize = 10;
//
//    @Bean
//    public Job redisItemReaderJob(Step redisItemReaderStep) {
//        return jobBuilderFactory.get("redisItemReaderJob")
//                .start(redisItemReaderStep)
//                .build();
//    }
//
//    @Bean
//    public Step redisItemReaderStep() {
//        return stepBuilderFactory.get("redisItemReaderStep")
//                .<PostTemp, PostTemp>chunk(chunkSize)
//                .reader(redisItemReader)
//                .processor(postTempToPostViewProcessor)
//                .writer(postViewItemWriter)
//                .build();
//    }
//
//    @Scheduled(cron = "0 0 3 * * ?", zone = "Asia/Seoul")
//    public void runBatchJob() throws Exception {
//        log.info("* -- 조회수 배치 시작 -- *");
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addString("JobID", String.valueOf(System.currentTimeMillis()))
//                .toJobParameters();
//        jobLauncher.run(redisItemReaderJob(redisItemReaderStep()), jobParameters);
//    }
//}
