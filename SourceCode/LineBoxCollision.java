package robotGUI;

public class LineBoxCollision {

	private double boxX;
	private double boxY;
	private double boxWidth;
	private double boxHeight;
	private double lineStartX;
	private double lineEndX;
	private double lineStartY;
	private double lineEndY;

	/**
	 * Constructor for the LineBoxCollision
	 * @param boxX box x position start 
	 * @param boxY box y position start
	 * @param boxWidth width of the box
	 * @param boxHeight height of the box
	 * @param lineStartX start x position of the line
	 * @param lineEndX end x position of the line 
	 * @param lineStartY start y position of the line
	 * @param lineendy end y position of the line
	 */
	
	public LineBoxCollision(double boxX, double boxY, double boxWidth,
			double boxHeight, double lineStartX, double lineEndX,
			double lineStartY, double lineendy) {
		this.boxX = boxX;
		this.boxY = boxY;
		this.boxWidth = boxWidth;
		this.boxHeight = boxHeight;
		this.lineStartX = lineStartX;
		this.lineEndX = lineEndX;
		this.lineStartY = lineStartY;
		this.lineEndY = lineendy;
	}

	/**
	 * Function for checking if there has been a collision between a line and a box. 
	 * Create an array of doubles for the box positions, if a line collision occurs on this box
	 * @return true if a collision has occurred, else return false. 
	 */
	
	public boolean CheckCollision() {
		// NESW
		double[] boxlsx = new double[] { boxX - boxWidth, boxX + boxWidth,
				boxX + boxWidth, boxX - boxWidth };
		double[] boxlex = new double[] { boxX + boxWidth, boxX + boxWidth,
				boxX - boxWidth, boxX - boxWidth };
		double[] boxlsy = new double[] { boxY - boxHeight,
				boxY - boxHeight, boxY + boxHeight, boxY + boxHeight };
		double[] boxley = new double[] { boxY - boxHeight, boxY + boxHeight,
				boxY + boxHeight, boxY - boxHeight };

		for (int i = 0; i < 4; i++) {
			double boxlineStartX = boxlsx[i];
			double boxlineStartY = boxlsy[i];
			double boxlineEndX = boxlex[i];
			double boxlineendy = boxley[i];

			if ((new LineCollision(lineStartX, lineEndX, lineStartY, lineEndY,
					boxlineStartX, boxlineEndX, boxlineStartY, boxlineendy))
					.CheckCollision()) {
				return true;
			}
		}

		return false;
	}
}