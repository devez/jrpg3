package game.states;

import game.Project;
import game.states.screens.MyScreen;

public class StateMachine {

	private Project project;
	private MyState currentState;

	public StateMachine(Project project) {
		this.project = project;
	}

	public void changeState(MyState state) {
		currentState.onLeave();
		this.setState(state);		
	}

	public void setState(MyState state) {
		currentState = state;
		project.setState(currentState);
		currentState.onEnter();		
	}
	
	public MyState getCurrentState() {
		return currentState;
	}

}
