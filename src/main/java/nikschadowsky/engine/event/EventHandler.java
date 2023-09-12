package nikschadowsky.engine.event;

/**
 * File created on 09.09.2023
 */
public interface EventHandler<T extends Event> {

    void dequeueAllEvents();
    void enqueueEvent(T event);

}
