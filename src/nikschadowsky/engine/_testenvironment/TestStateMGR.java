package nikschadowsky.engine._testenvironment;

import nikschadowsky.engine.hierarchy.State;
import nikschadowsky.engine.statemanager.StateManager;

import java.util.HashMap;

public class TestStateMGR extends StateManager {

	public TestStateMGR() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addStates(HashMap<String, State> states) {
		states.put("default", new TestState(true));

	}

	@Override
	public String setDefaultState() {

		return "default";
	}


}
