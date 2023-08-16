package nikschadowsky.engine.management;

import nikschadowsky.engine.configuration.ConfigurationMap;
import nikschadowsky.engine.properties.PropertyMap;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * File created on 12.08.2023
 */
public class ThreadManager {

    private static ThreadManager SINGLETON = null;

    /**
     * Create the Singleton ThreadManager Instance with a specified path to the global config file!
     *
     * @param globalConfigPath path to global config file
     */
    public static void createThreadManager(@NotNull String globalConfigPath, @NotNull String[] startArguments) {

        if (SINGLETON == null) {
            SINGLETON = new ThreadManager(globalConfigPath, startArguments);
        } else {
            System.err.println(ThreadManager.class.getName() + " can only be created once! Use ThreadManager.getInstance() instead!");
        }
    }

    /**
     * Get the Singleton Instance of ThreadManager
     *
     * @return Singleton Instance
     */
    @NotNull(value = "ThreadManager was not created yet!", exception = ThreadManagerInstantiationException.class)
    public static ThreadManager getInstance() {
        return SINGLETON;
    }

    //-----------------------------------------------------------------------------//


    private final String globalConfigPath;

    private final ConfigurationMap globalProperties;

    private final Set<ApplicationInstance> activeInstances;

    private final List<String> startArguments;

    private ThreadManager(String globalConfigPath, String[] startArguments) {
        this.globalConfigPath = globalConfigPath;
        this.globalProperties = new PropertyMap(globalConfigPath);
        this.startArguments = List.of(startArguments);

        activeInstances = new HashSet<>();

    }

    void registerInstance(ApplicationInstance instance) {
        activeInstances.add(instance);
    }

    public Map<String, String> getGlobalProperties() {
        return globalProperties.getMap();
    }

    public List<String> getStartArguments() {
        return startArguments;
    }

}
