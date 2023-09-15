package nikschadowsky.engine.input;

import nikschadowsky.engine.event.EventHandler;
import nikschadowsky.engine.event.InputEvent;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.Queue;

/**
 * File created on 20.08.2023
 */
public class InputHandler implements EventHandler<InputEvent> {

    private final InputUpdatable rootUpdatable;

    private final Queue<InputEvent> inputEventQueue;

    /**
     * Create an InputHandler as an entry point for controlled User inputs. This handler gets created automatically in
     * the SuperWindow creation process, thus not needing manual instantiation by the regular user. The method defined
     * by this interface gets invoked, when all events are dequeued.
     *
     * @param rootUpdatable entry point of input control
     *
     * @see #dequeueAllEvents()
     */
    public InputHandler(@NotNull InputUpdatable rootUpdatable) {
        this.rootUpdatable = rootUpdatable;

        inputEventQueue = new LinkedList<>();
    }

    /**
     * Invokes the updateInput() method of the InputUpdatable object for every recorded Input in order of recording.
     * Should only be invoked by the associated application's loop
     */
    public void dequeueAllEvents() {
        while (!inputEventQueue.isEmpty()) {
            rootUpdatable.updateInput(inputEventQueue.poll());
        }
    }

    /**
     * Register a new event for an Application Input.
     *
     * @param e the new InputEvent
     */
    public void enqueueEvent(@NotNull InputEvent e) {
        inputEventQueue.offer(e);
    }
}
