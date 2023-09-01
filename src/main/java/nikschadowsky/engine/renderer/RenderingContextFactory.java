package nikschadowsky.engine.renderer;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * File created on 19.08.2023
 */
public class RenderingContextFactory {

    private static final RendererAPIVariant DEFAULT_API = RendererAPIVariant.OPENGL;

    /**
     * Get a container to add to the SuperWindow and the root {@link Renderable}, which handles the user's rendering.
     * @param variant API variant
     * @param root {@link Renderable} which handles rendering
     * @return Component that contains the appropriate Rendering Context for the given API variant
     */
    @NotNull
    public static Container getRenderingContext(@NotNull RendererAPIVariant variant, Renderable root){
        switch (variant) {
            case OPENGL -> {return getContainerForOpenGl(root);}
            }
        return getRenderingContext(DEFAULT_API, root);
    }

    /**
     * Create and return the Container that uses the OpenGL implementation by JOGL on the GL 4.x version.
     * @param r root renderable
     * @return OpenGL Container
     */
    private static Container getContainerForOpenGl(Renderable r){
        GLJPanel panel = new GLJPanel(new GLCapabilities(GLProfile.get(GLProfile.GL4)));
        panel.setFocusable(true);

        panel.addGLEventListener(new GLRenderer(r));

        return panel;
    }
}
