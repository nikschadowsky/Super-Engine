package nikschadowsky.engine.statemanager;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import nikschadowsky.engine.hierarchy.State;
import nikschadowsky.engine.opengl.VertexArrayObject;
import nikschadowsky.engine.renderer.Renderable;
import nikschadowsky.engine.rendering_old.RenderingInformation;

import java.util.HashMap;

// FIXME: 20.08.2023 REWORK this bad boi
public abstract class StateManager implements GLEventListener, Renderable {

    private HashMap<String, State> states;

    private State defaultState, currentState;

    private RenderingInformation renderingInfo;

    private VertexArrayObject defaultVAO;

    public StateManager() {


        states = new HashMap<>();

        addStates(states);
        defaultState = states.get(setDefaultState());
        currentState = defaultState;

        renderingInfo = new RenderingInformation();
    }

    /**
     * Enter all of your layers in this method and add them to layers
     */
    public abstract void addStates(HashMap<String, State> states);

    public abstract String setDefaultState();


    public void update() {

        currentState.update();

    }

    public void input() {
        currentState.input();

    }


    @Override
    public void init(GLAutoDrawable drawable) {

        GL4 gl = drawable.getGL().getGL4();

        gl.glEnable(GL.GL_BLEND);
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);


        renderingInfo.initGL(gl);

        (defaultVAO = new VertexArrayObject()).create(gl);

        currentState.initRendering(renderingInfo);

    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        // TODO Auto-generated method stub

    }

    @Override
    public void display(GLAutoDrawable drawable) {

        GL4 gl = drawable.getGL().getGL4();

        gl.glClearColor(0f, 0f, 0f, 1f);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);


        renderingInfo.updateGL(gl);

        currentState.render(renderingInfo);

        defaultVAO.bind();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {


        currentState.reshape(width, height);

    }


}
