/*
 * TCSS - 342 Data Structures
 * Assignment 2 - Evolved Names
 */

/**
 * The population class must function according to this interface.
 * 
 * @author Steve Mwangi
 * @version Spring 2019
 */
public interface iPopulation {
	
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
	void day();
}
