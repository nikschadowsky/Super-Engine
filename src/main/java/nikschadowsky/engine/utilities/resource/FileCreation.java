package nikschadowsky.engine.utilities.resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * File created on 16.09.2023
 */
public class FileCreation {

    private static final IllegalArgumentException absolutePathException = new IllegalArgumentException("Specified Path is not absolute!");

    public static boolean fileExists(String absolutePath) {
        return checkForValidPath(absolutePath).exists();
    }

    public static boolean createFile(String absolutePath) {
        try {
            return checkForValidPath(absolutePath).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean createDirectory(String absolutePath) {
        return checkForValidPath(absolutePath).mkdir();
    }

    public static boolean deleteFileOrDirectory(String absolutePath) {
        return checkForValidPath(absolutePath).delete();
    }

    public static boolean isDirectoryEmpty(String absolutePath) {

        Path path = checkForValidPath(absolutePath).toPath();
        if (Files.isDirectory(path)) return false; // not a dir

        try {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
                return !stream.iterator().hasNext();
            }
        } catch (IOException e) {
            return false;
        }
    }

    private static File checkForValidPath(String path) {
        if (Paths.get(path).isAbsolute()) {
            return new File(path);
        }
        throw absolutePathException;
    }

}
