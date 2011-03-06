package GunzABlazin;

import java.util.ArrayList;

import org.newdawn.slick.Image;

/**
 * @author  aaronmcleod
 */
public class Sprite extends Polygon {
	
	/**
	 * @uml.property  name="graphic"
	 */
	private Image graphic;
	
	public Sprite(float x, float y, float sizeX, float sizeY, Image graphic) {
		super(x, y, sizeX, sizeY);
		this.graphic = graphic;
	}
	
	public Sprite(float x, float y, float sizeX, float sizeY, Image graphic, ArrayList<Point> points
		, boolean polygon) {
		super(x, y, sizeX, sizeY, polygon, points);
		this.graphic = graphic;
	}

	/**
	 * @param graphic
	 * @uml.property  name="graphic"
	 */
	public void setGraphic(Image graphic) {
		this.graphic = graphic;
	}

	/**
	 * @return
	 * @uml.property  name="graphic"
	 */
	public Image getGraphic() {
		return graphic;
	}
}
