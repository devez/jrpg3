package game.world.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

public class MapTile extends Tile {

	public MapTile(Cell cell, int i, int j) {
		super(cell, i, j);
	}
	
	public void render(SpriteBatch batch) {
			super.render(batch);
	}

}
