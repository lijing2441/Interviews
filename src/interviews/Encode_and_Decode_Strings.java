package interviews;

import java.util.ArrayList;
import java.util.List;

public class Encode_and_Decode_Strings {
	/**
	 * Design an algorithm to encode a list of strings to a string. The encoded
	 * string is then sent over the network and is decoded back to the original
	 * list of strings.
	 * 
	 * Machine 1 (sender) has the function:
	 * 
	 * string encode(vector<string> strs) { // ... your code return
	 * encoded_string; } Machine 2 (receiver) has the function: vector<string>
	 * decode(string s) { //... your code return strs; } So Machine 1 does:
	 * 
	 * string encoded_string = encode(strs); and Machine 2 does:
	 * 
	 * vector<string> strs2 = decode(encoded_string); strs2 in Machine 2 should
	 * be the same as strs in Machine 1.
	 * 
	 * Implement the encode and decode methods.
	 * 
	 * @param strs
	 * @return
	 */
	// Encodes a list of strings to a single string.
	public String encode(List<String> strs) {
		String res = "";
		for (String s : strs) {
			// encode the strings to len + " " + string
			res += Integer.toString(s.length()) + " " + s;
		}
		return res;
	}

	// Decodes a single string to a list of strings.
	public List<String> decode(String s) {
		List<String> res = new ArrayList<String>();
		// index pointing where we have processed
		int i = 0;
		while (i < s.length()) {
			int n = 0;
			StringBuilder sb = new StringBuilder();
			while (i < s.length() && Character.isDigit(s.charAt(i))) {
				n = n * 10 + (int) (s.charAt(i++) - '0');
			}
			// skip the space
			i++;
			for (int j = 0; j < n; j++) {
				sb.append(s.charAt(i++));
			}
			res.add(sb.toString());
		}
		return res;
	}

}
