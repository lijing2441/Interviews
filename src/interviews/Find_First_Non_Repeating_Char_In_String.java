package interviews;

public class Find_First_Non_Repeating_Char_In_String {
	/**
	 * Given a string, find the first non-repeating character in it. For
	 * example, if the input string is “GeeksforGeeks”, then output should be
	 * ‘f’ and if input string is “GeeksQuiz”, then output should be ‘G’.
	 * 
	 * Method 1: use a char array to keep track of the numbers 
	 */
	//Basic method => O(2N)
	public char firstNonRepeating(String str){
		char[] ch = str.toCharArray();
		//if ASCII
		int[] count = new int[128];
		//count the number of repeating times in the string
		for(int i = 0; i < str.length(); i++){
			count[ch[i]]++;
		}
		//find the first non-repeating
		for(int i = 0; i < str.length(); i++){
			if(count[ch[i]] == 1) return ch[i];
		}
		//all characters are duplicated
		return ' ';
	}
	/**
	 * Optimized version: instead of loop through the string the second time, 
	 * 					  we loop through the count array => O(n+k), k is the length of the count array
	 * 
	 * In real situations, string is expected to be much larger than your alphabet. 
	 * Take DNA sequences for example: they could be millions of letters long with 
	 * an alphabet of just 4 letters. 
	 * We can augment the count array by storing not just counts but also the index 
	 * of the first time you encountered the character. 
	 * So when it comes to finding the first non-repeater, we just have to scan the 
	 * count array, instead of the string.
	 */
	public char firstNonRepeating2(String str){
		char[] ch = str.toCharArray();
		//record the count, number of different characters involved
		int[] count = new int[128];
		//record the corresponding first appearing index
		int[] firsts = new int[128];
		for(int i = 0; i < str.length(); i++){
			count[ch[i]]++;
			if(count[ch[i]] == 1) firsts[ch[i]] = i;
		}
		//find the first non-repeating
		int index = Integer.MAX_VALUE;
		for(int i = 0; i < 128; i++){
			if(count[i] == 1 && index > firsts[i]){
				index = firsts[i];
			}
		}
		return str.charAt(index);
	}
}
