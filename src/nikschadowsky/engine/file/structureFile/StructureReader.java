package nikschadowsky.engine.file.structureFile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class StructureReader {

    public static void main(String[] args) {

        try {
            StructureReader sr = new StructureReader(new FileReader("res/test.txt"));

            sr.createStructure();
            System.out.println(sr.main);

            StructureWriter sw = new StructureWriter(new FileWriter("output.txt"));
            sw.writeStructure(sr.main);
            sw.close();

            System.out.println(sr.getValue("", "value"));

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    private BufferedReader br;
    private int lineCount = 0;
    private ArrayList<String> content = new ArrayList<>();
    private int currentLine = -1;

    private StructureElement main = new StructureElement("Main", 0);

    public StructureReader(Reader reader) {

        br = new BufferedReader(reader);

    }

    public StructureReader(InputStream reader) {

        br = new BufferedReader(new InputStreamReader(reader));

    }

    public ArrayList<String> readAllLines() throws IOException {

        String line = br.readLine();

        while (line != null) {

            if (!line.isBlank()) {

                lineCount += 1;

                content.add(line);
            }
            line = br.readLine();

        }

        return content;
    }

    public int getLineCount() {
        return lineCount;
    }

    public void createStructure() throws IOException {

        readAllLines();
        addData(main);
    }

    private void addData(StructureElement e) {
        currentLine++;

        HashMap<String, StructureValue> values = new HashMap<>();
        HashSet<StructureElement> keySet = new HashSet<>();

        String line = content.get(currentLine).strip();

        while (!line.contains("}")) {

            if (StructureElement.isValue(line)) {

                String[] value = line.split(":");
                values.put(value[0], StructureValue.returnValue(value[1]));

            } else if (StructureElement.isKey(line)) {

                StructureElement subkey = new StructureElement(line.substring(1, line.length() - 2), e.getDepth() + 1);
                keySet.add(subkey);
                addData(subkey);

            }
            if (currentLine == getLineCount() - 1)
                break;

            currentLine++;
            line = content.get(currentLine).strip();
        }

        e.setValues(values);

        StructureElement[] keys = new StructureElement[keySet.size()];
        keySet.toArray(keys);

        e.setSubKeys(keys);

    }

    public StructureValue getValue(String tree, String name) {

/**        CODE IN {@link #getSubkey(String)} **/
        StructureElement key = getSubKey(tree);

        if (key != null) {
            return key.getValues().get(name);
        }
        System.err.println("Cannot find given Value in given Tree!");
        return null;
    }

    public StructureElement getSubKey(String tree) {

        String[] keyNames = tree.split("/");

        StructureElement current = main;

        for (int i = 0; i < keyNames.length; i++) {

            if (keyNames[i].isBlank())
                continue;

            for (StructureElement x : current.getSubKeys()) {

                if (x.getName().equals(keyNames[i])) {
                    current = x;
                    break;
                }

                current = null;

            }

        }
        return current;


    }


}
