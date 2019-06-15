import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * Assignment #2: Evolved Names
 * TCSS 342: Data Structures 
 */

/**
 * This is the Population Test class using JUnit testing.
 * @author Steve Mwangi
 * @version Spring 2019
 */
public class PopulationTest {
	
	/**
	 * Variables for use in our test cases.
	 */
	private Population testPopulation;
	private Population testPopulation2;
	private Population testPopulation3;
	
	/**
     * This method initializes the test fixtures before each test.
	 */
	@BeforeEach
	public void setUp() {
		 testPopulation = new Population(20, 0.02);
		 testPopulation2 = new Population(30, 0.05);
		 testPopulation3 = testPopulation2;
	}

	/**
	 * Test method for {@link Population#Population(java.lang.Integer, java.lang.Double)}.
	 */
	@Test
	public void testPopulation() {
		assertTrue(testPopulation.mutationRate == 0.02); // Asserts mutation rate is as set
	}

	/**
	 * Test method for {@link Population#day()}.
	 * 
	 * An earlier population should have worse fitness
	 * compared to a more recent population. 
	 */
	@Test
	public void testDay() {
		Genome badFitness = testPopulation.myPopulation.get(0);
		for(int i = 0; i <= 50; i++) {
			testPopulation.day();
		}
		Genome goodFitness = testPopulation.myPopulation.get(0);
		
		assertTrue(badFitness.fitness() > goodFitness.fitness()); // They should not be equal
		
		while(testPopulation.myPopulation.get(0).fitness() != 0) {
			testPopulation.day();
		}
		
		assertTrue(testPopulation.myPopulation.get(0).fitness() == 0);
	}

	/**
	 * Test method for {@link Population#toString()}.
	 */
	@Test
	public void testToString() {
		String first = testPopulation.toString();
		String second = testPopulation2.toString();
		String third = testPopulation3.toString();
		assertTrue(first.compareTo(second) != 0);
		assertTrue(second.compareTo(third) == 0);
		assertFalse(third.compareTo(first) == 0);
	}
}
