package interviews;

import java.util.ArrayList;
import java.util.List;

public class Facebook_判断能否构成多边形 {
	/**
	 * 按照顺序连，要一个个点判断新加的线段是不是与前面相交，要考虑清楚全部共线和存在重合点的情况，
	 */
	// 主要function
	private static boolean isValidPolygon(List<Point> points) {
		// if point overlap
		List<Point> newPoints = new ArrayList<Point>();
		for (int i = 0; i < points.size(); i++) {
			// 重复点不考虑
			if (i != 0 && points.get(i).x == points.get(i - 1).x && points.get(i).y == points.get(i - 1).y) {
				continue;
			}
			newPoints.add(points.get(i));
		}
		if (newPoints.size() <= 2) {
			return false;
		}
		// 之前的某点和现在点重复，不是valid polygon
		for (int i = 0; i < newPoints.size(); i++) {
			for (int j = 0; j < i; j++) {
				Point p1 = newPoints.get(i);
				Point p2 = newPoints.get(j);
				if (p1.x == p2.x && p1.y == p2.y) {
					return false;
				}
			}
		}
		// check 连着三个点是否在一条线上
		for (int i = 2; i < newPoints.size(); i++) {
			Point p1 = newPoints.get(i - 2);
			Point p2 = newPoints.get(i - 1);
			Point p3 = newPoints.get(i);
			int o = orientation(p1, p2, p3);
			if (o == 0)
				return false;
		}
		// check if line overlap
		for (int i = 1; i < newPoints.size(); i++) {
			Point p1 = newPoints.get(i - 1);
			Point p2 = newPoints.get(i);
			for (int j = 1; j < i - 1; j++) {
				Point q1 = newPoints.get(j - 1);
				Point q2 = newPoints.get(j);
				if (doIntersect(p1, p2, q1, q2)) {
					return false;
				}
			}
		}
		Point p1 = newPoints.get(newPoints.size() - 1);
		Point p2 = newPoints.get(0);
		for (int i = 2; i < newPoints.size() - 1; i++) {
			Point q1 = newPoints.get(i - 1);
			Point q2 = newPoints.get(i);
			if (doIntersect(p1, p2, q1, q2)) {
				return false;
			}
		}
		return true;
	}
	
	private static int orientation(Point p, Point q, Point r) {
		double dx1 = q.x - p.x;
		double dy1 = q.y - p.y;
		double dx2 = r.x - q.x;
		double dy2 = r.y - q.y;

		double val = dx1 * dy2 - dx2 * dy1;
		if (val == 0)
			return 0;
		else
			return val > 0 ? 1 : -1;
	}
	
	// 
	private static boolean onLine(Point p1, Point p2, Point q) {
		if (q.x >= Math.min(p1.x, p2.x) && q.y <= Math.max(p1.y, p2.y)) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean doIntersect(Point p1, Point p2, Point q1, Point q2) {
		int o1 = orientation(p1, p2, q1);
		int o2 = orientation(p1, p2, q2);
		int o3 = orientation(q1, q2, p1);
		int o4 = orientation(q1, q2, p2);
		if (o1 != o2 && o3 != o4) {
			return true;
		}
		if (o1 == 0 && onLine(p1, p2, q1))
			return true;
		if (o2 == 0 && onLine(p1, p2, q2))
			return true;
		if (o3 == 0 && onLine(q1, q2, p1))
			return true;
		if (o4 == 0 && onLine(q1, q2, p2))
			return true;
		return false;
	}
	
	// driver
	public static void main(String[] args) {
		List<Point> points = new ArrayList<>();
		points.add(new Point(0, 0));
		points.add(new Point(0, 1));
		points.add(new Point(1, 0));
		points.add(new Point(1, 1));
		System.out.println(isValidPolygon(points));

		points.clear();
		points.add(new Point(0, 0));
		points.add(new Point(0, 1));
		points.add(new Point(1, 1));
		points.add(new Point(1, 0));
		System.out.println(isValidPolygon(points));

		points.clear();
		points.add(new Point(0, 0));
		points.add(new Point(1, 2));
		points.add(new Point(2, 4));
		System.out.println(isValidPolygon(points));

		points.clear();
		points.add(new Point(0, 0));
		points.add(new Point(1, 2));
		points.add(new Point(1, 2));
		System.out.println(isValidPolygon(points));

		points.clear();
		points.add(new Point(0, 0));
		points.add(new Point(1, 2));
		points.add(new Point(1, 2));
		points.add(new Point(2, 3));
		points.add(new Point(2, 3));
		System.out.println(isValidPolygon(points));
	}
}
