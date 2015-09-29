package interviews;

import java.util.Arrays;
import java.util.Comparator;

public class Airbnb_Float_Ceil_or_Floor {
	public static int[] getNearlyArrayWithSameSum(double[] arr) {
		//int[] floors = new int[arr.length];
		NumWithDiff[] arrWithDiff = new NumWithDiff[arr.length];
		double sum = 0.0;
		int floorSum = 0;
		for (int i = 0; i < arr.length; i++) {
			int floor = (int)arr[i];
			int ceil = floor;
			if (floor < arr[i]) ceil++;
			floorSum += floor;
			sum += arr[i];
			arrWithDiff[i] = new NumWithDiff(ceil, ceil - arr[i]);
		}
		int num = (int) Math.round(sum);
		int diff = num - floorSum;
		Arrays.sort(arrWithDiff, new Comparator<NumWithDiff>() {
			public int compare(NumWithDiff n1, NumWithDiff n2) {
				if (n1.diffWithCeil <= n2.diffWithCeil) return -1;
				else return 1;
			}
		});
		int[] res = new int[arr.length];
		int i = 0;
		for (; i < diff; i++) {
			res[i] = arrWithDiff[i].num;
		}
		for (;i < arr.length; i++) {
			res[i] = arrWithDiff[i].num - 1;
		}
		return res;
	}
	public static void main(String[] args) {
		double[] arr = { 1.2, 3.7, 2.3, 4.8 };
		int[] res = getNearlyArrayWithSameSum(arr);
		for (int i : res) System.out.print(i + " ");
		
	}
}
class NumWithDiff {
	int num;
	double diffWithCeil;
	public NumWithDiff(int n, double c) {
		this.num = n;
		this.diffWithCeil = c;
	}
}