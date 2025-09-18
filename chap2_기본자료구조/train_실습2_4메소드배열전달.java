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
	private static void showData(String str, int[] arr) {
		if (str == null || arr == null || str.isEmpty()) {
			return;
		}
		//top 갯수까지 출력한다 [1,2,3]등으로 출력하도록 작성
		System.out.println(str);
		for (int i = 0; i < arr.length; i++) {
			if (i == 0) {
				System.out.print("["+arr[i]+", ");
			} else if (i == arr.length-1) {
				System.out.println(arr[i]+"]");
			} else {
				System.out.print(arr[i]+", ");
			}
		}
		
	}
	private static void inputData(int[] arr) {//교재 63 - 난수의 생성
		if (arr == null) {
			return;
		}
		//top이 배열에 저장된 갯수를 저장
		Random rd = new Random();
		for (int i =0; i < arr.length; i++) {
			arr[i] = rd.nextInt(100)+1;
		}
	}
	private static int findMax(int[] arr) {
		if (arr == null) {
			return 0;
		}
		//최대값을 리턴한다 
		int max = arr[0];
		for (int i : arr) {
			if (i >= max) {
				max = i;
			}
		}
		
		return max;
	}
	
	private static boolean findValue(int[] arr, int i) {
		if (arr == null) {
			return false;
		}
		//items[]에 value 값이 있는지를 찾아 존재하면 true, 없으면 false로 리턴
		boolean result = false;
		for (int j : arr) {
			if (j == i) {
				result = true;
				break;
			} else {
				result = false; 
			}
		}
		
		return result;
				
	}
// reverse() 구현
	private static void reverse(int[] arr) {
		if (arr == null) {
			return;
		}
		
		int[] newArr = new int[arr.length];
		
		for (int i = arr.length-1; i >= 0; i--) {
			newArr[arr.length-1-i] = arr[i];
		}
		for (int j = 0; j <arr.length; j++) {
			arr[j] = newArr[j];
		}

	}
}
