package chap11_Graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * 문제 설명
네트워크란 컴퓨터 상호 간에 정보를 교환할 수 있도록 연결된 형태를 의미합니다. 
예를 들어, 컴퓨터 A와 컴퓨터 B가 직접적으로 연결되어있고, 
컴퓨터 B와 컴퓨터 C가 직접적으로 연결되어 있을 때 
컴퓨터 A와 컴퓨터 C도 간접적으로 연결되어 정보를 교환할 수 있습니다. 
따라서 컴퓨터 A, B, C는 모두 같은 네트워크 상에 있다고 할 수 있습니다.

컴퓨터의 개수 n, 연결에 대한 정보가 담긴 2차원 배열 computers가 매개변수로 주어질 때, 
네트워크의 개수를 return 하도록 solution 함수를 작성하시오.

제한사항
컴퓨터의 개수 n은 1 이상 200 이하인 자연수입니다.
각 컴퓨터는 0부터 n-1인 정수로 표현합니다.
i번 컴퓨터와 j번 컴퓨터가 연결되어 있으면 computers[i][j]를 1로 표현합니다.
computer[i][i]는 항상 1입니다.

입출력 예
n	computers		return
	[[1, 1, 0], 
3	 [1, 1, 0], 	2
	 [0, 0, 1]]		
	[[1, 1, 0], 
3	 [1, 1, 1], 	1
	 [0, 1, 1]]		
 * 
 * */

public class Solution43162 {
	
	public static int solution(int n, int[][] computers) {
		// 모든 연결된 것 구하기(경우의 수 = DFS) or 최단거리(BFS) => DFS
		// DFS - 재귀, 스택, 큐 => 스택으로 풀어보기
		// - 자바에서 제공하는 Stack
		// - 메서드를 하나 만들어야겠다.
		//  - 매개변수를 확인하자(computers, visited, i(방향이 있다면 j도 필요))
		
		
		
        int answer = 0;
        
        return answer;
    }
	
	static int solutionStack(int n, int[][] computers) {
		int networkCnt = 0;
		boolean[] visited = new boolean[n];
		
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				dfsStack(computers, visited, i);
				networkCnt++;
			}
		}
		
		return networkCnt;
	}
	
	static void dfsStack(int[][] computers, boolean[] visited, int start) {
		Stack<Integer> stack = new Stack<>();
		stack.push(start);
		visited[start] = true;
		
		while(!stack.isEmpty()) {
			int current = stack.pop();
			// DFS
			for (int i = 0; i < computers.length; i++) {
				if (computers[current][i] == 1 & !visited[i] ) {
					stack.push(i);
					visited[i] = true;
				}
			}
		}
	}
	
	static int solutionQueue(int n, int[][] computers) {
		int networkCnt = 0;
		boolean[] visited = new boolean[n];
		
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				dfsQueue(computers, visited, i);
				networkCnt++;
			}
		}
		
		return networkCnt;
	}
	
	static void dfsQueue(int[][] computers, boolean[] visited, int start) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			// DFS
			for (int i = 0; i < computers.length; i++) {
				if (computers[current][i] == 1 & !visited[i] ) {
					queue.offer(i);
					visited[i] = true;
				}
			}
		}
	}

	public static void main(String[] args) {
		
		int n1 = 3;
		int[][] computers1 = {{1, 1, 0},{1, 1, 0},{0, 0, 1}};
		
		int n2 = 3;
		int[][] computers2 = {{1, 1, 0},{1, 1, 1},{0, 1, 1}};
		
		System.out.println("DFS Case1: "+ solutionStack(n1, computers1));
		System.out.println("DFS Case2: "+ solutionStack(n2, computers2));
		System.out.println("DFS Case1(Queue): "+ solutionQueue(n1, computers1));
		System.out.println("DFS Case2(Queue): "+ solutionQueue(n2, computers2));


	}

}
