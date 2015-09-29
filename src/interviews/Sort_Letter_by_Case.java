package interviews;

public class Sort_Letter_by_Case {
	/**
	 * Given a string which contains only letters. Sort it by lower case first
	 * and upper case second.
	 * 
	 * Example For "abAcD", a reasonable answer is "acbAD"
	 */
	public void sortLetters(char[] chars) {
        int lowIndex = -1, upIndex = chars.length;
        for (int i = 0; i < upIndex; i++) {
            if (Character.isLowerCase(chars[i])) {
                lowIndex++;
                swap(chars, i, lowIndex);
            } else {
                upIndex--;
                swap(chars, i, upIndex);
                i--;
            }
        }
    }
    public void swap(char[] arr, int i, int j) {
        if(i != j) {
            char tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }
}
