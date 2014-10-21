package game.entities.mobs;

import game.entities.Entity;
import game.managers.MyGuiManager;
import game.util.Logger;
import game.util.MyAnimation;
import game.util.PlayerInfo;
import game.world.tiles.Tile;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends Mob {

	private PlayerInfo playerInfo;

	private MyAnimation currentAnimation;
	private MyAnimation prevAnimation;

	private int prevAnimationIndex = -1;

	private HashMap<Integer, MyAnimation> animations;

	public final static int IDLE_DOWN = 1;
	public final static int IDLE_RIGHT = 2;
	public final static int IDLE_UP = 3;
	public final static int IDLE_LEFT = 4;

	public final static int RUN_DOWN = 5;
	public final static int RUN_RIGHT = 6;
	public final static int RUN_UP = 7;
	public final static int RUN_LEFT = 8;

	private float friction = 0.0f;

	private float idleDownTime = 0.100f;
	private float idleRightTime = 0.100f;
	private float idleUpTime = 0.100f;
	private float idleLeftTime = 0.100f;
	private float runDownTime = 0.100f;
	private float runRightTime = 0.100f;
	private float runUpTime = 0.100f;
	private float runLeftTime = 0.100f;

	private Entity e;

	public Player(Vector2 position) {
		super(position);
		playerInfo = new PlayerInfo();
		rectColor = Color.CYAN;

		animations = new HashMap<Integer, MyAnimation>();

		animations.put(IDLE_DOWN, new MyAnimation("resources/mobs/player/idleDown.png", 1, 1, idleDownTime, false));
		animations.put(IDLE_RIGHT, new MyAnimation("resources/mobs/player/idleRight.png", 1, 1, idleRightTime, false));
		animations.put(IDLE_UP, new MyAnimation("resources/mobs/player/idleUp.png", 1, 1, idleUpTime, false));
		animations.put(IDLE_LEFT, new MyAnimation("resources/mobs/player/idleLeft.png", 1, 1, idleLeftTime, false));
		animations.put(RUN_DOWN, new MyAnimation("resources/mobs/player/runDown.png", 3, 2, runDownTime, false));
		animations.put(RUN_RIGHT, new MyAnimation("resources/mobs/player/runRight.png", 3, 2, runRightTime, false));
		animations.put(RUN_UP, new MyAnimation("resources/mobs/player/runUp.png", 3, 2, runUpTime, false));
		animations.put(RUN_LEFT, new MyAnimation("resources/mobs/player/runLeft.png", 3, 2, runLeftTime, false));
		currentAnimation = animations.get(IDLE_DOWN);

		this.init();
	}

	public void init() {
		rectOffset.x = width / 4f;
		rectOffset.y = 0f;
		speed = 125f;
	}

	public void update(ArrayList<Entity> entities) {
		this.updateCoordinates();
		updateAnimations();
		pollKeyboard();
		updateVelocity();
		updateMovement(entities);
		updateRectangles();
		interact(entities);
		// Logger.log(position.x + ", " + position.y);

		if (!canInteract) {
			MyGuiManager.stop();
		}
	}

	protected void updateRectangles() {
		rect.set(position.x + rectOffset.x, position.y + rectOffset.y, width - 2 * rectOffset.x, height / 4);
		interactionRect.set(position.x + rectOffset.x - width / 4, position.y + rectOffset.y - height / 4, width, height);
		// if (moving) {
		// interactionRect.set(rect.x + rect.width * prevDirection.x, rect.y + rect.height * prevDirection.y, rect.width, rect.height);
		// }
	}

	@Override
	public void interact(ArrayList<Entity> entities) {
		canInteract = false;
		for (Entity e : entities) {
			if (e instanceof Mob) {
				if (e != this) {
					if (interactionRect.overlaps(e.getRect())) {
						if (e.isInteractable()) {
							// sth
							canInteract = true;
							if (Gdx.input.isKeyPressed(Keys.E)) {
								MyGuiManager.initChat(0, e);
							}
							break;
						}
					}
				}
			}
		}
	}

	private void updateAnimations() {
		currentAnimation.update();
		prevAnimation = currentAnimation;
	}

	private void pollKeyboard() {
		float delta = Gdx.graphics.getDeltaTime();
		movementInput();
		actionsInput();
	}

	private void movementInput() {
		direction.x = 0;
		direction.y = 0;
		if (velocity.x == 0 && velocity.y == 0) {
			moving = false;
		}
		if (Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP)) {
			moving = true;
			direction.y = 1;
			prevDirection.y = 1;
		}
		if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)) {
			moving = true;
			direction.x = -1;
			prevDirection.x = -1;
		}
		if (Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)) {
			moving = true;
			direction.y = -1;
			prevDirection.y = -1;
		}
		if (Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)) {
			moving = true;
			direction.x = 1;
			prevDirection.x = 1;
		}

		if (velocity.x == 0) {
			prevDirection.x = 0;
		}
		if (velocity.y == 0) {
			prevDirection.y = 0;
		}

		if (direction.y == 1) {
			currentAnimation = animations.get(RUN_UP);
			prevAnimationIndex = RUN_UP;
		}
		if (direction.y == -1) {
			currentAnimation = animations.get(RUN_DOWN);
			prevAnimationIndex = RUN_DOWN;
		}
		if (direction.x == 1) {
			currentAnimation = animations.get(RUN_RIGHT);
			prevAnimationIndex = RUN_RIGHT;
		}
		if (direction.x == -1) {
			currentAnimation = animations.get(RUN_LEFT);
			prevAnimationIndex = RUN_LEFT;
		}

		if (!moving) {
			switch (prevAnimationIndex) {
				case RUN_UP:
					currentAnimation = animations.get(IDLE_UP);
					break;
				case RUN_DOWN:
					currentAnimation = animations.get(IDLE_DOWN);
					break;
				case RUN_RIGHT:
					currentAnimation = animations.get(IDLE_RIGHT);
					break;
				case RUN_LEFT:
					currentAnimation = animations.get(IDLE_LEFT);
					break;

			}
		}

		if (prevAnimation != currentAnimation) {
			prevAnimation.resetStateTime();
		}
	}

	private void actionsInput() {
		if (canInteract) {

		}
	}

	public void updateVelocity() {
		if (direction.x != 0) {
			velocity.x = speed * direction.x * Gdx.graphics.getDeltaTime();
		} else {
			velocity.x *= friction;
		}

		if (direction.y != 0) {
			velocity.y = speed * direction.y * Gdx.graphics.getDeltaTime();
		} else {
			velocity.y *= friction;
		}
	}

	public void render(SpriteBatch batch) {
		if (currentAnimation.getCurrentFrame() != null) {
			batch.draw(currentAnimation.getCurrentFrame(), position.x, position.y);
		}
	}

	public void renderRect(ShapeRenderer shapeRenderer) {
		shapeRenderer.setColor(getRectColor());
		shapeRenderer.rect(getRect().x, getRect().y, getRect().width, getRect().height);
		shapeRenderer.setColor(Color.GREEN);
		shapeRenderer.rect(getInteractionRect().x, getInteractionRect().y, getInteractionRect().width, getInteractionRect().height);
	}

	public PlayerInfo getPlayerInfo() {
		return playerInfo;
	}

	public MyAnimation getCurrentAnimation() {
		return currentAnimation;
	}

	public float getIdleDownTime() {
		return idleDownTime;
	}

	public void setIdleDownTime(float idleDownTime) {
		this.idleDownTime = idleDownTime;
	}

	public float getIdleRightTime() {
		return idleRightTime;
	}

	public void setIdleRightTime(float idleRightTime) {
		this.idleRightTime = idleRightTime;
	}

	public float getIdleUpTime() {
		return idleUpTime;
	}

	public void setIdleUpTime(float idleUpTime) {
		this.idleUpTime = idleUpTime;
	}

	public float getIdleLeftTime() {
		return idleLeftTime;
	}

	public void setIdleLeftTime(float idleLeftTime) {
		this.idleLeftTime = idleLeftTime;
	}

	public float getRunDownTime() {
		return runDownTime;
	}

	public void setRunDownTime(float runDownTime) {
		this.runDownTime = runDownTime;
	}

	public float getRunRightTime() {
		return runRightTime;
	}

	public void setRunRightTime(float runRightTime) {
		this.runRightTime = runRightTime;
	}

	public float getRunUpTime() {
		return runUpTime;
	}

	public void setRunUpTime(float runUpTime) {
		this.runUpTime = runUpTime;
	}

	public float getRunLeftTime() {
		return runLeftTime;
	}

	public void setRunLeftTime(float runLeftTime) {
		this.runLeftTime = runLeftTime;
	}

}
