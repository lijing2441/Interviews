package interviews;

import java.util.Arrays;

public class Resource_Allocate_Average {
	/**
	 *  Given n machines, each storing some resource. Now a batch of new resources of size=X comes,
	 *  how to allocate them to the machine to let them as average as possible.
	 *  
	 *  Edge case 1: if several machines have an extremely large mount of resources, 
	 *  			 we cannot allocate more on them.
	 *  			=> average and see if some value is even larger than after allocation
	 *  Edge case 2: cannot exactly divided.
	 *  			=> allocate one each to part of them
	 */
	// machines[] gives the number of resources already in the machine
	public void resourceAllocate(int[] machines, int newResource){
		if(machines.length < 2) return; // if only 1 machine, allocate to it
		
		Arrays.sort(machines);
		int n = machines.length;
		// get average
		int sum = 0;
		for(int i = 0; i < n; i++) sum += machines[i];
		// get rid of the edge case 1
		// end is the first element that cannot be allocated newResource
		int end = n;
		for(int i = n - 1; i >= 0; i--){
			if(machines[i] >= ((sum + newResource) / end)){
				end = i;
				sum -= machines[i];
			}else{
				break;
			}
		}
		int average = (sum + newResource) / end;
		// for the left smaller ones, I allocate one more resource
		int left = (sum + newResource) % end;
		for(int i = 0; i < end; i++){
			machines[i] = average;
			if(i < left){
				machines[i]++;
			}
		}
	}
}
