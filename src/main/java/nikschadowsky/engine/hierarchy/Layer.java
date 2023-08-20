package nikschadowsky.engine.hierarchy;

import nikschadowsky.engine.rendering_old.Renderer;
import nikschadowsky.engine.rendering_old.RenderingInformation;

public abstract class Layer implements SortableByZ {

    private Renderer renderer;

    public Layer(int virtualPixelsY) {

        renderer = new Renderer(virtualPixelsY);

    }

    public Layer(int virtualPixelsX, int virtualPixelsY) {
        renderer = new Renderer(virtualPixelsX, virtualPixelsY);
    }

    /**
     * This method will be run on every update cycle, when the {@link nikschadowsky.engine.gameloop.GameLoop} is not paused!
     * Inputs via {@link nikschadowsky.engine.input.Input} will be also be triggered when the window does not have focus!
     * <br>If you do not want this behaviour use the designated {@link #input()} method!
     *
     * @see #input()
     */
    public abstract void update();

    public void initRendering(RenderingInformation renderingInfo) {
        renderer.initRendering(renderingInfo);
    }

    public void render(RenderingInformation renderingInfo) {
        // renderer als parameter?

        draw(renderer);

        renderer.flush(renderingInfo);
    }

    public void reshape(int width, int height) {
        renderer.updateCanvasSize(width, height);
    }

    public abstract void draw(Renderer r);


    /**
     * This method will be run on every update cycle, but only when the window has focus!
     * It is therefore optimal for input handling via {@link nikschadowsky.engine.input.Input}.
     * <br>If you do not want this behaviour use the {@link #update()} method!
     *
     * @see #update()
     */
    public abstract void input();
}
