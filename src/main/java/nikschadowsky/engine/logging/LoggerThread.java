package nikschadowsky.engine.logging;

/**
 * File created on 21.09.2023
 */
public class LoggerThread implements Runnable {

    private static LoggerThread instance= new LoggerThread();

    public static LoggerThread getInstance(){
        return instance;
    }

    private final Thread thread;

    private LoggerThread() {
        this.thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void run() {

    }


}
