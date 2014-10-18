package game.entities.mobs;

import game.entities.Entity;
import game.world.tiles.Tile;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Npc extends Mob {

	private String name;
	// private Dialog dialog;

	private Texture texture;
	private String tempText;

	public Npc(Vector2 position, String tempText) {
		super(position);
		this.rectColor = Color.YELLOW;
		texture = new Texture("resources/mobs/random/human3.png");
		this.tempText = tempText;
	}

	public void update(ArrayList<Entity> entities) {
		this.updateCoordinates();
		rectOffset.x = width / 4f;
		rectOffset.y = 0f;
		rect.set(position.x + rectOffset.x, position.y + rectOffset.y, width - 2 * rectOffset.x, height / 4);
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(texture, position.x, position.y);
	}

	@Override
	public void renderRect(ShapeRenderer shapeRenderer) {
		shapeRenderer.setColor(getRectColor());
		//shapeRenderer.x(getRect().x + width / 2, getRect().y + height / 2, Tile.SIZE / 2);
		shapeRenderer.rect(getRect().x, getRect().y, getRect().width, getRect().height);
	}

	@Override
	public void interact(ArrayList<Entity> entities) {
		// TODO Auto-generated method stub
		
	}

	public String getTempText() {
		return tempText;
	}
	
}
