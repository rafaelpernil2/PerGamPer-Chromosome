package battle;

import robocode.control.*;

public class Battle {
	public static void main(String[] args) {
		RobocodeEngine engine = new RobocodeEngine(new java.io.File("C:/robocode"));
		engine.setVisible(true);
		int NumPixelRows = 832;
		int NumPixelCols = 640;
		BattlefieldSpecification battlefield = new BattlefieldSpecification(NumPixelRows, NumPixelCols);
		// 800x600
		// Setup battle parameters
		int numberOfRounds = 10;
		long inactivityTime = 10000000;
		double gunCoolingRate = 1.0;
		int sentryBorderSize = 50;
		boolean hideEnemyNames = false;
		int NumEnemies = 1; // Defined by programmer
		// Define specifications
		RobotSpecification[] modelRobots = engine
				.getLocalRepository("supersample.SuperRamFire*,supersample.SuperTracker*");
		RobotSpecification[] existingRobots = new RobotSpecification[NumEnemies + 1];
		RobotSetup[] robotSetups = new RobotSetup[NumEnemies + 1];
		existingRobots[NumEnemies] = modelRobots[1];
		existingRobots[0] = modelRobots[0];
		robotSetups[0] = new RobotSetup((double) (1 * 64 + 32), (double) (5 * 64 + 32), 0.0);
		double InitialAgentRow = (5 * 64) + 32;
		double InitialAgentCol = (11 * 64) + 32;
		robotSetups[NumEnemies] = new RobotSetup(InitialAgentCol, InitialAgentRow, 0.0);

		/* Create and run the battle */

		BattleSpecification battleSpec = new BattleSpecification(battlefield, numberOfRounds, inactivityTime,
				gunCoolingRate, sentryBorderSize, hideEnemyNames, existingRobots, robotSetups);

		// Run our specified battle and let it run till it is over
		engine.runBattle(battleSpec, true); // waits till the battle finishes
		// Cleanup our RobocodeEngine
		engine.close();

		System.exit(0);
	}
}
