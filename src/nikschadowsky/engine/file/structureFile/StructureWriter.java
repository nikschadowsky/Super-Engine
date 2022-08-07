package nikschadowsky.engine.file.structureFile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class StructureWriter extends BufferedWriter {

    // BufferedWriter bw = new BufferedWriter(new FileWriter("/../../output.txt"));

    public StructureWriter(FileWriter fw) {
        super(fw);

    }

    public void writeStructure(StructureElement e) throws IOException {

        // Key Name

        if (e.getDepth() != 0) {
            write(StructureElement.appendString("     ", e.getDepth() - 1) + "\"" + e.getName() + "\"{"
                    + System.lineSeparator());
        }

        // Values
        for (String s : e.getValues().keySet()) {
            write(StructureElement.appendString("     ", e.getDepth()) + s + ":" + e.getValues().get(s)
                    + System.lineSeparator());
        }

        // Sub keys
        for (StructureElement s : e.getSubKeys()) {
            writeStructure(s);
        }
        if (e.getDepth() != 0) {

            write(StructureElement.appendString("     ", e.getDepth() - 1) + "}" + System.lineSeparator());
        }

    }

}
