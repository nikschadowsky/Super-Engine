package nikschadowsky.engine.configuration;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * File created on 12.08.2023
 */
public class ConfigurationLoaderTest {

    @Test
    void testReadProperties() {

        Map<String, String> actual = ConfigurationLoader.readProperties("src/test/resources/testReadProperties.test");

        Map<String, String> expected = new HashMap<>();
        expected.put("prop1", "value1");
        expected.put("prop2", "value2");
        expected.put("prop3", "value1");
        expected.put("prop4", "value4");
        expected.put("prop5", "value3"); // intended repetition of value3

        System.out.println("%%% Actual Data %%%");
        actual.forEach((k,v)-> System.out.printf("[%s: %s]%n",k,v));
        System.out.println("%%%%%%%%%%%%%%%%%%%");

        assertEquals(expected.size(), actual.size());
        expected.forEach((k, v) -> assertTrue(actual.containsKey(k) && actual.get(k).equals(v)));

    }
}
