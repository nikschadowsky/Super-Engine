package nikschadowsky.engine.loop.observer;

import nikschadowsky.engine.loop.ApplicationLoop;

/**
 * File created on 19.08.2023
 */
public record LoopStateEvent(ApplicationLoop source, State state) {

    public enum State{
        STARTED, STOPPED, PAUSED, RESUMED
    }
}
