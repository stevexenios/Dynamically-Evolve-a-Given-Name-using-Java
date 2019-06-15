/*
 * TCSS - 342 Data Structures
 * Assignment 2 - Evolved Names
 */

import java.util.Random;
/**
 * This is a controller and will:
 * 
 * 1. Instantiate the Population class(Using 100 Genomes and mutation rate 0.05)
 * 2. Call day() from the Population class until the fitness of the most fit 
 * 	  Genome is zero.
 * 3. Output simulation progress.
 * 4. Output runtime statistics.
 * 
 * @author Steve Mwangi
 * @version Spring 2019
 */
public class Main {

	/** Constant for instantiating the population class with 100 Genomes. */
	public static final Integer CONSTANT_GENOMES = 100;
	
	/** Constant for instantiating the population class with 0.05 mutationRate. */
	public static final Double CONSTANT_MUTATION_RATE = 0.05;
	
	/** This is a random variable for selecting random characters. */
	public static Random randomSelector = new Random();
	
	/** Generations counter. */
	public static int generations = 0;

	/**
	 * This method should instantiate a population and call day()
	 * until the target string is part of the population.
	 * 1. Target String has fitness zero, so loop should repeat until
	 * 	  the most-fit genome has fitness zero.
	 * 2. After each execution of day(), output most fit genome.
	 * 3. To measure performance output the number of generations
	 *    that is: times day() is called and number of execution time.
	 * 
	 */
	public static void main(String[] args) {
		Population p1 = new Population(CONSTANT_GENOMES, CONSTANT_MUTATION_RATE);
		
		double start = System.currentTimeMillis();
		
		while(true) {
			p1.day();
			System.out.println("Generation: " + generations + " " + p1.mostFit.toString());
			generations++;
		
			if(p1.mostFit.fitness() == 0 || generations == 2000) {
				break;
			}
		}
		
		double stop = System.currentTimeMillis();
		double elapsedTime = stop - start;
		
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println(System.lineSeparator() + "Completed!");
		System.out.println(p1.mostFit.toString());
		System.out.println("Iterated for " + generations + " Generations. ");
		System.out.println("Runtime: " + elapsedTime + " milliseconds " + "(Or " + elapsedTime/1000 +" seconds).");
		System.out.println("");

		/*
		 * This is are some test cases.
		 * The test classes in GenomeTest and PopulationTest
		 * are more concise, just that I was unable to implement
		 * the results in the Main class without errors.
		 */
		
		/**
		 *  Tests Genome
		 */
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("Genome Class Tests: ");
		testGenome();
		
		/**
		 * Tests Population
		 */
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("Population Class Tests: ");
		testPopulation();
	}
	
	/**
	 * Tests Genome
	 */
	private static void testGenome() {
		Genome testGenome = new Genome(.05);
		Genome testGenome2 = new Genome(testGenome);
		int i = 0;
		while (i < 2) {
			testGenome.mutate();
			testGenome2.mutate();
			testGenome.crossover(testGenome2);
			System.out.println(testGenome.toString() + testGenome2.toString() + testGenome.compareTo(testGenome2));
			i++;
		}
		int j = 0;
		while(j < 50) {
			testGenome.mutate();
			j++;
		}
		System.out.println(testGenome.toString() + testGenome2.toString() + testGenome.compareTo(testGenome2));
	}
	/**
	 * Tests Population
	 */
	private static void testPopulation() {
		Population population = new Population(100, 0.05);
		System.out.println(population.toString());
		population.day();
		System.out.println(population.toString());
	}
		
}

///**
// * Results for displaying the test results for the GenomeTest class
// * and the PopulationTest class.
// */
//System.out.println("Running JUnit Test Suite. ");
//
//final Result results = JUnitCore.runClasses(GenomeTest.class, PopulationTest.class);
//for(org.junit.runner.notification.Failure failure: results.getFailures()) {
//	System.out.println(failure.toString());
//}
//System.out.println("Total number of Successful Tests: " + results.getRunCount());
//System.out.println("Total number of Failed tests: " + results.getFailureCount());