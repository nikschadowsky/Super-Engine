package nikschadowsky.engine.window;

public class WindowConfiguration {

    private String title = "";
    private int width, height; // window dimensions
    private int x, y; // window position
    private boolean isDecorated = true;
    private boolean isResizable = true;
    private int fps;
    private int ups;

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(String width) {
        this.width = Integer.parseInt(width);
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(String height) {
        this.height = Integer.parseInt(height);
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(String x) {
        this.x = Integer.parseInt(x);
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(String y) {
        this.y = Integer.parseInt(y);
    }

    /**
     * @return the isDecorated
     */
    public boolean isDecorated() {
        return isDecorated;
    }

    /**
     * @param isDecorated the isDecorated to set
     */
    public void setDecorated(String isDecorated) {
        this.isDecorated = Boolean.parseBoolean(isDecorated);
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the isResizable
     */
    public boolean isResizable() {
        return isResizable;
    }

    /**
     * @param isResizable the isResizable to set
     */
    public void setResizable(String isResizable) {
        this.isResizable = Boolean.parseBoolean(isResizable);
    }

    /**
     * @return the fps
     */
    public int getFPS() {
        return fps;
    }

    /**
     * @param fps the fps to set
     */
    public void setFPS(String fps) {
        this.fps = Integer.parseInt(fps);
    }

    /**
     * @return the ups
     */
    public int getUPS() {
        return ups;
    }

    /**
     * @param ups the ups to set
     */
    public void setUPS(String ups) {
        this.ups = Integer.parseInt(ups);
    }

}
