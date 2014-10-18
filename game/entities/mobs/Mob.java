package game.entities.mobs;

import java.util.ArrayList;

import game.entities.Entity;
import game.world.tiles.Tile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Mob extends Entity {
	
	protected Vector2 velocity;
	protected Vector2 rectOffset;
	protected Vector2 direction;
	protected Vector2 prevDirection;
	protected float speed;
	
	protected boolean moving = false;
	protected boolean canInteract = false;
	
	protected Rectangle interactionRect;

	public Mob(Vector2 position) {
		super(position);
		interactable = true;
		width = size.x * Tile.SIZE;
		height = size.y * Tile.SIZE;
		interactionRect = new Rectangle();
		rect.set(position.x, position.y, width, height);
		interactionRect.set(position.x - width/2f, position.y, width+width/2f, height);
		rectOffset = new Vector2(0, 0);
		coordinates = new Vector2((int) position.x / Tile.SIZE, (int) position.y / Tile.SIZE);
		cameraOffset = new Vector2(width / 2, height / 2);
		rectOffset = new Vector2();
		direction = new Vector2();
		prevDirection = new Vector2();
		velocity = new Vector2();
	}
	
	protected void updateMovement(ArrayList<Entity> entities) {
		boolean xCollided = xCollision(entities);
		boolean yCollided = yCollision(entities);
		if (!xCollided) {
			position.x += velocity.x;
		}
		if (!yCollided) {
			position.y += velocity.y;
		}
	}
	
	protected boolean xCollision(ArrayList<Entity> mapEntities) {
		for (Entity e : mapEntities) {
			if (e instanceof Tile) {
				if (((Tile) e).isSolid()) {
					if (this.getNextXRect().overlaps(e.getRect())) {
						return true;
					}
				}
			} else {
				if (e != this) {
					if (this.getNextXRect().overlaps(e.getRect())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	protected boolean yCollision(ArrayList<Entity> mapEntities) {
		for (Entity e : mapEntities) {
			if (e instanceof Tile) {
				if (((Tile) e).isSolid()) {
					if (this.getNextYRect().overlaps(e.getRect())) {
						return true;
					}
				}
			} else {
				if (e != this) {
					if (this.getNextYRect().overlaps(e.getRect())) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public Rectangle getNextXRect() {
		Rectangle temp = this.rect;
		temp.setPosition(position.x + rectOffset.x + velocity.x, position.y + rectOffset.y);
		return temp;
	}

	public Rectangle getNextYRect() {
		Rectangle temp = this.rect;
		temp.setPosition(position.x + rectOffset.x, position.y + rectOffset.y + velocity.y);
		return temp;
	}

	public abstract void update(ArrayList<Entity> entities);
	
	public abstract void interact(ArrayList<Entity> entities);
	
	public abstract void render(SpriteBatch batch);
	
	public abstract void renderRect(ShapeRenderer shapeRenderer);
	
	public Vector2 getCameraOffset() {
		return cameraOffset;
	}
	
	public Rectangle getInteractionRect() {
		return interactionRect;
	}
	


}
