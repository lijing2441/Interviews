package interviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Four_Sum {
  //O(n^3) time
  public List<List<Integer>> fourSumTraditional(int[] num, int target) {
    Arrays.sort(num);
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    if(num == null || num.length < 4) return res;
    
    for(int i = 0; i < num.length - 3; i++){
        if(i > 0 && num[i] == num[i - 1]) continue;
        for(int l = num.length - 1; l > i + 2; l--){
            if(l < num.length - 1 && num[l] == num[l + 1]) continue;
            int sum = target - num[i] - num[l];
            int j = i + 1;
            int k = l - 1;
            while(j < k){
                if(j > i + 1 && num[j] == num[j - 1]){
                    j++;
                    continue;
                }
                if(k < l - 1 && num[k] == num[k + 1]){
                    k--;
                    continue;
                }
                if(num[j] + num[k] == sum){
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(num[i]);
                    list.add(num[j]);
                    list.add(num[k]);
                    list.add(num[l]);
                    res.add(list);
                    j++;
                    k--;
                }else if(num[j] + num[k] > sum){
                    k--;
                }else{
                    j++;
                }
            }
        }
    }
    return res;
  }
}
