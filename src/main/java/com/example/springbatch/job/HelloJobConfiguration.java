package com.example.springbatch.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sskim
 */
@Configuration
@RequiredArgsConstructor
public class HelloJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job helloJob() {
        return jobBuilderFactory.get("helloJob")
                .start(helloStep1())
                .next(helloStep2())
                .build();
    }

    @Bean
    Step helloStep1() {
        return stepBuilderFactory.get("스텝 첫 시작")
                .tasklet((contribution, chunkContext) -> {
                            System.out.println("=======================");
                            System.out.println(">> Hello Spring Batch!");
                            System.out.println("=======================");
                            return RepeatStatus.FINISHED;
                        }
                )
                .build();
    }

    @Bean
    Step helloStep2() {
        return stepBuilderFactory.get("스텝 두 번째")
                .tasklet((contribution, chunkContext) -> {
                            System.out.println("=======================");
                            System.out.println(">> step2 was executed!");
                            System.out.println("=======================");
                            return RepeatStatus.FINISHED;
                        }
                ).build();
    }
}
