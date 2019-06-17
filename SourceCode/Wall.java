package robotGUI;

import java.io.Serializable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Wall extends Robot implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 184993420223491312L;

	Wall(double ix, double iy, double ir) {
		super(ix, iy, ir);
	}

	@Override
	/**
	 * function	to draw a wall
	 */
	public void drawRobot(RobotInterface ri) {
			GraphicsContext gc = ri.getgc();
			
			gc.setStroke(Color.BLACK);
			
			gc.setLineWidth(4);
			
			gc.strokeLine(x + (rad * 5), y, x - (rad * 5), y);
	}

	@Override
	protected void adjustRobot(RobotArena arena) {
		
	}
	
	protected String getStrType() {
		return "Wall";
	}
}
