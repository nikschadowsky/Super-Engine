package nikschadowsky.engine.configuration;

/**
 * File created on 13.08.2023
 */
public class NoSuchConfigurationValue extends RuntimeException{

    public NoSuchConfigurationValue(String key){
        super("There is no value associated with key '" + key + "'!");
    }
}
