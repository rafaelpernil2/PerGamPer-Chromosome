package supersample;

import robocode.*;
import java.awt.*;
import java.io.*;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

/**
 * SuperTracker - a Super Sample Robot by CrazyBassoonist based on the robot
 * Tracker by Mathew Nelson and maintained by Flemming N. Larsen
 * <p/>
 * Locks onto a robot, moves close, fires when close.
 */
public class SuperTracker extends AdvancedRobot {
	int moveDirection = 1;// which way to move
	/**
	 * run: Tracker's main run function
	 */
	private double distance_limit;
	private double speed_change_probability;
	private double range_of_speeds;
	private double min_robot_speed;
	private Random randomGenerator;

	public void run() {
		randomGenerator = new Random(0);
		try {
			readParameters(getDataFile("genes.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setAdjustRadarForRobotTurn(true);// keep the radar still while we turn
		setBodyColor(new Color(128, 128, 50));
		setGunColor(new Color(50, 50, 20));
		setRadarColor(new Color(200, 200, 70));
		setScanColor(Color.white);
		setBulletColor(Color.blue);
		setAdjustGunForRobotTurn(true); // Keep the gun still when we turn
		turnRadarRightRadians(Double.POSITIVE_INFINITY);// keep turning radar
														// right
	}

	/**
	 * onScannedRobot: Here's the good stuff
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		double absBearing = e.getBearingRadians() + getHeadingRadians();// enemies
																		// absolute
																		// bearing
		double latVel = e.getVelocity() * Math.sin(e.getHeadingRadians() - absBearing);// enemies
																						// later
																						// velocity
		double gunTurnAmt;// amount to turn our gun
		setTurnRadarLeftRadians(getRadarTurnRemainingRadians());// lock on the
																// radar
		if (randomGenerator.nextDouble() > (1 - speed_change_probability)) {
			if ((min_robot_speed * randomGenerator.nextDouble()) + min_robot_speed < range_of_speeds) {
				setMaxVelocity((min_robot_speed * randomGenerator.nextDouble()) + min_robot_speed);// randomly
																									// change
																									// speed
			}
		}
		if (e.getDistance() > distance_limit) {// if distance is greater than
												// 150
			gunTurnAmt = robocode.util.Utils.normalRelativeAngle(absBearing - getGunHeadingRadians() + latVel / 22);// amount
																													// to
																													// turn
																													// our
																													// gun,
																													// lead
																													// just
																													// a
																													// little
																													// bit
			setTurnGunRightRadians(gunTurnAmt); // turn our gun
			setTurnRightRadians(
					robocode.util.Utils.normalRelativeAngle(absBearing - getHeadingRadians() + latVel / getVelocity()));// drive
																														// towards
																														// the
																														// enemies
																														// predicted
																														// future
																														// location
			setAhead((e.getDistance() - distance_limit - 10) * moveDirection);// move
																				// forward
			setFire(3);// fire
		} else {// if we are close enough...
			gunTurnAmt = robocode.util.Utils.normalRelativeAngle(absBearing - getGunHeadingRadians() + latVel / 15);// amount
																													// to
																													// turn
																													// our
																													// gun,
																													// lead
																													// just
																													// a
																													// little
																													// bit
			setTurnGunRightRadians(gunTurnAmt);// turn our gun
			setTurnLeft(-90 - e.getBearing()); // turn perpendicular to the
												// enemy
			setAhead((e.getDistance() - distance_limit - 10) * moveDirection);// move
																				// forward
			setFire(3);// fire
		}
	}

	public void onHitWall(HitWallEvent e) {
		moveDirection = -moveDirection;// reverse direction upon hitting a wall
	}

	/**
	 * onWin: Do a victory dance
	 */
	public void onWin(WinEvent e) {
		for (int i = 0; i < 50; i++) {
			turnRight(30);
			turnLeft(30);
		}
	}

	public void onBattleEnded(BattleEndedEvent e) {

		try {
			PrintStream w = new PrintStream(new RobocodeFileOutputStream(getDataFile("score.txt")));
			int sc = e.getResults().getScore();
			w.println(sc);
			// PrintStreams don't throw IOExceptions during prints, they simply
			// set a flag.... so check it here.
			if (w.checkError()) {
				out.println("I could not write the count!");
			}
			if (w != null) {
				w.close();
			}
		} catch (IOException ex) {
			out.println("IOException trying to write: ");
			ex.printStackTrace(out);
		}
	}

	public void readParameters(File fl) throws IOException {
		Scanner sc = new Scanner(fl);
		sc.useLocale(Locale.US);
		// sc.useDelimiter("\n");
		distance_limit = sc.nextDouble();
		speed_change_probability = sc.nextDouble();
		range_of_speeds = sc.nextDouble();
		min_robot_speed = sc.nextDouble();
		System.out.println("Chromosome used:");
		System.out.println("Distance Limit: " + distance_limit);
		System.out.println("Speed change probability: " +speed_change_probability);
		System.out.println("Range of Speeds: " + range_of_speeds);
		System.out.println("Minimum Robot Speed: " + min_robot_speed);
		sc.close();
	}
}