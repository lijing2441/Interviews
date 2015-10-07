package interviews;

public class Snapchat_K_Snap_points {
	/**
	Consider a grid where all the points are represented by integers.

	.........................................
	...(-2,2)  (-1,2)  (0,2)  (1,2)  (2,2)...
	...(-2,1)  (-1,1)  (0,1)  (1,1)  (2,1)...
	...(-2,0)  (-1,0)  (0,0)  (1,0)  (2,0)...
	...(-2,-1) (-1,-1) (0,-1) (1,-1) (2,-1)...
	...(-2,-2) (-1,-2) (0,-2) (1,-2) (2,-2)...
	..........................................

	k-Snap point: A point whose digits sum up to less than or equal to k. In this
	question, we ignore all the signs in the number.  For example, (1, 0) is a 
	1-snap point, (0, 10) is a 1-snap point, and (-100, 0) is also a 1-snap point; 
	however (11, 0) is not a 1-snap point.

	Question 1: Implement the following function
	boolean isSnapPoint(Point p, int k). 

	Returns true if p is a k-snap point, and false otherwise.

	Reachable k-snap point: A k-snap point is a reachable k-snap point if there is 
	a path from (0,0) to that point, where the path only consists of k-snap points.

	Question 2: Given k, return all the reachable k-snap points.
	*/
	public static boolean isSnapPoint(Point p, int k) {
		int x = Math.abs(p.x);
		int y = Math.abs(p.y);
		int sum = 0;
		while (x > 0) {
			sum += x % 10;
			x /= 10;
			if (sum > k) return false;
		}
		while (y > 0) {
			sum += y % 10;
			y /= 10;
			if (sum > k) return false;
		}
		return true;
	}
	
}
