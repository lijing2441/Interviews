package interviews;

public class Long_Substraction {
	public static void main(String[] args) {
		int A[] = {1, 0, 0, 3, 7, 5, 2};
		int B[] = {         4, 2, 1, 0};
		
		int C[];
		
		C = LongSubtraction(A, B);
		
		for(int i=0; i<C.length; i++)
			System.out.print(C[i]+" ");
		System.out.println("");
	}
	
	public static int[] LongSubtraction(int[] a, int[] b) {
		int[] c = new int[a.length];
		
		int borrow = 0;
		for(int i=0; i<a.length; i++) {
			int sub = a[a.length-1-i]-borrow - (i>=b.length? 0 : b[b.length-1-i]);
			borrow = 0;
			if(sub < 0) {
				sub += 10;
				borrow = 1;
			}
			c[c.length-1-i] = sub;
		}
		return c;
	}
}
