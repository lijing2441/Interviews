
package interviews;

public class Find_Least_Common_Multiple {
	/**
	 * 找最小公约数, least common multiple and Greatest Common Factor
	 *
	 * Note: 
	 * if overflow, use long; 
	 * check boundary! if all positive, fine; if one = 0, return 0; if negative, change to positive
	 * 
	 * using Euclidean algorithm: the largest number that divides both of them without leaving a remainder.
	 * 
	 * Property: the greatest common divisor of two numbers does not change if the larger number 
	 * 			 is replaced by its difference with the smaller number.
	 */
	public static int gcd(int a, int b){
		while (b > 0){
			int temp = b;
	        b = a % b; // % is remainder
	        a = temp;
	    }
	    return a;
	}

	private static int gcd(int[] input){
	    int result = input[0];
	    for(int i = 1; i < input.length; i++){
	    	result = gcd(result, input[i]);
	    }
	    return result;
	}
	
	/**
	 * LCM: use the property of the GCD: reduction of GCD: LCM(a, b) = (a * b) / GCD(a, b);
	 */
	public static int lcm(int a, int b){
		return (a * b) / gcd(a, b);
	}
	public static int lcm(int[] input){
		int result = input[0];
		for(int i = 1; i < input.length; i++){
			result = lcm(result, input[i]);
		}
		return result;
	}
	
	//driver test
	public static void main(String[] args){
		int x = 21;
		int y = 15;
		System.out.println("GCD of " + x + " and " + y + " is: "+ gcd(x, y));
		System.out.println("LCM of " + x + " and " + y + " is: "+ lcm(x, y));
	}
}
