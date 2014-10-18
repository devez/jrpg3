package game.managers;

import game.Project;
import game.states.StateMachine;
import game.states.screens.InGame;
import game.states.screens.MainMenu;
import game.states.screens.MyScreen;

import java.util.ArrayList;

import com.badlogic.gdx.assets.AssetManager;

public class MyScreenManager extends Manager {

	private StateMachine screenStateMachine;
	private ArrayList<MyScreen> screens;

	public MyScreenManager(Project project) {
		super(project);
		this.create();
	}

	@Override
	public void create() {
		screenStateMachine = new StateMachine(project);
		screens = new ArrayList<MyScreen>();
	}

	public void init(AssetManager assetManager) {
		screens.add(MyScreen.MAINMENU, new MainMenu(project, assetManager));
		screens.add(MyScreen.INGAME, new InGame(project, assetManager));
	}
	
	public void changeScreen(int id) {
		this.screenStateMachine.changeState(screens.get(id));
	}

	public void setScreen(int id) {
		this.screenStateMachine.setState(screens.get(id));
	}
	
	public MyScreen getCurrentScreen() {
		return (MyScreen) this.screenStateMachine.getCurrentState();
	}

	public MyScreen getScreen(int id) {
		return screens.get(id);
	}

	@Override
	public void dispose() {
		for (int i = 0; i < screens.size(); i++) {
			screens.get(i).dispose();
		}
		System.out.println(this.getClass().getSimpleName() + ": " + "disposed");
	}


}
