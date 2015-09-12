package interviews;

public class Next_Smaller_Num_With_Same_Digits {
	// O(n), O(1)
	// find the next larger number with the same digits
	public static int findNextBiggerNum(int num){
		String str = String.valueOf(num);
		int stop = -1;
		int swap = -1;
		char[] ch = str.toCharArray();
		for(int i = ch.length - 2; i >= 0; i--){ // find the first place the right-digit is bigger than the left
			if(ch[i] < ch[i + 1]){
				stop = i;
				break;
			}
		}
		for(int i = ch.length - 1; i > stop; i--){ // find the smallest number larger than stop
			if(ch[i] > ch[stop]){
				swap = i;
				break;
			}
		}
		swap(ch, stop, swap);
		reverse(ch, stop + 1, ch.length - 1);
		int res = Integer.parseInt(new String(ch));
		return res;
	}
	public static void swap(char[] ch, int i, int j){
		char tmp = ch[i];
		ch[i] = ch[j];
		ch[j] = tmp;
	}
  
  /**
   * This problem can be seen as next permutation of digits.
   * 
   * Implement next permutation, which rearranges numbers into the lexicographically 
   * next greater permutation of numbers. If such arrangement is not possible, 
   * it must rearrange it as the lowest possible order (ie, sorted in ascending order).
   * 
   * The replacement must be in-place, do not allocate extra memory. Examples:
   * 
   * 1,2,3 → 1,3,2
   * 3,2,1 → 1,2,3
   * 1,1,5 → 1,5,1
   *
   * 思路：
   * 1) From right to left, find the first element "stop" smaller than its right element
   * 2) At this time, the right part of "stop" must be in decreasing order
   * 3) From right left, find the first element that is larger than "stop" and swap it with stop
   * 4) Then reverse the order of the right part of the "stop"
   * 5) In this way, the right part should be changed from decreasing order to increasing order.
   */
  public void nextPermutation(int[] num){
	  int n = num.length;
	  if(n < 2) return;
	  int stop = -1;
	  for(int i = n - 2; i >= 0; i--){
		  // find a digit with a right digit that is larger than it from right
		  if(num[i] < num[i + 1]){
			  stop = i;
			  break;
		  }
	  }
	  // if not exists, the current digits are sorted decreasingly
	  // reverse it to the increasing order, that is, the smallest number possible
	  if(stop == -1){
		  reverse(num, 0, n-1);
		  return;
	  }
	  // find the right digit that is larger than the current one, from right
	  // in order to let the swapped number smallest
	  int swap = -1;
	  for(int i = n - 1; i >= 0; i--){
		  if(num[i] > num[stop]){
			  swap = i;
			  break;
		  }
	  }
	  swap(num, stop, swap);
	  // the right part must be in decreasing order, make it increasing
	  // that is, smallest possible
	  reverse(num, stop+1, n -1);
  }
  public void swap(int[] num, int i, int j){
	  int tmp = num[i];
	  num[i] = num[j];
	  num[j] = tmp;
  }
  public void reverse(int[] num, int start, int end){
	  int left = start, right = end;
	  while(left < right){
		  swap(num, left, right);
		  left++;
		  right--;
	  }
  }
  
  
  	/**
  	 * find the next smaller number with the same digits, the largest number smaller than the current
  	 */
  	public static int findNextSmallerNum(int num){
  		String str = String.valueOf(num);
  		char[] ch = str.toCharArray();
  		int stop = -1, swap = -1;
  		for(int i = ch.length - 2; i >= 0; i--){  
  			// find the first place the left-digit is bigger than the right
  			if(ch[i] > ch[i + 1]){
  				stop = i;
  				break;
  			}
  		}
  		for(int i = ch.length - 1; i > stop; i--){   
  			// find the largest number smaller than stop
  			if(ch[i] < ch[stop]){
  				swap = i;
  				break;
  			}
  		}
  		swap(ch, stop, swap);
  		// the right part must be in increasing order, make it decreasing, that is largest
  		reverse(ch, stop+1, ch.length - 1);
  		int res = Integer.parseInt(new String(ch));
  		return res;
  	}
  	public static void reverse(char[] ch, int start, int end){
  		while(start < end){
  			swap(ch, start, end);
  			start++;
  			end--;
  		}
  	}
  
  	public static void main(String[] args){
  		int a = 432156;
  		System.out.print(a + ":");
  		System.out.println();
  		System.out.print("Next Smaller # :" + findNextSmallerNum(a));
  		System.out.println();
  		System.out.print("Next Bigger # :" + findNextBiggerNum(a));
  	}
}
