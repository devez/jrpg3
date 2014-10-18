package game.states.screens;

import game.Project;
import game.states.MyState;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;

public abstract class MyScreen extends MyState implements Screen {
	
	public static int MAINMENU = 0;
	public static int INGAME = 1;

	protected Project project;
	protected AssetManager assetManager;

	public MyScreen(Project project, AssetManager assetManager) {
		this.project = project;
		this.assetManager = assetManager;
	}
	
	public abstract boolean pollInput(int keycode);

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

}
