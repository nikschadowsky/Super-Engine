package nikschadowsky.engine.window.configuration;

import nikschadowsky.engine.development.NotImplementedException;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

/**
 * File created on 13.08.2023
 */
public class SuperWindowConfigurationTest {

    @Test
    void testDefaultConfiguration(){
        SuperWindowConfiguration config = SuperWindowConfiguration.DEFAULT_CONFIGURATION;

        assertEquals("Default Title",config.getTitle());
        assertEquals(600 ,config.getSize().getWidth());
        assertEquals(400,config.getSize().getHeight());
        assertEquals(60,config.getFramesPerSecond());
        assertEquals(20,config.getTicksPerSecond());

    }

    @Test
    void testBuilder(){
        SuperWindowConfiguration.SuperWindowConfigurationBuilder builder = new SuperWindowConfiguration.SuperWindowConfigurationBuilder();

        builder.addFPS(100).addTPS(200).addTitle("Test Title").addWidthAndHeight(500,500);

        SuperWindowConfiguration config = builder.build();

        assertEquals("Test Title",config.getTitle());
        assertEquals(500 ,config.getSize().getWidth());
        assertEquals(500,config.getSize().getHeight());
        assertEquals(100,config.getFramesPerSecond());
        assertEquals(200,config.getTicksPerSecond());

        assertThrowsExactly(NotImplementedException.class, () -> builder.addBackgroundColor(Color.white));
    }

    @Test
    void testConfigurationFromFile(){
        SuperWindowConfiguration config = new SuperWindowConfiguration("src/test/resources/testSuperConfigurationFromFile.test");

        assertEquals("GRRRR",config.getTitle());
        assertEquals(1 ,config.getSize().getWidth());
        assertEquals(2,config.getSize().getHeight());
        assertEquals(300,config.getFramesPerSecond());
        assertEquals(200,config.getTicksPerSecond());

    }
}
