package nikschadowsky.engine.loop;

import nikschadowsky.engine.loop.observer.ApplicationLoopObserver;

/**
 * File created on 18.08.2023
 */
public interface ApplicationLoop {

    void start();

    void pause();

    void resume();

    void stop();

    boolean isRunning();

    void addObserver(ApplicationLoopObserver observer);

}
