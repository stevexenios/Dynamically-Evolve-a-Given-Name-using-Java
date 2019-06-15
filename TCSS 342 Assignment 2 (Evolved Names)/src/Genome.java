import java.util.Arrays;
import java.util.Random;

/*
 * TCSS - 342 Data Structures
 * Assignment 2 - Evolved Names
 */

/**
 * This Genome class will contain a list of characters from:
 * 
 * 'A', 'B', 'C', 'D', 'E', 
 * 'F', 'G', 'H', 'I', 'J', 
 * 'K', 'L', 'M', 'N', 'O',
 * 'P', 'Q', 'R', 'S', 'T',
 * 'U', 'V', 'W', 'X', 'Y',
 * 'Z', ' ', '-', '\''
 * 
 * Strings in the virtual world will be represented by this class.
 * 
 * Genome class must:
 * 		1. Have some internal representation of the string of characters.
 * 		2. Initialize a new string to the default value 'A'.
 * 		3. Be able to mutate by possibly:
 * 		   a. adding a new character somewhere in the string.
 * 		   b. deleting a randomly selected character from the string.
 * 		   c. changing a character in the string to a different value.
 * 	
 * 		4. Be able to crossover with another genome (given two Genomes 
 * 		   create a third that is a combination of the two).
 * 		5. Be able to measure Genome fitness. Using one of the two zero-based
 * 		   fitness methods listed here:
 * 		   a. calculate how close the string in the Genome is from your name
 * 		      using the simple method detailed below.
 * 		   b. (Optional) calculate how close the string in the Genome is from
 * 			  your name using the Levenshtein edit distance.
 * 		6. Display output the string and its fitness in an easy to read format.
 * 
 * @author Steve Mwangi
 * @version Spring 2019
 */
public class Genome implements iGenome, Comparable<Genome> {
	
	private double mutationRate;
	
	/** Random variable for generating random values. */
	private Random randomSelector = new Random();
	
	/** String to start with. */
	private StringBuilder sb = new StringBuilder("A");

	/**
	 * A constructor that initializes a Genome with value 'A' and
	 * assigns the internal mutation rate.
	 * 
	 * 0 <= mutationRate <= 1
	 */
	public Genome(double mutationRate) {
		this.mutationRate = mutationRate;
	}

	/**
	 * A copy constructor that initializes a Genome with the same
	 * values as the input gene.
	 */
	public Genome(Genome gene) {
		this.mutationRate = gene.mutationRate;
		this.sb = new StringBuilder(gene.sb.toString());
	}
	
	/**
	 * This Function mutates the string in this Genome using the following rules:
	 * 1. with mutationRate chance add a randomly selected character to a randomly 
	 *    selected position in a string.
	 * 2. with mutationRate chance delete a single character from a randomly 
	 * 	  selected position of the string but do this only if the string has length
	 * 	  at least 2.
	 * 3. For each character in the string, with mutationRate chance the character
	 *    is replaced by a randomly selected character.
	 *    
	 * (According to extra lecture on Assignment 2: We can use a
	 * random double < mutationRate to execute mutations.)
	 */
	public void mutate() {
		
		/** Randomizing values for mutation cases. */
		double case_1 = randomSelector.nextDouble();
		double case_2 = randomSelector.nextDouble();
		double case_3 = randomSelector.nextDouble();
		
		/** To increase chance of mutation, thus keeping generation cycle < 2000. */
		case_1 *= 0.5;
		case_2 *= 0.25;
		case_3 *= 0.15;
		
		/**
		 * Mutation according to 1: Add random new character to
		 * a random position in String.
		 */
		if(case_1 <= mutationRate) {
			char randomCharacter = charArray[randomSelector.nextInt(charArray.length)];
			// Get random number less than or equal to (string length + 1)
			int position = randomSelector.nextInt(sb.length()+1);
			
			if(sb.length() == position) {
				sb.append(randomCharacter);
			} else {
				// Create a substring from where we are adding
				String subString = sb.substring(position);
				// Append the Char + substring
				sb.append(randomCharacter);
				sb.append(subString);
			}
		}
		
		/**
		 * Mutation according to 2: Possibly deleting a randomly selected
		 * character from the string.
		 */
		if(sb.length() > 1 && mutationRate >= case_2) {
			int w = randomSelector.nextInt(sb.length());
			sb.deleteCharAt(w);
		}
		
		/**
		 * Mutation according to 3: Possibly changing a character in the
		 * String to a different value.
		 */
		if(mutationRate >= case_3) {
			int x = randomSelector.nextInt(charArray.length);
			char randomCharacter = charArray[x];
			int y = randomSelector.nextInt(sb.length());
			sb.insert(y, randomCharacter);
			sb.deleteCharAt(y+1);
			
		}
	}

