package game.gui;

import game.util.MyFont;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class GuiEntity {
	
	protected Texture texture;
	protected MyFont font;
	protected Vector2 position;
	protected int width;
	protected int height;


	public abstract void render(SpriteBatch guiBatch);

}
