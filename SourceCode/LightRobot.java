package robotGUI;

import java.io.Serializable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class LightRobot extends Robot implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7879861476203145059L;

	LightRobot(double ix, double iy, double ir) {
		super(ix, iy, ir);
		col = 'y';
	}

	@Override
	public void drawRobot(RobotInterface ri) {
		GraphicsContext gc = ri.getgc();
		
		gc.setFill(ri.colFromChar(col));
		
		gc.fillArc(x - rad, y - rad, rad * 2, rad * 2, 0, 360, ArcType.ROUND);
				
	}

	@Override
	protected void adjustRobot(RobotArena arena) {
	}

}
