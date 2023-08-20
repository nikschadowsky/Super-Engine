package nikschadowsky.engine.renderer;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;

import java.awt.*;

/**
 * File created on 19.08.2023
 */
public class RenderingContextFactory {

    public static Container getRenderingContext(RendererVariant variant, Renderable r){
        switch (variant) {
            case OPENGL -> {return doOpenGl(r);}
            }
        return null;
    }

    private static Container doOpenGl(Renderable r){
        GLJPanel panel = new GLJPanel(new GLCapabilities(GLProfile.get(GLProfile.GL4)));
        panel.setFocusable(true);

        panel.addGLEventListener(new GLRenderer(r));

        return panel;
    }
}
