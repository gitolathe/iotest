package iotest;

import static iotest.Constants.RUN_TIME_MS;
import iotest.test.Test;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ola Theander <ola.theander@nasdaqomx.com>
 */
@Component
public class RunTests {

    @Autowired
    private Environment env;

    @Autowired
    private List<Test> tests;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @SuppressWarnings("SleepWhileInLoop")
    public void runTests() {
        System.out.println("Initating all tests.");

        //Initialize all tests.
        tests
                .stream()
                .forEach((test) -> {
                    test.prepare();
                    try {
                        //Wait for X ms.
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        System.err.println("Task Executor interrupted.");
                        ex.printStackTrace();
                    }
                });

        //Run all tests.
        tests.stream().forEach((test) -> {
            System.out.println("Executing test " + test.getName());
            taskExecutor.execute(test);
        });

        final long start = System.currentTimeMillis();
        try {
            //Wait for X ms.
            Thread.sleep(RUN_TIME_MS + 1000);
        } catch (InterruptedException ex) {
            System.err.println("Task Executor interrupted.");
            ex.printStackTrace();
        }

        final long end = System.currentTimeMillis();

        taskExecutor.shutdown();

        //Collect test information from supervisors.
        System.out.println("***************************** Result report *********************************");
        System.out.println("Test ran for " + RUN_TIME_MS / 1000 + " seconds.\n");

        tests
                .stream()
                .forEach(test -> {
                    System.out.println("Test " + test.getName() + " produced " + test.getCounter() + " I/O ops (" + test.getCounter() / (RUN_TIME_MS / 1000) + " ops/sec).");
                });

        System.out.println("Tests done, exiting.");
        System.out.println("*****************************************************************************");
    }
}
