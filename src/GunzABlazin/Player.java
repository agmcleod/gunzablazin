package GunzABlazin;

import org.newdawn.slick.Image;

public class Player extends Sprite {
	private int lives = 5;
	public Player(float x, float y, float sizeX, float sizeY, Image graphic) {
		super(x, y, sizeX, sizeY, graphic, null, false);
	}
	
	public int getLives() {
		return lives;
	}
	
	public void setLives(int life) {
		this.lives = life;
	}
	
	public void addLife() {
		addLife(1);
	}
	
	public void addLife(int value) {
		this.lives += value;
	}
	
	public void removeLife() {
		removeLife(1);
	}
	
	public void removeLife(int value) {
		this.lives -= value;
	}
	
	public int removeAndReturnLives() {
		return removeAndReturnLives(1);
	}
	
	public int removeAndReturnLives(int value) {
		this.lives -= value;
		return getLives();
	}
	
	public int addAndReturnLives(int value) {
		this.lives += value;
		return getLives();
	}
	
	public int addAndReturnLives() {
		return addAndReturnLives(1);
	}
}
