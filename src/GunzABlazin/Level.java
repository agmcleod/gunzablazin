package GunzABlazin;

import java.util.ArrayList;
import java.lang.ArrayIndexOutOfBoundsException;

import org.newdawn.slick.Image;

/**
 * @author  aaronmcleod
 */
public class Level {
	/**
	 * @uml.property  name="grounds"
	 */
	private ArrayList<Sprite> grounds;
	/**
	 * @uml.property  name="enemies"
	 */
	private ArrayList<Enemy> enemies;
	/**
	 * @uml.property  name="name"
	 */
	private String name;
	
	private float furthestX = 0;
	
	public Level(String name) {
		this.setName(name);
		setGrounds(new ArrayList<Sprite>());
		setEnemies(new ArrayList<Enemy>());
	}

	/**
	 * @param grounds
	 * @uml.property  name="grounds"
	 */
	public void setGrounds(ArrayList<Sprite> grounds) {
		this.grounds = grounds;
	}
	
	public void addGround(Sprite ground) {
		grounds.add(ground);
		float i = ground.getRightX();
		if(i > furthestX) {
			furthestX = i;
		}
	}

	/**
	 * @return
	 * @uml.property  name="grounds"
	 */
	public ArrayList<Sprite> getGrounds() {
		return grounds;
	}

	/**
	 * @param enemies
	 * @uml.property  name="enemies"
	 */
	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}

	/**
	 * @return
	 * @uml.property  name="enemies"
	 */
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	/**
	 * @param name
	 * @uml.property  name="name"
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}
	
	public float furthestXPointInLevel() {
		return furthestX;
	}
	
	public Sprite getLast() {
		try {
			return grounds.get(grounds.size()-1);
		}
		catch(ArrayIndexOutOfBoundsException ex) {
			System.err.println("Could not get last element of: " + this.name);
			return null;
		}
	}
}
