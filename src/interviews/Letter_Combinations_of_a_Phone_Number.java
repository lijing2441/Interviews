package interviews;

import java.util.ArrayList;

public class Letter_Combinations_of_a_Phone_Number {
	/**
	 * Given a digit string, return all possible letter combinations that the
	 * number could represent.
	 * 
	 * A mapping of digit to letters (just like on the telephone buttons) is
	 * given below.
	 * 
	 * Input:Digit string "23" 
	 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
	 * 
	 * @analysis
	 * 在这题里面，每一个数字基本上有三种可能字符，
	 * 所以任意一个节点会有三种扩展，如果数字串长为L的话，时间复杂度就是O(3^L).﻿
	 */
	public String[] board = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
	public ArrayList<String> letterCombinations(String digits){
		ArrayList<String> res = new ArrayList<String>();
		res.add("");
		if(digits == null || digits.length() == 0) return res;
		int n = digits.length();
		for(int i = 0; i < n; i++){
			ArrayList<String> tmp = new ArrayList<String>();
			String cur = board[digits.charAt(i) - '0'];
			for(String s: res){
				for(int j = 0; j < cur.length(); j++){
					String st = s + cur.charAt(j);
					tmp.add(st);
				}
			}
			res = tmp;
		}
		return res;
	}
	
	// recursive method
	public ArrayList<String> letterCombinationsRecur(String digits){
		ArrayList<String> res = new ArrayList<String>();
		helper(digits, 0, res, "");
		return res;
	}
	public void helper(String digits, int index, ArrayList<String> res, String cur){
	    if(index == digits.length()){
	        res.add(cur);
	        return;
	    }
	    int letterIdx = digits.charAt(index) - '0';
	    String choice = board[letterIdx];
	    for(int i = 0; i < choice.length(); i++){
	        String next = cur + choice.charAt(i);
	        helper(digits, index + 1, res, next);
	    }
	}
}
