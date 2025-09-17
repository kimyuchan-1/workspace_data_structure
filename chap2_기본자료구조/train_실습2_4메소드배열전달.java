package chap2_기본자료구조;
/*
 * 2장: 메소드 함수에 parameter 전달
 * 메소드에 배열 전달 실습: 교재 59 - 메소드의 매개변수로 배열 사용하기
 * function parameters를 작성할 수 있어야 한다 
 */

import java.util.Random;
public class train_실습2_4메소드배열전달 {
	static int top = 0;
	static final int MAX_LENGTH = 20;
	public static void main(String[] args) {
		int []data = new int[10];
		inputData(data);
		showData("소스데이터",data);
		int max = findMax(data);
		System.out.println("\nmax = " + max);
		boolean existValue = findValue(data, 3);
		System.out.println("찾는 값 = " + 3 + ", 존재여부 = " + existValue);
		reverse(data);// 역순으로 출력 
		showData("역순 데이터", data);
		
	}
	public static void showData(String str, int[] arr) {
		//top 갯수까지 출력한다 [1,2,3]등으로 출력하도록 작성
	}
	public static void inputData(int[] arr) {//교재 63 - 난수의 생성
		//top이 배열에 저장된 갯수를 저장
	}
	public static int findMax(int[] arr) {
		//최대값을 리턴한다 
	}
	findValue() {
		//items[]에 value 값이 있는지를 찾아 존재하면 true, 없으면 false로 리턴

	}
// reverse() 구현
}
