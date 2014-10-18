package game.world.tiles;

import game.util.Logger;

import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;

public class TileFactory {

	public TileFactory() {

	}

	private TextureRegion textureRegion;

	// private int counter = 0;

	public Tile create(Cell cell, int i, int j) {
		Tile tile = null;
		if (cell != null) {
			MapProperties properties = cell.getTile().getProperties();

			String tileType = (String) properties.get("type");
			if (tileType == null) {
				tileType = "default";
			}

			tile = createTile(tileType, cell, i, j);
		}
		return tile;
	}

	private Tile createTile(String tileType, Cell cell, int i, int j) {
		Tile tile = null;
		// counter++;
		// if (counter == 1) {
		// this.textureRegion = textureRegion;
		// }
		// if (counter == 2) {
		// if (this.textureRegion == textureRegion) {
		// Logger.log("TRUE");
		// }
		// else {
		// Logger.log("FALSE");
		// }
		// }

		switch (tileType) {
			case "solidTile":
				tile = new SolidTile(cell, i, j);
				break;
			case "spawnTile":
				tile = new SpawnTile(cell, i, j);
				break;
			default:
				tile = new MapTile(cell, i, j);
				break;
		}

		return tile;
	}

}
