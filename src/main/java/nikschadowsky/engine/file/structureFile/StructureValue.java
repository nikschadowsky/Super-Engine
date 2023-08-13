package nikschadowsky.engine.file.structureFile;

public class StructureValue {

    private boolean isInteger = false;
    private boolean isDouble = false;
    private boolean isBoolean = false;
    private boolean isString = false;

    private String value;

    public StructureValue(int v) {

        isInteger = true;
        value = String.valueOf(v);
    }

    public StructureValue(double v) {
        isDouble = true;
        value = String.valueOf(v);
    }

    public StructureValue(String v) {
        isString = true;
        value = v;
    }

    public StructureValue(boolean v) {
        isBoolean = true;
        value = String.valueOf(v);
    }

    public String getValue() {
        return value;
    }

    public static StructureValue returnValue(String input) {

        input = input.strip();

        boolean hasLetter = false;

        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetter = true;
            }
        }
        if (!hasLetter) {
            if (input.contains(".")) {
                try {
                    return new StructureValue(Double.parseDouble(input));
                } catch (Exception E) {
                    return new StructureValue(input);
                }
            } else {
                try {
                    return new StructureValue(Integer.parseInt(input));
                } catch (Exception e) {
                    return new StructureValue(input);
                }
            }

        } else {
            if (input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false")) {
                return new StructureValue(Boolean.parseBoolean(input));
            }
        }

        return new StructureValue(input);

    }

    public boolean isInteger() {
        return isInteger;
    }

    public boolean isDouble() {
        return isDouble;
    }

    public boolean isBoolean() {
        return isBoolean;
    }

    public boolean isString() {
        return isString;
    }

    @Override
    public String toString() {
        return getValue();
    }

}
