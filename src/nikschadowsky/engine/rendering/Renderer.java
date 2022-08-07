package nikschadowsky.engine.rendering;

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL;
import nikschadowsky.engine.canvasCoordinateSystem.CanvasCoordinate;
import nikschadowsky.engine.opengl.BufferObject.ElementBufferObject;
import nikschadowsky.engine.opengl.BufferObject.VertexBufferObject;
import nikschadowsky.engine.opengl.Texture;
import nikschadowsky.engine.opengl.VertexArrayObject;
import nikschadowsky.engine.resources.Resource;
import nikschadowsky.engine.textureatlas.TextureAtlas;
import nikschadowsky.engine.utilities.array.ArrayUtilities;
import nikschadowsky.engine.utilities.resource.ImageUtilities;

import java.awt.*;
import java.util.ArrayList;


public class Renderer {
    /*
     * Layer besitzen FBO, VBO für gesammelte Vertices, und EBO für deren Zeichnung
     *
     * Insgesamt 2 Drawcalls: 1. alle Objekte zu FBO, 2. FBO mit
     * PostProcessingShader PPS zu state-FBO
     *
     * Universalshader zum drawn, TextureAtlas und blank Triangles und Quads samplen
     * weißes Bild
     *
     *
     *
     */

    private ArrayList<Integer> eboData;
    private ArrayList<Float> vboData; // consists of 2 floats vertexPos and 2 floats vertexUV and 4 floats vertexColor per Vertex

    private int vertexCount = 0;

    private boolean isCanvasRatioConstant = false;
    private float canvasRatio = 1f;

    /**
     * pixels on Canvas -> usable as regular coordinates
     */
    private CanvasCoordinate canvasDimension;

    private float translateX = 0f, translateY = 0f;

    private VertexArrayObject vao;
    private VertexBufferObject vbo;
    private ElementBufferObject ebo;

    private Texture textureAtlas;

    private Color color = Color.white;

    /**
     * window resizing affects canvas size only height-wise
     *
     * @param canvasSizeY, number of grid-like pixels in vertical direction
     */

    public Renderer(int canvasSizeY) {

        canvasDimension = new CanvasCoordinate(0, canvasSizeY);

        init();

    }

    /**
     * window resizing affects canvas size in all dimensions, the canvas will be scaled
     *
     * @param canvasSizeX number of grid-like pixels in vertical direction
     * @param canvasSizeY number of grid-like pixels in horizontal direction
     */
    public Renderer(int canvasSizeX, int canvasSizeY) {

        canvasDimension = new CanvasCoordinate(canvasSizeX, canvasSizeY);

        init();

        isCanvasRatioConstant = true;

        // define constant Ratio
        canvasRatio = canvasDimension.getX() / canvasDimension.getY();
    }

    private void init() {
        // vboData, eboData erstellen

        eboData = new ArrayList<>();
        vboData = new ArrayList<>();

    }

    public void initRendering(RenderingInformation renderingInfo) {

        // vao erzeugen
        (vao = new VertexArrayObject()).create(renderingInfo.GL());
        vao.setAttribute(3, 0, 0); // position
        vao.setAttribute(2, 1, 3); // uv
        vao.setAttribute(4, 2, 5); // color


        // vbo erzeugen
        (vbo = new VertexBufferObject()).create(renderingInfo.GL());
        vbo.setStride(9);


        // ebo erzeugen
        (ebo = new ElementBufferObject()).create(renderingInfo.GL());

        (textureAtlas = new Texture()).create(renderingInfo.GL());
        // pps

    }

    // TODO refactoring
    float initialCanvasW;
    boolean isInit = false;

    public void updateCanvasSize(int width, int height) {


        if (!isCanvasRatioConstant) {
            canvasRatio = (float) width / (float) height;

            // type = floats?
            canvasDimension.translatePoint(canvasDimension.getY() * canvasRatio, canvasDimension.getY());

            // TODO remove this ugly a$$ if statement for some cool math

            if (!isInit) {
                initialCanvasW = canvasDimension.getX();
                isInit = true;
            }

            translateX = (canvasDimension.getX() - initialCanvasW) / 2f;

        }


    }


