package chap5_Recursive;

import java.util.Stack;
/*
 * Knight's Tour 문제는 체스판에서 나이트(Knight) 말이 모든 체스판의 칸을 한 번씩만 방문하면서
 * 체스판의 모든 방을 방문하면 종료. 
 * 나이트는 체스에서 "L" 모양으로 움직이는데, 두 칸 직진하고 한 칸 옆으로 이동하는 방식입니다.
 * 임의 위치에서 시작

문제 설명
체스판은 보통 8x8 크기이지만, 이 문제는 임의의 N x N 체스판에서 해결할 수 있습니다.
목표는 나이트가 시작점에서 출발하여 모든 칸을 한 번씩만 방문하면서 끝나는 경로를 찾는 것입니다.
종료조건: 모든 칸이 방문하였을 때 종료 > 방문한 순서를 출력

구현조건:
(x,y)를 저장하는 point 객체를 사용하여 스택으로 non-recursive backtracking 알고리즘으로 구현
 */

// 위치 정보
class Offsets4 {
	int a;
	int b;
	
	public Offsets4(int a, int b) {
		this.a = a; 
		this.b = b;
	}
}

public class train_5_7_1KnightTracking_실습 {
	
	static final int N = 7; // 체스판 크기
	// 체스판 배열
    private static int[][] board = new int[N][N];
	static final Offsets4[] moves = new Offsets4[8];//static을 선언하는 이유를 알아야 한다
	// 나이트가 이동할 수 있는 8가지 방향
    static {
        moves[0] = new Offsets4(-2, -1); // NW
        moves[1] = new Offsets4(-2,  1); // NE
        moves[2] = new Offsets4(-1,  2); // EN
        moves[3] = new Offsets4( 1,  2); // ES
        moves[4] = new Offsets4( 2,  1); // SE
        moves[5] = new Offsets4( 2, -1); // SW
        moves[6] = new Offsets4( 1, -2); // WS
        moves[7] = new Offsets4(-1, -2); // WN
    }
	
    // Point 객체로 나이트의 위치를 저장
    static class Point {
        int x, y, moveToward;

        Point(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.moveToward = move;
        }
    }
    
    // 체스판을 초기화 (-1로 설정)
    private static void initializeBoard() {
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < N; j++) {
    			board[i][j] = -1;
    		}
    	}
    }
    
    // 체스판의 범위 내에서 유효한 움직임인지 확인 & 경계 검사
    private static boolean isSafe(int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < N && board[x][y] == -1);
    }
    

    // 나이트 투어 알고리즘 (비재귀적으로 스택 사용)
    private static boolean solveKnightTracking(int startX, int startY) throws Exception{
    	
    	if (!(startX >= 0 && startX < N && startY >= 0 && startY < N)) {
    		throw new Exception("체스판을 벗어남");
    	}
    	
    	Stack<Point> stack = new Stack<>();
    	
    	initializeBoard();
    	board[startX][startY] = 0;
    	stack.push(new Point(startX, startY, 0));
  
    	int moveCount = 1;
    	
    	while (!stack.isEmpty()) {
    		Point current = stack.peek();
    		
    		if (moveCount == N * N) {
    			return true;
    		}
    		
    		boolean moved = false;
    		
    		for (int i = current.moveToward; i < 8; i++) {
    			int nextX = current.x + moves[i].a;
    			int nextY = current.y + moves[i].b;
    			
    			if (isSafe(nextX, nextY)) {
    				current.moveToward = i + 1;
    				board[nextX][nextY] = moveCount++;
    				stack.push(new Point(nextX, nextY, 0));
    				moved = true;
    				break;
    			}
    		}
    		
    		// 백트래킹
    		if (!moved) {
    			Point backtrack= stack.pop();
    			if (backtrack.x == startX && backtrack.y == startY) {
    				board[backtrack.x][backtrack.y] = -1;
    				return false;
    			}
    			board[backtrack.x][backtrack.y] = -1;
    			moveCount--;
    		}
    	}
    	return false;
    	
    }

    // 결과 출력
    private static void showTracking() {
    	System.out.println("기사의 여행 경로 : ");
    	for (int i = 0; i < N; i++) {
        	for (int j = 0; j < N; j++) {
        		System.out.printf("%3d", board[i][j]);
        	}
        	System.out.println();
        }
    }

    public static void main(String[] args) {
    	
    	try {
    		// 나이트가 (0, 0)에서 시작
            if (solveKnightTracking(0, 0)) {
                showTracking();
            } else {
                System.out.println("해결할 수 없습니다.");
            }
    	} catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
        
    }
}
