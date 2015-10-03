package interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Airbnb_Find_All_Palindrome_Pairs {
	public List<String[]> find_pairs(Set<String> words) {
		 List<String[]> res = new ArrayList<String[]>();
		 unordered_map<string,string> map;

		 for (auto& str : words) {
		   for (int l = 0; l < (int) str.length() && str[l] == str.front(); ++l) {
		     String key = string(str.begin() + l + 1, str.end());
		     reverse(key.begin(), key.end());
		if (words.count(key)) map[key] = str;     
		   }   
		   for (int r = (int) str.length() - 1; r >= 0 && str[r] == str.back(); --r) {
		     String key = string(str.begin(), str.begin() + r);
		     reverse(key.begin(), key.end());
		     if (words.count(key)) map[key] = str;
		   }   
		   String reversed = string(str.rbegin(), str.rend());
		   if (words.count(reversed)) map[str] = reversed;
		 }

		 for (auto& str : words) {
		   if (map.count(str)) {
		     result.emplace_back(map[str], str);
		   }
		 }
		 return result;. 1point 3acres 璁哄潧
		}

}
