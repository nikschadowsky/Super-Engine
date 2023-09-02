package nikschadowsky.engine.input;

import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * File created on 02.09.2023
 */
public class InputMapGenerator {

    public static void main(String[] args) throws IllegalAccessException {

        Field[] fields = KeyEvent.class.getDeclaredFields();

        List<DataHolder> data = new LinkedList<>();

        for (Field f : fields) {
            if (Modifier.isStatic(f.getModifiers()) && f.getName().contains("VK_")) {
                //System.out.println("public static final int " + f.getName().replace("VK_", "") + " = " + f.getInt(f.getName()));

                data.add(new DataHolder(f.getName().replace("VK_", ""), f.getInt(f.getName())));

            }
        }

        data.stream().sorted(Comparator.comparingInt(DataHolder::value)).forEach(
                a -> System.out.println("public static final int " + a.name() + " = " + a.value() + ";"));

    }

    private record DataHolder(String name, int value) {
    }
}
