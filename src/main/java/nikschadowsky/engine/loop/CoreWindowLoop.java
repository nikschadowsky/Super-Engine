package nikschadowsky.engine.loop;

import nikschadowsky.engine.loop.observer.ApplicationLoopObserver;
import nikschadowsky.engine.loop.observer.LoopStateEvent;
import nikschadowsky.engine.renderer.RendererAPIVariant;
import nikschadowsky.engine.renderer.RenderingContextFactory;
import nikschadowsky.engine.statemanager.StateManager;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * File created on 18.08.2023
 */
public class CoreWindowLoop implements ApplicationLoop {

    private final int tps;
    private final int fps;
    private final StateManager stateMgr;

    private final List<ApplicationLoopObserver> observers;

    private boolean isRunning, isPaused;

    private final Thread coreThread;

    public CoreWindowLoop(int tps, int fps, StateManager stateManager, Container contentPane, RendererAPIVariant variant) {
        this.tps = tps;
        this.fps = fps;
        this.stateMgr = stateManager;

        observers = new ArrayList<>();

        contentPane.add(RenderingContextFactory.getRenderingContext(variant, stateMgr), BorderLayout.CENTER);

        coreThread = new Thread(this::runTask, "CoreThread");
    }

    private double nsPerUpdate, nsPerRender;

    private void runTask() {

        System.err.println("STARTED");

        setNsPerUpdate(tps);
        setNsPerRender(fps);

        long lastTime = System.nanoTime();
        double unprocessedTicks = 0, unprocessedFrames = 0;

        long timer = System.currentTimeMillis();

        while (isRunning) {

            final long now = System.nanoTime();
            unprocessedTicks += (now - lastTime) / nsPerUpdate;
            unprocessedFrames += (now - lastTime) / nsPerRender;


            lastTime = now;

            if (unprocessedTicks >= 1) {
                System.out.println("Update");
                unprocessedTicks--;
            }
            if (unprocessedFrames >= 1) {


                unprocessedFrames--;
            }


            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
            }

        }

    }

    public void setNsPerUpdate(int tps) {
        nsPerUpdate = 1000_000_000.0 / tps;
    }

    public void setNsPerRender(int fps) {
        nsPerRender = 1000_000_000.0 / fps;
    }


    @Override
    public void start() {
        if (!isRunning) {
            fireStateChangeEvent(LoopStateEvent.State.STARTED);

            isRunning = true;
            coreThread.start();
        }
    }

    @Override
    public void pause() {
        fireStateChangeEvent(LoopStateEvent.State.PAUSED);
    }

    @Override
    public void resume() {
        fireStateChangeEvent(LoopStateEvent.State.RESUMED);
    }

    @Override
    public void stop() {
        if (isRunning) {
            isRunning = false;

            fireStateChangeEvent(LoopStateEvent.State.STOPPED);
        }
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public void addObserver(ApplicationLoopObserver observer) {
        observers.add(observer);
    }

    private LoopStateEvent.State lastState;

    public void fireStateChangeEvent(@NotNull LoopStateEvent.State state) {
        if (!state.equals(lastState))
            for (ApplicationLoopObserver observer : observers) {
                observer.loopStateUpdate(new LoopStateEvent(this, state));
            }

        lastState = state;
    }
}
