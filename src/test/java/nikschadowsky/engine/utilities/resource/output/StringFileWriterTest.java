package nikschadowsky.engine.utilities.resource.output;

import nikschadowsky.engine.utilities.resource.FileCreation;
import nikschadowsky.engine.utilities.resource.input.ByteReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * File created on 16.09.2023
 */
public class StringFileWriterTest {

    private static final String directory = System.getProperty("user.home") + "/.SUPER_ENGINE_TEST";
    private static final String filename = "_StringFileWriterTest.test";

    private static final String filePath = directory + "/" + filename;

    @BeforeEach
    void createFileStructure() {
        assertFalse(FileCreation.fileOrDirectoryExists(directory), "The folder already exists!");
        assertTrue(FileCreation.createDirectory(directory));
        FileCreation.createFile(filePath);
    }

    @Test
    void testWriting() throws IOException {

        String sampleString1 = "this is a test 12345";
        String sampleString2 = " and this too";


        StringFileWriter writer = new StringFileWriter(filePath, false);
        writer.write(sampleString1);
        writer.write(sampleString2);
        writer.save();

        String reread = new String(ByteReader.readAllBytesFromFile(filePath));
        assertEquals(sampleString1 + sampleString2, reread);

        String toAppend = "\nthis part was appended";
        writer = new StringFileWriter(filePath, true);
        writer.write(toAppend);
        writer.save();

        reread = new String(ByteReader.readAllBytesFromFile(filePath));
        assertEquals(sampleString1 + sampleString2 + toAppend, reread);

    }

    @AfterEach
    void deleteFileStructure() {
        FileCreation.deleteFileOrDirectory(filePath);
        assertTrue(FileCreation.deleteFileOrDirectory(directory), "The folder cannot be deleted!");
    }

}
