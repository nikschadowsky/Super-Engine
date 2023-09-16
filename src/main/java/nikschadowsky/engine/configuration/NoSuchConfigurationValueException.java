package nikschadowsky.engine.configuration;

/**
 * File created on 13.08.2023
 */
public class NoSuchConfigurationValueException extends RuntimeException{

    public NoSuchConfigurationValueException(String key){
        super("There is no value associated with key '" + key + "'!");
    }
}
