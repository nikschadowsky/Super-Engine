package nikschadowsky.engine.rendering;

import com.jogamp.opengl.GL4;
import nikschadowsky.engine.file.FileReader;
import nikschadowsky.engine.opengl.Shader.FragmentShader;
import nikschadowsky.engine.opengl.Shader.ShaderProgram;
import nikschadowsky.engine.opengl.Shader.VertexShader;

public class RenderingInformation {

    private GL4 gl;

    private ShaderProgram defaultShaderProgram;

    /**
     * @return the gl
     */
    public GL4 GL() {
        return gl;
    }

    public void initGL(GL4 gl) {

        this.gl = gl;

        // default shader erzeugen
        defaultShaderProgram = new ShaderProgram();
        defaultShaderProgram.create(gl);

        // vertex shader
        VertexShader vs = new VertexShader();
        vs.create(gl);
        vs.setSourceCode(FileReader.readFile("shader/vert.glsl"));

        FragmentShader fs = new FragmentShader();
        fs.create(gl);
        fs.setSourceCode(FileReader.readFile("shader/frag.glsl"));

        defaultShaderProgram.attach(vs);
        defaultShaderProgram.attach(fs);

        defaultShaderProgram.link();


    }

    /**
     * @return the defaultShaderProgram
     */
    public ShaderProgram getDefaultShaderProgram() {
        return defaultShaderProgram;
    }

    public void disable() {
        gl.glUseProgram(0);
    }

    /**
     * @param gl the gl to set
     */
    public void updateGL(GL4 gl) {
        this.gl = gl;
    }

}
