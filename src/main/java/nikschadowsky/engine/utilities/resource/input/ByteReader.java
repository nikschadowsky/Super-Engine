package nikschadowsky.engine.utilities.resource.input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * File created on 12.08.2023
 */
public class ByteReader {

    /**
     * Reads file content from provided Path, returning the data as a byte array.
     * If there is no file at the provided path, a 0-length empty byte array is returned!
     *
     * @param path Path to the File to be read
     * @return a byte array containing data, or 0-length empty byte array on IOException
     */
    public static byte[] readAllBytesFromFile(String path) {

        try {
            return Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            System.err.printf("Specified file '%s' does not exist!%n", path);
            return new byte[0];
        }
    }
}
