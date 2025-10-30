package Lv1;

import java.util.LinkedList;
import java.util.Queue;

class job {
	private int 
}

class jobQue {
	private int[] insertTime;
	private int[] needTime;
	
	public jobQue(int[][] jobs) {
		insertTime = new int[jobs.length];
		needTime = new int[jobs.length];
		
		for (int i = 0; i < jobs.length; i++) {
			insertTime[i] = jobs[i][0];
			needTime[i] = jobs[i][1];
		}
		
	}
	
	
}

public class solve42627 {
	
	private static int solve(int[][] jobs) {
		int avgTime = 0;
		
		Queue<int[]> jobQue = new LinkedList<>();
		
		for (int[] item : jobs) {
			jobQue.offer(item);
		}
		
		
		
		
		return avgTime;
	}

	public static void main(String[] args) {
		int[][] jobs = {{0, 3},{1, 9},{3, 5}};
		int avgTime = solve(jobs);

	}

}
