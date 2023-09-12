package nikschadowsky.engine.event;

import nikschadowsky.engine.input.Input;
import nikschadowsky.engine.input.InputHandler;

import java.awt.event.KeyEvent;

/**
 * File created on 03.09.2023
 */
public class InputEvent implements Event {
    private final Input actionType;
    private final int identifier;

    private final boolean isConsumable;

    private boolean consumed; // track if this event was already processed
    private int scrollAmount = 0;


    public InputEvent(Input actionType, int identifier, InputHandler handler) {
        this(actionType, identifier, handler, true);
    }

    public InputEvent(Input actionType, int identifier, InputHandler handler, boolean isConsumable) {
        this.actionType = actionType;
        this.identifier = identifier;
        //this.handler = handler;
        this.isConsumable = isConsumable;
    }

    /**
     * Event for registration of a mouse wheel scroll event
     *
     * @param scrollAmount
     * @param handler
     */
    public InputEvent(int scrollAmount, InputHandler handler) {
        this(Input.SCROLLED, -1, handler);
        this.scrollAmount = scrollAmount;
    }

    public InputEvent(Input actionType, InputHandler handler) {
        this(actionType, -1, handler);
    }

    public boolean hasPressed(int identifier) {
        return !isConsumed() && actionTypeEquals(Input.PRESSED) && (identifier == this.identifier);
    }

    public boolean hasPressed(char character) {
        return hasPressed(KeyEvent.getExtendedKeyCodeForChar(character));
    }

    public boolean hasReleased(int identifier) {
        return !isConsumed() && actionTypeEquals(Input.RELEASED) && (identifier == this.identifier);
    }

    public boolean hasReleased(char character) {
        return hasReleased(KeyEvent.getExtendedKeyCodeForChar(character));
    }

    public boolean hasClicked(int identifier) {
        return !isConsumed() && actionTypeEquals(Input.CLICKED) && (identifier == this.identifier);
    }

    public boolean hasClicked(char character) {
        return hasClicked(KeyEvent.getExtendedKeyCodeForChar(character));
    }

    public boolean hasExitedWindow() {
        return actionTypeEquals(Input.EXITED);
    }

    public boolean hasEnteredWindow() {
        return actionTypeEquals(Input.ENTERED);
    }

    public int getScrollAmount() {
        return scrollAmount;
    }

    public boolean hasScrolled() {
        return !isConsumed() && actionTypeEquals(Input.SCROLLED);
    }

    public boolean consume() {
        if (hasScrolled()) scrollAmount = 0;
        if (isConsumable) consumed = true;
        return isConsumable;
    }

    public boolean isConsumed() {
        return consumed;
    }

    public boolean actionTypeEquals(Input actionType) {
        return this.actionType.equals(actionType);
    }


    @Override
    public String toString() {
        return "InputEvent{" +
                "actionType=" + actionType +
                ", identifier=" + identifier +
                ", isConsumable=" + isConsumable +
                ", consumed=" + consumed +
                ", scrollAmount=" + scrollAmount +
                '}';
    }
}
