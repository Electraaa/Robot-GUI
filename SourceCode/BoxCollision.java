package robotGUI;

public class BoxCollision {

	/**
	 * Author Ilektra Pavlopoulou
	 */
	
	private double boxoneX; //declare first robot to compare x 
	private double boxtwoX; //declare second robot to compare x
	private double boxoneY; //declare first robot to compare y
	private double boxtwoY; //declare second robot to compare y
	private double boxoneWidth; //declare first robot width
	private double boxtwoWidth; //declare second robot width
	private double boxoneHeight; //declare first robot height
	private double boxtwoHeight; //declare second robot height
	
	/**
	 * Constructor for the BoxCollision class
	 * @param x first box x position
	 * @param x2 second box x position
	 * @param y first box y position
	 * @param y2 second box y position
	 * @param width first box width
	 * @param width2 second box width
	 * @param height first box height
	 * @param height2 second box height
	 */
	public BoxCollision(double x, double x2, double y, double y2, double width, double width2, double height, double height2){
		boxoneX = x;
		boxtwoX = x2;
		boxoneY = y;
		boxtwoY = y2;
		boxoneWidth = width;
		boxtwoWidth = width2;
		boxoneHeight = height;
		boxtwoHeight = height2;
		
	}
	
	/**
	 * Function to check the collision between two boxes. 
	 * @return True if there is a collision on both the x and y planes.
	 */
	
	public boolean checkCollision(){
		
		double leftx;
		double leftwidth;
		double rightx;
		double rightwidth;
		double topy;
		double topheight;
		double lowery;
		double lowerheight;
		
		if(boxoneX < boxtwoX){ //if first robot has a smaller x coord than second
			leftx = boxoneX; //set left robot to boxone
			rightx = boxtwoX; //set right robot to boxtwo
			leftwidth = boxoneWidth;
			rightwidth = boxtwoWidth;
		}
		else { //invert
			leftx = boxtwoX;
			rightx = boxoneX;
			leftwidth = boxtwoWidth;
			rightwidth = boxoneWidth;
		}
		
		boolean xColl = leftx + leftwidth > rightx - rightwidth; //condition for collision
		
		if (boxoneY < boxtwoY){ //if first robot has smaller y coord than second
			topy = boxoneY; //set top y to first robot
			lowery = boxtwoY; //set lower y to second robot
			topheight = boxoneHeight;
			lowerheight = boxtwoHeight;
		}else { //invert
			topy = boxtwoY; 
			lowery = boxoneY;
			topheight = boxtwoHeight;
			lowerheight = boxoneHeight;
		}
			
		boolean yColl = topy + topheight > lowery - lowerheight; //condition for collision
			
		
		return xColl && yColl; //if both cases are colliding return true
	}
}