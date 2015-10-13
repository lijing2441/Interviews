package interviews;

public class Singleton {
	/**
	 * In Java:
	 * 
	 * A a = A.getInstance(); A b = A.getInstance(); a should equal to b.
	 */
	public static volatile Singleton instance = null; // toolkit
	public Singleton() {}
	
	public static synchronized Singleton getInstance() {
		// write your code here
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
}