    public void flush(RenderingInformation renderingInfo) {

        // upload data to gpu (ebo, joined vbo)


        vbo.setData(Buffers.newDirectFloatBuffer(ArrayUtilities.toFloatArray(vboData)), Float.BYTES);
        ebo.setData(Buffers.newDirectIntBuffer(ArrayUtilities.toIntArray(eboData)), Integer.BYTES);

        vao.bind();
        vbo.bind(vao);
        ebo.bind(vao);

        textureAtlas.setData(TextureAtlas.maxWidth, TextureAtlas.maxHeight, ImageUtilities.getImageData(TextureAtlas.getFinalImage()));
        textureAtlas.bind();


        // issue one drawElements-Call

        renderingInfo.getDefaultShaderProgram().use();

        renderingInfo.getDefaultShaderProgram().setVec1i("textureAtlas", textureAtlas.getTextureSlot());

        renderingInfo.GL().glDrawElements(GL.GL_TRIANGLES, eboData.size(), GL.GL_UNSIGNED_INT, 0);


        vboData.clear();
        eboData.clear();

        vertexCount = 0;


    }

    public void drawQuad(int x, int y, int width, int height) {

        eboData.add(vertexCount + 0); // 0
        eboData.add(vertexCount + 1); // 1
        eboData.add(vertexCount + 2); // 2

        eboData.add(vertexCount + 0); // 0
        eboData.add(vertexCount + 2); // 2
        eboData.add(vertexCount + 3); // 3

        addVertex(x, y, 1f, 1f);
        addVertex(x + width, y, 1f, 1f);
        addVertex(x + width, y + height, 1f, 1);
        addVertex(x, y + height, 1f, 1f);

        vertexCount += 4;
    }

    public void drawTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {

        eboData.add(vertexCount + 0); // 0
        eboData.add(vertexCount + 1); // 1
        eboData.add(vertexCount + 2); // 2

        addVertex(x1, y1, 1f, 1f);
        addVertex(x2, y2, 1f, 1f);
        addVertex(x3, y3, 1f, 1f);

        vertexCount += 3;
    }

    public void drawImage(Resource res, int x, int y, int width, int height) {


        if (!(res instanceof Resource.Image)) {
            System.err.println("\'" + res.getPath() + "\' is not recognised as an image resource! Check file extension!");
            return;
        }

        Color old = new Color(color.getRGB());

        color = Color.white;

        Resource.Image image = (Resource.Image) res;

        eboData.add(vertexCount + 0); // 0
        eboData.add(vertexCount + 1); // 1
        eboData.add(vertexCount + 2); // 2

        eboData.add(vertexCount + 0); // 0
        eboData.add(vertexCount + 2); // 2
        eboData.add(vertexCount + 3); // 3

        addVertex(x, y, image.getX1(), image.getY2());
        addVertex(x + width, y, image.getX2(), image.getY2());
        addVertex(x + width, y + height, image.getX2(), image.getY1());
        addVertex(x, y + height, image.getX1(), image.getY1());

        System.out.println(image.getX1() + " " + image.getX2() + " " + image.getY1() + " " + image.getY2());

        vertexCount += 4;

        color = old;
    }

    private void addVertex(int x, int y, float uvX, float uvY) {

        vboData.add(2f * (x + translateX) / canvasDimension.getX() - 1f);
        vboData.add(2f * y / canvasDimension.getY() - 1f);
        vboData.add(0f);


        vboData.add(uvX);
        vboData.add(uvY);

        // color
        vboData.add(color.getRed() / 255f);
        vboData.add(color.getGreen() / 255f);
        vboData.add(color.getBlue() / 255f);
        vboData.add(color.getAlpha() / 255f);

    }

    /**
     * Translate virtual-coordinate-space by given parameters.
     *
     * @param x in virtualPixel-Coordinates from the origin 0;0
     * @param y in virtualPixel-Coordinates from the origin 0;0
     */
    public void translate(float x, float y) {

        translateX = x;
        translateY = y;

    }

    /**
     * @return the screenRatio
     */
    public float getCanvasRatio() {
        return canvasRatio;
    }

    public Color getVertexColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return the eboData
     */
    public ArrayList<Integer> getEboData() {
        return eboData;
    }

    /**
     * @return the vboData
     */
    public ArrayList<Float> getVboData() {
        return vboData;
    }
}
