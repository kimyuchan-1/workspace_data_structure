package chap8_List;

import java.util.Random;

//import java.util.Scanner;

public class Josephus {

	public static void main(String[] args) {
		
		/*
		 * 입력:
		 * N: 사람의 수(각 사람의 id는 정수 난수로 생성- 생성 순서로 원형 singly linked list에 id의 올림차순으로 정렬되게 삽입)
		 * K: 제거할 사람의 순서
		 * 
		 * 출력:
		 * 1.	올림차순으로 정렬된 사람 id 순서를 출력
		 * 2.	제거되는 k 번째 id 순서대로 출력
		 * 3.	마지막으로 남는 사람의 id를 출력
		 * 
		 * */
		
		// Scanner sc = new Scanner(System.in);
		int n = 7;
		int k = 3;
		
		Random rd = new Random(42);
		
		CurcularLinkedList sll = new CurcularLinkedList();
		
		for (int i = 0 ; i < n; i++) {
			int id = rd.nextInt(1000)+1;
			sll.insertSorted(id);
			}
		
		sll.showList();
		
		//sll.solveJosephus(n, k);

	}

}
