package battle;

import robocode.control.*;

public class Battle {
	private BattlefieldSpecification battlefieldSize;
	private int numRounds, sentryBorderSize;
	private long inactivityTime;
	private double gunCoolingRate;
	private boolean hideEnemyNames;
	private RobotSpecification[] robots;

	public Battle(BattlefieldSpecification battlefieldSize, int numRounds, long inactivityTime, double gunCoolingRate,
			int sentryBorderSize, boolean hideEnemyNames, RobotSpecification[] robots) {
		this.battlefieldSize = battlefieldSize;
		this.numRounds = numRounds;
		this.inactivityTime = inactivityTime;
		this.sentryBorderSize = sentryBorderSize;
		this.gunCoolingRate = gunCoolingRate;
		this.hideEnemyNames = hideEnemyNames;
		this.robots = robots;
	}
	
	
	
	
}
