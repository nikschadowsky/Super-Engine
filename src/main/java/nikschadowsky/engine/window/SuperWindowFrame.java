package nikschadowsky.engine.window;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

/**
 * File created on 12.08.2023
 */
public abstract class SuperWindowFrame {

    private final JPanel frameRootContainer;

    private final JPanel renderingContainer;

    private SuperWindow assignedWindow;

    /**
     * Create a SuperWindow consisting of a separate Frame-Root-Container and a Rendering-Container. They are not in any
     * relationship by default. While the Frame-Root-Container will be added by the SuperWindow, the Rendering-Container
     * will hold the Rendering Component only. When used by the SuperWindow class, validity will be tested beforehand to
     * check, if Rendering-Container is a descendant of the Root-Container and if the
     * Rendering-Container does not have any children of its own before it is being added. Extending this class will
     * allow you to customize the behaviour to create a window frame of your own choice.
     */
    public SuperWindowFrame() {
        frameRootContainer = new JPanel();
        frameRootContainer.setLayout(new BorderLayout());

        renderingContainer = new JPanel();
        renderingContainer.setLayout(new BorderLayout());

        init();
    }

    public void init(){}

    /**
     * Since subclasses may use information provided by the SuperWindow, can an instance of SuperWindowFrame only be
     * used by one SuperWindow. To check for multiple usages and to register the assigned SuperWindow
     *
     * @param window assigned Window
     */
    void setAssignedSuperWindow(@NotNull SuperWindow window) {
        if (assignedWindow != null) {
            throw new SuperWindowInitializationException("SuperWindowFrame Object cannot be reused on other SuperWindows!");
        }
        assignedWindow = window;
    }

    /**
     * Tests the component hierarchy. The component hierarchy is correct, if the {@link #getRenderingContainer()}
     * container is a descendant of the {@link #getFrameRootContainer()} container AND if the
     * {@link #getRenderingContainer()} container does not have any children of its own.
     *
     * @return whether the component hierarchy is valid
     */
    public final boolean validateComponentHierarchy() {

        Container parent = getRenderingContainer().getParent();

        if(getRenderingContainer().getComponentCount() != 0) throw new SuperWindowInitializationException("Invalid setup of SuperWindowFrame component hierarchy! Rendering Container has Descendants!");

        while (parent != null) {

            if (parent.equals(getFrameRootContainer())) return true;

            parent = parent.getParent();
        }


        throw new SuperWindowInitializationException("Invalid setup of SuperWindowFrame component hierarchy! Frame root container is not a ancestor of Rendering Container! ");
    }

    /**
     * @return the Rendering Container
     */
    @NotNull
    public final Container getRenderingContainer() {
        return renderingContainer;
    }

    /**
     * @return the Root Container of this SuperWindowFrame
     */
    @NotNull
    public final Container getFrameRootContainer() {
        return frameRootContainer;
    }

    /**
     * @return the SuperWindow using this instance
     */
    public SuperWindow getAssignedWindow() {
        return assignedWindow;
    }

}
