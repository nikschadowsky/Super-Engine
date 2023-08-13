package nikschadowsky.engine.resources;

import nikschadowsky.engine.file.structureFile.StructureElement;
import nikschadowsky.engine.file.structureFile.StructureReader;
import nikschadowsky.engine.file.structureFile.StructureValue;
import nikschadowsky.engine.window.WindowConfiguration;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

public abstract class Resource {


    public static final long MAX_INACTIVITY_TIME = 0x45D964B800L; // 3*10^11 seconds or 5 minutes

    boolean isAutoDeletable = true;

    long lastAccess = 0;


    String path;

    public Resource(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public static class Configuration extends Resource {

        private StructureReader structure;

        boolean isWindowConfigInit = false;

        private WindowConfiguration windowConfiguration;

        public Configuration(String path) {
            super(path);
            constructFromFile(path);
        }

        public void constructFromFile(String path) {

            try {
                structure = new StructureReader(getClass().getClassLoader().getResourceAsStream(path));
                structure.createStructure();
            } catch (IOException e) {
                e.printStackTrace();

            }
        }

        public WindowConfiguration getWindowConfiguration() {
            if (!isWindowConfigInit) {

                generateWindowConfiguration();
                isWindowConfigInit = true;
            }

            return windowConfiguration;
        }

        public void generateWindowConfiguration() {


            windowConfiguration = new WindowConfiguration();

            StructureElement element = structure.getSubKey("/window");

            for (String valueName : element.getValues().keySet()) {

                StructureValue value = element.getValues().get(valueName);

                try {
                    WindowConfiguration.class.getMethod("set" + valueName, String.class).invoke(windowConfiguration, value.getValue());
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {

                    System.err.println("Illegal value in Configuration-File under \'/window\'-Tree");

                }
            }
        }

    }

    public static class Image extends Resource {


        public int x, y; // position on textureAtlas

        private float uvX1, uvX2, uvY1, uvY2;

        private BufferedImage img;

        public Image(String path) {
            super(path);

            load(path);
        }

        public void load(String path) {

            try (InputStream is = getClass().getClassLoader().getResourceAsStream(path)) {
                img = ImageIO.read(is);
            } catch (Exception e) {
                try (InputStream is = getClass().getClassLoader().getResourceAsStream("image/MISSING.png")) {
                    img = ImageIO.read(is);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        public BufferedImage getImage() {
            return img;
        }

        public float getX1() {
            return uvX1;
        }

        public float getX2() {
            return uvX2;
        }

        public float getY1() {
            return uvY1;
        }

        public float getY2() {
            return uvY2;
        }

        public void setUVCoordinates(float uvX1, float uvX2, float uvY1, float uvY2) {

            this.uvX1 = uvX1;
            this.uvX2 = uvX2;
            this.uvY1 = uvY1;
            this.uvY2 = uvY2;

        }
    }


    public long getLastAccess() {
        return lastAccess;
    }

    public void updateLastAccess() {
        this.lastAccess = System.nanoTime();
    }

    public boolean isAutoDeletable() {
        return isAutoDeletable;
    }

    public void setAutoDeletable(boolean autoDeletable) {
        isAutoDeletable = autoDeletable;
    }
}
