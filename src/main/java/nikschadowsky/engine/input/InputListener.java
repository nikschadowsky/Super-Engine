package nikschadowsky.engine.input;

import nikschadowsky.engine.event.InputEvent;

import java.awt.event.*;
import java.util.HashMap;

/**
 * File created on 03.09.2023
 */
public class InputListener implements KeyListener, MouseListener, MouseWheelListener {

    public static final long KEY_CLICKED_EVENT_THRESHOLD = 200_000_000; // 200 milliseconds in nanoseconds

    private final InputHandler handler;

    private final HashMap<Integer, Long> pressedKeys;

    public InputListener(InputUpdatable updatable) {
        handler = new InputHandler(updatable);
        pressedKeys = new HashMap<>();
    }

    public InputHandler getInputHandler() {
        return handler;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        handler.enqueueEvent(new InputEvent(Input.TYPED, e.getExtendedKeyCode(), handler));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        handler.enqueueEvent(new InputEvent(Input.PRESSED, e.getExtendedKeyCode(), handler));
        pressedKeys.put(e.getExtendedKeyCode(), System.nanoTime());
    }

    @Override
    public void keyReleased(KeyEvent e) {


        // if for some reason the map does not contain the System.nanoTime() to e.getExtendedKeyCode()
        // we use the negative of the threshold, so then at no time we can trigger a CLICKED event
        // The way this is implemented here, resembles the way the CLICKED event with mouse button inputs works
        if (System.nanoTime() - pressedKeys
                .getOrDefault(e.getExtendedKeyCode(), -KEY_CLICKED_EVENT_THRESHOLD) < KEY_CLICKED_EVENT_THRESHOLD) {
            handler.enqueueEvent(new InputEvent(Input.CLICKED, e.getExtendedKeyCode(), handler));
        } else {
            handler.enqueueEvent(new InputEvent(Input.RELEASED, e.getExtendedKeyCode(), handler));
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        handler.enqueueEvent(new InputEvent(Input.CLICKED, e.getButton(), handler));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        handler.enqueueEvent(new InputEvent(Input.PRESSED, Input.getMouseInputOffset() + e.getButton(), handler));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        handler.enqueueEvent(new InputEvent(Input.RELEASED, Input.getMouseInputOffset() + e.getButton(), handler));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        handler.enqueueEvent(new InputEvent(Input.ENTERED, handler));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        handler.enqueueEvent(new InputEvent(Input.EXITED, handler));
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        handler.enqueueEvent(new InputEvent(e.getUnitsToScroll(), handler));
    }
}
