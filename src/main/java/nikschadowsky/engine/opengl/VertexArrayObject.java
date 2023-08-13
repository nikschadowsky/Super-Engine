package nikschadowsky.engine.opengl;

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL4;

import java.nio.IntBuffer;

public class VertexArrayObject {

	private int id;

	private GL4 gl;

	public void create(GL4 gl) {
		this.gl = gl;

		IntBuffer buffer = Buffers.newDirectIntBuffer(2);

		gl.glCreateVertexArrays(1, buffer);

		this.id = buffer.get(0);

	}

	public void bind() {

		gl.glBindVertexArray(id);

	}

	public void unbind() {
		gl.glBindVertexArray(0);
	}

	/**
	 * @param vecSize
	 * @param attribLocation
	 * @param offset         in number of floats
	 */
	public void setAttribute(int vecSize, int attribLocation, int offset) {

		gl.glVertexArrayAttribFormat(id, attribLocation, vecSize, GL4.GL_FLOAT, false, offset * Float.BYTES);
		gl.glVertexArrayAttribBinding(id, attribLocation, 0);
		gl.glEnableVertexArrayAttrib(id, attribLocation);

	}

	public int getID() {
		return id;
	}

}
