package interviews;

import java.util.ArrayList;
import java.util.List;

public class Pinterest_Longest_Common_Substring_Between_Two_Words_In_Array {
	/**
	 * Given an array of words find what is and how long is the length of the
	 * longest common substring between two words in the array
	 */
	// 应该是用suffix tree。。。400行，太烦了，不写。
	// simple two strings comparison: 见LCS
	public static String longestCommonSubstringInArray(List<String> list) {
		//SuffixTree suffixTree = new SuffixTree();
		return "";
	}
	public static void main(String[] args) {
		List<String> l = new ArrayList<String>();
		l.add("HarryPotterAB");
		l.add("HarryPotterCD");
		l.add("HarryPottEF");
		l.add("Harry");
		l.add("HarryPotterfg");
		l.add("LarryPotterAB");
		System.out.println(longestCommonSubstringInArray(l) + " , it should be 12.");
	}
}
class SuffixTreeNode {
	public String key;
	public List<SuffixTreeNode> children = new ArrayList<SuffixTreeNode>();
	public boolean isEnd;
	public int minStartIndex; // ?
	public SuffixTreeNode() {
		this.key = "";
		this.minStartIndex = -1;
	}
	public SuffixTreeNode(String key) {
		this.key = key;
	}
	public String toString(){             
		return this.key + (this.isEnd?"$":"") + "(" + children.size() +")";  
    }
}
class SuffixTree {
	public SuffixTreeNode root;
	public String text;
	public char[] terminators;
	public SuffixTree(String text) {
		this.text = text;
		
	}
}