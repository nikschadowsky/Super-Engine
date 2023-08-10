package nikschadowsky.engine.file;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class FileReader {

    static HashMap<String, String> fileList = new HashMap<>();

    public static String readFile(String path) {

        if (fileList.containsKey(path)) {
            return fileList.get(path);
        }

        try (InputStream in = FileReader.class.getClassLoader().getResourceAsStream(path)) {

            assert in != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(in));


            String content = "";
            String line;

            while ((line = br.readLine()) != null) {
                content += line + System.lineSeparator();
            }
            br.close();

            fileList.put(path, content);

            return content;

        } catch (Exception e) {

            System.err.println("Loading File \'" + path + "\' failed! \nCheck if the path provided is correct!");
            return null;
        }
    }

}
