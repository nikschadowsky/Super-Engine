package nikschadowsky.engine.configuration;

/**
 * File created on 12.08.2023
 */
public class InvalidConfigurationSyntaxException extends RuntimeException{
    public InvalidConfigurationSyntaxException(String path){
        super("Invalid Syntax in Config-File " + path + "!");
    }
}
