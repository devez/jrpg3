package game.world.tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

public class SolidTile extends Tile {

	public SolidTile(Cell cell, int i, int j) {
		super(cell, i, j);
		solid = true;
		this.rectColor = Color.RED;
//		while (keys.hasNext()) {
//		String key = keys.next();
//		initProperty(key, (String) properties.get(key));
	//}

	}
	
//	private void initProperty(String key, String value) {
//	switch (key) {
//		case "solid":
//			solid = true;
//			rectColor = Color.RED;
//			// System.out.println(value);
//			break;
//		default:
//			// System.out.println(value);
//			break;
//	}
//}

}
