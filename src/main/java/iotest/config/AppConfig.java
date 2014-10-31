package iotest.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 *
 * @author Ola Theander <ola.theander@nasdaqomx.com>
 */
@Configuration
@ComponentScan({"com.nasdaqomx.test.smrv"})
public class AppConfig {
    private static final Logger LOG = LoggerFactory.getLogger(AppConfig.class);

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        final ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(5);
        pool.setMaxPoolSize(10);
        pool.setWaitForTasksToCompleteOnShutdown(true);
        return pool;
    }

}
