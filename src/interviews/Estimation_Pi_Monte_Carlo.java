package interviews;

public class Estimation_Pi_Monte_Carlo {
	/**
	 * estimate pi using computer, using Monte Carlo method
	 */
	public static double getPi() {
		double circleArea = 0;
		double squareArea = 0;
		double pi = 0.0;
		for (int i = 0; i < 100000; i++) {
			double x = Math.random();
			double y = Math.random();
			if (withinCircle(x, y)) {
				circleArea += 1;
			}
			squareArea += 1;
		}
		pi = 4.0 * (circleArea/squareArea);
		//System.out.println(circleArea);
		System.out.println(pi);
		return pi;
	}
	public static boolean withinCircle(double x, double y) {
		if((x * x) + (y * y) < 1) {
			return true;
		} else {
			return false;
		}
	}
	public static void main(String[] args) {
		double pi = getPi();
		System.out.println("Approximate value for pi: " + pi);
	    System.out.println("Difference to exact value of pi: " + Math.PI);
	    System.out.println("Error: (approx-exact)/exact=" + (Math.PI)/pi*100 + "%");
	}
}
