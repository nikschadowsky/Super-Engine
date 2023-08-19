package nikschadowsky.engine.loop;

import nikschadowsky.engine.loop.observer.ApplicationLoopObserver;
import nikschadowsky.engine.renderer.RendererVariant;
import nikschadowsky.engine.renderer.RenderingContextFactory;
import nikschadowsky.engine.statemanager.StateManager;

import java.awt.*;

/**
 * File created on 18.08.2023
 */
public class CoreWindowLoop implements ApplicationLoop{

    private final int tps;
    private final int fps;
    private final StateManager stateMgr;

    public CoreWindowLoop(int tps, int fps, StateManager stateMgr, Container contentPane, RendererVariant variant){
        this.tps = tps;
        this.fps = fps;
        this.stateMgr = stateMgr;

        contentPane.add(RenderingContextFactory.getRenderingContext(variant, stateMgr));
    }

    @Override
    public void start() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void addObserver(ApplicationLoopObserver observer) {

    }
}
