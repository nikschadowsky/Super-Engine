package nikschadowsky.engine.utilities.resource;

import com.jogamp.common.nio.Buffers;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

public class ImageUtilities {

    // usage : texture atlas upload data


    public static ByteBuffer getImageData(BufferedImage source) {


        int[] pixelData = new int[source.getWidth() * source.getHeight()];

        source.getRGB(0, 0, source.getWidth(), source.getHeight(), pixelData, 0, source.getWidth());

        ByteBuffer buffer = Buffers.newDirectByteBuffer(pixelData.length * 4); // 4 channels for rgba

        for (int y = 0; y < source.getHeight(); y++) {
            for (int x = 0; x < source.getWidth(); x++) {
                int pixel = pixelData[y * source.getWidth() + x];
                buffer.put((byte) ((pixel >> 16) & 0xFF)); // Red component
                buffer.put((byte) ((pixel >> 8) & 0xFF)); // Green component
                buffer.put((byte) (pixel & 0xFF)); // Blue component
                buffer.put((byte) ((pixel >> 24) & 0xFF)); // Alpha component. Only for RGBA
            }
        }

        buffer.flip();

        return buffer;
    }
}
