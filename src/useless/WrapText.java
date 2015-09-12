package useless;
/**
 * Write a Java method that takes a String of text and inserts newline characters
 * ('\n') so that each line of text has at most maxCharsPerLine characters (not
 * counting the newline).
 * It may also be necessary to delete some spaces.
 * The method should obey the following rules:
 *
 * 1. Words should never be broken up, unless a word has more than maxCharsPerLine
 * characters in it.
 * 2. A word is a sequence of characters that contains no spaces and no newline characters.
 * 3. Existing newline characters should be preserved.
 * 4. The number of newline characters added should be as small as possible.
 * 5. Lines should not be made shorter than necessary. In other words, if an
 * additional word would fit on a line it should go on that line.
 * 6. Words should be separated by either a single space or by one or more newlines.
 * 7. Lines cannot start or end with any spaces.
 * Assume that the text does not contain any other whitespace characters besides
 * spaces.
 * 
 * The signature for your method should be:
 * public static String wrapText(String text, int maxCharsPerLine)
 */
public class WrapText {
	public static String wrapText(String text, int maxCharsPerLine) {
		if (text == null || text.length() < maxCharsPerLine)
			return new String(text + "\n");
		StringBuffer sb = new StringBuffer();
		String[] words = text.split("\\s+");

		int remaining = breakWord(sb, words[0], maxCharsPerLine);

		for (int i = 1; i < words.length; i++) {
			if (remaining + 1 + words[i].length() <= maxCharsPerLine) {
				sb.append(" ").append(words[i]);
				remaining += 1 + words[i].length();
			} else {
				sb.append("\n");
				remaining = breakWord(sb, words[i], maxCharsPerLine);
			}
		}
		return sb.toString();
	}

	public static int breakWord(StringBuffer sb, String toBreak, int max) {
		while (toBreak.length() > max) {
			sb.append(toBreak.substring(0, max)).append(" ").append("\n");
			toBreak = toBreak.substring(max);
		}
		sb.append(toBreak);
		return toBreak.length();
	}

	public static void main(String[] args) {
		String text;
		int maxCharsPerLine;

		maxCharsPerLine = 12;
		text = "Here is an example of text justification";
		System.out.println(wrapText(text, maxCharsPerLine));

		text = "This is an example of word wrapper";
		System.out.println(wrapText(text, maxCharsPerLine));

		text = "abcdefghijklmnopqrstuvwxyz";
		System.out.println(wrapText(text, maxCharsPerLine));

	}
}
