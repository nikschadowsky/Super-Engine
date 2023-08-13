package nikschadowsky.engine.development;

/**
 * File created on 13.08.2023
 */
public class NotImplementedException extends UnsupportedOperationException{
    public NotImplementedException(String reason){
        super("Sorry, but this method was not implemented yet! Reasoning: " + reason);
    }
}
