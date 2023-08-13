package nikschadowsky.engine.opengl;

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL4;

import java.nio.Buffer;
import java.nio.IntBuffer;

public abstract class BufferObject {

	protected int id = -1;

	protected GL4 gl;

	public void create(GL4 gl) {
		this.gl = gl;

		IntBuffer buffer = Buffers.newDirectIntBuffer(1);

		gl.glCreateBuffers(1, buffer);

		id = buffer.get(0);
	}

	public void setData(Buffer data, int size) {
		gl.glNamedBufferData(id, data.capacity() * size, data, GL.GL_DYNAMIC_DRAW);
	}

	public abstract void bind(nikschadowsky.engine.opengl.VertexArrayObject vao);

	public static class VertexBufferObject extends BufferObject {

		private int stride = 4;

		@Override
		public void bind(nikschadowsky.engine.opengl.VertexArrayObject vao) {

			gl.glVertexArrayVertexBuffer(vao.getID(), 0, id, 0, stride * Float.BYTES);

		}

		public int getStride() {
			return stride;
		}

		public void setStride(int stride) {
			this.stride = stride;
		}
	}

	public static class ElementBufferObject extends BufferObject {

		@Override
		public void bind(nikschadowsky.engine.opengl.VertexArrayObject vao) {

			gl.glVertexArrayElementBuffer(vao.getID(), id);

		}
	}

	public int getID() {
		return id;
	}

}
