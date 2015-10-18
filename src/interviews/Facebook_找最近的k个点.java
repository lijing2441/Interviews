package interviews;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Facebook_找最近的k个点 {
	// 用heap做 O(nlogk)
	public static List<Point> getClosestKPoints(Point source, List<Point> points, int k) {
		List<Point> res = new ArrayList<Point>();
		if (k == 0 || points == null || points.size() == 0) return res;
		PriorityQueue<Point> pq = new PriorityQueue<Point>(new Comparator<Point>() {
			public int compare(Point p1, Point p2) {
				return getDist(p2, source) - getDist(p1, source);
			}
		});
		int i = 0;
		for (; i < k; i++) {
			pq.offer(points.get(i));
		}
		for (; i < points.size(); i++) {
			pq.offer(points.get(i));
			pq.poll();
		}
		res.addAll(pq);
		return res;
	}
	
	public static int getDist(Point p1, Point p2) {
		return (int) Math.sqrt(Math.pow((p1.x - p2.x), 2) + Math.pow((p1.y - p2.y), 2));
	}
	
	public static void main(String[] args) {
		Point source = new Point(0, 0);
		Point p1 = new Point(1, 0);
		Point p2 = new Point(2, 1);
		Point p3 = new Point(0, 1);
		Point p4 = new Point(1, 2);
		List<Point> l = new ArrayList<Point>();
		l.add(p1);
		l.add(p2);
		l.add(p3);
		l.add(p4);
		for (Point p: getClosestKPoints(source, l, 2)) {
			System.out.println(p.x + ", " + p.y);
		}
	}
}
