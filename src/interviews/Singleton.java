package interviews;

public class Singleton {
	/**
	 * In Java:
	 * 
	 * A a = A.getInstance(); A b = A.getInstance(); a should equal to b.
	 */
	public static Solution instance;

	public static Solution getInstance() {
		// write your code here
		if (instance == null) {
			instance = new Solution();
		}
		return instance;
	}
}
class Solution {
	public Solution(){
		
	}
}
