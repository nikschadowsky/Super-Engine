package nikschadowsky.engine._testenvironment;

import nikschadowsky.engine.hierarchy.Layer;
import nikschadowsky.engine.hierarchy.State;

import java.util.ArrayList;

public class TestState extends State {

	public TestState(boolean layersAreFixed) {
		super(layersAreFixed);
	}

	@Override
	public void addLayers(ArrayList<Layer> layers) {

		layers.add(new Testlayer(1));

//		layers.add(new Testlayer(-2));
	}

}
