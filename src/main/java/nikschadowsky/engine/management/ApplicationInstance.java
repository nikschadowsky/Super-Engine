package nikschadowsky.engine.management;

/**
 * File created on 12.08.2023
 */
public interface ApplicationInstance {
    /**
     * Register this Application Instance and start the Thread
     */
    void start();

    /**
     * Terminate Thread. It cannot be restarted after that.
     */
    void stop();

    /**
     * @return the associated Thread
     */
    Thread getThread();

    /**
     * @return whether
     */
    boolean isAlive();
}
