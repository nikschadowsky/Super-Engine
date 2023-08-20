package nikschadowsky.engine.gameloop;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import nikschadowsky.engine.input.Input;
import nikschadowsky.engine.statemanager.StateManager;

import java.awt.event.*;

@Deprecated
public class GameLoop extends GLJPanel implements Runnable {


    private boolean isRunning = false; // volatile?
    private boolean isPaused = false;

    private final int ups;
    private int fps, currentFPS;

    private double nsPerUpdate, nsPerRepaint;

    private Thread gameThread;

    private final StateManager statemgr;

    public GameLoop(int ups, int fps, StateManager statemgr) {
        super(new GLCapabilities(GLProfile.get(GLProfile.GL4)));


        this.ups = ups;
        this.fps = fps;
        this.statemgr = statemgr;

        this.addGLEventListener(statemgr);
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                Input.press(e.getKeyCode());

            }

            @Override
            public void keyReleased(KeyEvent e) {
                Input.release(e.getKeyCode());
            }
        });
        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                Input.press(Input.MOUSE_OFFSET + e.getButton());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Input.press(Input.MOUSE_OFFSET + e.getButton());
            }

        });
        this.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                Input.setMWClicks(e.getWheelRotation());
            }
        });
    }

    public void start(int instanceID) {
        isRunning = true;
        gameThread = new Thread(null, this, Integer.toString(instanceID));
        gameThread.start();

    }

    public void pause() {

        isPaused = true;
        System.out.println("Pausing Thread " + gameThread.getName() + "...");

    }

    public void resume() {
        isPaused = false;
        System.out.println("Resuming Thread " + gameThread.getName() + "...");
    }

    public void stop() {

        isRunning = false;
        System.out.println("Stopping Thread " + gameThread.getName() + "...");

    }

    @Override
    public void run() {
        nsPerUpdate = 1000000000.0 / ups;
        nsPerRepaint = 1000000000.0 / fps;

        int fps = 0;
        long lastTime = System.nanoTime();
        double unprocessed = 0, unprocessedFrames = 0;

        long timer = System.currentTimeMillis();

        while (isRunning) {

            final long now = System.nanoTime();
            unprocessed += (now - lastTime) / nsPerUpdate;
            unprocessedFrames += (now - lastTime) / nsPerRepaint;

            lastTime = now;
            if (unprocessed >= 1) {

                if (this.hasFocus()) {
                    statemgr.input();
                    Input.update();
                }
                if (!isPaused) {

                    // update
                    statemgr.update();

                    unprocessed -= 1;
                }
            }
            if (unprocessedFrames >= 1) {
                fps++;

                // repaint
                repaint();

                unprocessedFrames -= 1;
            }


            try {

                Thread.sleep(3);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }

            if (System.currentTimeMillis() - timer > 1000) {

                currentFPS = fps;

                fps = 0;
                timer += 1000;

            }
        }

    }

    /**
     * @return the isRunning
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * @return the isPaused
     */
    public boolean isPaused() {
        return isPaused;
    }

    /**
     * @return the tps
     */
    public int getUPS() {
        return ups;
    }

    /**
     * @return the maximum fps
     */
    public int getMaxFPS() {
        return fps;
    }

    public void setMaxFPS(int maxFPS) {
        fps = maxFPS;
    }

    /**
     * @return the current FPS
     */
    public int getFPS() {
        return currentFPS;
    }

}
