package nikschadowsky.engine.file.structureFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class StructureElement {

    private String name;
    private int depth;


    private HashMap<String, StructureValue> values = new HashMap<>();
    private ArrayList<StructureElement> subkeys = new ArrayList<>();

    public StructureElement(String name, int depth) {
        this.name = name;
        this.setDepth(depth);
    }

    public String getName() {
        return name;
    }

    public void setValues(HashMap<String, StructureValue> values) {

        this.values.putAll(values);

    }

    public void setSubKeys(StructureElement[] elements) {

        List<StructureElement> tempList = Arrays.asList(elements);

        if (tempList.contains(this)) {
            System.err.println("Content under Key " + getName() + " cannot be itself");
            tempList.remove(this);
        }
        subkeys.addAll(tempList);

    }

    public HashMap<String, StructureValue> getValues() {
        return values;
    }

    public ArrayList<StructureElement> getSubKeys() {
        return subkeys;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public static boolean isValue(String s) {
        String line = s;

        if (line.contains(":") && line.indexOf(':') != 0)
            if (!line.contains("\""))
                if (!line.contains("{"))
                    return true;

        return false;
    }

    public static boolean isKey(String s) {

        String line = s.stripLeading();

        if (line.charAt(0) == '\"')
            if (line.charAt(line.length() - 2) == '\"')
                if (line.charAt(line.length() - 1) == '{')
                    return true;

        return false;
    }

    public String toString() {
        return (System.lineSeparator() + appendString("     ", depth) + "Key:" + getName() + "{"
                + System.lineSeparator() + appendString("     ", depth + 1) + "values:" + values
                + System.lineSeparator() + appendString("     ", depth + 1) + "subkeys:" + subkeys
                + System.lineSeparator() + appendString("     ", depth) + "}");
    }

    /**
     * @param s String to be multiplied
     * @param a Number of times adding s to new String
     */

    public static String appendString(String s, int a) {

        return new String(new char[a]).replace("\0", s);
    }

    public static String allHashMapValuesToString(HashMap<String, StructureValue> h) {

        String output = "";

        for (String key : h.keySet()) {
            output += h.get(key) + ", ";
        }

        return output.substring(0, output.length() - 1);
    }


}
