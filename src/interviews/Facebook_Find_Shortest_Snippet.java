package interviews;

public class Facebook_Find_Shortest_Snippet {
	/**
	 * 比如给一个document是A,X,X,B,A,X,B，Query是A,B
	 * 
	 * 如果query有序，那么要返回A,X,B
	 * 如果query无序，那么要返回B,A => minimal sliding window
	 * 如果Document非常大，怎么优化？
	 */
	public static String 有序Query(String doc, String query) {
		int ptrQ = 0;
		int lenD = doc.length(), lenQ = query.length();
		int minStart = 0, minLen = lenD + 1;
		int start = 0;
		
		for (int i = 0; i < lenD; i++) {
			if (doc.charAt(i) == query.charAt(0)) {
				start = i;
				for (int j = i; j <= lenD; j++) {
					if (ptrQ == lenQ) {
						int curLen = j - start;
						if (minLen > curLen) {
							minStart = start;
							minLen = curLen;
							ptrQ = 0;
							break;
						}
					}
					if (j < lenD && doc.charAt(j) == query.charAt(ptrQ)) {
						ptrQ++;
					}
				}
			}
		}
		if (minLen > lenD) return "None";
		return doc.substring(minStart, minStart + minLen);
	}
	public static void main(String[] args) {
		String doc = "AXXBAXB";
		String query = "AB";
		System.out.println(有序Query(doc, query));
	}
}
