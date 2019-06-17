package robotGUI;

import java.io.Serializable;

public abstract class Robot implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6977896756405176270L;
	protected double x, y, rad;						// position and size of robot
	protected char col;								// used to set colour
	static int robotCounter = 0;						// used to give each robot a unique identifier
	protected int robotID;							// unique identifier for item

	/**
	 * construct a robot of radius ir at ix,iy
	 * @param ix
	 * @param iy
	 * @param ir
	 */
	Robot (double ix, double iy, double ir) {
		x = ix;
		y = iy;
		rad = ir;
		robotID = robotCounter++;			// set the identifier and increment class static 
		col = 'r';
	}
	/**
	 * return x position
	 * @return
	 */
	public double getX() { return x; }
	/**
	 * return y position
	 * @return
	 */
	public double getY() { return y; }	
	/**
	 * return radius of robot
	 * @return
	 */
	public double getRad() { return rad; }
	/** 
	 * set the robot at position nx,ny
	 * @param nx
	 * @param ny
	 */
	public void setXY(double nx, double ny) {
		x = nx;
		y = ny;
	}
	/**
	 * return the identity of robot
	 * @return
	 */
	public int getID() {return robotID; }
	/**
	 * draw a robot into the interface bi
	 * @param bi
	 */
	public abstract void drawRobot(RobotInterface ri);
	
	protected String getStrType() {
		return "Robot";
	}
	/** 
	 * return string describing robot
	 */
	public String toString() {
		return getStrType()+" at "+Math.round(x)+", "+Math.round(y);
	}
	
	/**
	 * abstract method for adjusting a robot (moving it)
	 */
	protected abstract void adjustRobot(RobotArena arena);
	
}
