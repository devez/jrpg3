package game.states.screens;

import game.Project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;

public class MainMenu extends MyScreen {

	public MainMenu(Project project, AssetManager assetManager) {
		super(project, assetManager);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean loadAssets() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean unloadAssets() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void onEnter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLeave() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		System.out.println(this.getClass().getSimpleName() + ": " + "disposed");
	}

	@Override
	public boolean pollInput(int keycode) {
		switch (keycode) {
			case Keys.ESCAPE:
				Gdx.app.exit();
				return true;
			case Keys.ENTER:
				project.getMyAssetManager().setScreen(MyScreen.INGAME);
				return true;
		}
		return false;
	}

}
