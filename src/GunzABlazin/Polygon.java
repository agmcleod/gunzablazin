package GunzABlazin;
import java.util.ArrayList;

/**
 * Basic class for shape information
 * @author  aaronmcleod
 */
public class Polygon {
	/**
	 * @uml.property  name="x"
	 */
	private float x;
	/**
	 * @uml.property  name="y"
	 */
	private float y;
	/**
	 * @uml.property  name="sizeX"
	 */
	private float sizeX;
	/**
	 * @uml.property  name="sizeY"
	 */
	private float sizeY;
	boolean polygon;
	// array list here for vectors, in the event it is not a rectangle
	private ArrayList<Point> points;
	
	public Polygon(float x, float y, float sizeX, float sizeY) {
		this.setX(x);
		this.setY(y);
		this.setSizeX(sizeX);
		this.setSizeY(sizeY);
		this.polygon = false;
		points = new ArrayList<Point>();
	}
	
	public Polygon(float x, float y, float sizeX, float sizeY, boolean polygon, ArrayList<Point> points) {
		this.setX(x);
		this.setY(y);
		this.setSizeX(sizeX);
		this.setSizeY(sizeY);
		this.polygon = polygon;
		points = new ArrayList<Point>();
	}
	
	public void addPoint(Point point) {
		points.add(point);
	}

	/**
	 * @param x
	 * @uml.property  name="x"
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * @return
	 * @uml.property  name="x"
	 */
	public float getX() {
		return x;
	}

	/**
	 * @param y
	 * @uml.property  name="y"
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * @return
	 * @uml.property  name="y"
	 */
	public float getY() {
		return y;
	}

	/**
	 * @param sizeX
	 * @uml.property  name="sizeX"
	 */
	public void setSizeX(float sizeX) {
		this.sizeX = sizeX;
	}

	/**
	 * @return
	 * @uml.property  name="sizeX"
	 */
	public float getSizeX() {
		return sizeX;
	}

	/**
	 * @param sizeY
	 * @uml.property  name="sizeY"
	 */
	public void setSizeY(float sizeY) {
		this.sizeY = sizeY;
	}

	/**
	 * @return
	 * @uml.property  name="sizeY"
	 */
	public float getSizeY() {
		return sizeY;
	}
	
	public float getRightX() {
		return x + sizeX;
	}
	
	public float getBottomY() {
		return y + sizeY;
	}
	
	public boolean intersects(Polygon p) {
		return ((p.getX() <= getRightX())
				&& (p.getRightX() >= getX())
				&& (p.getY() <= getBottomY())
				&& (p.getBottomY() >= getY()));
	}
	
	public boolean intersectsToTheLeftSide(Polygon p) {
		if((p.getRightX() > getX() && p.getX() < getX()) && (p.getY() < getBottomY()
			&& p.getBottomY() > getY())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean intersectsToTheRightSide(Polygon p) {
		if((p.getX() < getRightX() && p.getRightX() > getRightX()) 
			&& (p.getY() < getBottomY() && p.getBottomY() > getY())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void incrementY(float y) {
		this.setY(this.getY() + y);
	}
	
	public void deIncrementY(float y) {
		this.setY(this.getY() - y);
	}
	
	public void incrementX(float x) {
		this.setX(this.getX() + x);
	}
	
	public void deIncrementX(float x) {
		this.setX(this.getX() - x);
	}
}
