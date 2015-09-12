package interviews;

public class Traveling_Salesman {
	/**
	 * There are many tourist sites and each has their own holiday. If you arrive there during the holiday,
	 * you can gain one gift. It costs you hours W_ij traveling from site_i to site_j. What's more, you can
	 * only travel once in one week. Now you are initially placed in one site, how do you plan your routine
	 * in this year to gain most gifts.
	 * 
	 * NP-hard: 
	 * 1. Convert all time values to hour for consistency 
	 * 2. Each site represents an interval. If not given in that format already, 
	 * 	  create an array containing start , end and index of site form which this interval was taken. 
	 * 	  Add an interval with start==end with index=start site. 
	 * 3. Sort that array based on end time 
	 * 4. Initialize count =0 and start with first interval and do the following till all intervals are exhausted: 
	 * 		4.1 Let i = current interval 
	 * 		4.2 Iteratively find the first j such that interval[j].start>=interval[i].end + w[i][j] 
	 * 			and is not in the same week as i 
	 * 		4.3 i = j ; ++count 
	 * 5. return count 
	 * 
	 * Plus: more thoughts: Lets say the year has only one holiday and all the sites are giving gifts. 
	 * Then the schedule is clear, but the way to travel to all of them not. Because it reduces to the TSP. 
	 * This problem is NP-hard.
	 * 
	 * DP might solve it.
	 * 
	 * Let us define a term C(S, i) be the cost of the minimum cost path visiting each vertex in set S 
	 * exactly once, starting at 1 and ending at i.
	 * 
	 * We start with all subsets of size 2 and calculate C(S, i) for all subsets where S is the subset, 
	 * then we calculate C(S, i) for all subsets S of size 3 and so on. Note that 1 must be present in
	 * every subset.
	 * 
	 * Recurrence:
	 * 		If size of S is 2 (only 1 and i)     S must be {1, i}, C(S, i) = dist(1, i) 
	 *		If size of S is greater than 2,      C(S, i) = min { C(S-{i}, j) + dist(j, i)} 
	 *										     where j belongs to S, j != i and j != 1
	 */
}
