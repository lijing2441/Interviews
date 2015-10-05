package interviews;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.Set;

public class Pinterest_Job_Scheduler {
	/**
	 * Write a job scheduler.
	 * 
	 * Input : list of jobs with dependencies.
	 * 
	 * Output: list of jobs to be executed according to dependencies.
	 */
	// 应该是topo sort
	public static List<String> jobScheduler(List<List<String>> jobs) {
		List<String> res = new ArrayList<String>();
		// build the edge map
		Map<String, Set<String>> map = new HashMap<String, Set<String>>();
		Map<String, Integer> indegree = new HashMap<String, Integer>();
		Set<String> jobSet = new HashSet<String>();
		for(List<String> list : jobs) {
			for (String s: list) jobSet.add(s);
			if (list.size() < 2) continue;
			for (int i = 1; i < list.size(); i++) {
				String j1 = list.get(i - 1);
				String j2 = list.get(i);
				Set<String> set = null;
				if (map.containsKey(j1)) {
					set = map.get(j1);
				} else {
					set = new HashSet<String>();
				}
				if (set.add(j2)) {
					int num = 1;
					if (indegree.containsKey(j2)) {
						num += indegree.get(j2);
					}
					indegree.put(j2, num);
				}
				map.put(j1, set);
			}
		}
		
		// get the 0-indegree queue
		Queue<String> noIncome = new LinkedList<String>();
		for (String s : map.keySet()) {
			if (!indegree.containsKey(s)) {
				noIncome.offer(s);
			}
		}
		// do the topo sort to get the list of job execution
		while (!noIncome.isEmpty()) {
			String cur = noIncome.poll();
			res.add(cur);
			Set<String> ends = map.get(cur);
			if (ends != null) {
				for (String end : ends) {
					int income = indegree.get(end);
					income--;
					if (income == 0) {
						noIncome.offer(end);
						indegree.remove(end);
					}
				}
			}
			map.remove(cur);
		}
		// if cycle exists
		if (res.size() != jobSet.size()) {
			System.out.println("no valid schedule found!");
			return new ArrayList<String>();
		}
		return res;
	}
	
	// driver
	public static void main(String[] args) {
		List<List<String>> jobs = new ArrayList<List<String>>();
		List<String> job1 = new ArrayList<String>();
		job1.add("A");
		job1.add("B");
		job1.add("C");
		jobs.add(job1);
		List<String> job2 = new ArrayList<String>();
		job2.add("C");
		job2.add("D");
		job2.add("F");
		jobs.add(job2);
		List<String> job3 = new ArrayList<String>();
		job3.add("B");
		job3.add("E");
		jobs.add(job3);
		List<String> jobSeq = jobScheduler(jobs);
		for (String s: jobSeq) {
			System.out.print(s + " ");
		}
	}
}
