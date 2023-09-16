package nikschadowsky.engine.utilities.resource.output;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * File created on 16.09.2023
 */
public class StringFileWriter implements FileWriter {

    private final String path;
    private final boolean append;

    private final StringBuilder builder;

    /**
     * @param path   path to the file to write to. file must not exist upon instantiation but has to exists upon
     *               saving.
     * @param append
     */
    public StringFileWriter(@NotNull String path, boolean append) {
        this.path = path;
        this.append = append;
        this.builder = new StringBuilder();
    }

    public void write(String content) {
        builder.append(content);
    }

    @Override
    public void save() throws IOException {

        try (java.io.FileWriter writer = new java.io.FileWriter(getPath(), append)) {
            writer.write(builder.toString());
            builder.setLength(0);
        }
    }

    @Override
    public @NotNull String getPath() {
        return path;
    }
}
