package interviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Airbnb_推荐没去过的城市 {
	/**
	 * 给出用户和去过的城市，给定用户找出跟他去过的城市相似的用户，然后推荐没去过的城市
	 * 
	 * U1: Pittsburgh, Boston, San Francisco
	 * U2: Beijing, Shanghai
	 * U3: Beijing, Pittsburgh
	 * 
	 * 要求U3,然后推荐Boston，SF和Shanghai，因为U1，U2的相似数量都是1，把余下没去过的城市推荐就可以
	 */
	public static List<String> getRecommendation(AirUser currentUser, List<AirUser> users) {
		Set<String> cities = new HashSet<String>();
		cities.addAll(currentUser.historyCity);
		//Map<AirUser, Integer> similarity = new HashMap<AirUser, Integer>();
		int maxCount = 0;
		List<AirUser> candidates = new ArrayList<AirUser>();
		for (AirUser user: users) {
			if (user.equals(currentUser)) continue;
			int curCount = 0;
			for (String s: user.historyCity) {
				if (cities.contains(s)) {
					curCount++;
				}
			}
			if (curCount > maxCount) {
				candidates.clear();
				candidates.add(user);
				maxCount = curCount;
			} else if (curCount == maxCount) {
				candidates.add(user);
			}
		}
		
		if (maxCount == 0) {
			// ask interviewer what to recommend
			System.out.println("No city to recommend");
			return new ArrayList<String>();
		}
		List<String> res = new ArrayList<String>();
		for (AirUser c : candidates) {
			for (String s : c.historyCity) {
				if (!cities.contains(s)) {
					res.add(s);
				}
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		String[] c1 = {"Pittsburgh", "Boston", "San Francisco"};
		String[] c2 = {"Beijing", "Shanghai"};
		String[] c3 = {"Beijing", "Pittsburgh"};
		AirUser user1 = new AirUser(Arrays.asList(c1));
		AirUser user2 = new AirUser(Arrays.asList(c2));
		AirUser user3 = new AirUser(Arrays.asList(c3));
		List<AirUser> users = new ArrayList<AirUser>();
		users.add(user1);
		users.add(user2);
		users.add(user3);
		
		List<String> recommendation = getRecommendation(user3, users);
		for (String s : recommendation) {
			System.out.println(s);
		}
	}
}
class AirUser {
	List<String> historyCity;
	public AirUser(List<String> cities) {
		this.historyCity = cities;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((historyCity == null) ? 0 : historyCity.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AirUser other = (AirUser) obj;
		if (historyCity == null) {
			if (other.historyCity != null) {
				return false;
			}
		} else if (!historyCity.equals(other.historyCity)) {
			return false;
		}
		return true;
	}
	
}