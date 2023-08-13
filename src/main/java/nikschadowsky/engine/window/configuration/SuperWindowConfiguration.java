package nikschadowsky.engine.window.configuration;

import nikschadowsky.engine.configuration.ConfigurationMap;
import nikschadowsky.engine.development.NotImplementedException;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * File created on 12.08.2023
 */
public class SuperWindowConfiguration {

    public static final SuperWindowConfiguration DEFAULT_CONFIGURATION = new SuperWindowConfigurationBuilder().build();
    private final ConfigurationMap map;

    private final String title;

    private final Dimension size;

    // private Color background; what even is color ?
    private final int tps;
    private final int fps;

    private static final String KEY_TITLE = "title";
    private static final String KEY_WIDTH = "width";
    private static final String KEY_HEIGHT = "height";
    private static final String KEY_TPS = "tps";
    private static final String KEY_FPS = "fps";
    private static final String KEY_BACKGROUND = "background";

    /**
     * Create a SuperWindowConfiguration using a File at a specific Path.
     * @param path Path of Configuration File.
     */
    public SuperWindowConfiguration(String path) {
        this(new SuperWindowConfigurationMap(path));
    }

    /**
     * Create a SuperWindowConfiguration using a SuperWindowConfigurationMap.
     * @param map SuperWindowConfigurationMap
     */
    public SuperWindowConfiguration(SuperWindowConfigurationMap map) {
        this.map = map;

        title = map.getOrDefault(KEY_TITLE);

        size = new Dimension(Integer.parseInt(map.getOrDefault(KEY_WIDTH)), Integer.parseInt(map.getOrDefault(KEY_HEIGHT)));
        tps = Integer.parseInt(map.getOrDefault(KEY_TPS));
        fps = Integer.parseInt(map.getOrDefault(KEY_FPS));
    }

    /**
     * @return Window Title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return Window Dimensions
     */
    public Dimension getSize() {
        return new Dimension(size);
    }

    /**
     * @return Window Ticks per Second
     */
    public int getTicksPerSecond() {
        return tps;
    }

    /**
     * @return  Window Frames per Second
     */
    public int getFramesPerSecond() {
        return fps;
    }


    /**
     * Builder class for SuperWindowConfiguration
     * No need to use an external File to define a Configuration.
     */
    public static class SuperWindowConfigurationBuilder {

        private final Map<String, String> properties;

        public SuperWindowConfigurationBuilder() {
            properties = new HashMap<>();
        }

        public SuperWindowConfigurationBuilder addTitle(String title) {
            properties.put(KEY_TITLE, title);
            return this;
        }

        public SuperWindowConfigurationBuilder addWidthAndHeight(int width, int height) {
            properties.put(KEY_WIDTH, String.valueOf(width));
            properties.put(KEY_HEIGHT, String.valueOf(height));
            return this;
        }

        public SuperWindowConfigurationBuilder addTPS(int tps) {
            properties.put(KEY_TPS, String.valueOf(tps));
            return this;
        }

        public SuperWindowConfigurationBuilder addFPS(int fps) {
            properties.put(KEY_FPS, String.valueOf(fps));
            return this;
        }


        public SuperWindowConfigurationBuilder addBackgroundColor(Color c) {
            throw new NotImplementedException("It has not been decided, whether the native Color type will be used or an own implementation"); // TODO implement this bad boi
        }

        /**
         * Build this Configuration
         * @return built configuration
         */
        public SuperWindowConfiguration build() {
            return new SuperWindowConfiguration(new SuperWindowConfigurationMap(properties));
        }
    }
}
