package chap11_Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//문제: 해밀턴 경로 존재 여부와 경로 출력
//문제 설명
//정점 수 N과 무방향 간선 목록이 주어진다. 
//해밀턴 경로는 모든 정점을 중복 없이 정확히 한 번씩 방문하는 단순 경로를 말한다.
//이 그래프에 해밀턴 경로가 존재하는지 판별하고, 존재한다면 그 중 하나의 경로를 출력하라.
//
//입력 형식
//첫째 줄: 정점 수 N (1 ≤ N ≤ 20), 
//		 간선 수 M (0 ≤ M ≤ N(N−1)/2N(N−1)/2)
//다음 M줄: 정수 uv (0 ≤ u, v < N, u ≠ v) — 정점 u와 v를 잇는 무방향 간선
//중복 간선 없음, 자기 루프 없음
//
//출력 형식
//해밀턴 경로가 존재하면:
//첫째 줄: YES
//둘째 줄: 경로를 이루는 정점 번호를 공백으로 구분하여 N개 출력 (예: 0 2 1 3)
//존재하지 않으면:
//한 줄에 NO
//여러 경로가 있으면 아무거나 한 개를 출력해도 된다.
//(선택: 사전식 최소 경로를 요구하려면 “가능하면 사전식 최소 순서를 출력”이라고 한 줄 추가)


public class Hamilton {
	
	static int N;
	static int M;
	static boolean[] visited;
	static boolean found;
	static List<List<Integer>> graph;
	static List<Integer> path;
	
	static void solve(int n, int[][] edges) {
		N = n;
		M = edges.length;
		
		if (N < 1 || N > 20) {
			System.out.println("No");
			return;
		}
		
		// 그래프 만들기
		graph = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
		}
		
		// 간선 입력
		for (int i = 0; i < M; i++) {
			int u = edges[i][0];
		    int v = edges[i][1];
			
			if (u < 0 || u >= N || v < 0 || v >= N) {
				System.out.println("No");
				return;
			}
			
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		
		// 인접 리스트 정렬
		for (int i = 0; i < N; i++) {
			Collections.sort(graph.get(i));
		}
		
		// 각 정점을 시작으로 DFS
		visited = new boolean[N];
		path = new ArrayList<>();
		found = false;
		
		for (int start = 0; start < N && !found; start++) {
			Arrays.fill(visited, false);
			path.clear();
			
			visited[start] = true;
			path.add(start);
			dfs(start, 1);
		}
		
		if (found) {
			System.out.println("Yes");
			for (int i = 0; i < path.size(); i++) {
				if (i > 0) {
					System.out.print(" ");
				}
				System.out.print(path.get(i));
			}
			System.out.println();
		} else {
			System.out.println("No");
		}
		
	}
	
	static void dfs(int current, int depth) {
		if (found) {
			return;
		}
		
		if (depth == N) {
			found = true;
			return;
		}
		
		for (int next : graph.get(current)) {
			if (!visited[next]) {
				visited[next] = true;
				path.add(next);
				dfs(next, depth +1);
				
				if (!found) {
					visited[next] = false;
					path.remove(path.size() - 1);
				}
 			}
		}
	}

	public static void main(String[] args) {
		System.out.println("== 해밀턴 경로 ==");
		// 입력 데이터를 출력
		// 해밀턴 경로가 존재하면 Yes / 없으면 No / 종료
		
		int N = 5;
		int[][] edges = {{0,1},{1,2},{2,3},{3,4}};
		
		solve(N, edges);
		solve(4, new int[][] {{0,1},{2,3}});
		solve(4, new int[][] {{0,1},{0,2},{1,3}});
		solve(5, new int[][] {
			  {0,1},{1,2},{2,0}, // 삼각형
			  {2,3},{3,4}        // 끝으로 이어짐
			});
		
	}

}
