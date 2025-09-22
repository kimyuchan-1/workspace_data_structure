package chap2_기본자료구조;

/*
 * 3번째 실습
 * 교재 83 - 배열 처리 + function parameter 전달 숙달 훈련 
 *  함수에서 배열을 리턴할 때 리턴 타입 정의할 수 있어야 한다
 */

//import java.util.Arrays;
import java.util.Random;
public class train_실습2_6다차원배열 {
	
	private static void inputData(int[][] mat) {
		if (mat == null) {
			return;
		}
		Random rd = new Random();
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				mat[i][j] = rd.nextInt(10)+1;
			}
		}
		
	}
	
	private static void showData(String str, int[][] mat) {
		if (str == null || str.isEmpty() || mat == null) {
			return;
		}
		System.out.println(str);
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				System.out.print(mat[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	private static int[][] addMatrix(int[][] mat1, int[][] mat2) {
		if (mat1 == null || mat2 == null|| mat1.length != mat2.length || mat1[0].length != mat2[0].length) {
			return mat1;
		}
		int[][] addMat = new int[mat1.length][mat1[0].length];
		
		for (int i = 0; i < mat1.length; i++) {
			for (int j = 0; j < mat1[0].length; j++) {
				addMat[i][j] = mat1[i][j]+mat2[i][j];
			}
		}
		
		return addMat;
		
	}
	
	private static int[][] multiplyMatrix(int[][] mat1, int[][] mat2) {
		if (mat1 == null || mat2 == null || mat1[0].length != mat2.length) {
			return mat1;
		}
		int[][] multiMat = new int[mat1.length][mat2[0].length];
		
		for (int i = 0; i < mat1.length; i++) {
			for (int j = 0; j < mat2[0].length; j++) {
				for (int k = 0; k < mat1[0].length; k++) {
					multiMat[i][j] += mat1[i][k] * mat2[k][j];
				}
			}
		}
		
		return multiMat;
	}
	
	private static int[][] transposeMatrix(int[][] mat) {
		if (mat == null ) {
			return mat;
		}
		int[][] transMat = new int[mat[0].length][mat.length];
		
		for (int i = 0; i < mat[0].length; i++) {
			for (int j = 0; j < mat.length; j++) {
				transMat[i][j] = mat[j][i];
			}
		}
		
		
		return transMat;
	}
	
	private static int[][] multiplyMatrixTransposed(int[][] mat1, int[][] mat2) {
		if (mat1 == null || mat2 == null|| mat1[0].length != mat2.length) {
			return mat1;
		}
		
		int[][] mulMat = new int[mat1.length][mat2[0].length];
		int[][] mulTransMat = new int[mulMat[0].length][mulMat.length];
		
		mulMat = multiplyMatrix(mat1, mat2);
		mulTransMat = transposeMatrix(mulMat);
		
		return mulTransMat;
	}
	
	private static boolean equals(int[][] mat1, int[][] mat2) {
		if (mat1 == null || mat2 == null || mat1.length != mat2.length || mat1[0].length != mat2[0].length) {
			return false;
		}
		
		boolean result = false;
		
		for (int i = 0; i < mat1.length; i++) {
			for (int j = 0; j < mat1[0].length; j++) {
				if (mat1[i][j] != mat2[i][j]) {
					result = false;
				} else {
					result = true;
				}
			}
		}
		
		return result;
	}

	public static void main(String[] args) {
		int [][]A = new int[2][3];
		int [][]B = new int[3][4];
		int [][]C = new int[2][4];

		inputData(A);inputData(B);
		int [][]D = A.clone();//교재83 - 배열 복제
		System.out.println("A[2][3] = ");
		showData("행렬 A", A);
		System.out.println("D[2][3] = ");
		showData("행렬 D", D);
		System.out.println();
		System.out.println("B[3][4] = ");
		showData("행렬 B", B);
		int [][]E = addMatrix(A,D);
		System.out.println("E[2][3] = ");
		showData("행렬 E", E);
		C = multiplyMatrix(A,B);
		System.out.println("C[2][4] = ");
		showData("행렬 C", C);

		int [][]F = transposeMatrix(B);
		System.out.println("F[4][3] = ");
		showData("행렬 F", F);
		C= multiplyMatrixTransposed(A,F);
		System.out.println("C[2][3] = ");
		showData("행렬 곱셈 결과-전치행렬 사용", C);
		boolean result = equals(A,C);
		if (result)
			System.out.println("행렬 A,C는 equal이다");
		else
			System.out.println("행렬 A,C는 equal 아니다");
	}
	
}

