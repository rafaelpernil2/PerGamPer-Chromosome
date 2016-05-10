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

		
		int ramfire = 0, supertracker = 0;
		try {
			Scanner sc = new Scanner(new java.io.File(
					"/home/rafaelpernil/git/PerGamPer-Chromosome/SuperTracker/bin/supersample/SuperRamFire.data/score.txt"));
			if (sc.hasNext()) {
				ramfire = sc.nextInt();
			}
			sc.close();
			Scanner sc2 = new Scanner(new java.io.File(
					"/home/rafaelpernil/git/PerGamPer-Chromosome/SuperTracker/bin/supersample/SuperTracker.data/score.txt"));
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
		try {
			PrintStream w = new PrintStream(new FileOutputStream(new File(
					"/home/rafaelpernil/git/PerGamPer-Chromosome/SuperTracker/bin/supersample/SuperTracker.data/genes.txt")));
			w.println(150);
			w.println(0.1);
			w.println(12);
			w.println(12);
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

		try {
			Scanner sc = new Scanner(new File(
					"/home/rafaelpernil/git/PerGamPer-Chromosome/SuperTracker/bin/supersample/SuperTracker.data/genes.txt"));
			// sc.useDelimiter("\n");
			sc.useLocale(Locale.US);
			System.out.println(sc.nextDouble());
			System.out.println(sc.nextDouble());
			System.out.println(sc.nextDouble());
			System.out.println(sc.nextDouble());
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	try {
			DefaultConfiguration config = new DefaultConfiguration();
			DoubleGene[] genes = new DoubleGene[4];
			genes[0] = new DoubleGene (config, 0.0, 832);
			genes[0].setAllele(150.0);
			genes[1] = new DoubleGene (config, 0.0, 0.999999);
			genes[1].setAllele(0.1);
			genes[2] = new DoubleGene (config, 0.0, 150);
			genes[2].setAllele(12.0);
			genes[3] = new DoubleGene (config, 0.0, 150);
			genes[3].setAllele(12.0);
			IChromosome pgp = new Chromosome(genes);
			OurFitnessFunction fit = new OurFitnessFunction (pgp.getFitnessValue());
			for (int i=0; i < 1000; i++){
				double origFitness = pgp.getFitnessValue();
				double evaluate = fit.evaluate(pgp);
				pgp.setFitnessValue(Math.max(origFitness,evaluate));
				double goodFitness = pgp.getFitnessValue();
				genes[0].applyMutation(0, 1-evaluate);
				genes[1].applyMutation(0, 1-evaluate);
				genes[2].applyMutation(0, 1-evaluate);
				genes[3].applyMutation(0, 1-evaluate);
				evaluate = fit.evaluate(pgp);
				pgp.setFitnessValue(Math.max(evaluate, goodFitness));
				System.out.println("Calculated " + i);
			}
			System.out.println("Ended");
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.exit(0);

	}
}
