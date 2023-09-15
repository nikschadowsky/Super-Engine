package nikschadowsky.engine.utilities.resource.output;

import java.io.IOException;

/**
 * File created on 15.09.2023
 */
public interface FileWriter {

    /**
     * Save specified content to the specified filepath
     *
     * @throws IOException if the named file exists but is a directory rather than a regular file, does not exist but
     *                     cannot be created, or cannot be opened for any other reason. <br>
     *                     For more information see
     *                     {@link java.io.FileWriter}
     */
    void save() throws IOException;

    /**
     * @return the specified filepath of the output
     */
    String getPath();
}
