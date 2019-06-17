package robotGUI;

import java.io.Serializable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class GameRobot extends Robot implements Serializable{

	/**
	 * @author Ilektra Pavlopoulou 
	 */
	private static final long serialVersionUID = -597415466112396758L;
		double rAngle, rSpeed;			// Angle and speed of travel
		
		/** Create game robot, size ir ay ix,iy, moving at Angle ia and speed is
		 * @param ix
		 * @param iy
		 * @param ir
		 * @param ia
		 * @param is
		 */
		public GameRobot(double ix, double iy, double ir, double ia, double is) {
			super(ix, iy, ir);
			rAngle = ia;
			rSpeed = is;
		}

		/**	
		 * adjustRobot
		 * Here, move robot depending on speed and Angle
		 */
		@Override
		protected void adjustRobot(RobotArena arena) {
			
			int arenaX = arena.getXSize();
			int arenaY = arena.getYSize();
			int collisionCount = 0;
			
			for(Robot r: arena.getAllRobots()) {
				if (new BoxCollision(r.x, this.x, r.y, this.y, r.rad, this.rad, r.rad, this.rad).checkCollision()) {
					collisionCount++;
				}
			}
			
			if (y <= 0 + rad + rSpeed 
					|| y >= arenaY - rad - rSpeed 
					|| x <= 0 + rad + rSpeed 
					|| x >= arenaX - rad - rSpeed
					|| collisionCount > 1) 
					
			{
				
				x -= rSpeed * Math.cos(getAngleRad());		
				y -= rSpeed * Math.sin(getAngleRad());
				rAngle += 90;
			}
			
		
			x += rSpeed * Math.cos(getAngleRad());		// new X position
			y += rSpeed * Math.sin(getAngleRad());		// new Y position
		}
		/**
		 * return string defining robot type
		 */
		protected String getStrType() {
			return "Robot";
		}
		/**
		 * return the angle in rads
		 * @return
		 */
		public double getAngleRad() {
			return rAngle * (Math.PI / 180);
		}
		/**
		 * function that returns the angle in degrees
		 */
		public double getAngleDegrees() {
			return rAngle;
		}
		@Override
		/**
		 * function that draws robot
		 */
		public void drawRobot(RobotInterface ri) {
			GraphicsContext gc = ri.getgc();
			
			double Angle = getAngleRad();
			
			gc.setFill(ri.colFromChar(col));
			
			gc.fillArc(x - rad, y - rad, rad * 2, rad * 2, 0, 360, ArcType.ROUND);
			
			gc.setStroke(Color.BLACK);
			
			gc.setLineWidth(4);
			
					//draw the right wheel on the robot
					gc.strokeLine(
							x + (rad * Math.cos(Angle + Math.PI/2)) + (rad * Math.cos(Angle)), 
							y + (rad * Math.sin(Angle + Math.PI/2)) + (rad * Math.sin(Angle)),
							x + (rad * Math.cos(Angle + Math.PI/2)) + (rad * Math.cos(Angle + Math.PI)),
							y + (rad * Math.sin(Angle + Math.PI/2)) + (rad * Math.sin(Angle + Math.PI))
							); 
					
					//draw the left wheel on the robot
					gc.strokeLine(
							x + (rad * Math.cos(Angle - Math.PI/2)) + (rad * Math.cos(Angle)), 
							y + (rad * Math.sin(Angle - Math.PI/2)) + (rad * Math.sin(Angle)),
							x + (rad * Math.cos(Angle - Math.PI/2)) + (rad * Math.cos(Angle + Math.PI)),
							y + (rad * Math.sin(Angle - Math.PI/2)) + (rad * Math.sin(Angle + Math.PI))
							);
		}
		

}
