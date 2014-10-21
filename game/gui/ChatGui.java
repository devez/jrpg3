package game.gui;

import game.Project;
import game.entities.Entity;
import game.entities.mobs.Npc;
import game.util.MyFont;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ChatGui extends GuiEntity {
	
	private Entity entity;

	public ChatGui() {
		this.texture = new Texture("resources/gui/chatGui0.png");
		font = new MyFont("resources/fonts/13px/font0/font.ttf", 13*2);
		this.position = new Vector2(0, 0);
		this.width = Project.WIDTH;
		this.height = 100;
		entity = null;
	}
	
	public void init(int id, Entity e) {
		entity = e;
	}
	
	public void update() {
		
	}
	
	public void render(SpriteBatch guiBatch) {
		guiBatch.draw(texture, position.x, position.y, width, height);
		renderDialog(guiBatch, entity);
	}
	
	private void renderDialog(SpriteBatch guiBatch, Entity e) {
		font.render(guiBatch, ((Npc) e).getTempText(), 10, height - 10);
	}
	
}
