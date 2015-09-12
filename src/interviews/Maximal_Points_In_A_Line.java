package interviews;

import java.util.HashMap;

public class Maximal_Points_In_A_Line {
	/**
	 * Given n points on a 2D plane, find the maximum number of points that lie
	 * on the same straight line.
	 * 
	 * @logic:
	 * 
	 * O(n^2), O(n)
	 * 
	 * Construct a map with slope as key and number of points with this slope as
	 * value, then we have one current point and a slope, we can determine a line and
	 * get the number of points on it.
	 * 
	 * Start with one point and move forward with the other points, but not back since
	 * the previous points have been counted.
	 * 
	 * Pay attention to the duplicate points and the vertical lines
	 */

	public int maxPoints(Point[] points) {
		if (points == null || points.length == 0)
			return 0;

		int max = 1;
		HashMap<Double, Integer> map = new HashMap<Double, Integer>();

		for (int i = 0; i < points.length; i++) {
			map.clear();
			map.put((double) Integer.MIN_VALUE, 1); // mark the first point with MIN_VALUE

			int dups = 0;
			for (int j = i + 1; j < points.length; j++) {
				// this is duplicates, which is on every line of points[i]
				if (points[i].x == points[j].x && points[i].y == points[j].y) {
					dups++;
					continue;
				}
				// these are the points of same slope, but they are not on the same line
				// Mark the vertical slope with MAX_VALUE
				double slope = points[i].x == points[j].x ? (double) Integer.MAX_VALUE: 
					0.0 + ((double) (points[i].y - points[j].y) / (double) (points[i].x - points[j].x));
				if (map.containsKey(slope)) {
					map.put(slope, map.get(slope) + 1);
				} else {
					map.put(slope, 2);
				}
			}

			for (int value : map.values()) {
				if (dups + value > max)
					max = dups + value;
			}
		}
		return max;
	}
}
