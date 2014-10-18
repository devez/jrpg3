package game.gui;

import game.Project;
import game.util.MyFont;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ChatGui extends GuiEntity {

	public ChatGui() {
		this.texture = new Texture("resources/gui/chatGui0.png");
		font = new MyFont("resources/fonts/13px/font0/font.ttf", 13*2);
		this.position = new Vector2(0, 0);
		this.width = Project.WIDTH;
		this.height = 100;
	}
	
	public void update() {
		
	}
	
	public void render(SpriteBatch guiBatch, String text) {
		guiBatch.draw(texture, position.x, position.y, width, height);
		renderDialog(guiBatch, text);
	}
	
	private void renderDialog(SpriteBatch guiBatch, String text) {
		font.render(guiBatch, text, 10, height - 10);
	}
	
}
