package chap5_Recursive;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


//https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/?ref=lbp
//N Queen problem / backtracking
//모든 해가 나오는 버젼 만들기 
/*
 * 체스판은 8 x 8 체스의 기물: king/가로세로대각선 1칸만 이동, queen/가로세로 대각선/같은 편의 기물을 넘을 수 없다,
 * Rook/가로,세로 이동/다른 기물을 넘을 수없다, bishop/대각선, knight/1-2칸 이동/다른 기물을 넘을 수 있다,
 * pawn/처음 이동은 2칸까지 가능, 그 후 한칸만 가능, 잡을 때는 대각선 가능 체스판 최대 배치 문제 : king/16,
 * Queen/8, rook/8, bishop/?, knight/? rook 2개/a, h, knight 2개/b, g, bishop
 * 2개/c, f, queen 1개/black queen은 black 칸에, 폰 8개
 */
/*
 * 8-Queen 문제는 체스판 위에 8개의 퀸을 배치하되, 서로 공격할 수 없도록 배치하는 문제입니다. 
 * 이 문제를 해결하기 위한 비재귀적(스택 기반) 알고리즘을 구현하려면, 다음과 같은 방법을 사용할 수 있습니다.

개요
1. 스택을 사용하여 백트래킹을 구현합니다. 각 스택의 요소는 체스판의 각 열에 대한 퀸의 배치 상태를 나타냅니다.
2. 퀸을 한 줄씩 배치한 후, 유효한지 확인하고, 다음 줄로 이동합니다.
3. 유효하지 않으면 스택을 이용해 이전 상태로 돌아가서 다른 경로를 시도합니다.

알고리즘
1. 스택을 이용하여 백트래킹을 구현하기 때문에, 현재 상태를 스택에 저장합니다. 
   스택의 각 원소는 퀸의 배치를 나타냅니다.
2. 체스판의 각 열에 대해 가능한 위치를 하나씩 확인하면서 퀸을 배치하고, 
   충돌이 발생하지 않는다면 다음 열로 넘어갑니다.
3. 더 이상 유효한 위치가 없으면, 스택에서 이전 상태로 되돌아가서 새로운 경로를 탐색합니다.
4. 퀸을 8개 다 배치하면, 해를 찾은 것이므로 스택을 이용해 해결책을 저장합니다.
 */


public class NQueen2 {
	private static final int BOARD_SIZE = 8;
	private int solutionCount = 0;
	private List<List<Integer>> allSolutions = new ArrayList<>();
	private List<Integer> queens = new ArrayList<>();
	
	private Set<Integer> usedCol = new HashSet<>();
	private Set<Integer> usedDiag1 = new HashSet<>();
	private Set<Integer> usedDiag2 = new HashSet<>();
	
	public NQueen2() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            queens.add(-1);
        }
    }
	
	public List<List<Integer>> getAllSolutions() {
        // 깊은 복사 (외부에서 변경 못 하도록)
        List<List<Integer>> copy = new ArrayList<>();
        for (List<Integer> sol : allSolutions) {
            copy.add(new ArrayList<>(sol));
        }
        return copy;
    }
	
	public int getSolutionCount() {
		return solutionCount;
	}
	
	private void backtrack(int row) {
		if (row == BOARD_SIZE) {
			solutionCount++;
			allSolutions.add(new ArrayList<>(queens));
			return;
		}
		
		for (int col = 0; col < BOARD_SIZE; col++) {
			if (isSafe(row, col)) {
				queens.set(row, col);
				usedCol.add(col);
				usedDiag1.add(row - col);
				usedDiag2.add(row + col);
				
				backtrack(row+1);
				
				queens.set(row, -1);
                usedCol.remove(col);
                usedDiag1.remove(row - col);
                usedDiag2.remove(row + col);
				
			}
		}
	}
	
	private void solve() {
		backtrack(0);
	}
	
	private boolean isSafe(int row, int col) {
		return (!usedCol.contains(col) 
				&& !usedDiag1.contains(row - col) 
				&& !usedDiag2.contains(row + col));
	}
	
	public void printSolve() {
		for (List<Integer> sol : allSolutions) {
            System.out.println("=== Solution ===");
            printBoard(sol);
            System.out.println();
        }
        System.out.println("Total solutions: " + solutionCount);
	}

	private void printBoard(List<Integer> sol) {
		int n = sol.size();
        for (int r = 0; r < n; r++) {
            int c = sol.get(r);
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(j == c ? "Q " : ". ");
            }
            System.out.println(sb);
        }
		
	}

	public static void main(String[] args) {
		NQueen2 solver = new NQueen2();
		solver.solve();
		solver.printSolve();
        System.out.println("Found: " + solver.getSolutionCount());
	}
	
}