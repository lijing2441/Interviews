package interviews;

import java.util.HashSet;

/**
 * Write a Java method that takes an array of "sets" of String objects, and
 * determines whether _all_ sets in the array are equivalent.
 * 
 * Each "set" in the input array is represented as an array of String objects,
 * in no particular order, and possibly containing duplicates. Nevertheless,
 * when determining whether two of these "sets" are equivalent, you should
 * disregard order and duplicates. For example, the sets represented by these
 * arrays are all equivalent:
 * 
 * {"a", "b"} {"b", "a"} {"a", "b", "a"}
 * 
 * @author Tina
 *
 */
public class All_String_Sets_Equivalent {
	public static boolean allStringSetsIdentical(String[][] sets) {
		if (sets == null || sets.length < 2)
			return true;
		// count the number of different strings in the first set
		HashSet<String> hash = new HashSet<String>();
		for (String s : sets[0]) {
			hash.add(s);
		}
		// check whether all sets have same components
		for (int i = 1; i < sets.length; i++) {
			HashSet<String> visited = new HashSet<String>();
			for (int j = 0; j < sets[i].length; j++) {
				if (!hash.contains(sets[i][j])) {
					return false;
				} else {
					visited.add(sets[i][j]);
				}
			}
			if (visited.size() != hash.size())
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		String[][] test = { { "b", "a" }, { "a", "b" } };
		boolean identical = allStringSetsIdentical(test);
		if (identical)
			System.out.println("true");
		else
			System.out.println("false");
	}
}
