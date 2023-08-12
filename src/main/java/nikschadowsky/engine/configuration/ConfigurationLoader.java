package nikschadowsky.engine.configuration;

import nikschadowsky.engine.resources.ByteReader;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

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
     * }
     * As specified in {@link Map}, no duplicates are allowed and they will override eachother in the process.
     *
     * @param path Path of File
     * @return Map of Properties
     */
    public static Map<String, String> readProperties(String path) {

        Map<String, String> propMap;

        byte[] data = ByteReader.readAllBytesFromFile(path);

        String rawData = new String(data);

        try {
            propMap = rawData.lines().map(line -> line.split("="))
                    .collect(Collectors.toMap(split -> split[0], split -> split[1], (a, b) -> b));

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidConfigurationSyntaxException(path);
        }
        return Collections.unmodifiableMap(propMap);
    }

}
