package chap5_Recursive;
//print로 변수 값 확인하는 디버깅 노동 피하자
//이클립스 디버깅 실습 필요 : variables tab, Expressions tab 사용하기
//92개 해 확인 필요
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

public class train_QueenEight_구현실습과제 {
	
	private static final int N = 8;
	private static int[][] board;
	
	private static int numberOfSolutions = 0;
	
	private static void initializeBoard() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] = 0;
			}
		}
	}
	
	private static boolean isSafe(int row, int col) {
		
		// 행 검사
		for (int i = col; i < N; i++) {
			if (board[row][i] == 1) {
				return false;
			}
		}
		
		// 대각선 검사(오른쪽 위)
		for (int i = N-1, j = N-1; i >= row && j >= col; i--, j--) {
			if (board[i][j] == 1) {
				return false;
			}
		}
		
		// 대각선 검사(왼쪽 위)
		for (int i = N-1, j = col; i >= row && j < N; i--, j++) {
			if (board[i][j] == 1) {
				return false;
			}
		}
		
		return true;
	}
	
	private static void solveNQueen() {
		
		int count = 0; // 퀸 배치 갯수
		int ix = 0, iy = 0; // 행 ix, 열 iy
		
		Stack<Point> st = new Stack<>(); 
		Point p = new Point(ix, iy); //현 위치를 객체로 만들고
		board[ix][iy] = 1;//현 위치에 queen을 넣었다는 표시를 하고
		count++;
		st.push(p);// 스택에 현 위치 객체를 push
		ix++;//ix는 행별로 퀸 배치되는 것을 말한다.
		
		while (true) {
			
			for 
			
			if (isSafe(ix, iy)) {
				//pop() 물리기
				Point backtrack = st.pop();
				if (backtrack.getIx() == ix && backtrack.getIy() == iy) {
					board[backtrack.getIx()][backtrack.getIy()] = 0;
					break;
				}
				board[backtrack.getIx()][backtrack.getIy()] = 0;
				count--;
			}
				//push() nextMove()의 반환값이 -1이 아닌 경우
			if (count == 8) { //8개를 모두 배치하면
				numberOfSolutions++;
			}
			
		}
		
	}

	private static void showQueens() {// 배열 출력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.printf("%3d",board[i][j] == 1 ? "Q" : "." );
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		
		initializeBoard();
		solveNQueen();
		showQueens();
	}
}