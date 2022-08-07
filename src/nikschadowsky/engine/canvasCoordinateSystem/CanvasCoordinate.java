package nikschadowsky.engine.canvasCoordinateSystem;

import java.awt.geom.Point2D;

public class CanvasCoordinate {

    private float x;


    private float y;

    public CanvasCoordinate(float x, float y) {

        this.x = x;
        this.y = y;

    }

    public float getX() {
        return x;
    }


    public float getY() {
        return y;
    }

    /**
     * Overwrites the old coordinate and sets its coordinates to the new location
     *
     * @param newX as new Coordinate X
     * @param newY as new Coordinate Y
     */
    public void translatePoint(float newX, float newY) {
        this.x = newX;
        this.y = newY;

    }

    public Point2D getPoint() {
        return new Point2D.Float(x, y);
    }
}