	/**
	 * This function will update the current Genome by crossing it over with other.
	 * In short, be able to create a third genome that is a combination of the two
	 * parents.
	 * 
	 * Create the new list by following these steps for each index in the new string
	 * (which can be of any length between the minimum and the maximum of the two 
	 * string lengths), starting at the first index:
	 * 		1. Randomly choose one of the two parent strings.
	 * 		2. If the parent string has a character at this index(i.e. it is long enough)
	 * 		   copy that character into the new list. Otherwise end the new list here
	 * 		   and replace the old list.
	 */
	public void crossover(Genome other) {
		StringBuilder sb1 = new StringBuilder("");
		Boolean randomParent = randomSelector.nextBoolean();
		int i = 0;
		while(true) {
			if(randomParent) {
				if(i < other.sb.length()) {
					sb1.append(other.sb.charAt(i));
				} else {
					break; // if not long enough
				}
			} else { // other 50% chance
				if(i < sb.length()) {
					sb1.append(sb.charAt(i));
				} else {
					break; //...break while loop
				}
			}
			i++;
		}
		sb = sb1; // replace old list
	}

	/**
	 * Returns the fitness(f) of the Genome calculated using the following algorithm:
	 * 		1. Let n be the length of the current string.
	 * 		2. Let m be the length of the target string.
	 * 		3. Let f be initialized to |m-n|.
	 * 		4. For each character position 0 <= i <= L add one to f if the character 
	 * 		   in the current string is different from the character in the target string
	 * 		   (or if no character exists at that position in either string). Otherwise
	 * 		   leave f unchanged.
	 * 		5. L initialized to max(n, m).
	 * 
	 * @return f
	 */
	public Integer fitness() {
		int n = sb.length();
		int m = TARGET.length();
		int f = Math.abs(m-n);
		int L = Math.max(n, m);
		int lowerBound = Math.min(n, m);
		for(int i = 0; i < L; i++) {
			if(i < lowerBound) {
				if(sb.charAt(i) != TARGET.charAt(i)){
					f++;
				}
			} else { 
				f++; // Accounts for less characters in sb than in TARGET
			}
		}
		return f;
	}
	
	/**
	 * Fitness using:
	 * 
	 * Wagner-Fischer algorithm for calculating the Levenshtein edit distance.
	 * @return fitness
	 */
	public Integer fitnessWagnerFischer() {
		int fitness = 0;
		int n = sb.length();
		int m = TARGET.length();
		int[][] wagnerFischer = new int[n+1][m+1];
		Arrays.stream(wagnerFischer).forEach(a -> Arrays.fill(a,  0));
		
		for(int i = 0; i <= m; i++) {
			wagnerFischer[0][i] = i;
		}
		
		for(int j = 0; j <= n; j++) {
			wagnerFischer[j][0] = j;
		}
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= m; j++) {
				if(sb.charAt(i-1) == TARGET.charAt(j-1)) {
					wagnerFischer[i][j] = wagnerFischer[i-1][j-1];
				} else {
					wagnerFischer[i][j] = Math.min(wagnerFischer[i-1][j] + 1, Math.min(wagnerFischer[i][j-1] + 1, wagnerFischer[i-1][j-1] + 1));
				}
			}
		}
		
		fitness = (wagnerFischer[n][m] + Math.abs(n-m) + 1) / 2;
		
		return fitness;
	}
			
	/**
	 * This Genome will display the Genome's current string and fitness in an easy to read
	 * format.
	 * 
	 * @return currentString
	 */
	public String toString() {
		return sb.toString().concat(System.lineSeparator() + "Normal Fitness: " + fitness() + System.lineSeparator() + 
				"Wagner-Fischer Fitness: " + fitnessWagnerFischer());
	}

	@Override
	public int compareTo(Genome o) { // For use by the comparable interface for sorting
		if(fitness() > o.fitness()) {
			return 1;
		} else if (fitness() < o.fitness()) {
			return -1;
		} else {
			return 0;
		}
	}
}

/* Other method for Fitness determination: */
//int i = 0;
//while(i < L) {
//	if(i < lowerBound && sb.charAt(i) != TARGET.charAt(i)) {
//		f++;
//	} else {
//		f++; // Accounts for m-n characters not in sb 
//	}
//	i++;
//}

/* Other Way for getting chance for mutation rate. */
//int scope = (int) (1/mutationRate);
//
///**
// * Variable for holding a generated random number between 0.0 and 1.0 
// * and if it is less than the mutation rate then carry out the operation.
// */
//int chance = randomSelector.nextInt(scope);
//

