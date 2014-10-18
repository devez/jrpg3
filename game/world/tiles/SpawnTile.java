package game.world.tiles;

import game.util.Logger;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

public class SpawnTile extends Tile {

	private ArrayList<String> entities;

	public SpawnTile(Cell cell, int i, int j) {
		super(cell, i, j);

		entities = new ArrayList<String>();
		Iterator<String> properties = cell.getTile().getProperties().getKeys();
		
		while (properties.hasNext()) {
			String property = properties.next();
			initProperty(property, (String) cell.getTile().getProperties().get(property));
		}

	}

	protected void initProperty(String property, String value) {
		switch (property) {
			case "entity":
				Logger.log(value);
				entities.add(value);
				break;
			default:
				//Logger.log(value);
				break;
		}
	}
	
	public ArrayList<String> getEntities() {
		return entities;
	}

}

// while (keys.hasNext()) {
// String key = keys.next();
// initProperty(key, (String) properties.get(key));
// }

// private void initProperty(String key, String value) {
// switch (key) {
// case "solid":
// solid = true;
// rectColor = Color.RED;
// // System.out.println(value);
// break;
// default:
// // System.out.println(value);
// break;
// }
// }
