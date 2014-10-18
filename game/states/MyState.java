package game.states;

public abstract class MyState {
	
	public abstract void init();

	public abstract boolean loadAssets();

	public abstract boolean unloadAssets();

	public abstract void update(float delta);

	public abstract void render(float delta);

	public abstract void onEnter();

	public abstract void onLeave();

	public abstract void dispose();

}
