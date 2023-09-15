package nikschadowsky.engine.event;

import nikschadowsky.engine.input.Input;
import nikschadowsky.engine.input.InputHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;

/**
 * File created on 03.09.2023
 */
public class InputEvent implements Event {
    private final Input actionType;
    private final int identifier;

    private final boolean isConsumable;

    private boolean consumed; // track if this event was already processed
    private int scrollAmount = 0;

    private final InputHandler origin;


    /**
     * Event for registration of a key or mouse event. This event is consumable by default
     *
     * @param actionType the type of action
     * @param identifier the unique identifier or 'code' of that key or mouse event
     * @param origin     the calling or creating class
     */
    public InputEvent(@NotNull Input actionType, int identifier, InputHandler origin) {
        this(actionType, identifier, origin, true);
    }

    /**
     * Event for registration of a key or mouse event.
     *
     * @param actionType   the type of action
     * @param identifier   the unique identifier or 'code' of that key or mouse event
     * @param origin       the calling or creating class
     * @param isConsumable whether this event can be consumed and ignored from further processing
     */
    public InputEvent(@NotNull Input actionType, int identifier, InputHandler origin, boolean isConsumable) {
        this.actionType = actionType;
        this.identifier = identifier;
        this.origin = origin;
        this.isConsumable = isConsumable;
    }

    /**
     * Event for registration of a mouse wheel scroll event
     *
     * @param scrollAmount the amount of units which the wheel has been turned (see
     *                     {@link MouseWheelEvent#getUnitsToScroll()})
     * @param origin       the calling or creating class
     */
    public InputEvent(int scrollAmount, InputHandler origin) {
        this(Input.SCROLLED, -1, origin);
        this.scrollAmount = scrollAmount;
    }

    /**
     * Event for registration of ENTERED or EXITED events
     *
     * @param actionType the type of action
     * @param origin     the calling or creating class
     */
    public InputEvent(@NotNull Input actionType, InputHandler origin) {
        this(actionType, -1, origin);
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

    /**
     * Tries to consume this event suppressing it from further processing.
     *
     * @return if consuming was successful. If an event is not consumable or was consumed already, this method returns
     * false.
     */
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

    @Nullable
    public InputHandler getOrigin() {
        return origin;
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
