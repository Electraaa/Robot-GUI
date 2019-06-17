package robotGUI;

import java.io.Serializable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class WhiskerRobot extends GameRobot implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 996102061219845271L;
	private double whiskerSize;
	public WhiskerRobot(double ix, double iy, double ir, double ia, double is, double ws) {
		super(ix, iy, ir, ia, is);
		whiskerSize = ws;
		
		col = 'g';
	
	}
	
	protected void adjustRobot(RobotArena arena) {
		int arenax = arena.getXSize();
		int arenay = arena.getYSize();
		double angle = getAngleRad();
		
		double rwsx = x + (rad * Math.cos(angle + Math.PI/4));
		double rwsy = y + (rad * Math.sin(angle + Math.PI/4));
		double rwex = x + ((rad + whiskerSize) * Math.cos(angle + Math.PI/4));
		double rwey = y + ((rad + whiskerSize) * Math.sin(angle + Math.PI/4));
		
		double lwsx = x + (rad * Math.cos(angle - Math.PI/4));
		double lwsy = y + (rad * Math.sin(angle - Math.PI/4));
		double lwex = x + ((rad + whiskerSize) * Math.cos(angle - Math.PI/4));
		double lwey = y + ((rad + whiskerSize) * Math.sin(angle - Math.PI/4));
		
		
		//robot collisions
		int numCollisionsCtr = 0; //counter for number of collisions
		for (Robot r: arena.getAllRobots()){ //for each robot in the arena
			if(new BoxCollision(r.x, this.x, r.y, this. y, r.rad, this.rad, r.rad, this.rad).checkCollision()) { //check collisions
				numCollisionsCtr++; //increment counter
			}
			if(new LineBoxCollision(r.x, r.y, r.rad, r.rad, rwsx, rwex, rwsy, rwey).CheckCollision()){ //robot collision
				rAngle -= 3;
			}
			if(new LineBoxCollision(r.x, r.y, r.rad, r.rad, lwsx, lwex, lwsy, lwey).CheckCollision()){ //robot collision
				rAngle += 3;
			}
			if(new LineBoxCollision(0, 0, arenax, 5, lwsx, lwex, lwsy, lwey).CheckCollision()){ //top border
				rAngle += 3; 
			}
			if(new LineBoxCollision(0, 0, arenax, 5, rwsx, rwex, rwsy, rwey).CheckCollision()){ //top border
				rAngle -=3;
			}
			if(new LineBoxCollision(0, arenay, arenax, 5, lwsx, lwex, lwsy, lwey).CheckCollision()){ //bottom border
				rAngle += 3; 
			} 
			if(new LineBoxCollision(0, arenay, arenax, 5, rwsx, rwex, rwsy, rwey).CheckCollision()){ //bottom border
				rAngle -=3;
			}
			if(new LineBoxCollision(0, arenay, 5, arenay, lwsx, lwex, lwsy, lwey).CheckCollision()){ //left border
				rAngle += 3; 
			} 
			if(new LineBoxCollision(0, arenay, 5, arenay, rwsx, rwex, rwsy, rwey).CheckCollision()){ //left border
				rAngle -= 3; 
			} 
			if(new LineBoxCollision(arenax, arenay, 5, arenay, lwsx, lwex, lwsy, lwey).CheckCollision()){ //right border
				rAngle += 3; 
			} 
			if(new LineBoxCollision(arenax, arenay, 5, arenay, rwsx, rwex, rwsy, rwey).CheckCollision()){ //right border
				rAngle -= 3; 
			}
			
		}
		
	
		//arena boundary collision or robot collision
		if (y <= 0 + rad + rSpeed || y >= arenay - rad - rSpeed || x <= 0 + rad + rSpeed|| x >= arenax - rad - rSpeed || numCollisionsCtr > 1){
			//turn 90 deg clockwise if hitting a wall
			x -= rSpeed * Math.cos(getAngleRad()); // back up when hitting wall
			y -= rSpeed * Math.sin(getAngleRad()); // back up when hitting wall
			
			rAngle += 90;
		}
	
		
		x += rSpeed * Math.cos(getAngleRad()); // change in x position
		y += rSpeed * Math.sin(getAngleRad()); //change in y position
	}
	/**
	 * return string defining ball type
	 */
	protected String getStrType() {
		return "Whisker Robot";
	}
	
	public double getAngleRad() {
		return rAngle * (Math.PI / 180);
	}

	public double getAngleDegrees() {
		return rAngle;
	}
	@Override
	public void drawRobot(RobotInterface ri) {
		super.drawRobot(ri);
		
		GraphicsContext gc = ri.getgc();
		
		double Angle = getAngleRad();
		
		gc.setStroke(Color.BLACK);
		
		gc.setLineWidth(4);
		
				//draw the right whiskers on the robot
				gc.strokeLine(
						x + (rad * Math.cos(Angle + Math.PI/4)),
						y + (rad * Math.sin(Angle + Math.PI/4)),
						x + ((rad + whiskerSize) * Math.cos(Angle + Math.PI/4)),
						y + ((rad + whiskerSize) * Math.sin(Angle + Math.PI/4)));
				
				//draw the left whiskers on the robot
				gc.strokeLine(
						x + (rad * Math.cos(Angle - Math.PI/4)),
						y + (rad * Math.sin(Angle - Math.PI/4)),
						x + ((rad + whiskerSize) * Math.cos(Angle - Math.PI/4)),
						y + ((rad + whiskerSize) * Math.sin(Angle - Math.PI/4)));
	}
	

}


