package nikschadowsky.engine.management;

import nikschadowsky.engine.resources.DefaultResourceLoader;
import nikschadowsky.engine.resources.Resource;
import nikschadowsky.engine.statemanager.StateManager;
import nikschadowsky.engine.window.Window;

import java.util.ArrayList;

public class ThreadManager {


    private static ArrayList<Window> windowInstances = new ArrayList<>();


    /**
     * Sollte zum starten von jedem Window-Thread verwendet werden!
     *
     * @param configPath of config file <br>
     * <br>
     * @implNote TODO statemanager angeben!
     */
    private static boolean isInit = false;

    /**
     * ONLY RUN ONCE ON STARTUP
     */
    private static void init() {

        // file manager magic

        if (!isInit) {
            FileManager.setResLoader(new DefaultResourceLoader());
            FileManager.startThread();
        }

        isInit = true;

    }

    public static void start(String configPath, StateManager sm) {
        init();

//        FileManager.loadResource(configPath, false);

        Window w = new Window(((Resource.Configuration) FileManager.getResource(configPath)).getWindowConfiguration(), sm);

        windowInstances.add(w);
        w.setInstanceID(windowInstances.indexOf(w));
        w.start();

        System.gc();
    }

    public static boolean close(int instanceID) {

        boolean toReturn = false;

        if (instanceID < windowInstances.size()) {
            windowInstances.set(instanceID, null);
            toReturn = true;
        }

        for (Window w : windowInstances) {
            if (w != null) {
                return toReturn;
            }
        }
        exit(0);

        return toReturn;

    }

    public static void exit(int exitCode) {
        System.out.println("Exiting!");
        System.exit(exitCode);

    }

}
