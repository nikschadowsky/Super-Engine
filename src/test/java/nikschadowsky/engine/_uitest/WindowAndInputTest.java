package nikschadowsky.engine._uitest;

import nikschadowsky.engine.event.InputEvent;
import nikschadowsky.engine.input.InputListener;
import nikschadowsky.engine.input.InputUpdatable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * File created on 09.09.2023
 */
public class WindowAndInputTest {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Title");
        frame.setFocusTraversalKeysEnabled(false );

        frame.setSize(600,600);

        InputListener listener = new InputListener(new TestUpdatable());

        frame.addKeyListener(listener);
        frame.addMouseListener(listener);
        frame.addMouseWheelListener(listener);

        frame.setLayout(new BorderLayout());
        TextArea comp = new TextArea();
        comp.setFocusTraversalKeysEnabled(true);

        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                System.out.println("Closing");
                listener.getInputHandler().dequeueAllEvents();
                System.exit(0);
            }
        });

        frame.setVisible(true);
    }

    static class TestUpdatable implements InputUpdatable{

        @Override
        public void updateInput(InputEvent e) {
            System.out.println(e);
        }
    }
}
