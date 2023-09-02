package nikschadowsky.engine.window_old;

import nikschadowsky.engine.statemanager.StateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Deprecated
public class Window {

    private JFrame window;


    private int instanceID = -1;


    public Window(WindowConfiguration wc, StateManager statemgr) {

        window = new JFrame(wc.getTitle());

        window.setFocusable(false);

        window.setBounds(wc.getX(), wc.getY(), wc.getWidth(), wc.getHeight());
        window.setUndecorated(!wc.isDecorated());
        window.setResizable(wc.isResizable());

        /* */

        window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        window.addWindowListener(new WindowAdapter() {


            @Override
            public void windowClosing(WindowEvent e) {
                close();

            }

        });


        window.setLayout(new BorderLayout());

        // finalize window


        window.setVisible(true);
    }

    public void close() {

        window.setVisible(false);


        // threadmanager zum nullen in instance-list aufrufen
        /*if (!ThreadManager.close(instanceID)) {
            System.err.println("Couldn't close window instance #" + instanceID);
        }*/

    }


    /**
     * @return this window's instanceID set by called start-method
     */
    public int getInstanceID() {
        return instanceID;
    }


    public void setInstanceID(int instanceID) {
        this.instanceID = instanceID;
    }
}
