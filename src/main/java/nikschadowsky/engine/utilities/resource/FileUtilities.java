package nikschadowsky.engine.utilities.resource;

public class FileUtilities {

    public static String getFileExtension(String path) {

        String[] bits = path.split("\\.");

        return bits[bits.length - 1];
    }
}
