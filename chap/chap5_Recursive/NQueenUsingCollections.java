package chap5_Recursive;

import java.util.*;

public class NQueenUsingCollections {
	private static final int BOARD_SIZE = 8;
	private int solutionCount = 0;
	private List<List<Integer>> allSolutions = new ArrayList<>();

	private List<Integer> queens = new ArrayList<>();

	// 충돌 체크를 위한 Set들 (O(1) 검사)
	private Set<Integer> usedCols = new HashSet<>();  // 사용 중인 열
	private Set<Integer> usedDiag1 = new HashSet<>(); // 대각선 \ (row - col)
	private Set<Integer> usedDiag2 = new HashSet<>(); // 대각선 / (row + col)

	public void solve() {
		backtrack(0);
	}

	private void backtrack(int row) {
		if (row == BOARD_SIZE) {
			solutionCount++;
			saveSolution();
			printSolution();
			return;
		}

		for (int col = 0; col < BOARD_SIZE; col++) {
			if (isSafe(row, col)) {
				placeQueen(row, col);
				backtrack(row + 1);
				removeQueen(row, col);
			}
		}
	}

	private boolean isSafe(int row, int col) {
		return !usedCols.contains(col) && !usedDiag1.contains(row - col) && !usedDiag2.contains(row + col);
	}

	private void placeQueen(int row, int col) {
		queens.add(col);
		usedCols.add(col);
		usedDiag1.add(row - col);
		usedDiag2.add(row + col);
	}

	private void removeQueen(int row, int col) {
		queens.remove(queens.size() - 1);
		usedCols.remove(col);
		usedDiag1.remove(row - col);
		usedDiag2.remove(row + col);
	}

	private void saveSolution() {
		allSolutions.add(new ArrayList<>(queens));
	}

	private void printSolution() {
		System.out.println("해 " + solutionCount + ":");

		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				if (queens.get(row) == col) {
					System.out.print("Q ");
				} else {
					System.out.print(". ");
				}
			}
			System.out.println();
		}

		// 퀸의 위치를 리스트로 출력
		System.out.println("퀸 위치 (행별 열 번호): " + queens);
		System.out.println();
	}

	public List<List<Integer>> getAllSolutions() {
		return new ArrayList<>(allSolutions);
	}

	public int getSolutionCount() {
		return solutionCount;
	}

	public static void main(String[] args) {
		System.out.println("=== N-Queen 문제: Collections 활용 ===\n");
		NQueenUsingCollections solver = new NQueenUsingCollections();
		solver.solve();
		List<List<Integer>> solutions = solver.getAllSolutions();
		System.out.println("총 해의 개수: " + solutions.size());

	}
}