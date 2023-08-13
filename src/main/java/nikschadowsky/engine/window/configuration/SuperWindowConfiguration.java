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

    private final ConfigurationMap map;

    private String title;
    private Dimension size;

    // private Color background; what is color even?
    private int tps;
    private int fps;

    public SuperWindowConfiguration(String path) {
        this(new SuperWindowConfigurationMap(path));
    }

    private SuperWindowConfiguration(SuperWindowConfigurationMap map) {
        this.map = map;

        initFields(map);
    }


    private void initFields(SuperWindowConfigurationMap map) {
        try {
            title = map.getOrDefault("title");

            size = new Dimension(Integer.parseInt(map.getOrDefault("width")), Integer.parseInt(map.getOrDefault("height")));
            tps = Integer.parseInt(map.getOrDefault("tps"));
            fps = Integer.parseInt(map.getOrDefault("fps"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static class SuperWindowConfigurationBuilder {

        private final Map<String, String> properties;

        public SuperWindowConfigurationBuilder() {
            properties = new HashMap<>();
        }

        public SuperWindowConfigurationBuilder addTitle(String title) {
            properties.put("title", title);
            return this;
        }

        public SuperWindowConfigurationBuilder addWidthAndHeight(int width, int height) {
            properties.put("width", String.valueOf(width));
            properties.put("height", String.valueOf(height));
            return this;
        }

        public SuperWindowConfigurationBuilder addTPS(int tps) {
            properties.put("tps", String.valueOf(tps));
            return this;
        }

        public SuperWindowConfigurationBuilder addFPS(int fps) {
            properties.put("fps", String.valueOf(fps));
            return this;
        }

        public SuperWindowConfigurationBuilder addBackgroundColor(Color c) {
            throw new NotImplementedException(); // TODO implement this bad boi
        }

        public SuperWindowConfiguration build() {
            return new SuperWindowConfiguration(new SuperWindowConfigurationMap(properties));
        }
    }
}
