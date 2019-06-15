/*
 * TCSS - 342 Data Structures
 * Assignment 2 - Evolved Names
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
/**
 * The Population class must:
 * 1. Maintain a list of Genomes representing the current
 * 	  population.
 * 2. Initialize the population with a fixed number of default
 *    Genomes.
 * 3. Update the list of Genomes every breeding cycle by:
 * 	  a. Removing the least-fit members of the population.
 * 	  b. Mutating or breeding the most-fit members of the
 * 		 population.
 * 	  c. NOTE: Zero-based fitness means:
 * 
 * 		 MOST FIT has LOWEST FITNESS SCORE
 * 4. Display the entire population.
 * 5. Display the most-fit individual in the population.
 * 
 * @author Steve Mwangi
 * @version Spring 2019
 */
public class Population implements iPopulation {

	/**
	 * A data element that is equal to the most-fit Genome in the population.
	 */
	Genome mostFit;
	
	/** List for sorting according to fitness. */
	public final ArrayList<Genome> myPopulation;
	
	public final Double mutationRate;
	
	public Random randomSelector = new Random();
	
	/**
	 * A constructor that initializes a Population with a number of default
	 * genomes.
	 */
	public Population(Integer numGenomes, Double mutationRate) {
		myPopulation = new ArrayList<Genome>(numGenomes);
		this.mutationRate = mutationRate;
		for(int i = 1; i < numGenomes; i++) {
			myPopulation.add(new Genome(mutationRate));
		}
		mostFit = myPopulation.get(0);
	}

	/**
	 * This function is called every breeding and carries out the following
	 * steps:
	 * 
	 * 1. Update mostFit variable to the most-fit Genome in the population.
	 * 	  (Remember this is the genome with the lowest fitness!)
	 * 2. Delete the least-fit half of the population.
	 * 3. Create new genomes from the remaining (most-fit) population until
	 * 	  the number of genomes is restored by doing either of the following 
	 * 	  with equal chance:
	 * 	  a. Pick a remaining genome at random and clone it(with copy constructor)
	 * 		 and mutate the clone.
	 *    b. Pick a remaining genome at random and clone it and then crossover 
	 *       the clone with another remaining genome selected at random and then
	 *       mutate the result.
	 */
	public void day() {
		mostFit = myPopulation.get(0);
		final int size = myPopulation.size();
		Collections.sort((ArrayList<Genome>) myPopulation); // Sort
		int i;
		for(i = myPopulation.size()-1; i >= size/2; i--) { // Remove least healthy population
			myPopulation.remove(i);
		}
		
		while(myPopulation.size() < size) { // Re-populate
			if(randomSelector.nextBoolean()) {
				Genome ideal = new Genome(myPopulation.get(randomSelector.nextInt(size/2)));
				ideal.mutate();
				myPopulation.add(ideal);
			} else {
				Genome ideal1 = new Genome(myPopulation.get(randomSelector.nextInt(size/2)));
				Genome ideal2 = new Genome(myPopulation.get(randomSelector.nextInt(size/2)));
				ideal1.crossover(ideal2);
				ideal1.mutate();
				myPopulation.add(ideal1);
			}
		}
		Collections.sort((ArrayList<Genome>) myPopulation);
	}
	
	public String toString() {
		StringBuilder populationString = new StringBuilder();
		for(int i = 0; i < myPopulation.size(); i++) {
			populationString.append(i + ": " + this.myPopulation.get(i).toString());
		}
		
		return populationString.toString();
	}

}
