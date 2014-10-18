package game.managers;

import game.Project;
import game.entities.Entity;
import game.entities.mobs.Npc;
import game.gui.ChatGui;
import game.gui.GuiEntity;
import game.util.Logger;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGuiManager extends Manager {

	public static final int CHATGUI = 0;

	private SpriteBatch guiBatch;

	private static GuiEntity currentGuiEntity;
	private static Entity e = null;
	
	private static GuiEntity chat;

	public MyGuiManager(Project project) {
		super(project);
		this.guiBatch = new SpriteBatch();
		
		//prevent memory leak by instantiating the object only 1 time
		chat = new ChatGui();
	}

	@Override
	public void create() {

	}

	public static void init(int id, Entity entity) {
		currentGuiEntity = chat;
		e = entity;
	}

	@Override
	public void dispose() {
		guiBatch.dispose();
	}

	public void update() {
//		if (e != null) {
//			Logger.log();
//		}
	}

	public void render() {
		guiBatch.begin();
		if (currentGuiEntity != null) {
			if (e != null) {
				currentGuiEntity.render(guiBatch, ((Npc) e).getTempText());
			}
		}
		guiBatch.end();
	}

	public void setCurrentGuiEntity(GuiEntity guiEntity) {
		if (currentGuiEntity == null) {
			this.currentGuiEntity = guiEntity;
		}
	}

	public static void stop() {
		currentGuiEntity = null;
		e = null;
	}

}
