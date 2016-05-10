package supersample;

import java.awt.List;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import org.jgap.Chromosome;
import org.jgap.FitnessFunction;
import org.jgap.IChromosome;
import robocode.*;
import robocode.control.BattleSpecification;
import robocode.control.BattlefieldSpecification;
import robocode.control.RobocodeEngine;
import robocode.control.RobotSetup;
import robocode.control.RobotSpecification;

/**
 * This class provides an implementation of the classic "Make change" problem
 * using a genetic algorithm. The goal of the problem is to provide a specified
 * amount of change (from a cash purchase) in the fewest coins possible. This
 * example implementation uses American currency (quarters, dimes, nickels, and
 * pennies).
 *
 * This example may be seen as somewhat significant because it demonstrates the
 * use of a genetic algorithm in a less-than-optimal problem space. The genetic
 * algorithm does best when there is a smooth slope of fitness over the problem
 * space towards the optimum solution. This problem exhibits a more choppy space
 * with more local optima. However, as can be seen from running this example,
 * the genetic algorithm still will get the correct answer virtually everytime.
 */
@SuppressWarnings("serial")
public class OurFitnessFunction extends FitnessFunction {
	private final double my_fitness_percentage;

	public OurFitnessFunction(double fitness_percentage) {
		if (fitness_percentage < 0 || fitness_percentage >= 1) {
			throw new IllegalArgumentException("Percentage must be between 0 and 99");
		}

		my_fitness_percentage = fitness_percentage;
	}

	/**
	 * Determine the fitness of the given Chromosome instance. The higher the
	 * return value, the more fit the instance. This method should always return
	 * the same fitness value for two equivalent Chromosome instances.
	 *
	 * @param a_subject:
	 *            The Chromosome instance to evaluate.
	 *
	 * @return A positive integer reflecting the fitness rating of the given
	 *         Chromosome.
	 */
	protected double evaluate(IChromosome a_subject) {
		//Read genes
		double [] results = new double [10];
		for (int i=0; i < 10; i++){
		double distance_limit = (double) a_subject.getGene(0).getAllele();
		double speed_change_probability = (double) a_subject.getGene(1).getAllele();
		double range_of_speeds = (double) a_subject.getGene(2).getAllele();
		double min_robot_speed = (double) a_subject.getGene(3).getAllele();
		//Write file
		try {
			PrintStream w = new PrintStream(new FileOutputStream(new File("C:/Users/rafae/git/PerGamPer-Chromosome/SuperTracker/bin/supersample/SuperTracker.data/genes.txt")));
			w.println(distance_limit);
			w.println(speed_change_probability);
			w.println(range_of_speeds);
			w.println(min_robot_speed);
			// PrintStreams don't throw IOExceptions during prints, they simply
			// set a flag.... so check it here.
			if (w.checkError()) {
				System.out.println("I could not write!");
			}
			if (w != null) {
				w.close();
			}
		} catch (IOException ex) {
			System.out.println("IOException trying to write: ");
			ex.printStackTrace(System.out);
		}
		
		
		//Within 10 iterations...
		
		//Run Battle
		RobocodeEngine engine = new RobocodeEngine(new java.io.File("C:/robocode"));
		engine.setVisible(false);
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
		RobotSpecification[] modelRobots = engine.getLocalRepository("supersample.SuperRamFire*,supersample.SuperTracker*");
		RobotSpecification[] existingRobots = new RobotSpecification[NumEnemies + 1];
		RobotSetup[] robotSetups = new RobotSetup[NumEnemies + 1];
		existingRobots[NumEnemies] = modelRobots[1];
		existingRobots[0] = modelRobots[0];
		robotSetups[0] = new RobotSetup((double)(1*64+32),(double)(5*64+32), 0.0);
		double InitialAgentRow = (5*64)+32;
		double InitialAgentCol = (11*64)+32;
		robotSetups[NumEnemies] = new RobotSetup(InitialAgentCol, InitialAgentRow, 0.0);

		/* Create and run the battle */

		BattleSpecification battleSpec = new BattleSpecification(battlefield, numberOfRounds, inactivityTime,
				gunCoolingRate, sentryBorderSize, hideEnemyNames, existingRobots, robotSetups);

		// Run our specified battle and let it run till it is over
		engine.runBattle(battleSpec, true); // waits till the battle finishes
		// Cleanup our RobocodeEngine
		engine.close();
		
		
		
		
		//Read battle results
		int ramfire=0, supertracker=0;
		try {
			Scanner sc = new Scanner(new java.io.File ("C:/Users/rafae/git/PerGamPer-Chromosome/SuperTracker/bin/supersample/SuperRamFire.data/score.txt"));
			if (sc.hasNext()){
				ramfire = sc.nextInt();
			}
			sc.close();
			Scanner sc2 = new Scanner(new java.io.File ("C:/Users/rafae/git/PerGamPer-Chromosome/SuperTracker/bin/supersample/SuperTracker.data/score.txt"));
			if (sc2.hasNext()){
				supertracker = sc2.nextInt();
			}
			sc2.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Compare values
		double fitness = supertracker / (ramfire+supertracker);
		results[i] = fitness;
		}
		double max = 0.0;
		for (int counter = 0; counter < results.length; counter++)
		{
		     if (results[counter] > max)
		     {
		      max = results[counter];
		     }
		}

		//Generate Fitness
		return max;
	}

//	@Override
//	protected double evaluate(IChromosome a_subject) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
}