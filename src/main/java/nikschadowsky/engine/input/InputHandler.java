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

    public InputHandler(@NotNull InputUpdatable rootUpdatable) {
        this.rootUpdatable = rootUpdatable;

        inputEventQueue = new LinkedList<>();
    }

    public void dequeueAllEvents() {
        while (!inputEventQueue.isEmpty()) {
            rootUpdatable.updateInput(inputEventQueue.poll());
        }
    }

    public void enqueueEvent(InputEvent e){
        inputEventQueue.offer(e);
    }
}
