package chap5_Recursive;

import java.util.Stack;


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

class Position {
	private int row;
	private int col;

	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	@Override
	public String toString() {
		return "(" + row + ", " + col + ")";
	}

}


public class NQueen {
	private static final int BOARD_SIZE = 8;
	private int[][] board;
	private Stack<Position> queens;
	private int solutionCount;
	
	public NQueen() {
		this.board = new int[BOARD_SIZE][BOARD_SIZE];
		this.queens = new Stack<>();
		this.solutionCount = 0;
		initializeboard();
	}
	
	private void initializeboard() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = 0;
			}
		}
	}
	
	private void solve() {
		
		// 시작위치가 필요
		int row = 0;
		int col = 0;
		
		// 반복(무한히)
		while(true) {
			boolean placed = false;
			
			// 현재 행에서 퀸을 배치할 수 있는 열 찾기
			while (col < BOARD_SIZE) {
				if (isSafe(row, col)) {
					board[row][col] = 1;
					queens.push(new Position(row,col));
					placed = true;
					break;
				}
				col++;
			}
			
			if (placed) {
				// - 퀸을 배치했으면, 다른 형태의 배치 방법이 있는지 확인
				if (queens.size() == BOARD_SIZE) {
					// 모든 퀸을 다 찾았으면 
					solutionCount++;
					
					// 출력
					printSolve(solutionCount);
					
					// 다른 해를 찾기 위한 백트래킹
					Position last = queens.pop();
					board[last.getRow()][last.getCol()] = 0;
					row = last.getRow();
					col = last.getCol() + 1;
					
				} else {
					row++;
					col = 0;
				}
			// - 퀸을 배치했는데, 다른 형태의 배치 방법이 없으면 => 백트래킹
			} else {
				if (queens.isEmpty()) {
					break;
				}
				
				Position last = queens.pop();
				board[last.getRow()][last.getCol()] = 0;
				row = last.getRow();
				col = last.getCol() + 1;
				
			}
		}
		System.out.println("\n총 " + solutionCount + "개의 해를 찾았습니다.");
	}
	
	private boolean isSafe(int row, int col) {
		// 퀸이 같은 열
		for (int i = 0; i < row; i++) {
			if (board[i][col] == 1) {
				return false;
			}
		}
		
		// 대각선(왼쪽 위)
		for (int i = row-1, j = col-1; i >= 0 && j >= 0; i--, j--) {
			if (board[i][j] == 1) {
				return false;
			}
			
		}
		// 대각선(오른쪽 위)
		for (int i = row-1, j = col+1; i >= 0 && j < BOARD_SIZE; i--, j++) {
			if (board[i][j] == 1) {
				return false;
			}
			
		}
		return true;
		
	}
	
	public int getSolutionCount() {
		return solutionCount;
	}
	
	public void printSolve(int solutionCount) {
		// 2중 for문
		// 퀸 => Q
		// 無 => .
		System.out.println(solutionCount +"번 해");
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				System.out.printf("%3s", board[i][j] == 1 ? "Q" : "." );
			}
			System.out.println();
		}
		
		System.out.println("퀸의 위치: ");
		for (int i = 0; i < queens.size(); i++) {
			System.out.print(queens.get(i).toString()+" ");
		}
		System.out.println("\n");
	}

	public static void main(String[] args) {
		NQueen solver = new NQueen();
		solver.solve();
	}
	
}