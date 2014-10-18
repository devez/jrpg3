package game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyAnimation {
	private int cols;
	private int rows;

	private Animation animation;
	private Texture sheet;
	private TextureRegion[] frames;
	private TextureRegion currentFrame;

	private float stateTime;
	private float animationTime;

	private int currX;
	private int currY;

	private boolean looping = true;

	public MyAnimation(String path, int cols, int rows, float time, boolean flipX) {
		this.cols = cols;
		this.rows = rows;
		this.animationTime = time;

		sheet = new Texture(path);

		TextureRegion[][] tmp = TextureRegion.split(sheet, (sheet.getWidth()) / cols, (sheet.getHeight()) / rows);
		frames = new TextureRegion[cols * rows];
		int index = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				frames[index] = tmp[i][j];
				if (flipX) {
					frames[index].flip(true, false);
				}
				index++;
			}
		}
		animation = new Animation(animationTime, frames);
	}

	public void update() {
		stateTime += Gdx.graphics.getDeltaTime();
		currentFrame = animation.getKeyFrame(stateTime, looping);
		if (looping) {
			if (animation.isAnimationFinished(stateTime)) {
				stateTime = 0;
			}
		}
	}

	public TextureRegion getCurrentFrame() {
		return animation.getKeyFrame(stateTime, looping);
	}

	public Animation getAnimation() {
		return animation;
	}

	public float getStateTime() {
		return stateTime;
	}

	public float getAnimationTime() {
		return animationTime;
	}

	public void setAnimation(Animation tmp) {
		this.animation = tmp;
	}

	public TextureRegion[] getFrames() {
		return frames;
	}

	public int getCurrX() {
		return currX;
	}

	public int getCurrY() {
		return currY;
	}

	public void stopLooping() {
		looping = false;
	}

	public void startLooping() {
		looping = true;
	}

	public void resetStateTime() {
		stateTime = 0f;
	}

	public void setAnimationTime(float time) {
		this.animationTime = time;
		this.animation = new Animation(animationTime, frames);
	}
}
