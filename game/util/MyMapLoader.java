package game.util;

import game.world.tiles.Tile;
import game.world.tiles.TileFactory;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

public class MyMapLoader {

	private HashMap<String, ArrayList<Tile[][]>> tileArraysHashMap;
	private TileFactory tileFactory;

	public MyMapLoader() {
		tileArraysHashMap = new HashMap<String, ArrayList<Tile[][]>>();
		tileArraysHashMap.put("background", new ArrayList<Tile[][]>());
		tileArraysHashMap.put("foreground", new ArrayList<Tile[][]>());
		tileArraysHashMap.put("entities", new ArrayList<Tile[][]>());
		tileFactory = new TileFactory();
	}

	public HashMap<String, ArrayList<Tile[][]>> tmxToTileArrayHashMap(TiledMap map) {
		MapLayers layers = map.getLayers();
		for (MapLayer layer : layers) {
			if (layer instanceof TiledMapTileLayer) {
				TiledMapTileLayer tileLayer = (TiledMapTileLayer) layer;
				Tile[][] tiles = new Tile[tileLayer.getWidth()][tileLayer.getHeight()];

				tiles = initTileArray(tileLayer, tiles);
				addTileArrayToList(tileLayer, tiles);
			}
		}
		return tileArraysHashMap;
	}

	private Tile[][] initTileArray(TiledMapTileLayer tileLayer, Tile[][] tiles) {
		Cell cell = null;
		for (int i = 0; i < tileLayer.getWidth(); i++) {
			for (int j = 0; j < tileLayer.getHeight(); j++) {
				cell = tileLayer.getCell(i, j);
				tiles[i][j] = tileFactory.create(cell, i, j);
			}
		}
		return tiles;
	}

//	private MapTile createMapTile(Cell cell, int i, int j) {
//		if (cell != null) {
//			TextureRegion textureRegion = cell.getTile().getTextureRegion();
//			Vector2 position = new Vector2(i * textureRegion.getRegionWidth(), j * textureRegion.getRegionHeight());
//			MapProperties properties = cell.getTile().getProperties();
//			return new MapTile(textureRegion, position, properties);
//		}
//		return null;
//	}

	private void addTileArrayToList(MapLayer layer, Tile[][] tiles) {
		if (layer.getName().contains("background")) {
			tileArraysHashMap.get("background").add(tiles);
		} else if (layer.getName().contains("foreground")) {
			tileArraysHashMap.get("foreground").add(tiles);
		} else if (layer.getName().contains("entities")) {
			tileArraysHashMap.get("entities").add(tiles);
		} else {
			System.out.println(layer.getName());
		}
	}

	public ArrayList<Tile[][]> getBackgroundTileArraysList() {
		return tileArraysHashMap.get("background");
	}

	public ArrayList<Tile[][]> getForegroundTileArraysList() {
		return tileArraysHashMap.get("foreground");
	}

}
