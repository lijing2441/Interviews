package interviews;

import java.util.ArrayList;
import java.util.List;

public class Valid_Words_In_Dict {
	/**
	 * Given a dictionary and a list of letters find all valid words that can be built with the letters.
	 * 
	 * @analysis Assume that letters only contains letters from a to z.
	 * 
	 * => Use an integer array to count the number of occurrence of a character in letters.
	 * => For each word in the dictionary, check if there is a specific character in the word 
	 * that appears more than allowed, if not, add this word into result.
	 * 
	 * So we can see that the time complexity is O(m*k) with m is number of word in the dictionary 
	 * and k is the maximum total of characters in a word.
	 * 
	 */
	public List<String> findValidWords(List<String> dict, char letters[]){
		int []avail = new int[26];
		// check all the letters in the given char
        for(char c : letters){
            int index = c - 'a';
            avail[index]++;
        }
        List<String> result = new ArrayList<String>();
        for(String word: dict){
        	// check the current string in the dict
            int []count = new int[26];
            boolean valid = true;
            for(char c : word.toCharArray()){
                int index = c - 'a';
                count[index]++;
                if(count[index] > avail[index]){
                    valid = false;
                    break;
                }
            }
            if(valid){
                result.add(word);
            }
        }
        return result;
	}
}
