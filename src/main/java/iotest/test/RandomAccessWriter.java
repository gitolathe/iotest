package iotest.test;

import static iotest.Constants.FILE_SIZE;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;

/**
 *
 * @author Ola Theander <ola.theander@nasdaqomx.com>
 */
public class RandomAccessWriter implements Test {

    public static final byte[] BLANK_FILE = new byte[FILE_SIZE];

    private final long runForMs;
    private final String name;
    private final String fileName;
    private long writeCount = 0;

    public RandomAccessWriter(long runForMs, String name, String fileName) {
        this.runForMs = runForMs;
        this.name = name;
        this.fileName = fileName;
    }

    @Override
    public void prepare() {

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void run() {
        try {
            final long start = System.currentTimeMillis();
            final Random random = new Random();

            while (System.currentTimeMillis() - start < runForMs) {
                FileChannel channel
                        = new RandomAccessFile(fileName, "rws").getChannel();
                
//                random.nextBytes(BLANK_FILE);
                channel.write(ByteBuffer.wrap(BLANK_FILE));
                channel.close();

                //Increment the write count.
                writeCount += 1;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public long getCounter() {
        return writeCount;
    }

}
