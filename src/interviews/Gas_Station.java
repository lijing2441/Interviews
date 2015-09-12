package interviews;

public class Gas_Station {
	/**
	 * There are N gas stations along a circular route, where the amount of gas
	 * at station i is gas[i].
	 * 
	 * You have a car with an unlimited gas tank and it costs cost[i] of gas to
	 * travel from station i to its next station (i+1). You begin the journey
	 * with an empty tank at one of the gas stations.
	 * 
	 * Return the starting gas station's index if you can travel around the
	 * circuit once, otherwise return -1.
	 * 
	 * Note: The solution is guaranteed to be unique.
	 */
	// every time found the gasInTank is not enough, 
	// we need to change to the next start
	// O(n), O(1)

	public int canCompleteCircuit(int[] gas, int[] cost) {
		if (gas == null || cost == null || (gas.length != cost.length))
			return -1;

		int gasInTank = 0, gasInTotal = 0;
		int start = 0;
		for (int i = 0; i < gas.length; i++) {
			gasInTank += gas[i] - cost[i];
			if (gasInTank < 0) {
				start = i + 1;
				gasInTotal += gasInTank;
				gasInTank = 0;
			}
		}
		return ((gasInTotal + gasInTank) < 0 ? -1 : start);
	}
}
