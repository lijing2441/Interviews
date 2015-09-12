package interviews;

public class Implement_toUpper_using_toLower {
	/**
	 * Given a list of characters and the function toLower, implement toUpper.
	 */
	//O(n)
	// if not alphabetic, break
	public char[] toUpper(char[] arr){
		char[] lower = toLower(arr);
		int diff = 'A' - 'a';
		for(int i = 0; i < lower.length; i++){
			if(!Character.isAlphabetic(lower[i])) continue;
			lower[i] = (char) (lower[i] + diff);
		}
		return lower;
	}
	
	
	//given
	public char[] toLower(char[] arr){
		return new char[128];
	}
}
