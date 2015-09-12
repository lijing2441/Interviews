package interviews;

public class Rectangle_Area {
	/**
	 * Find the total area covered by two rectilinear rectangles in a 2D plane.
	 * 
	 * Each rectangle is defined by its bottom left corner and top right corner
	 * as shown in the figure.
	 * 
	 * Rectangle Area Assume that the total area is never beyond the maximum
	 * possible value of int.
	 */
	// to avoid overflow, need to compare first
	public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
		int innerHeight = 0, innerWidth = 0;
        if(Math.min(H, D) > Math.max(B, F)) innerHeight = Math.min(H, D) - Math.max(B, F);
        if(Math.min(C, G) > Math.max(A, E)) innerWidth = Math.min(C, G) - Math.max(A, E);
        
        int area1 = (D-B) * (C-A);
        int area2 = (H-F) * (G-E);
        int res = area1 + area2 - innerHeight * innerWidth;
        return res;
    }
}
