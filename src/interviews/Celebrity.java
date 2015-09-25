package interviews;

/**
 * 
 * @author Tina
 *
 *         In a party of N people, only one person is known to everyone. Such a
 *         person may be present in the party, if yes, (s)he doesn't know anyone
 *         in the party. We can only ask questions like "does A know B?". Find
 *         the stranger (celebrity) in minimum number of questions.
 * 
 * @logic
 * 
 *         1. If A knows B, then A can't be celebrity. Discard A, and B may be celebrity. 
 *         2. If A doesn't know B, then B can't be celebrity. Discard B, and A may be celebrity. 
 *         3. Repeat above two steps till we left with only one person. 
 *         4. Ensure the remained person is celebrity. (Why do we need this step?)
 *
 */
public class Celebrity {
	// 这个case，所有人必须认识名人，名人必须谁都不认识
	public int findCelebrity(int n) {
        if (n < 2) return -1;
        int potential = 0;
        for (int i = 1; i < n; i++) {
            if (knows(potential, i)) {
                potential = i;
            }
        }
        for (int i = 0; i < n; i++) {
            if (potential == i) continue;
            if (knows(potential, i) || !knows(i, potential)) return -1;
        }
        return potential;
    }
	
	boolean knows(int i, int j) {
		return false;
	}
	
	// Another case 别的做法
	// 不用所有人认识名人，名人必须谁都不认识
	public int searchCelebrity(int[] personIdArr){
		int n = personIdArr.length;
		if(n < 2) return -1; // base case, none or only 1 exists
		// the index of the potential celebrity
		int potential = 0;
		// check the potential
		for(int i = 1; i < n; i++){
			if(haveAcquiantance(potential, i)){
				potential = i;
			}
		}
		//check again if the potential is the celebrity
		// it's possible that no celebrity exists
		for(int i = 0; i < n; i++){
			if(i == potential) continue;
			if(haveAcquiantance(potential, i)) return -1;
		}
		return potential;
	}
	public boolean haveAcquiantance(int a, int b){
		return false;
	}
}
