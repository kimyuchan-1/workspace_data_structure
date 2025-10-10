package chap5_Recursive;

/*
 * 마방진: 마법 magic + 정방형 배열 + 배치 진열의 진 > 숫자를 특이하게 배열하여 모든 방향의 합이 일정
 * **매직 스퀘어(Magic Square)**는 n×n 크기의 정사각형 배열에 숫자를 배치하되, 
 * 모든 행, 열, 대각선의 숫자 합이 동일하게 되는 배열을 말합니다. 
 * 이때 이 동일한 합을 **매직 상수(Magic Constant)**라고 합니다.
 * n은 3,5,7 등 홀수일 때
 */
public class train_5_5_1마방진_실습 {

    public static void main(String[] args) {
        int n = 3; // 마방진의 크기
        int[][] magicSquare = new int[n][n];
/*
 * 루벤스의 방법 단계:
1. 첫 번째 숫자를 첫 번째 행의 가운데 열에 배치합니다.
2. 다음 숫자는 항상 대각선 위 오른쪽(북동쪽)으로 이동하여 배치합니다.
    2.1 만약 배열의 경계를 벗어나면 반대편으로 이동합니다.
        예를 들어, 열이 배열의 오른쪽 끝을 벗어나면 맨 왼쪽 열로 이동하고, 
        행이 배열의 맨 위를 벗어나면 맨 아래로 이동합니다.
3. 이미 숫자가 있는 칸에 도달한 경우, 현재 위치 바로 아래의 행으로 이동하여 다음 숫자를 배치합니다.
 */
        // 마방진 생성 알고리즘 (루벤스의 방법)
        int row = 0, col = n / 2; // 시작 위치
        for (int num = 1; num <= n * n; num++) {
            magicSquare[row][col] = num; // 현재 위치에 숫자 배치
            //구현
            
            int nextRow = (row - 1 + n) % n;
            int nextCol = (col + 1) % n;
            
            if (magicSquare[nextRow][nextCol] != 0) {
            	row = (row + 1) % n;
            } else {
            	row = nextRow;
            	col = nextCol;
            }
            
        }

        // 마방진 출력
        showSquare(magicSquare);

        // 마방진의 합 확인
        int magicSum = n * (n * n + 1) / 2;
        System.out.println("가로, 세로, 대각선의 합 =  " + magicSum );
        System.out.println("마방진 검사 = " + checkSquare(magicSquare, magicSum));
    }

    // 마방진 출력 메서드
    static void showSquare(int[][] magicSquare) {
    	//구현
    	int n = magicSquare.length;
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++) {
    			System.out.printf("%d \t", magicSquare[i][j]);
    		}
    		System.out.println();
    	}
    }

    // 마방진 유효성 검증 메서드
    static boolean checkSquare(int[][] magicSquare, int magicSum) {
    	// 구현 
    	boolean result = false;
    	int n = magicSquare.length;
    	int rSum, cSum, dSum, rdSum;
    	
    	for (int i = 0; i < n; i++) {
    		rSum = cSum = 0;
    		for (int j = 0; j < n; j++) {
    			rSum += magicSquare[i][j];
    			cSum += magicSquare[j][i];
    		}
    		if (rSum == magicSum && cSum == magicSum) {
    			result = true;
    		} else {
    			result = false;
    		}
    	}
    	
    	dSum = rdSum = 0;
    	for (int i = 0; i < n; i++) {
    		dSum += magicSquare[i][i];
    		rdSum += magicSquare[i][n-1-i];
    	}
    	
    	if (dSum == magicSum && rdSum == magicSum) {
    		result = true;
    	} else {
    		result = false;
    	}
    	
    	return result;
    }
}
