package nikschadowsky.engine.window;

import nikschadowsky.engine.loop.ApplicationLoop;
import nikschadowsky.engine.loop.CoreWindowLoop;
import nikschadowsky.engine.loop.observer.ApplicationLoopObserver;
import nikschadowsky.engine.loop.observer.LoopStateEvent;
import nikschadowsky.engine.management.ApplicationInstanceImpl;
import nikschadowsky.engine.renderer.RendererVariant;
import nikschadowsky.engine.statemanager.StateManager;
import nikschadowsky.engine.window.configuration.SuperWindowConfiguration;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * File created on 12.08.2023
 */
public class SuperWindow extends ApplicationInstanceImpl implements ApplicationLoopObserver {

    private final StateManager stateManager;

    private final SuperWindowConfiguration windowConfiguration;
    private final RendererVariant rendererVariant;

    private SuperWindowFrame windowTitleFrame;

    private JFrame window;

    private Container contentPane;

    private ApplicationLoop loop;

    public SuperWindow(@NotNull StateManager stateManager, @NotNull SuperWindowConfiguration windowConfig, @NotNull RendererVariant rendererVariant) {
        this.stateManager = stateManager;
        this.windowConfiguration = windowConfig;

        this.rendererVariant = rendererVariant;
    }

    public void setWindowFrame(@NotNull SuperWindowFrame frame) {
        if (isInitialized())
            throw new SuperWindowInitializationException("Cannot set Window-Frame after Window was started!");
        windowTitleFrame = frame;
    }

    @Override
    public void init() {
        window = createFrame(windowConfiguration);

        setupFrame();

        loop = createCoreWindowLoop(windowConfiguration, stateManager, contentPane, rendererVariant);
        // We want to monitor this Loops Status to react accordingly, when the loop stops
        loop.addObserver(this);


        /*
         * ABLAUF INIT
         * -- WindowFrame gesetzt: Check
         * -- contentPane setzen. chECK
         * -- GLContext init : CoreLoops Task
         * -- CoreLoop erzeugen und per DI context reinreichen zum repainten: Check
         * -- *start()*
         */

        window.setVisible(true);
    }

    private JFrame createFrame(SuperWindowConfiguration windowConfiguration) {
        JFrame newFrame = new JFrame(windowConfiguration.getTitle());

        newFrame.setSize(windowConfiguration.getSize());
        newFrame.setMinimumSize(windowConfiguration.getMinimumSize());
        newFrame.setMaximumSize(windowConfiguration.getMaximumSize());

        newFrame.setResizable(windowConfiguration.isResizable());

        newFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stop();
            }
        });

        return newFrame;
    }

    private void setupFrame() {
        contentPane = window.getContentPane();

        if (windowTitleFrame != null) contentPane = windowTitleFrame.getContentPane();
    }

    private CoreWindowLoop createCoreWindowLoop(SuperWindowConfiguration windowConfiguration, StateManager stateManager, Container contentPane, RendererVariant renderer) {

        return new CoreWindowLoop(windowConfiguration.getTicksPerSecond(), windowConfiguration.getFramesPerSecond(), stateManager, contentPane, renderer);
    }

    @Override
    public void stop() {
        System.err.println("EXITING WINDOW");
        loop.stop();
        window.dispose();
        removeFromActiveApplicationInstances();
    }

    @Override
    public ApplicationLoop getLoop() {
        return loop;
    }

    @Override
    public boolean isAlive() {
        return loop.isRunning();
    }

    @Override
    public void loopStateUpdate(LoopStateEvent e) {
        if(e.state().equals(LoopStateEvent.State.STOPPED)){
            stop();
        }
    }
}
