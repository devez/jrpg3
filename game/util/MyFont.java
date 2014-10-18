package game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class MyFont {
	
	private BitmapFont font;
	private int size;
	
	public MyFont(String path, int size) {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(path));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		this.size = size;
		parameter.size = size;
		font = generator.generateFont(parameter);
		generator.dispose();
	}
	
	public void render(SpriteBatch batch, String text, int x, int y) {
		//font.setColor(Color.WHITE);
		font.drawMultiLine(batch, text, x, y);
	}
}
