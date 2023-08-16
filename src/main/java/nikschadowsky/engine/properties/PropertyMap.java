package nikschadowsky.engine.properties;

import nikschadowsky.engine.configuration.ConfigurationLoader;
import nikschadowsky.engine.configuration.ConfigurationMap;

import java.util.Map;

/**
 * File created on 15.08.2023
 */
public class PropertyMap implements ConfigurationMap {

    private final Map<String, String> props;

    public PropertyMap(String path){
        props = ConfigurationLoader.readProperties(path);
    }

    /**
     * Get a Value from Properties with a given Key
     * @param key Name of Property
     * @return associated value, or empty string if key not in properties
     */
    @Override
    public String getOrDefault(String key) {
        return props.getOrDefault(key, "");
    }

    @Override
    public Map<String, String> getMap() {
        return props;
    }
}
