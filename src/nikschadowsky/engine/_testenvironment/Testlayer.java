package nikschadowsky.engine._testenvironment;

import nikschadowsky.engine.hierarchy.Layer;
import nikschadowsky.engine.input.Input;
import nikschadowsky.engine.management.FileManager;
import nikschadowsky.engine.rendering.Renderer;

import static nikschadowsky.engine.input.Input.*;

public class Testlayer extends Layer {

    int x, y;

    public Testlayer(int x) {
        super(100);
        this.x = x;

    }

    @Override
    public int getZ() {
        // TODO Auto-generated method stub
        return x;
    }


    @Override
    public void update() {

    }

    @Override
    public void draw(Renderer r) {
        // TODO Auto-generated method stub


        r.drawImage(FileManager.getResource("image/MISSING.png"), x, y, 50, 50);


    }

    @Override
    public void input() {

        if (isPressed(LEFT)) {
            x--;
        }
        if (isPressed(RIGHT)) {
            x++;
        }
        if (Input.isPressed(UP)) {
            y++;
        }
        if (Input.isPressed(DOWN)) {
            y--;
        }


    }

}
