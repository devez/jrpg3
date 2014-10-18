package game.world;

import game.Project;
import game.entities.MyCamera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class World {

	private Project project;

	private ShapeRenderer shapeRenderer;
	private SpriteBatch batch;
	private MyCamera camera;

	private Area currentArea;

	private boolean renderShapes = true;

	public World(Project project) {
		shapeRenderer = new ShapeRenderer();
		this.project = project;
	}

	public void init(SpriteBatch batch, MyCamera camera) {
		this.batch = batch;
		this.camera = camera;
	}

	public void update() {
		currentArea.setCamera(camera);
		currentArea.update();
		camera = currentArea.getCamera();
		camera.getCamera().zoom = 0.5f;
		camera.update();
	}

	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//depthBuffer
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		renderArea();
		renderRects();
	}

	private void renderArea() {
		batch.setProjectionMatrix(camera.getCamera().combined);
		batch.begin();
		currentArea.renderBackround(batch);
		currentArea.renderEntities(batch);
		currentArea.renderForeground(batch);
		batch.end();
	}

	private void renderRects() {
		if (renderShapes) {
			shapeRenderer.setProjectionMatrix(camera.getCamera().combined);
			shapeRenderer.begin(ShapeType.Line);
			// currentArea.renderBackgroundRects(shapeRenderer);
			currentArea.renderEntityRects(shapeRenderer);
			// currentArea.renderForegroundRects(shapeRenderer);
			currentArea.renderMapEntitiesRects(shapeRenderer);
			shapeRenderer.end();
		}
	}

	public void setCurrentArea(Area currentArea) {
		this.currentArea = currentArea;
	}

	public Area getCurrentArea() {
		return currentArea;
	}

	public void toggleShapeRendering() {
		renderShapes = !renderShapes;
	}

}
