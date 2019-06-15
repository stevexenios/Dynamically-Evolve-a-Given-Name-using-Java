/*
 * TCSS - 342 Data Structures
 * Assignment 2 - Evolved Names
 */

/**
 * The Genome class must function according to the following 
 * interface.
 * 
 * @author Steve Mwangi
 * @version Spring 2019
 */
public interface iGenome {
	
	/**
	 * A data target initialized to the target name.
	 */
	final String TARGET = "PAULO SERGIO LICCIARDI MESSEDER BARRETO";
	
	/**
	 * Viable characters in charArray we can use.
	 */
	public static final char[] charArray = {'A', 'B', 'C', 'D', 'E',
			'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
			'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '-', '\''};
	
	/**
	 * This Function mutates the string in this Genome using the following rules:
	 * 1. with mutationRate chance add a randomly selected character to a randomly 
	 *    selected position in a string.
	 * 2. with mutationRate chance delete a single character from a randomly 
	 * 	  selected position of the string but do this only if the string has length
	 * 	  at least 2.
	 * 3. For each character in the string, with mutationRate chance the character
	 *    is replaced by a randomly selected character.
	 */
	void mutate();
	
	/**
	 * This function will update the current Genome by crossing it over with other
	 * 
	 * Create the new list by following these steps for each index in the new string
	 * (which can be of any length between the minimum and the maximum of the two 
	 * string lengths), starting at the first index:
	 * 		1. Randomly choose one of the two parent strings.
	 * 		2. If the parent string has a character at this index(i.e. it is long enough)
	 * 		   copy that character into the new list. Otherwise end the new list here
	 * 		   and replace the old list.
	 */
	void crossover(Genome other);
	
	/**
	 * Returns the fitness(f) of the Genome calculated using the following algorithm:
	 * 		1. Let n be the length of the current string.
	 * 		2. Let m be the length of the target string.
	 * 		3. Let f be initialized to |m-n|.
	 * 		4. For each character position 0 <= i <= L add one to f if the character 
	 * 		   in the current string is different from the character in the target string
	 * 		   (or if no character exists at that position in either string). Otherwise
	 * 		   leave f unchanged.
	 * 
	 * @return f
	 */
	Integer fitness();
	
	/**
	 * This Genome will display the Genome's current string and fitness in an easy to read
	 * format.
	 * 
	 * @return currentString
	 */
	String toString();
	
}
