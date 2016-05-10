package supersample; // Rafa me lo tiene que enviar 

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

import org.jgap.Configuration;
import org.jgap.Gene;
import org.jgap.IChromosome;
import org.jgap.IGeneConstraintChecker;
import org.jgap.InvalidConfigurationException;
import org.jgap.RandomGenerator;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.DoubleGene;

import robocode.control.*;

public class DefBattle {
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

		int ramfire = 0, supertracker = 0;
		try {
			Scanner sc = new Scanner(new java.io.File(
					"C:/Users/rafae/git/PerGamPer-Chromosome/SuperTracker/bin/supersample/SuperRamFire.data/score.txt"));
			if (sc.hasNext()) {
				ramfire = sc.nextInt();
			}
			sc.close();
			Scanner sc2 = new Scanner(new java.io.File(
					"C:/Users/rafae/git/PerGamPer-Chromosome/SuperTracker/bin/supersample/SuperTracker.data/score.txt"));
			if (sc2.hasNext()) {
				supertracker = sc2.nextInt();
			}
			sc2.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ramfire);
		System.out.println(supertracker);

//		try {
//			PrintStream w = new PrintStream(new FileOutputStream(new File(
//					"C:/Users/rafae/git/PerGamPer-Chromosome/SuperTracker/bin/supersample/SuperTracker.data/genes.txt")));
//			w.println(150);
//			w.println(0.1);
//			w.println(12);
//			w.println(12);
//			// PrintStreams don't throw IOExceptions during prints, they simply
//			// set a flag.... so check it here.
//			if (w.checkError()) {
//				System.out.println("I could not write!");
//			}
//			if (w != null) {
//				w.close();
//			}
//		} catch (IOException ex) {
//			System.out.println("IOException trying to write: ");
//			ex.printStackTrace(System.out);
//		}
//
//		try {
//			Scanner sc = new Scanner(new File(
//					"C:/Users/rafae/git/PerGamPer-Chromosome/SuperTracker/bin/supersample/SuperTracker.data/genes.txt"));
//			// sc.useDelimiter("\n");
//			sc.useLocale(Locale.US);
//			System.out.println(sc.nextDouble());
//			System.out.println(sc.nextDouble());
//			System.out.println(sc.nextDouble());
//			System.out.println(sc.nextDouble());
//			sc.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		RandomGenerator randomGenerator = null;
		try {
			DefaultConfiguration config = new DefaultConfiguration();
			DoubleGene[] genes = new DoubleGene[4];
			genes[0] = new DoubleGene (config, 0.0, 1500);
			genes[0].setAllele(150.0);
			genes[1] = new DoubleGene (config, 0.0, 0.9999);
			genes[1].setAllele(0.1);
			genes[2] = new DoubleGene (config, 0.0, 1500);
			genes[2].setAllele(12.0);
			genes[3] = new DoubleGene (config, 0.0, 1500);
			genes[3].setAllele(12.0);
			IChromosome pgp = new Chromosome(genes);
			OurFitnessFunction fit = new OurFitnessFunction (pgp.getFitnessValue());
			for (int i=0; i < 1000; i++){
				double evaluate = fit.evaluate(pgp);
				pgp.setFitnessValue(evaluate);
				genes[0].applyMutation(0, 1-evaluate);
				genes[1].applyMutation(0, 1-evaluate);
				genes[2].applyMutation(0, 1-evaluate);
				genes[3].applyMutation(0, 1-evaluate);
				double evaluate2 = fit.evaluate(pgp);
				pgp.setFitnessValue(Math.max(evaluate, evaluate2));
				System.out.println("Calculated" + i);
			}
			System.out.println("Ended");
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.exit(0);

	}
}
