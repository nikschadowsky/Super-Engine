package nikschadowsky.engine.window.configuration;

import nikschadowsky.engine.configuration.ConfigurationLoader;
import nikschadowsky.engine.configuration.ConfigurationMap;

import java.util.Map;

/**
 * File created on 13.08.2023
 */
public class SuperWindowConfigurationMap implements ConfigurationMap {

    private final Map<String, String> properties;

    public SuperWindowConfigurationMap(String path) {
        properties = ConfigurationLoader.readProperties(path);
    }

    public SuperWindowConfigurationMap(Map<String, String> propertyMap) {
        properties = propertyMap;
    }

    @Override
    public String getOrDefault(String key) {
        return properties.getOrDefault(key, SuperWindowConfigurationDefaults.getDefaultValue(key));
    }

    @Override
    public Map<String, String> getMap() {
        return properties;
    }
}
