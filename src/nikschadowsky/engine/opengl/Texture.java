package nikschadowsky.engine.opengl;

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL3;
import com.jogamp.opengl.GL4;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class Texture {

    public static enum Mode {
        NEAREST(GL.GL_NEAREST),
        LINEAR(GL.GL_LINEAR);

        public final int value;

        Mode(int mode) {
            this.value = mode;
        }
    }

    private int id = -1;

    private int textureSlot = 0;

    private GL4 gl;

    private Mode currentMode = Mode.NEAREST;

    public void create(GL4 gl) {
        this.gl = gl;

        IntBuffer buffer = Buffers.newDirectIntBuffer(1);

        gl.glCreateTextures(GL.GL_TEXTURE_2D, 1, buffer);

        id = buffer.get(0);

        setMode(currentMode);
    }

    public void setData(int width, int height, ByteBuffer imageData) {

        bind();
        gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, width, height, 0, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, imageData);
    }

    public void setMode(Mode mode) {
        bind();

        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL4.GL_TEXTURE_MAG_FILTER, mode.value);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL4.GL_TEXTURE_MIN_FILTER, mode.value);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL3.GL_TEXTURE_WRAP_S, GL3.GL_CLAMP_TO_EDGE);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL3.GL_TEXTURE_WRAP_T, GL3.GL_CLAMP_TO_EDGE);

        currentMode = mode;

        unbind();
    }

    public void bind() {
        gl.glActiveTexture(GL4.GL_TEXTURE0 + textureSlot);
        gl.glBindTexture(GL.GL_TEXTURE_2D, id);
    }

    public void unbind() {
        gl.glBindTexture(GL.GL_TEXTURE_2D, 0);

    }

    public int getID() {
        return id;
    }

    public void setTextureSlot(int slot) {
        textureSlot = slot;
    }

    public int getTextureSlot() {
        return textureSlot;
    }
}
