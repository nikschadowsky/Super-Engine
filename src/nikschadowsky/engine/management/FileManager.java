package nikschadowsky.engine.management;

import nikschadowsky.engine.resources.Resource;
import nikschadowsky.engine.resources.ResourceLoader;
import nikschadowsky.engine.textureatlas.TextureAtlas;

import java.util.HashMap;

public class FileManager {

    private static Thread fileManagerThread;
    private static ResourceLoader resLoader;
    public static HashMap<String, Resource> resources;


    protected static void startThread() {

        resources = new HashMap<>();

        fileManagerThread = new Thread(null, () -> {

            while (true) {
                autoDelete();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
                , "FILE_LOADER");


        fileManagerThread.start();

        loadResource("image/MISSING.png", true);
        loadResource("image/test.png", true);
        loadResource("image/test2.png", true);

        TextureAtlas.generateTextureAtlas();
    }

    public static void autoDelete() {

        long currentTime = System.nanoTime();

        for (String entry : resources.keySet()) {

            Resource res = resources.get(entry);

            if (res.isAutoDeletable() && currentTime - res.getLastAccess() > Resource.MAX_INACTIVITY_TIME) {
                resources.remove(entry);
                System.out.println("Resource unloaded");
                TextureAtlas.generateTextureAtlas();
                System.gc();
            }
        }


    }

    /**
     * Loads a resource into memory and grants global access to it by using the path as a key for the @resources hashmap.
     * The path should start with the directories the file is in starting AFTER a source folder.
     * e.g. "image/MISSING.png", neither "/image/MISSING.png" nor "$sourcefoldername/image/MISSING.png"
     *
     * @param path            file path on the classpath
     * @param isAutoDeletable if this resource should get marked to get its accessibility removed after
     */
    public static void loadResource(String path, boolean isAutoDeletable) {

        if (resLoader == null) {
            System.err.println("No Resource-Loader defined! Loading Resource cancelled!");
            return;
        }

        Resource res = resLoader.loadResource(path);

        res.setAutoDeletable(isAutoDeletable);
        res.updateLastAccess();

        resources.put(path, res);

        TextureAtlas.generateTextureAtlas();
    }

    /**
     * Get a Resource loaded in memory, or if not yet in memory, this method will load it into memory and create an entry in @resources
     *
     * @param path
     * @return a resource on the classpath
     * @see #loadResource(String, boolean)
     */
    public static Resource getResource(String path) {

        if (!resources.containsKey(path)) {

            // load resource if not already loaded
            System.out.println("Loading Resource \'" + path + "\', because it was not loaded, when requested!");

            loadResource(path, true);

        }

        return resources.getOrDefault(path, null);


    }

    public static ResourceLoader getResLoader() {
        return resLoader;
    }

    public static void setResLoader(ResourceLoader resLoader) {
        FileManager.resLoader = resLoader;
    }

}
