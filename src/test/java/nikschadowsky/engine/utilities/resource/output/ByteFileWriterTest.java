package nikschadowsky.engine.utilities.resource.output;

import nikschadowsky.engine.utilities.resource.FileCreation;
import nikschadowsky.engine.utilities.resource.input.ByteReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * File created on 16.09.2023
 */
public class ByteFileWriterTest {
    private static final String directory = System.getProperty("user.home") + "/.SUPER_ENGINE_TEST";
    private static final String filename = "_ByteFileWriterTest.test";

    private static final String filePath = directory + "/" + filename;

    @BeforeEach
    void createFileStructure() {
        assertFalse(FileCreation.fileOrDirectoryExists(directory), "The folder already exists!");
        assertTrue(FileCreation.createDirectory(directory));
        FileCreation.createFile(filePath);
    }

    @Test
    void testWriting() throws IOException {

        byte[] arr1 = "byte data from string 123".getBytes(StandardCharsets.UTF_8);
        byte[] arr2 = "another bit of dätä".getBytes(StandardCharsets.UTF_8);

        ByteFileWriter writer = new ByteFileWriter(filePath, false);
        writer.write(arr1);
        writer.write(arr2);
        writer.save();

        byte[] reread = ByteReader.readAllBytesFromFile(filePath);

        byte[] combined = Arrays.copyOf(arr1, arr1.length + arr2.length);
        System.arraycopy(arr2, 0, combined, arr1.length, arr2.length);

        assertArrayEquals(combined, reread);

        byte[] toAppend = "\nthis should get appended".getBytes(StandardCharsets.UTF_8);

        writer = new ByteFileWriter(filePath, true);
        writer.write(toAppend);
        writer.save();

        combined = Arrays.copyOf(arr1, arr1.length + arr2.length + toAppend.length);
        System.arraycopy(arr2, 0, combined, arr1.length, arr2.length);
        System.arraycopy(toAppend, 0, combined, arr1.length + arr2.length, toAppend.length);

        reread = ByteReader.readAllBytesFromFile(filePath);

        assertArrayEquals(combined, reread);
    }

    @AfterEach
    void deleteFileStructure() {
        FileCreation.deleteFileOrDirectory(filePath);
        assertTrue(FileCreation.deleteFileOrDirectory(directory), "The folder cannot be deleted!");
    }

}
