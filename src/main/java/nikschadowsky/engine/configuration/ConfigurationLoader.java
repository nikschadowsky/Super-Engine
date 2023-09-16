package nikschadowsky.engine.configuration;

import nikschadowsky.engine.utilities.resource.input.ByteReader;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * File created on 12.08.2023
 */
public class ConfigurationLoader {

    /**
     * Reads in a file from a provided path and creates a Map representing the properties in that file.
     * A file might look like this:
     * {@snippet :
     *     property_key1=property_value
     *     property_key2=property_value
     *     property_key3=property_value
     *}
     * As specified in {@link Map}, no duplicates are allowed, and they will override eachother in the process.
     *
     * @param path Path of File
     * @return Map of Properties
     */
    public static Map<String, String> readProperties(String path) {

        Map<String, String> propMap = new HashMap<>();

        byte[] data = ByteReader.readAllBytesFromFile(path);

        String rawData = new String(data);


        for (String line : removeCommentsAndEmptyLines(rawData).lines().toList()) {
            String[] split = line.split("=");

            if (split.length != 2) {
                throw new InvalidConfigurationSyntaxException(path);
            }
            propMap.put(split[0], split[1]);
        }

        return Collections.unmodifiableMap(propMap);
    }

    private static String removeCommentsAndEmptyLines(String data) {
        data = data.replaceAll("#.*\\v", "");
        data = data.replaceAll("\\v+", System.lineSeparator());

        return data;
    }

}
