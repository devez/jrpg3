package game.managers;

import game.Project;
import game.entities.Entity;
import game.util.Logger;
import game.util.MyMapLoader;
import game.world.Area;

import java.util.HashMap;
import java.util.Set;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class MyLevelManager extends Manager {

	private HashMap<String, String> areaIds;
	private HashMap<String, Area> areas;
	private MyMapLoader mapLoader;
	private HashMap<String, Entity> characters;

	public MyLevelManager(Project project, HashMap<String, Entity> characters) {
		super(project);
		this.characters = characters;
		this.create();
	}

	@Override
	public void create() {
		areaIds = new HashMap<String, String>();
		areas = new HashMap<String, Area>();
		mapLoader = new MyMapLoader();
		initAreaIds();
		initAllMaps();
	}
	
	private void initAreaIds() {
		areaIds.put("area0", "resources/maps/area0/area0.tmx");
	}

	private void initAllMaps() {
		//initMap("testMap", "resources/maps/testMap.tmx");
		//initMap("testMap2", "resources/maps/testMap2/testMap2.tmx");
	
		for (String name : areaIds.keySet()) {
			Logger.log("Name: " + name + "\t Path: " + areaIds.get(name));
			initMap(name, areaIds.get(name), characters);
		}
		//initMap("area0", "resources/maps/area0/area0.tmx", characters);
	}

	private void initMap(String name, String path, HashMap<String, Entity> characters) {
		TiledMap map = new TmxMapLoader().load(path);
		Area area =  new Area(new MyMapLoader().tmxToTileArrayHashMap(map), characters);
		area.init(map.getProperties().get("width", Integer.class), map.getProperties().get("height", Integer.class));
		areas.put(name, area);
	}
	
	public Set<String> getAreaNames() {
		return areaIds.keySet();
	}
	
	public Area getArea(String name) {
		return areas.get(name);
	}

	@Override
	public void dispose() {
		System.out.println(this.getClass().getSimpleName() + ": " + "disposed");
	}

}
