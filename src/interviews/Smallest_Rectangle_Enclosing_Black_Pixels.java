package interviews;

public class Smallest_Rectangle_Enclosing_Black_Pixels {
	/**
	 * An image is represented by a binary matrix with 0 as a white pixel and 1
	 * as a black pixel. The black pixels are connected, i.e., there is only one
	 * black region. Pixels are connected horizontally and vertically. Given the
	 * location (x, y) of one of the black pixels, return the area of the
	 * smallest (axis-aligned) rectangle that encloses all black pixels.
	 * 
	 * For example, given the following image:
	 * 
	 * [ "0010", 
	 *   "0110", 
	 *   "0100" ] 
	 *   
	 * and x = 0, y = 2, Return 6.
	 */
	// 找出来x的range和y的range
	public int minArea(char[][] image, int x, int y) {
		int m = image.length;
		if (m == 0) return 0;
		int n = image[0].length;
		if (n == 0) return 0;
		int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (image[i][j] == '1') {
					minX = Math.min(minX, i);
					minY = Math.min(minY, j);
					maxX = Math.max(maxX, i);
					maxY = Math.max(maxY, j);
				}
			}
		}
		if (minX == Integer.MAX_VALUE) return -1;
		return (maxX - minX + 1) * (maxY - minY + 1);
    }
	// binary search
	
	
	
	public static void main(String[] args) {
		char[][] image = { {'0', '0', '1', '0'}, 
						   {'0', '1', '1', '0'}, 
						   {'0', '1', '0', '0'}
						 };
		System.out.println(new Smallest_Rectangle_Enclosing_Black_Pixels().minArea(image, 0, 2));
	}
}
