package game.world.tiles;

import game.Project;
import game.entities.Entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Tile extends Entity {

	public static int SIZE = 32;

	protected TextureRegion textureRegion;

	protected boolean solid = false;

//	public Tile(TextureRegion textureRegion, Vector2 position) {
//		super(position);
//		this.textureRegion = textureRegion;
//		width = size.x * Tile.SIZE;
//		height = size.y * Tile.SIZE;
//		this.rect = new Rectangle(position.x, position.y, width, height);
//	}

	public Tile(Cell cell, int i, int j) {
		super(new Vector2(i * cell.getTile().getTextureRegion().getRegionWidth(), j * cell.getTile().getTextureRegion().getRegionHeight()));
		this.textureRegion = cell.getTile().getTextureRegion();
		width = size.x * Tile.SIZE;
		height = size.y * Tile.SIZE;
		this.rect = new Rectangle(position.x, position.y, width, height);
	}

	public void render(SpriteBatch batch) {
		batch.draw(textureRegion, position.x, position.y, width * Project.SIZE, height * Project.SIZE);
	}

	public boolean isSolid() {
		return solid;
	}
		
	protected void initProperty(String property, String value) {
		
	}

}
