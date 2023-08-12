package nikschadowsky.engine.resources;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * File created on 12.08.2023
 */
public class ByteReaderTest {

    @Test
    void testReadAllBytesFromFile(){

        byte[] fileData = ByteReader.readAllBytesFromFile("src/test/resources/testReadAllBytesFromFile.txt");

        String actual = new String(fileData).replaceAll("\r\n",System.lineSeparator());

        String expected = "this is the file content to\nbe\nread\n\nyeehaw!\n1234567890".replaceAll("\\v", System.lineSeparator());

        assertEquals(expected, actual);
    }

}
