package game.managers;

import game.Project;
import game.states.screens.MyScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class MyInputManager extends Manager implements InputProcessor {

	public MyInputManager(Project project) {
		super(project);
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public boolean keyDown(int keycode) {
		MyScreen screen = project.getMyAssetManager().getCurrentScreen();
		return screen.pollInput(keycode);
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	@Override
	public void create() {

	}

	@Override
	public void dispose() {
		project.dispose();
	}

}
