package interviews;

import java.util.ArrayList;
import java.util.Scanner;

public class Print_X_to_j_or_k {
	/**
	 * 	Problem: Print out all the possible strings of a given string, in which 'X' could be 'j' or 'k'.
	 *  
	 *  For example: "abXbX" -> "abjbj", "abjbk", "abkbj", "abkbk".
	 *  
	 *  @complexity: O(lengthOfCandidates^(# of X)); exponential，如上例即为：O(2^2)
	 *  			 取决于答案个数。
	 */
	// X -> 对应X，可为任意character ; candidates[] -> 对应 {j, k}
	public static void printXtojk(String str, char X, char[] candidates){
		if(str == null || str.length() == 0) return;
		ArrayList<String> res = new ArrayList<String>();
		res.add(str);
		for(int i = 0; i < str.length(); i++){
			// not X, ignore
			if(str.charAt(i) != X) continue;
			// we have a X currently at index i
			ArrayList<String> tmp = new ArrayList<String>();
			for(String s: res){
				char[] ch = s.toCharArray();
				for(char c: candidates){
					ch[i] = c;
					String newWord = new String(ch);
					tmp.add(newWord);
				}
			}
			res = tmp;
		}
		for(String s: res){
			System.out.println(s);
		}
	}
	/*
	public static void helper(String str, char X, char[] candidates, int index){
		if(index == str.length()){
			System.out.print(str + " ");
		}
		for(int i = index; i < str.length(); i++){
			if(str.charAt(i) != X) helper(str, X, candidates, i + 1);
			else{
				char[] ch = str.toCharArray();
				for(int j = 0; j < candidates.length; j++){
					ch[i] = candidates[j];
					String newWord = ch.toString();
					helper(newWord, X, candidates, i + 1);
				}
			}
		}
	}
	*/
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a string to start: ");
		String input = in.next();
		char X = 'X';
		char[] candidates = {'j', 'k'};
		printXtojk(input, X, candidates);
	}
}
