package nikschadowsky.engine.opengl;

import com.jogamp.opengl.GL4;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public abstract class Shader {

    protected int id;

    protected GL4 gl;

    public abstract void create(GL4 gl);

    public void setSourceCode(String code) {

        gl.glShaderSource(id, 1, new String[]{code}, null);

        gl.glCompileShader(id);
    }

    public int getID() {
        return id;
    }

    public static class ShaderProgram {

        private int id;

        private GL4 gl;

        private boolean succeeded = false;

        public void create(GL4 gl) {

            this.gl = gl;

            id = gl.glCreateProgram();

        }

        public void attach(Shader s) {

            gl.glAttachShader(id, s.getID());

        }

        public void use() {

            if (succeeded)
                gl.glUseProgram(id);

        }

        public void link() {

            gl.glLinkProgram(id);

            IntBuffer intBuffer = IntBuffer.allocate(1);
            gl.glGetProgramiv(id, GL4.GL_LINK_STATUS, intBuffer);

            if (intBuffer.get(0) != 1) {
                gl.glGetProgramiv(id, GL4.GL_INFO_LOG_LENGTH, intBuffer);
                int size = intBuffer.get(0);

                System.err.println("Shader linking failed!");

                succeeded = false;

                if (size > 0) {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(size);
                    gl.glGetProgramInfoLog(id, size, intBuffer, byteBuffer);
                    System.err.println(new String(byteBuffer.array()));
                }

                return;

            }

            gl.glValidateProgram(id);
            succeeded = true;
        }

        /**
         * @return the id
         */
        public int getID() {
            return id;
        }

        /**
         * @return the succeeded
         */
        public boolean isSucceeded() {
            return succeeded;
        }

        public void setVec1f(String name, float v0) {
            gl.glUniform1f(gl.glGetUniformLocation(getID(), name), v0);
        }

        public void setVec1i(String name, int i0) {
            gl.glUniform1i(gl.glGetUniformLocation(getID(), name), i0);
        }

        public void setVec2f(String name, float v0, float v1) {
            gl.glUniform2f(gl.glGetUniformLocation(getID(), name), v0, v1);
        }

        public void setVec2i(String name, int i0, int i1) {
            gl.glUniform2i(gl.glGetUniformLocation(getID(), name), i0, i1);
        }

        public void setVec3f(String name, float v0, float v1, float v2) {
            gl.glUniform3f(gl.glGetUniformLocation(getID(), name), v0, v1, v2);
        }

        public void setVec3i(String name, int i0, int i1, int i2) {
            gl.glUniform3i(gl.glGetUniformLocation(getID(), name), i0, i1, i2);
        }

        public void setVec4f(String name, float v0, float v1, float v2, float v3) {
            gl.glUniform4f(gl.glGetUniformLocation(getID(), name), v0, v1, v2, v3);
        }

        public void setVec4i(String name, int i0, int i1, int i2, int i3) {
            gl.glUniform4i(gl.glGetUniformLocation(getID(), name), i0, i1, i2, i3);
        }
    }

    public static class VertexShader extends Shader {

        @Override
        public void create(GL4 gl) {
            this.gl = gl;

            id = gl.glCreateShader(GL4.GL_VERTEX_SHADER);
        }

    }

    public static class FragmentShader extends Shader {

        @Override
        public void create(GL4 gl) {
            this.gl = gl;

            id = gl.glCreateShader(GL4.GL_FRAGMENT_SHADER);

        }

    }

    public static class ComputeShader extends Shader {

        @Override
        public void create(GL4 gl) {
            this.gl = gl;

            id = gl.glCreateShader(GL4.GL_COMPUTE_SHADER);
        }

    }

    public static class GeometryShader extends Shader {

        @Override
        public void create(GL4 gl) {
            this.gl = gl;

            id = gl.glCreateShader(GL4.GL_GEOMETRY_SHADER);

        }

    }

}
