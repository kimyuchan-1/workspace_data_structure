package chap6_Sorting;

//퀵 정렬(비재귀 버전) - 교재 버젼으로 stack을 2개 사용하는 문제가 있다 

import java.util.Scanner;
import java.util.Stack;

public class 실습6_10_1QuickSortStack {
	 //--- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
	 static void swap(int[] a, int idx1, int idx2) {
	     int t = a[idx1];  a[idx1] = a[idx2];  a[idx2] = t;
	 }
	 
	 class Point {
		 int left;
		 int right;
		 
		 public Point(int left, int right) {
			 this.left = left;
			 this.right = right;
		 }
	 }
	 //--- 퀵 정렬(비재귀 버전)---//
	 static void quickSort(int[] a, int left, int right) {
		 // 현재 비재귀 버전의 퀵 정렬은 스택을 2개를 사용하는데, 1개만 써서 비재귀 퀵 정렬을 실행할 수 있음
		 Stack<Point> st = new Stack<>();
		 Point p = new Point(left,right);
		 st.push(p);
		 /*
	     IntStack lstack = new IntStack(right - left + 1);
	     IntStack rstack = new IntStack(right - left + 1);
	
	     lstack.push(left);
	     rstack.push(right);
		*/
	     while (!st.isEmpty()) {
	         int pl = left  = p.left;        // 왼쪽 커서
	         int pr = right = p.right;        // 오른쪽 커서
	         int x = a[(left + right) / 2];        // 피벗은 가운데 요소
	
	         do {
	             while (a[pl] < x) pl++;
	             while (a[pr] > x) pr--;
	             if (pl <= pr)
	                 swap(a, pl++, pr--);
	         } while (pl <= pr);
	         showData(a);
	         System.out.println();
	         if (left < pr) {
	        	 System.out.println("left = " + left + ", pr = " + pr);
	        	 p = new Point(left, pr);
	        	 st.push(p);
	             //lstack.push(left);           // 왼쪽 그룹 범위의
	             //rstack.push(pr);             // 인덱스를 푸시
	         }
	         if (pl < right) {
	          	 System.out.println("pl = " + pl + ", right = " + right);
	          	 p = new Point(pl, right);
	        	 st.push(p);
	             //lstack.push(pl);             // 오른쪽 그룹 범위의
	             //rstack.push(right);          // 인덱스를 푸시
	         }
	     }
	 }
	 static void showData(int[] d) {
		 System.out.println();
	     for (int i = 0; i < d.length; i++)
	         System.out.print(d[i] + " ");
	 }
	 public static void main(String[] args) {
	     @SuppressWarnings("resource")
		 Scanner stdIn = new Scanner(System.in);
	
	     System.out.println("퀵 정렬");
	     System.out.print("요솟수: ");
	     int nx = stdIn.nextInt();
	     int[] x = new int[nx];
	
	     for (int i = 0; i < nx; i++) {
			double d = Math.random();
			x[i] = (int) (d * 20);
	         //System.out.print("x[" + i + "]: ");
	         //x[i] = stdIn.nextInt();
	     }
	     showData(x);
	
	     quickSort(x, 0, nx - 1);            // 배열 x를 퀵정렬
	
	     System.out.println("오름차순으로 정렬했습니다.");
	     showData(x);
	 }
}
