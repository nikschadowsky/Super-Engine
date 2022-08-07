package nikschadowsky.engine._testenvironment;

import nikschadowsky.engine.hierarchy.Layer;
import nikschadowsky.engine.hierarchy.State;

import java.util.ArrayList;

public class TestState2 extends State {

    public TestState2(boolean layersAreFixed) {
        super(layersAreFixed);
    }

    @Override
    public void addLayers(ArrayList<Layer> layers) {

        layers.add(new Testlayer2(1));

//		layers.add(new Testlayer(-2));
    }

}
