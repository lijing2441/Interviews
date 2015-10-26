package interviews;

public class Airbnb_HilbertCurve {
	int[][] cellSeq = {{0, 3, 2, 1}, {0, 1, 2, 3}, {0, 1, 2, 3}, {2, 1, 0, 3}};
    int[][] pattern = {{3, 0, 1, 2}, {0, 1, 2, 3}, {0, 1, 2, 3}, {1, 2, 3, 0}};
    int[][] position = {{0, 1}, {3, 2}};
    
    // 二维变一维
    public int pointToIndex(int x, int y, int order) {
        int offset = 0;
        int patternId = 1;
        
        for (int i = order; i >= 1; i--) {
            offset <<= 2;
            int mask = 1 << (i - 1);
            int posX = (x & mask) == 0 ? 0 : 1;
            int posY = (y & mask) == 0 ? 0 : 1;

            int pos = position[posX][posY];
            int id = cellSeq[patternId][pos];
            offset |= id;

            patternId = pattern[patternId][pos];
        }
        return offset;
    }

    public static void main(String[] args) {
        System.out.println(new Airbnb_HilbertCurve().pointToIndex(0, 0, 2));
        System.out.println(new Airbnb_HilbertCurve().pointToIndex(1, 0, 2));
        System.out.println(new Airbnb_HilbertCurve().pointToIndex(1, 1, 2));
        System.out.println(new Airbnb_HilbertCurve().pointToIndex(0, 1, 2));

        System.out.println(new Airbnb_HilbertCurve().pointToIndex(0, 2, 2));
        System.out.println(new Airbnb_HilbertCurve().pointToIndex(0, 3, 2));
        System.out.println(new Airbnb_HilbertCurve().pointToIndex(1, 3, 2));
        System.out.println(new Airbnb_HilbertCurve().pointToIndex(1, 2, 2));

        System.out.println(new Airbnb_HilbertCurve().pointToIndex(2, 2, 2));
        System.out.println(new Airbnb_HilbertCurve().pointToIndex(2, 3, 2));
        System.out.println(new Airbnb_HilbertCurve().pointToIndex(3, 3, 2));
        System.out.println(new Airbnb_HilbertCurve().pointToIndex(3, 2, 2));

        System.out.println(new Airbnb_HilbertCurve().pointToIndex(3, 1, 2));
        System.out.println(new Airbnb_HilbertCurve().pointToIndex(2, 1, 2));
        System.out.println(new Airbnb_HilbertCurve().pointToIndex(2, 0, 2));
        System.out.println(new Airbnb_HilbertCurve().pointToIndex(3, 0, 2));
    }
}
