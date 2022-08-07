package nikschadowsky.engine._testenvironment;

import nikschadowsky.engine.management.ThreadManager;

public class WindowTest {

    public static void main(String[] args) {

		/*
	    TODO : Image File Reading + Texture-Atlas
		 */

        ThreadManager.start("_test/windowconfig.config", new TestStateMGR());
        ThreadManager.start("_test/windowconfig.config", new TestStateMGR2());
    }
}
