package iotest.test;

/**
 * Base interface defining a test.
 *
 * @author Ola Theander <ola.theander@nasdaqomx.com>
 */
public interface Test extends Runnable {

    void prepare();

    String getName();
    
    long getCounter();
}
