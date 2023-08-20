package nikschadowsky.engine.renderer;

import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

/**
 * File created on 19.08.2023
 */
public class GLRenderer implements GLEventListener {

    private final Renderable renderable;

    GLRenderer(Renderable renderable){
        this.renderable = renderable;
    }

    @Override
    public void init(GLAutoDrawable drawable) {

    }

    @Override
    public void dispose(GLAutoDrawable drawable) {

    }

    @Override
    public void display(GLAutoDrawable drawable) {
        renderable.render();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

    }
}
