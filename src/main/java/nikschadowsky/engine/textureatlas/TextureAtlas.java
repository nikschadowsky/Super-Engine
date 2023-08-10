package nikschadowsky.engine.textureatlas;

import nikschadowsky.engine.management.FileManager;
import nikschadowsky.engine.opengl.Texture;
import nikschadowsky.engine.resources.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TextureAtlas {

    public static int maxWidth, maxHeight;

    private static BufferedImage finalImage;

    private Texture finalTexture;

    public static void generateTextureAtlas() {

        // filter for resource.image in resources and determine the maxWidth by the widest image

        ArrayList<Resource.Image> images = new ArrayList<>();

        for (String s : FileManager.resources.keySet()) {

            Resource res = FileManager.resources.get(s);

            if (res instanceof Resource.Image) {

                maxWidth = Math.max(maxWidth, ((Resource.Image) res).getImage().getWidth());

                images.add((Resource.Image) res);

            }
        }

        int imgHeight = 0;
        int currentX = 0, currentY = 0;

        // calculate image dimensions

        for (int i = 0; i < images.size(); i++) {

            Resource.Image img = images.get(i);

            if (currentX + img.getImage().getWidth() > maxWidth) {
                currentX = 0;
                currentY = imgHeight;
            }

            img.x = currentX;
            img.y = currentY;

            currentX += img.getImage().getWidth();
            imgHeight = Math.max(imgHeight, currentY + img.getImage().getHeight());


        }
        maxHeight = imgHeight + 1;

        // draw texture atlas to BufferedImage

        finalImage = new BufferedImage(maxWidth, maxHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = finalImage.createGraphics();

        g.fillRect(0, 0, finalImage.getWidth(), finalImage.getHeight());

        for (int i = 0; i < images.size(); i++) {

            updateImageUV(images.get(i), maxWidth, maxHeight);

            g.drawImage(images.get(i).getImage(), null, images.get(i).x, images.get(i).y);

        }


//        DEBUG PURPOSES
//        File outputfile = new File("image.png");
//        try {
//            ImageIO.write(finalImage, "png", outputfile);
//        } catch (Exception e) {
//
//        }
    }

    public static BufferedImage getFinalImage() {
        return finalImage;
    }

    public static void updateImageUV(Resource.Image image, int imageWidth, int imageHeight) {

        image.setUVCoordinates(image.x / (float) imageWidth,
                (image.x + image.getImage().getWidth()) / (float) imageWidth,
                image.y / (float) imageHeight,
                (image.y + image.getImage().getHeight()) / (float) imageHeight);

    }

}
