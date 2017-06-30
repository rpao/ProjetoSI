package ProjetoSI;

import robocode.*;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;

import robocode.util.Utils;

public class Bushin extends AdvancedRobot {
	boolean lado = false;
	String[] array = new String[5];
	double previousEnergy = 100;
	int movementDirection = 1;
	int gunDirection = 1;
	int wallMargin = 60;
	ArrayList<String> robs = new ArrayList<String>();
	double nextPredictionX, nextPredictionY;

	public void run() {
		setBodyColor(Color.orange);
		setGunColor(Color.white);
		setRadarColor(Color.blue);
		setScanColor(Color.blue);
		setBulletColor(Color.blue);

		setAdjustRadarForGunTurn(true);

		do {
			if (getRadarTurnRemaining() == 0.0)
				setTurnRadarRightRadians(Double.POSITIVE_INFINITY);
			execute();
		} while (true);
	}

	public boolean contains(ArrayList<String> array, String in){
		for (int i = 0; i < array.size(); i++) {
			if(array.get(i).equalsIgnoreCase(in)){
				return true;
			}
		}
		return false;
	}

	public void onScannedRobot(ScannedRobotEvent e) {

		if (e.getName().contains("Bushin")) {
			if (lado) {
				setAhead(50);
			} else {
				setBack(50);
			}
			lado = !lado;
		}

		if (!contains(robs, e.getName())) {
			if (!e.getName().contains("Bushin")) {
				robs.add(e.getName());
			}
			Collections.sort(robs);
			Collections.reverse(robs);
		}

		if(getTime() > 9){
			if (robs.get(0).equals(e.getName())) {

				setTurnRight(e.getBearing() + 90);

				double changeInEnergy = previousEnergy - e.getEnergy();

				double dist = (e.getDistance() / 4 + (Math.random() * 100))
						* movementDirection;

				if (changeInEnergy > 0 && changeInEnergy <= 3) {
					double alfa, x, y, target_x, target_y;
					alfa = getHeadingRadians() + (Math.PI / 2);
					x = Math.cos(alfa) * dist;
					y = Math.sin(alfa) * dist;
					y = (-1) * y;
					target_x = x + getX();
					target_y = y + getY();

					movementDirection = -movementDirection;
					if (target_x > 900 || target_x < 100 || target_y > 900
							|| target_y < 100) {
						movementDirection = -movementDirection;
					}
					setAhead(dist);
				}

				double angleToEnemy = getHeadingRadians() + e.getBearingRadians();

				double radarTurn = Utils.normalRelativeAngle(angleToEnemy
						- getRadarHeadingRadians());

				double extraTurn = Math.min(Math.atan(5.0 / e.getDistance()),
						Rules.RADAR_TURN_RATE_RADIANS);

				radarTurn += (radarTurn < 0 ? -extraTurn : extraTurn);

				setTurnRadarRightRadians(radarTurn);
				setTurnGunRightRadians(getRadarHeadingRadians()
						- getGunHeadingRadians());

				double bulletPower = Math.min(3.0, getEnergy());
				double myX = getX();
				double myY = getY();
				double absoluteBearing = getHeadingRadians()
						+ e.getBearingRadians();
				double enemyX = getX() + e.getDistance()
				* Math.sin(absoluteBearing);
				double enemyY = getY() + e.getDistance()
				* Math.cos(absoluteBearing);
				double enemyHeading = e.getHeadingRadians();
				double enemyVelocity = e.getVelocity();

				double deltaTime = 0;
				double battleFieldHeight = getBattleFieldHeight(), battleFieldWidth = getBattleFieldWidth();
				double predictedX = enemyX, predictedY = enemyY;
				while ((++deltaTime) * (20.0 - 3.0 * bulletPower) < Point2D.Double
						.distance(myX, myY, predictedX, predictedY)) {
					predictedX += Math.sin(enemyHeading) * enemyVelocity;
					predictedY += Math.cos(enemyHeading) * enemyVelocity;
					if (predictedX < 18.0 || predictedY < 18.0
							|| predictedX > battleFieldWidth - 18.0
							|| predictedY > battleFieldHeight - 18.0) {
						predictedX = Math.min(Math.max(18.0, predictedX),
								battleFieldWidth - 18.0);
						predictedY = Math.min(Math.max(18.0, predictedY),
								battleFieldHeight - 18.0);
						break;
					}
				}

				double theta = Utils.normalAbsoluteAngle(Math.atan2(predictedX
						- getX(), predictedY - getY()));

				setTurnRadarRightRadians(Utils.normalRelativeAngle(absoluteBearing
						- getRadarHeadingRadians()));
				setTurnGunRightRadians(Utils.normalRelativeAngle(theta
						- getGunHeadingRadians()));
				fire(bulletPower);


				previousEnergy = e.getEnergy();
			}
		}
	}

	public void onHitByBullet(HitByBulletEvent e) {
		movementDirection = -movementDirection;
		setAhead((200 / 4 + 25) * movementDirection);
	}

	public void onHitWall(HitWallEvent e) {
		movementDirection = -movementDirection;
	}

	public void onRobotDeath(RobotDeathEvent e) {
		robs.remove(robs.indexOf(e.getName()));
	}

}