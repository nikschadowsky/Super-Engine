package nikschadowsky.engine.logging;

import nikschadowsky.engine.management.ThreadManager;
import nikschadowsky.engine.utilities.resource.FileCreation;
import nikschadowsky.engine.utilities.resource.output.StringFileWriter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * File created on 16.09.2023
 */
public class Logger {

    public static final boolean LOGGING_TO_FILE;
    public static final String LEVEL_LOG = "LOG";
    public static final String LEVEL_ERROR = "ERROR";


    static {
        LOGGING_TO_FILE = Boolean.parseBoolean(ThreadManager.getInstance()
                .getGlobalProperties()
                .getOrDefault("LOG_FILE", "true"));

    }

    private static final String LOG_FILE_DIRECTORY = FileCreation.CURRENT_WORKING_PATH + "/logFile/";
    private static final String LOG_FILE = LOG_FILE_DIRECTORY
            + String.format("Log-%1$td-%1$tm-%1$tY_%1$tH-%1$tM-%1$tS.log", System.currentTimeMillis());

    private final Class<?> clazz;

    public Logger(@NotNull Class<?> clazz) {
        this.clazz = clazz;
    }

    private static final String loggingLevelTemplate = "%1$s: ";
    private static final String loggingTimeTemplate = "%2$td-%2$tm-%2$tY %2$tH:%2$tM:%2$tS:%2$tL";
    private static final String loggingClassTemplate = " at %3$s.class : ";
    private static final String loggingMessageTemplate = "%4$s";

    /**
     * This field ist the representation for a formatted string used by this logger class. It consists of the Log Level
     * at %1, the full date and time (including millis) at %2, the registered class name at %3 and the log's content at
     * %4
     */
    private static final String loggingTemplate = loggingLevelTemplate + loggingTimeTemplate + loggingClassTemplate + loggingMessageTemplate;

    public void log(Object o) {
        log(o, clazz);
    }


    public void error(Object o) {
        error(o, clazz);
    }

    // static logging methods for one time logging

    public static void log(Object o, Class<?> caller) {
        String msg = String.format(loggingTemplate, LEVEL_LOG, System.currentTimeMillis(), caller.getSimpleName(), o);
        System.out.println(msg);
        logToFile(msg);
    }

    public static void error(Object o, Class<?> caller) {
        String msg = String.format(loggingTemplate, LEVEL_ERROR, System.currentTimeMillis(), caller.getSimpleName(), o);
        System.err.println(msg);
        logToFile(msg);
    }

    private static final StringFileWriter writer = new StringFileWriter(LOG_FILE, true);

    private static void logToFile(String loggingMessage) {
        if (!LOGGING_TO_FILE) return;

        if (!FileCreation.fileOrDirectoryExists(LOG_FILE)) {
            FileCreation.createDirectory(LOG_FILE_DIRECTORY);
            FileCreation.createFile(LOG_FILE);
        }

        writer.write(loggingMessage + System.lineSeparator());
        try {
            writer.save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
