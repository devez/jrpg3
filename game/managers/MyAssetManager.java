package game.managers;

import game.Project;
import game.entities.Entity;
import game.entities.MyCamera;
import game.entities.mobs.Npc;
import game.entities.mobs.Player;
import game.states.screens.InGame;
import game.states.screens.MyScreen;
import game.util.Logger;
import game.world.Area;
import game.world.World;

import java.util.HashMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class MyAssetManager extends Manager {

	private MyScreenManager screenManager;
	private MyLevelManager levelManager;
	private AssetManager assetManager;
	private MyInputManager inputManager;
	private MyXmlManager xmlManager;
	private MyGuiManager guiManager;
	
	private SpriteBatch batch;
	private MyCamera camera;
	
	private World world;
	private Player player;
	
	private HashMap<String, Entity> characters;
	
	public MyAssetManager(Project project) {
		super(project);
		characters = new HashMap<String, Entity>();
		this.create();
	}

	public void create() {
		batch = new SpriteBatch();
		camera = new MyCamera(new Vector2(0,0));
		world = new World(project);
		player = createPlayer(0, 0);
		
		characters.put("npc1", new Npc(new Vector2(100, 100), "gr8 game m8 i r8 8/8"));
		//characters.put("npc1", new Npc(new Vector2(100, 100), "npc8"));
		//characters.put("npc2", new Npc(new Vector2(110, 120), "npc2"));
		
		xmlManager = new MyXmlManager(project);
		guiManager = new MyGuiManager(project);
		
		//guiManager.setCurrentGuiEntity(new ChatGui());

		screenManager = new MyScreenManager(project);
		levelManager = new MyLevelManager(project, characters);
		inputManager = new MyInputManager(project);
		//assetManager = new AssetManager();
		
		createEntityInstances();
		
		this.init();
	}
	
	private Player createPlayer(int x, int y) {
		Player player = new Player(new Vector2(x, y));
		characters.put("player", player);		
		return player;
	}
	
	private void createEntityInstances() {
		//for each area
		for (String name : levelManager.getAreaNames()) {
			//create characters, according to xmlManager and SpawnTile's entities
			Logger.log(levelManager.getArea(name) + " ");
		}
	}

	public void init() {
		initPlayer();
		initWorld();
		initScreens();
	}
	
	private void initPlayer() {
		//init
		player.getPlayerInfo().setCurrentAreaName("area0");
	}
	
	private void initWorld() {
		//get last area from save?
		Area currentArea = levelManager.getArea(player.getPlayerInfo().getCurrentAreaName());
		//currentArea.addCharacter("player", player);
		world.init(batch, camera);
		world.setCurrentArea(currentArea);		
	}
	
	private void initScreens() {
		screenManager.init(assetManager);
		this.setScreen(MyScreen.INGAME);
		((InGame) screenManager.getCurrentScreen()).setWorld(world);
	}
	
	public void setScreen(int id) {
		screenManager.setScreen(id);
	}

	public MyScreen getScreen(int id) {
		return screenManager.getScreen(id);
	}
	
	public MyLevelManager getLevelManager() {
		return levelManager;
	}
	
	public MyGuiManager getGuiManager() {
		return guiManager;
	}
	
	
	public World getWorld() {
		return world;
	}

	@Override
	public void dispose() {
		screenManager.dispose();
		levelManager.dispose();
		System.out.println(this.getClass().getSimpleName() + ": " + "disposed");
	}

	public MyScreen getCurrentScreen() {
		return screenManager.getCurrentScreen();
	}

}
