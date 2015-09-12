package interviews;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Repeated_DNA_Sequences {
	/**
	 * All DNA is composed of a series of nucleotides abbreviated as A, C, G,
	 * and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes
	 * useful to identify repeated sequences within the DNA.
	 * 
	 * Write a function to find all the 10-letter-long sequences (substrings)
	 * that occur more than once in a DNA molecule.
	 * 
	 * For example,
	 * 
	 * Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
	 * 
	 * Return: ["AAAAACCCCC", "CCCCCAAAAA"].
	 */
	public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<String>();
        if(s == null || s.length() <= 10) return res;
        int len = s.length();
        HashSet<String> resultSet = new HashSet<String>();
        HashSet<Integer> checkSet = new HashSet<Integer>();
        
        for(int i = 0; i + 10 <= len; i++) {
            String cur = s.substring(i, i + 10);
            int code = encode(cur);
            if(!checkSet.add(code)) {
                resultSet.add(cur);
            }
        }
        for(String str : resultSet) {
            res.add(str);
        }
        return res;
    }
    public int encode(String s) {
        int res = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == 'A') {
                res = 4 * res;
            } else if(s.charAt(i) == 'C') {
                res = 4 * res + 1;
            } else if(s.charAt(i) == 'G') {
                res = 4 * res + 2;
            } else if(s.charAt(i) == 'T') {
                res = 4 * res + 3;
            }
        }
        return res;
    }
}
