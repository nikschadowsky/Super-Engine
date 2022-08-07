package nikschadowsky.engine._testenvironment;

import nikschadowsky.engine.hierarchy.Layer;
import nikschadowsky.engine.rendering.Renderer;

import java.awt.*;

public class Testlayer2 extends Layer {

	int x, y = 0;

	public Testlayer2(int x) {
		super(4);
		this.x = x;

	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public void update() {
		y += 5;
		if (y > 255)
			y = 0;

	}

	@Override
	public void draw(Renderer r) {
		// TODO Auto-generated method stub

		r.setColor(Color.white);

		r.drawQuad(1, 1, 2, 2);

		r.setColor(new Color(0, 255 - y, y, 100));

		r.drawTriangle(0, 0, 2, 2, 4, 2);


	}

	@Override
	public void input() {

	}

}
