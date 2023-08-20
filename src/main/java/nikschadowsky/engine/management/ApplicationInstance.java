package nikschadowsky.engine.management;

import nikschadowsky.engine.loop.ApplicationLoop;

/**
 * File created on 12.08.2023
 */
public sealed interface ApplicationInstance permits ApplicationInstanceImpl {
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
    ApplicationLoop getLoop();

    /**
     * @return whether
     */
    boolean isAlive();
}
