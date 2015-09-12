package interviews;

import java.util.ArrayList;

public class Shortest_Sentence_With_All_Words {
	/**
	 * We have words and there positions in a paragraph in sorted order. Write
	 * an algorithm to find the least distance for a given 3 words. 
	 * eg. for 3 words 
	 * job: 5, 9 , 17 
	 * in: 4, 13, 18 
	 * google: 8, 19, 21 ... 
	 * Answer: 17, 18, 19 
	 * 
	 * Follow up: Can you extend it to "n" words?
	 * 
	 * Context: In Google search results, the search terms are highlighted in
	 * the short paragraph that shows up. We need to find the shortest sentence
	 * that has all the words if we have word positions as mentioned above.
	 * 
	 * @method: Go with the first array, move forward with the one with smallest element.
	 * 			Until one of the arrays exhausted, we can just finish the loop, since the
	 * 			other elements must be larger than the current one and generate larger gap.
	 * 
	 * @complexity: worst case is O(sizeoflist1+sizeoflist2+sizeof3), if n arrays, O(overall_size)
	 * 				space complexity is constant
	 * 			
	 */
	// n = 3
	public int[] minGap3(int[] arr1, int[] arr2, int[] arr3){
		// corresponds to the elements in each array
		int[] res = new int[3];
		int minDiff = Integer.MAX_VALUE;
		// ptrs for each array in the list
		int i = 0, j = 0, k = 0;
		while(minDiff > 0 && i < arr1.length && j < arr2.length && k < arr3.length){
			int min = arr1[i];
			int max = arr1[i];
			//we assume the first array is the minimal value among all arrays currently
			int minIdx = 1;
			//check the following two arrays
			if(arr2[j] > max){
				max = arr2[j];
			}
			if(arr2[j] < min){
				min = arr2[j];
				minIdx = 2;
			}
			
			if(arr3[k] > max){
				max = arr3[k];
			}
			if(arr3[k] < min){
				min = arr3[k];
				minIdx = 3;
			}
			//check if we need to update the minDiff
			if(minDiff > max - min){
				minDiff = max- min;
				res[0] = i;
				res[1] = j;
				res[2] = k;
			}
			//proceed the smallest one
			if(minIdx == 1) i++;
			else if(minIdx == 2) j++;
			else k++;
		}
		return res;
	}
	
	
	// proceed to "n" words
	public int[] minGap(ArrayList<int[]> arrays){
		int n = arrays.size();
		// corresponds to the elements in each array
		// res[0] -> arrays.get(0) ... etc.
		int[] res = new int[n];
		// we maintain the minDiff to update
		int minDiff = Integer.MAX_VALUE;
		// ptrs for each array in the list, we assume n = 3 here
		int[] ptrs = new int[n];
		// we will end the loop if either (1) all arrays own a same element
		//               				  (2) one of the arrays exhausted
		while(minDiff > 0){
			//check the boundary
			for(int i = 0; i < n; i++){
				if(ptrs[i] >= arrays.get(i).length){
					return res;
				}
			}
			//if the boundary is valid, go forward with the first element
			int max = arrays.get(0)[ptrs[0]];
			int min = arrays.get(0)[ptrs[0]];
			int minIdx = 0;
			for(int i = 1; i < n; i++){
				int[] cur = arrays.get(i);
				int curPtr = ptrs[i];
				if(cur[curPtr] > max) max = cur[curPtr];
				if(cur[curPtr] < min){
					min = cur[curPtr];
					minIdx = i;
				}
			}
			//check if we need to update the minDiff
			if(minDiff > max - min){
				minDiff = max - min;
				for(int i = 0; i < n; i++){
					res[i] = arrays.get(i)[ptrs[i]];
				}
			}
			//proceed the smallest one
			ptrs[minIdx]++;
		}
		return res;
	}
	/**
	 * @method2:
	 * 		  Since we have the arrays with increasing order, we take the list which 
	 * 		has minimum element start from first element element1 on list1 and have 
	 * 		an index for both of other list now move one by one to find the nearest 
	 * 		value of element1 in list2 and list3. We need to find max(i,j,k)-min(i,j,k) and 
	 * 		store it in result.
	 * 		  Now take element2 from list1 and move index on list2 and list3 to find 
	 * 		the nearest of element2 now again find max(i,j,k)-min(i,j,k) if it is less 
	 * 		than result then replace result. 
	 * 		  Repeat to end of element in list1 return the result value. If u need the element 
	 * 		value then maintain i,j,k when result is replaced.
	 * 
	 * @complexity: O(sizeoflist1 * sizeoflist2 * sizeoflist3)
	 */
}
