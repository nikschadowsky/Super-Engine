package nikschadowsky.engine.resources;

import nikschadowsky.engine.utilities.resource.FileUtilities;

public class DefaultResourceLoader implements ResourceLoader {
    @Override
    public Resource loadResource(String path) {

        switch (FileUtilities.getFileExtension(path)) {
            case "png":

                return new Resource.Image(path);

            case "config":

                return new Resource.Configuration(path);

            case "txt":

                return null;

        }
        return null;
    }


}
