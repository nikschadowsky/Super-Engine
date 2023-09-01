package nikschadowsky.engine.window;

import nikschadowsky.engine.hierarchy.State;
import nikschadowsky.engine.management.ThreadManager;
import nikschadowsky.engine.renderer.RendererAPIVariant;
import nikschadowsky.engine.statemanager.StateManager;
import nikschadowsky.engine.window.configuration.SuperWindowConfiguration;
import org.junit.jupiter.api.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * File created on 01.09.2023
 */
public class SuperWindowTest {


    private SuperWindowConfiguration config;

    private StateManager statemanager;

    /* Concept
     * ThreadManager tm =  ThreadManager.create(String config);
     *
     * ThreadManager.getInstance(); after that
     *
     * SuperWindow win = new SuperWindow(ThreadManager tm, StateManager sm, String windowConfig);
     * SuperWindow win = new SuperWindow(ThreadManager tm, StateManager sm); // windowConfig is optional
     *
     * SuperWindow::setWindowFrame(SuperWindowFrame frame); // else default window frame
     *
     */

    @BeforeAll
    static void setup() {
        ThreadManager.createThreadManager("", new String[0]);
    }

    @BeforeEach
    void createConfig() {
        config = new SuperWindowConfiguration.SuperWindowConfigurationBuilder().addTPS(1).build();

        statemanager = new StateManager() {
            @Override
            public void render() {

            }

            @Override
            public void addStates(HashMap<String, State> states) {

            }

            @Override
            public String setDefaultState() {
                return null;
            }
        };
    }

    @Test
    public void testWindowCreation() {

        SuperWindowFrame frame = new SuperWindowFrame() {

            @Override
            public void init() {
                JButton b = new JButton("Exit");
                b.addActionListener(e -> {
                    System.err.println("Exiting via Button");
                    getAssignedWindow().end();
                });

                getFrameRootContainer().add(b, BorderLayout.NORTH);

                JPanel middle = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        g.setColor(Color.BLUE);
                        g.fillRect(10, 10, 200, 200);
                    }
                };
                middle.setLayout(new BorderLayout());
                middle.add(getRenderingContainer(), BorderLayout.EAST);

                getRenderingContainer().setPreferredSize(new Dimension(200, 1));

                getFrameRootContainer().add(middle, BorderLayout.CENTER);

            }
        };


        SuperWindow windowCustom = new SuperWindow(statemanager, config, frame, RendererAPIVariant.OPENGL);

        windowCustom.start();



    }

    @Test
    void testDefaultWindowFrame(){
        SuperWindow windowDefault = new SuperWindow(statemanager, config, null, RendererAPIVariant.OPENGL);

        windowDefault.start();
    }

    @AfterEach
    void waitAfter() throws InterruptedException {

        // wait 10 seconds before the test ends
        Thread.sleep(10000);
    }
}
