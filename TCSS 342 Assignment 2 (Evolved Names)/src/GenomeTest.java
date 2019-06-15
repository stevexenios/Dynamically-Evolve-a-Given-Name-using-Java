import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * Assignment #2: Evolved Names
 * TCSS 342: Data Structures 
 */

/**
 * This is the Genome Test class using JUnit testing.
 * @author Steve Mwangi
 * @version Spring 2019
 */
public class GenomeTest {
	
	/** Variables to hold the Genomes to be tested. */
	private Genome testGenome, testGenome2, testGenome3;
	
	/**
	 * This is for the number of times we execute mutations
	 * to ensure uniqueness.
	 * 
	 * Values such as 50 varied the results of different tests, so
	 * took an arbitrarily large value, i.e. 500
	 */
	private int UNI_Q = 500;

	@BeforeEach
	public void setUp() {
		testGenome = new Genome(0.05);
		testGenome2 = new Genome (0.08);
		testGenome3 = new Genome(testGenome);
	}
	
	@AfterEach
	public void cleanUpEach(){}

	@Test
	public void testGenomeDouble() {
		assertTrue(testGenome.compareTo(new Genome(0.05)) == 0); // True when both are similar
		assertNotSame("Asserting that the 2nd Genomes is not the same object. ", testGenome.compareTo(new Genome(0.05)));	
	}

	@Test
	public void testGenomeGenome() {
		assertNotSame("Asserting that the 2nd Genomes is copy constructed the Same. ", testGenome.compareTo(new Genome(testGenome))); // Copy constructor creates a different object
	}

	@Test
	public void testMutate() {
		Genome t = new Genome(testGenome3);
		for(int i = 0; i < UNI_Q; i++) { 
			testGenome3.mutate();
		}
		
		/** 
		 * The mutation should change the Genome,
		 * therefore, t should be different from outcome
		 * of the mutation.
		 */
		assertFalse(t.compareTo(testGenome3) == 0); 
	}

	@Test
	public void testCrossover() {
		Genome t = new Genome(testGenome);
		testGenome2.crossover(testGenome);
		for(int i = 0; i < UNI_Q; i++) {
			testGenome2.mutate();
			testGenome3.mutate();
		}
		testGenome3.crossover(testGenome2);
		t.crossover(testGenome3);
		
		assertTrue(t.compareTo(testGenome) != 0); // Not same as it started
	}

	@Test
	public void testFitness() {
		int tg = testGenome.fitness();
		int tg2 = testGenome2.fitness();
		assertTrue(tg >= 0 && tg < 100 && tg2 > 0 && tg2 < 100 ); // TODO
	}

	@Test
	public void testFitnessWagnerFischer() {
		int tg = testGenome.fitness();
		int tg2 = testGenome2.fitness();
		int tg0 = testGenome.fitnessWagnerFischer();
		int tg20 = testGenome2.fitnessWagnerFischer();
		int c1 = Math.abs(tg0 - tg);
		int c2 = Math.abs(tg2 - tg20);
		String str = "Checking to ensure both fitness measurements are roughly the same. ";
		assertEquals((double) c1, (double) c2, (double) 2.0, str); // TODO
	}

	@Test
	public void testToString() {
		Genome uno = new Genome(0.05);
		int i = uno.fitness();
		int j = uno.fitnessWagnerFischer();
		StringBuilder a = new StringBuilder("A");
		a.append(System.lineSeparator() + "Normal Fitness: " + i + System.lineSeparator() + 
				"Wagner-Fischer Fitness: " + j); 
		assertTrue((a.toString()).compareTo(uno.toString()) == 0);
	}

	@Test
	public void testCompareTo() {
		assertTrue(testGenome3.compareTo(testGenome) == 0); // Fitness are the same since they haven't gone through multiple generations
	}
}
