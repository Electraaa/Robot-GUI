package robotGUI;

import java.io.Serializable;

public class LightDetectorRobot extends GameRobot implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6883185000650065415L;
	private double lastLightValue;

	public LightDetectorRobot(double ix, double iy, double ir, double ia, double is) {
		super(ix, iy, ir, ia, is);

		col = 'b';

		lastLightValue = 0;
	}

	/**
	 * adjustRobot Here, move robot depending on speed and Angle
	 */
	@Override
	protected void adjustRobot(RobotArena arena) {

		int arenaX = arena.getXSize();
		int arenaY = arena.getYSize();
		int collisionCount = 0;

		for (Robot r : arena.getAllRobots()) {
			if (new BoxCollision(r.x, this.x, r.y, this.y, r.rad, this.rad, r.rad, this.rad).checkCollision()) {
				collisionCount++;
			}
		}

		if (y <= 0 + rad + rSpeed || y >= arenaY - rad - rSpeed || x <= 0 + rad + rSpeed || x >= arenaX - rad - rSpeed
				|| collisionCount > 1)

		{

			x -= rSpeed * Math.cos(getAngleRad());
			y -= rSpeed * Math.sin(getAngleRad());
			rAngle += 90;
		} else {
			double lightValue = 0;

			for (Robot r : arena.getAllRobots()) {
				if (r instanceof LightRobot) {
					lightValue += Math.pow(Math.pow(this.x - r.getX(), 2) + Math.pow(this.y - r.getY(), 2), -0.5);
				}

			}
			if (lightValue < lastLightValue) {
				rAngle += 5;
			}
			lastLightValue = lightValue;

		}

		x += rSpeed * Math.cos(getAngleRad()); // new X position
		y += rSpeed * Math.sin(getAngleRad()); // new Y position
	}

	/**
	 * return string defining ball type
	 */
	protected String getStrType() {
		return "Light Detector Robot";
	}

	public double getAngleRad() {
		return rAngle * (Math.PI / 180);
	}

	public double getAngleDegrees() {
		return rAngle;
	}

}
