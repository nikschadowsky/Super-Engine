package nikschadowsky.engine.utilities.resource.output;

import org.jetbrains.annotations.NotNull;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * File created on 16.09.2023
 */
public class ByteFileWriter implements FileWriter {


    private final String path;

    private final Queue<byte[]> toBeWritten;

    private final boolean append;

    public ByteFileWriter(@NotNull String path, boolean append) {
        this.path = path;
        this.toBeWritten = new LinkedList<>();
        this.append = append;
    }

    public void write(byte[] data) {
        toBeWritten.add(data);
    }

    @Override
    public void save() throws IOException {

        try (FileOutputStream writer = new FileOutputStream(getPath(), append)) {
            while (!toBeWritten.isEmpty()) {
                writer.write(toBeWritten.poll());
            }
        }

    }

    @Override
    public @NotNull String getPath() {
        return path;
    }
}
