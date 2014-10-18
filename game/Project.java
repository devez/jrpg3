package game;

import game.managers.MyAssetManager;
import game.states.MyState;
import game.states.screens.MyScreen;
import game.util.Logger;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class Project extends Game {

	public static String VERSION = "Alpha v0.0.2.4";
	public static String TITLE = "JRPG3" + " - " + VERSION;
	public static boolean GL30 = false;
	public static int WIDTH = 800;
	public static int HEIGHT = 600;
	public static int SIZE = 1;
	public static boolean FULLSCREEN = false;
	public static boolean RESIZABLE = false;
	public static boolean VSYNCENABLED = false;
	public static int FPSCAP = 300;
	// Gdx.graphics.setDisplayMode(200, 200, false);

	private MyAssetManager assetManager;

	@Override
	public void create() {
		assetManager = new MyAssetManager(this);

		// depthBuffer
		// Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
		// Gdx.gl20.glDepthFunc(GL20.GL_LESS);
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void dispose() {
		super.dispose();
		assetManager.dispose();
		System.out.println(this.getClass().getSimpleName() + ": " + "disposed");
	}

	public MyAssetManager getMyAssetManager() {
		return assetManager;
	}

	public void setState(MyState currentState) {
		if (currentState instanceof MyScreen) {
			this.setScreen((Screen) currentState);
		}
		else {
			Logger.log("temp");
		}
	}
}
