package GunzABlazin;

import org.newdawn.slick.Image;

/**
 * @author  aaronmcleod
 */
public class Enemy extends Sprite {
	
	/**
	 * @uml.property  name="name"
	 */
	private String name;
	
	public Enemy(String name, float x, float y, float sizeX, float sizeY, Image graphic) {
		super(x, y, sizeX, sizeY, graphic, null, false);
		this.setName(name);
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
}
