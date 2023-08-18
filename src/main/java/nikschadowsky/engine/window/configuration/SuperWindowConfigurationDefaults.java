package nikschadowsky.engine.window.configuration;

import nikschadowsky.engine.configuration.NoSuchConfigurationValue;

import java.util.HashMap;
import java.util.Map;

/**
 * File created on 13.08.2023
 */
public class SuperWindowConfigurationDefaults {

    private final static Map<String, String> DEFAULT_CONFIG;


    static {
        DEFAULT_CONFIG = new HashMap<>();

        DEFAULT_CONFIG.put("title", "Default Title");
        DEFAULT_CONFIG.put("width", "600");
        DEFAULT_CONFIG.put("height", "400");
        DEFAULT_CONFIG.put("background", "#000000");
        DEFAULT_CONFIG.put("tps", "20");
        DEFAULT_CONFIG.put("fps", "60");
        DEFAULT_CONFIG.put("resizable", "true");
        DEFAULT_CONFIG.put("min-width", "200");
        DEFAULT_CONFIG.put("min-height", "200");
        DEFAULT_CONFIG.put("max-width", "0");
        DEFAULT_CONFIG.put("max-height", "0");

    }

    public static String getDefaultValue(String key) {

        DEFAULT_CONFIG.computeIfAbsent(key, k -> {throw new NoSuchConfigurationValue(k);});

        return DEFAULT_CONFIG.getOrDefault(key, "");
    }
}
