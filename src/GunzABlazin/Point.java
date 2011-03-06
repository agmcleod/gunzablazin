package GunzABlazin;

/**
 * @author  aaronmcleod
 */
public class Point {
	
	/**
	 * @uml.property  name="x"
	 */
	private float x;
	/**
	 * @uml.property  name="y"
	 */
	private float y;
	
	public Point(float x, float y) {
		this.x = x;
		this.y = y;
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
}
