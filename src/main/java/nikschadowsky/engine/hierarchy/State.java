package nikschadowsky.engine.hierarchy;

import nikschadowsky.engine.rendering_old.RenderingInformation;

import java.util.ArrayList;
import java.util.Comparator;

public abstract class State {

	private ArrayList<Layer> layers;

	private boolean layersAreFixed;

	public State(boolean layersAreFixed) {
		this.layersAreFixed = layersAreFixed;
		layers = new ArrayList<>();

		addLayers(layers);

		sort();

	}

	/**
	 * Enter all of your layers in this method and add them to layers
	 */
	public abstract void addLayers(ArrayList<Layer> layers);


	public void update() {
		for (int i = 0; i < layers.size(); i++) {
			layers.get(i).update();
		}
	}

	public void input() {
		for (int i = 0; i < layers.size(); i++) {
			layers.get(i).input();
		}
	}

	private void sort() {
		layers.sort(new Comparator<SortableByZ>() {

			@Override
			public int compare(SortableByZ o1, SortableByZ o2) {
				return Integer.compare(o1.getZ(), o2.getZ());
			}

		});
	}

	public void initRendering(RenderingInformation renderingInfo) {
		for (int i = 0; i < layers.size(); i++) {
			layers.get(i).initRendering(renderingInfo);
		}
	}

	public void render(RenderingInformation renderingInfo) {// renderer als parameter?

		// layer sortieren

		if (!layersAreFixed)
			sort();

		// layer nacheinander rendern

		for (int i = 0; i < layers.size(); i++) {
			layers.get(i).render(renderingInfo);
		}

	}

	public void reshape(int width, int height) {
		for (int i = 0; i < layers.size(); i++) {
			layers.get(i).reshape(width, height);
		}
	}


}
