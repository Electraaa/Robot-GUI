package robotGUI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class RobotArena implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4389551849848646804L;
	public Random RandomGenerator;
	int xSize, ySize;						// size of arena
	private ArrayList<Robot> allRobots;		// array list of all robots in arena
	/**
	 * construct an arena
	 */
	RobotArena() {
		this(500, 400);			// default size
	}
	/**
	 * construct arena of size xS by yS
	 * @param xS
	 * @param yS
	 */
	RobotArena(int xS, int yS){
		xSize = xS;
		ySize = yS;
		allRobots = new ArrayList<Robot>();						// list of all robots, initially empty
	}
	
	public ArrayList<Robot> getAllRobots(){
		return allRobots;
	}
	/**
	 * return arena size in x direction
	 * @return
	 */
	public int getXSize() {
		return xSize;
	}
	/**
	 * return arena size in y direction
	 * @return
	 */
	public int getYSize() {
		return ySize;
	}
	public void setXSize(int xbufferparsed) {
		xbufferparsed = xSize;
		return;
	}
	public void setYSize(int ybufferparsed) {
		ybufferparsed = ySize;
		return;
	}
	/**
	 * draw all robots in the arena into interface ri
	 * @param ri
	 */
	public void drawArena(RobotInterface bi) {
		for (Robot r : allRobots) r.drawRobot(bi);		// draw all robots
	}
	
	/**
	 * adjust all robots .. move any moving ones
	 */
	public void adjustRobots() {
		for (Robot r : allRobots)
			r.adjustRobot(this);
	}
	
	/**
	 * return list of strings defining each robot
	 * @return
	 */
	public ArrayList<String> describeAll() {
		ArrayList<String> ans = new ArrayList<String>();			// set up empty arraylist
		for (Robot r : allRobots) ans.add(r.toString());			// add string defining each robot
		return ans;												// return string list
	}
	
	
	public void addRobot() {
		Random rng = new Random();
		int randomAngle = rng.nextInt();
		int randomX = rng.nextInt(xSize - 60) + 30;
		int randomY = rng.nextInt(ySize - 60) + 30;
		allRobots.add(new GameRobot(randomX, randomY, 10, randomAngle, 2));
		
	}
	
	public void addWhiskerRobot() {
		Random rng = new Random();
		int randomAngle = rng.nextInt();
		int randomX = rng.nextInt(xSize - 60) + 30;
		int randomY = rng.nextInt(ySize - 60) + 30;
		int randomWS = rng.nextInt(15) + 20;
		allRobots.add(new WhiskerRobot(randomX, randomY, 10, randomAngle, 2, randomWS));
		
	}
	
	public void addWall() {
		Random rng = new Random();
		int randomX = rng.nextInt(xSize - 60) + 30;
		int randomY = rng.nextInt(ySize - 60) + 30;
		allRobots.add(new Wall(randomX, randomY, 10));
	}
	
	public void addLightRobot() {
		Random rng = new Random();
		int randomX = rng.nextInt(xSize - 60) + 30;
		int randomY = rng.nextInt(ySize - 60) + 30;
		allRobots.add(new LightRobot(randomX, randomY, 10));
		
	}
	public void addLightDetecctorRobot() {
		Random rng = new Random();
		int randomAngle = rng.nextInt();
		int randomX = rng.nextInt(xSize - 60) + 30;
		int randomY = rng.nextInt(ySize - 60) + 30;
		allRobots.add(new LightDetectorRobot(randomX, randomY, 10, randomAngle, 2));
		
	}
	
}
