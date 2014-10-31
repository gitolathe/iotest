package iotest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        final ApplicationContext context = SpringApplication.run(Application.class, args);
        final RunTests runTests = context.getBean(RunTests.class);
        runTests.runTests();
    }
}
