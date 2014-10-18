package game.states.screens;

import game.Project;
import game.world.World;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;

public class InGame extends MyScreen {

	private World world;

	public InGame(Project project, AssetManager assetManager) {
		super(project, assetManager);
		this.init();
	}

	@Override
	public void init() {

	}

	@Override
	public boolean loadAssets() {
		return false;
	}

	@Override
	public boolean unloadAssets() {
		return false;
	}

	@Override
	public void update(float delta) {
		world.update();
		project.getMyAssetManager().getGuiManager().update();

	}

	@Override
	public void render(float delta) {
		this.update(delta);
		world.render();
		project.getMyAssetManager().getGuiManager().render();

	}

	@Override
	public void onEnter() {

	}

	@Override
	public void onLeave() {

	}

	@Override
	public void dispose() {
		System.out.println(this.getClass().getSimpleName() + ": " + "disposed");
	}

	public void setWorld(World world) {
		this.world = world;
	}

	@Override
	public boolean pollInput(int keycode) {
		switch (keycode) {
			case Keys.ESCAPE:
				project.getMyAssetManager().setScreen(MyScreen.MAINMENU);
				return true;
			case Keys.EQUALS:
				world.getCurrentArea().getPlayer().getCurrentAnimation().setAnimationTime(world.getCurrentArea().getPlayer().getCurrentAnimation().getAnimationTime()/2);
				return true;
			case Keys.MINUS:
				world.getCurrentArea().getPlayer().getCurrentAnimation().setAnimationTime(world.getCurrentArea().getPlayer().getCurrentAnimation().getAnimationTime()*2);
				return true;
			case Keys.NUM_1:
				world.toggleShapeRendering();
				return true;
		}
		return false;
	}

}
