package iotest.test;

import static iotest.Constants.FILE_SIZE;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 * @author Ola Theander <ola.theander@nasdaqomx.com>
 */
public class RandomAccessReader implements Test {

    public static final byte[] BLANK_FILE = new byte[FILE_SIZE];

    private final long runForMs;
    private final String name;
    private final String fileName;
    private long readCount = 0;

    public RandomAccessReader(long runForMs, String name, String fileName) {
        this.runForMs = runForMs;
        this.name = name;
        this.fileName = fileName;
    }

    @Override
    public void prepare() {
//        try {
//            //Prepare a file to read.
//            final byte[] BLANK_FILE = new byte[FILE_SIZE];
//            new Random().nextBytes(BLANK_FILE);
//
//            FileChannel channel
//                    = new RandomAccessFile(fileName, "rws").getChannel();
//            channel.write(ByteBuffer.wrap(BLANK_FILE));
//            channel.close();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void run() {
        try {
            //Wait until the file exists.
            final File file = new File(fileName);
            while(!file.exists()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            System.out.println("Starting read test.");
            
            final long start = System.currentTimeMillis();

            while (System.currentTimeMillis() - start < runForMs) {
                FileChannel channel
                        = new RandomAccessFile(fileName, "rws").getChannel();
                channel.read(ByteBuffer.wrap(BLANK_FILE));
                channel.close();

                //Increment the read count.
                readCount += 1;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public long getCounter() {
        return readCount;
    }

}
