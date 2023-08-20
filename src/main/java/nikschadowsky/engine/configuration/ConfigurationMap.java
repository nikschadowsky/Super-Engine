package nikschadowsky.engine.configuration;

import java.util.Map;

/**
 * File created on 13.08.2023
 */
public interface ConfigurationMap {


    String getOrDefault(String key);

    Map<String, String> getMap();
}
