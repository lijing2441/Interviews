package interviews;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Facebook_凸包问题 {
	// 求凸包，结果存入lines中
	List<Point> pts = null;
	List<Line> lines = new ArrayList<Line>();

	public void setPointList(List<Point> pts) {
		this.pts = pts;
	}

	public List<Line> eval() {
		lines.clear();
		if (pts == null || pts.isEmpty()) {
			return lines;
		}

		List<Point> ptsLeft = new ArrayList<Point>();
		List<Point> ptsRight = new ArrayList<Point>();

		// 按x坐标对pts排序
		Collections.sort(pts, new Comparator<Point>() {
			public int compare(Point p1, Point p2) {
				return ((Integer) p1.x).compareTo(p2.x);
			}
		});
		Point p1 = pts.get(0); // 最左边的点
		Point p2 = pts.get(pts.size() - 1); // 最右边的点
		Point p3 = null;
		int area = 0;
		for (int i = 1; i < pts.size(); i++) {
			p3 = pts.get(i);
			area = getArea(p1, p2, p3);
			if (area > 0) {
				ptsLeft.add(p3);
			} else if (area < 0) {
				ptsRight.add(p3);
			}
		}
		d(p1, p2, ptsLeft);
		d(p2, p1, ptsRight);

		return lines;
	}

	private void d(Point p1, Point p2, List<Point> s) {
		// s集合为空
		if (s.isEmpty()) {
			lines.add(new Line(p1, p2));
			return;
		}
		// s集合不为空，寻找Pmax
		int area = 0;
		int maxArea = 0;
		Point pMax = null;
		for (int i = 0; i < s.size(); i++) {
			area = getArea(p1, p2, s.get(i));
			if (area > maxArea) {
				pMax = s.get(i);
				maxArea = area;
			}
		}
		// 找出位于(p1, pMax)直线左边的点集s1
		// 找出位于(pMax, p2)直线左边的点集s2
		List<Point> s1 = new ArrayList<Point>();
		List<Point> s2 = new ArrayList<Point>();
		Point p3 = null;
		for (int i = 0; i < s.size(); i++) {
			p3 = s.get(i);
			if (getArea(p1, pMax, p3) > 0) {
				s1.add(p3);
			} else if (getArea(pMax, p2, p3) > 0) {
				s2.add(p3);
			}
		}
		// 递归
		d(p1, pMax, s1);
		d(pMax, p2, s2);
	}

	// 三角形的面积等于返回值绝对值的二分之一, 当且仅当点p3位于直线(p1, p2)左侧时，表达式的符号为正
	private int getArea(Point p1, Point p2, Point p3) {
		return p1.x * p2.y + p3.x * p1.y + p2.x * p3.y - p3.x * p2.y - p2.x * p1.y - p1.x * p3.y;
	}
}

class Line {
	public Line(Point p12, Point p22) {
		this.p1 = p12;
		this.p2 = p22;
	}

	Point p1;
	Point p2;
}