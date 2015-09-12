package interviews;

public class Sum_From_Both_Ways {
	//O(n)
	public static int equalSumIndex(int[] arr){
		int n = arr.length;
		int arrSum = 0;
		int leftSum = 0;
		// we get the sum from left to right
		for(int i = 0; i < n; i++){
			arrSum += arr[i];
		}
		// minus the current element from left to right
		for(int i = 0; i < n; i++){
			arrSum -= arr[i];
			if(arrSum == leftSum) return i;
			// the current element is not a valid pivot
			// add it to the left Sum
			leftSum += arr[i];
		}
		// we did not get a valid pivot
		return -1;
	}
	
	public static void main(String[] args){
		int[] arr = {-7, 1, 5, 2, -4, 3, 0};
        System.out.println("Equilibrium index is :" + equalSumIndex(arr));
	}
}
