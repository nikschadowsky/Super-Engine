package nikschadowsky.engine.logging;

import nikschadowsky.engine.management.ThreadManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * File created on 16.09.2023
 */
public class LoggerTest {

    private ByteArrayOutputStream newLogOut, newErrOut;

    @BeforeAll
    static void initializeAll() {


        ThreadManager.createThreadManager("", new String[0]);

        stdOut = System.out;
        stdErr = System.err;
    }

    static PrintStream stdOut, stdErr;


    @BeforeEach
    void setup() {

        System.setOut(stdOut);
        System.setErr(stdErr);

        newLogOut = new ByteArrayOutputStream();
        newErrOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newLogOut));
        System.setErr(new PrintStream(newErrOut));

    }

    @Test
    void testInstantiatedLogger() {

        Object obj = new Object();
        String expectedString = "This is the test";

        Logger logger = new Logger(getClass());
        logger.log(expectedString);
        logger.log(obj);

        String logStreamContent = newLogOut.toString();

        assertTrue(logStreamContent.contains(expectedString));
        assertTrue(logStreamContent.contains(obj.toString()));
        assertTrue(logStreamContent.contains(Logger.LEVEL_LOG));
        assertTrue(logStreamContent.contains(String.format("%1$td-%1$tm-%1$tY", System.currentTimeMillis())));

        assertEquals(2, logStreamContent.split("\\n").length);

        logger.error(expectedString);
        logger.error(obj);

        String errStreamContent = newErrOut.toString();

        assertTrue(errStreamContent.contains(expectedString));
        assertTrue(errStreamContent.contains(obj.toString()));
        assertTrue(errStreamContent.contains(Logger.LEVEL_ERROR));
        assertTrue(errStreamContent.contains(String.format("%1$td-%1$tm-%1$tY", System.currentTimeMillis())));

        assertEquals(2, errStreamContent.split("\\n").length);
    }


}
