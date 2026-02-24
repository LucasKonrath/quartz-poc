package com.example.quartzpoc.config

import com.example.quartzpoc.job.SimpleJob
import org.quartz.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QuartzConfig {

    @Bean
    fun simpleJobDetail(): JobDetail {
        return JobBuilder.newJob(SimpleJob::class.java)
            .withIdentity("simpleJob")
            .storeDurably()
            .build()
    }

    @Bean
    fun simpleJobTrigger(simpleJobDetail: JobDetail): Trigger {
        return TriggerBuilder.newTrigger()
            .forJob(simpleJobDetail)
            .withIdentity("simpleJobTrigger")
            .withSchedule(
                SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(10)
                    .repeatForever()
            )
            .build()
    }
}
