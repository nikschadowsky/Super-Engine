package nikschadowsky.engine._testenvironment;

import nikschadowsky.engine.hierarchy.State;
import nikschadowsky.engine.statemanager.StateManager;

import java.util.HashMap;

public class TestStateMGR2 extends StateManager {

    public TestStateMGR2() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void addStates(HashMap<String, State> states) {
        states.put("default", new TestState2(true));

    }

    @Override
    public String setDefaultState() {

        return "default";
    }


}
