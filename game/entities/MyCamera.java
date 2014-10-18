package game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class MyCamera extends Entity {

	private OrthographicCamera camera;

	public MyCamera(Vector2 position) {
		super(position);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.update();
	}

	public void update() {
		camera.update();
	}

	public void pointAt(Entity entity, float width, float height) {
		this.position.x = entity.position.x;
		this.position.y = entity.position.y;
		if (entity.position.x > width - Gdx.graphics.getWidth() / (2 / camera.zoom) - entity.cameraOffset.x) {
			this.position.x = width - Gdx.graphics.getWidth() / (2 / camera.zoom) - entity.cameraOffset.x;
		}
		if (entity.position.x < Gdx.graphics.getWidth() / (2 / camera.zoom) - entity.cameraOffset.x) {
			this.position.x = Gdx.graphics.getWidth() / (2 / camera.zoom) - entity.cameraOffset.x;
		}
		if (entity.position.y > height - Gdx.graphics.getHeight() / (2 / camera.zoom) - entity.cameraOffset.y) {
			this.position.y = height - Gdx.graphics.getHeight() / (2 / camera.zoom) - entity.cameraOffset.y;
		}
		if (entity.position.y < Gdx.graphics.getHeight() / (2 / camera.zoom) - entity.cameraOffset.y) {
			this.position.y = Gdx.graphics.getHeight() / (2 / camera.zoom) - entity.cameraOffset.y;
		}
		this.setPosition(position.x + entity.cameraOffset.x, position.y + entity.cameraOffset.y);
	}

	public void setPosition(Vector2 position) {
		camera.position.x = position.x;
		camera.position.y = position.y;
	}

	public void setPosition(float x, float y) {
		camera.position.x = x;
		camera.position.y = y;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

}
