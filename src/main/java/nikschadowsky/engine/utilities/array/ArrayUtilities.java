package nikschadowsky.engine.utilities.array;

import java.util.List;

public class ArrayUtilities {

    public static int[] toIntArray(List<Integer> list) {

        int[] result = new int[list.size()];

        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }

        return result;
    }

    public static float[] toFloatArray(List<Float> list) {


//		System.out.println(list);

        float[] result = new float[list.size()];

        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }


        return result;
    }

}
