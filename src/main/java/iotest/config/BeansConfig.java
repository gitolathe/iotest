package iotest.config;

import static iotest.Constants.RUN_TIME_MS;
import iotest.test.RandomAccessReader;
import iotest.test.RandomAccessWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Ola Theander <ola.theander@nasdaqomx.com>
 */
@Configuration
public class BeansConfig {
//    @Bean
//    public RandomAccessReader getRandomAccessReader() {
//        return new RandomAccessReader(RUN_TIME_MS, "RandomAccessReader 1", "test1.dat");
//    }

//    @Bean
//    public RandomAccessReader getRandomAccessReader2() {
//        return new RandomAccessReader(RUN_TIME_MS, "RandomAccessReader 2", "read2.dat");
//    }

    @Bean
    public RandomAccessWriter getRandomAccessWriter() {
        return new RandomAccessWriter(RUN_TIME_MS, "RandomAccessWriter 1", "test1.dat");
    }

    @Bean
    public RandomAccessWriter getRandomAccessWriter2() {
        return new RandomAccessWriter(RUN_TIME_MS, "RandomAccessWriter 2", "write2.dat");
    }
}
