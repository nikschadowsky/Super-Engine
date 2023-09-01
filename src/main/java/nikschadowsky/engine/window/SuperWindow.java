package nikschadowsky.engine.window;

import nikschadowsky.engine.loop.ApplicationLoop;
import nikschadowsky.engine.loop.CoreWindowLoop;
import nikschadowsky.engine.loop.observer.ApplicationLoopObserver;
import nikschadowsky.engine.loop.observer.LoopStateEvent;
import nikschadowsky.engine.management.ApplicationInstanceImpl;
import nikschadowsky.engine.renderer.RendererAPIVariant;
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

    private final RendererAPIVariant rendererApiVariant;

    private final SuperWindowFrame customWindowFrame;

    private JFrame window;

    private Container renderingContainer;

    private ApplicationLoop loop;

    /**
     * Creates a SuperWindow. This window may be started by invoking {@code start();} and after that may be terminated unrestorably by invoking {@code end();} on this object
     *
     * @param stateManager      StateManager
     * @param windowConfig      Configuration for this Window
     * @param customWindowFrame Custom Application Frame or {@code null} for the OS-default
     * @param rendererApi       API Variant used by this Window
     */
    public SuperWindow(@NotNull StateManager stateManager, @NotNull SuperWindowConfiguration windowConfig, SuperWindowFrame customWindowFrame, @NotNull RendererAPIVariant rendererApi) {
        this.stateManager = stateManager;
        this.windowConfiguration = windowConfig;
        this.customWindowFrame = customWindowFrame;
        this.rendererApiVariant = rendererApi;
    }

    @Override
    public void init() {
        window = createFrame(windowConfiguration);

        setupWindowFrame();

        loop = createCoreWindowLoop(windowConfiguration, stateManager, renderingContainer, rendererApiVariant);
        // We want to monitor this Loops Status to react accordingly, when the loop changes state
        loop.addObserver(this);

        window.setVisible(true);
    }

    /**
     * Instantiates a new JFrame with attributes set to the ones specified in windowConfiguration.
     *
     * @param windowConfiguration Configuration for the JFrame created
     * @return configured JFrame
     */
    private JFrame createFrame(SuperWindowConfiguration windowConfiguration) {
        JFrame newFrame = new JFrame(windowConfiguration.getTitle());

        newFrame.setSize(windowConfiguration.getSize());
        newFrame.setMinimumSize(windowConfiguration.getMinimumSize());
        newFrame.setMaximumSize(windowConfiguration.getMaximumSize());

        newFrame.setResizable(windowConfiguration.isResizable());

        newFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                end();
            }
        });

        return newFrame;
    }

    /**
     * Set up the requirements for using a custom Window Frame.
     */
    private void setupWindowFrame() {
        renderingContainer = window.getContentPane();
        renderingContainer.setLayout(new BorderLayout());

        if (customWindowFrame != null) {
            renderingContainer = customWindowFrame.getRenderingContainer();
            window.setUndecorated(true);
            customWindowFrame.setAssignedSuperWindow(this);
            customWindowFrame.validateComponentHierarchy();
            window.getContentPane().add(customWindowFrame.getFrameRootContainer());
        }
    }

    /**
     * Create a ApplicationLoop Object for this Window.
     *
     * @param windowConfiguration associated windowConfiguration
     * @param stateManager        associated stateManager
     * @param contentPane         associated contentPane
     * @param rendererApi         associated rendererApi
     * @return Application Loop for this Window
     */
    private ApplicationLoop createCoreWindowLoop(SuperWindowConfiguration windowConfiguration, StateManager stateManager, Container contentPane, RendererAPIVariant rendererApi) {
        return new CoreWindowLoop(windowConfiguration.getTicksPerSecond(), windowConfiguration.getFramesPerSecond(), stateManager, contentPane, rendererApi);
    }

    /**
     * @return the Window Configuration
     */
    public SuperWindowConfiguration getWindowConfiguration() {
        return windowConfiguration;
    }

    /**
     * @return this Window's associated ApplicationLoop
     */
    @Override
    public ApplicationLoop getLoop() {
        return loop;
    }

    /**
     * @return whether the loop is running. Should always return true.
     * TODO: 20.08.2023 Check if this method makes any sense, since the window cannot exist without a running loop.
     */
    @Override
    public boolean isAlive() {
        return loop.isRunning();
    }

    /**
     * Attempt to stop the associated ApplicationLoop and ultimately close this Window.
     */
    @Override
    public void end() {
        System.err.println("EXITING WINDOW, TRYING TO STOP LOOP!");
        loop.stop();
    }

    /**
     * Method, when the loop sends a Status update.
     * Should not be invoked manually or else unexpected behaviour may occur!
     *
     * @param e LoopStateEvent
     */
    @Override
    public void loopStateUpdate(LoopStateEvent e) {
        if (e.state().equals(LoopStateEvent.State.STOPPED)) {
            endInstance();
        }
    }

    /**
     * End this windows lifecycle by disposing it and removing it from all active instances.
     */
    private void endInstance() {
        window.dispose();
        removeFromActiveApplicationInstances();
    }

}
