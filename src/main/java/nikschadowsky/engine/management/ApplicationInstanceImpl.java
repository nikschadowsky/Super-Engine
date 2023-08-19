package nikschadowsky.engine.management;

/**
 * File created on 13.08.2023
 */
public non-sealed abstract class ApplicationInstanceImpl implements ApplicationInstance{


    private boolean initialized;

    @Override
    public final void start() {
        // register this bad boi
        ThreadManager.getInstance().registerInstance(this);

        init();
        initialized = true;

        // start this bad boi
        getThread().start();
    }

    /**
     * Will be executed, before the Thread is started.
     */
    public abstract void init();

    protected void removeFromActiveApplicationInstances(){
        ThreadManager.getInstance().removeInstance(this);
    }

    protected boolean isInitialized() {
        return initialized;
    }
}
