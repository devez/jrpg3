package game.world;

import game.entities.Entity;
import game.entities.MyCamera;
import game.entities.mobs.Mob;
import game.entities.mobs.Player;
import game.util.Logger;
import game.world.tiles.SpawnTile;
import game.world.tiles.Tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Area {

	private ArrayList<Tile[][]> backgroundTiles;
	private ArrayList<Tile[][]> foregroundTiles;
	private ArrayList<Tile[][]> mapEntities;
	private ArrayList<Entity> entities;

	private int xTiles;
	private int yTiles;

	private float width;
	private float height;

	private MyCamera camera;

	private HashMap<String, Entity> characters;	
	private Entity[] sortedCharacters;

	public Area(HashMap<String, ArrayList<Tile[][]>> tileArraysHashMap, HashMap<String, Entity> characters) {
		this.characters = characters;
		backgroundTiles = tileArraysHashMap.get("background");
		foregroundTiles = tileArraysHashMap.get("foreground");
		mapEntities = tileArraysHashMap.get("entities");
		entities = new ArrayList<Entity>();
	}

	public void init(int xTiles, int yTiles) {
		this.xTiles = xTiles;
		this.yTiles = yTiles;
		width = xTiles * Tile.SIZE;
		height = yTiles * Tile.SIZE;
		initEntityArrayList();
		initCharacters();
	}

	private void initEntityArrayList() {
		for (Tile[][] tileArrays : mapEntities) {
			for (int i = 0; i < xTiles; i++) {
				for (int j = 0; j < yTiles; j++) {
					if (tileArrays[i][j] != null) {
						entities.add(tileArrays[i][j]);
					}
				}
			}
		}
		
		for (Entity e : characters.values()) {
			entities.add(e);
		}
	}

	public void addCharacter(String name, Mob mob) {
		characters.put(name, mob);
	}

	private void initCharacters() {
		for (Entity e : entities) {
			if (e instanceof SpawnTile) {
				for (String name : ((SpawnTile) e).getEntities()) {
					Entity entity = characters.get(name);
					if (entity != null) {
						entity.setPosition(e.getPosition());
						characters.put(name, entity);
					}
				}
			}
		}
	}

	public void update() {
		camera.pointAt(characters.get("player"), width, height);
		for (Entity e : characters.values()) {
			((Mob) e).update(entities);
		}
	}

	public void renderBackround(SpriteBatch batch) {
		render(backgroundTiles, batch);
	}

	public void renderEntities(SpriteBatch batch) {
		sortEntities(characters);
		for (Entity e : sortedCharacters) {
			((Mob)e).render(batch);
		}
//		for (Entity e : characters.values()) {
//			((Mob)e).render(batch);
//
//		}
	}
	
	private void sortEntities(HashMap<String, Entity> characters) {
		Entity[] tempArray = new Entity[characters.size()];
		for (Entity e : characters.values()) {
			Entity temp = e;
			for (int i = 0; i < tempArray.length; i++) {
				if (tempArray[i] != null) {
					if (temp.getPosition().y > tempArray[i].getPosition().y) {
						for (int j = tempArray.length - 2; j >= i; j--) {
							tempArray[j + 1] = tempArray[j];
						}
						tempArray[i] = temp;
						break;
					}
				}
				else {
					tempArray[i] = temp;
					break;
				}
			}
		}

		sortedCharacters = tempArray;
	}

	public void renderForeground(SpriteBatch batch) {
		render(foregroundTiles, batch);
	}

	private void render(ArrayList<Tile[][]> tilesList, SpriteBatch batch) {
		for (Tile[][] tiles : tilesList) {
			renderVisibleTiles(tiles, batch);
		}
	}

	private void renderVisibleTiles(Tile[][] tiles, SpriteBatch batch) {
		for (int i = 0; i < xTiles; i++) {
			for (int j = 0; j < yTiles; j++) {
				checkForExceptions(batch, tiles, i, j);
			}
		}
	}

	private void checkForExceptions(SpriteBatch batch, Tile[][] tiles, int i, int j) {
		int xOffset = (int) 21;
		int yOffset = (int) 12;
		Vector2 xBounds = new Vector2(characters.get("player").getCoordinates().x - xOffset, characters.get("player").getCoordinates().x + xOffset);
		Vector2 yBounds = new Vector2(characters.get("player").getCoordinates().y - yOffset, characters.get("player").getCoordinates().y + yOffset);
		if (tiles[i][j] != null) {
			if ((i > xBounds.x && i < xBounds.y) && (j > yBounds.x && j < yBounds.y)) {
				tiles[i][j].render(batch);
			}
		}
	}

	public void renderBackgroundRects(ShapeRenderer shapeRenderer) {
		renderRect(backgroundTiles, shapeRenderer);
	}

	public void renderEntityRects(ShapeRenderer shapeRenderer) {
		// ((Mob) characters.get("player")).renderRect(shapeRenderer);
		for (Entity e : characters.values()) {
			((Mob) e).renderRect(shapeRenderer);
		}
	}

	public void renderForegroundRects(ShapeRenderer shapeRenderer) {
		renderRect(foregroundTiles, shapeRenderer);
	}

	public void renderMapEntitiesRects(ShapeRenderer shapeRenderer) {
		renderRect(mapEntities, shapeRenderer);
	}

	private void renderRect(ArrayList<Tile[][]> tilesList, ShapeRenderer shapeRenderer) {
		for (Tile[][] tiles : tilesList) {
			for (int i = 0; i < xTiles; i++) {
				for (int j = 0; j < yTiles; j++) {
					if (tiles[i][j] != null) {
						shapeRenderer.setColor(tiles[i][j].getRectColor());
						shapeRenderer.rect(tiles[i][j].getRect().x, tiles[i][j].getRect().y, tiles[i][j].getRect().width, tiles[i][j].getRect().height);
					}
				}
			}
		}
	}

	public void setCamera(MyCamera camera) {
		this.camera = camera;
	}

	public MyCamera getCamera() {
		return camera;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public Player getPlayer() {
		return (Player) characters.get("player");
	}
}
