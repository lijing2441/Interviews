package interviews;

public class Split_Sentence_Into_Words {
	// if other regular expression exists, ask for it, such as "\\s+" "[^\w]"
	public String[] splitSentenceIntoWords(String s) {
		String[] words = s.split("\\s+");
		return words;
	}

	// reverse O(n), O(n)
	public String reverseWords(String s) {
		String _s = s.trim();
		String[] words = _s.split(" +");
		StringBuffer res = new StringBuffer();
		for (int i = words.length - 1; i >= 0; i--) {
			res.append(words[i] + " ");
		}
		String reverse = new String(res);
		String _reverse = reverse.trim();
		return _reverse;
	}
}
