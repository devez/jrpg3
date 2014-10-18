package game.entities;

import game.world.tiles.Tile;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

	protected Vector2 position;
	protected Vector2 size;
	
	protected Rectangle rect;
	protected Color rectColor;
	
	protected float width;
	protected float height;
	
	protected Vector2 coordinates;
	protected Vector2 cameraOffset;
	
	protected boolean interactable = false;

	public Entity(Vector2 position) {
		this.position = position;
		size = new Vector2(1, 1);
		rect = new Rectangle();
		rectColor = Color.WHITE;
	}

	public Entity(float x, float y) {
		position = new Vector2(x, y);
	}
	
	protected void updateCoordinates() {
		coordinates.x = (int) position.x / Tile.SIZE;
		coordinates.y = (int) position.y / Tile.SIZE;
	}

	public Vector2 getCoordinates() {
		return coordinates;
	}
	
	public Vector2 getPosition() {
		return position;
	}

	public Rectangle getRect() {
		return rect;
	}
	
	public Color getRectColor() {
		return rectColor;
	}
	
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
	public boolean isInteractable() {
		return interactable;
	}
}
