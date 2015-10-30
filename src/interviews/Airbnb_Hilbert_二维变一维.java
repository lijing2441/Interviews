package interviews;

public class Airbnb_Hilbert_二维变一维 {
	public int[][] position = {{0, 1}, {3, 2}};
	public int[][] cellSeq = {{0, 3, 2, 1}, {0, 1, 2, 3}, {0, 1, 2, 3}, {2, 1, 0, 3}};
	public int[][] pattern = {{3, 0, 1, 2}, {0, 1, 2, 3}, {0, 1, 2, 3}, {0, 3, 2, 1}};
	public int transferHilbert(int x, int y, int order) {
		int offset = 0;
		int patternID = 1;
	
		for (int i = order; i >= 1; i--) {
			offset <<= 2;
			int mask = 1 << (i - 1);
			int posX = (mask & x) == 0 ? 0 : 1;
			int posY = (mask & y) == 0 ? 0 : 1;
			
			int pos = position[posX][posY];
			int id = cellSeq[patternID][pos];
			offset |= id;
			
			patternID = pattern[patternID][pos];
		}
		return offset;
	}
	
	public static void main(String[] args) {
		System.out.println(new Airbnb_Hilbert_二维变一维().transferHilbert(1, 3, 2));
	}
}
