package chap2_기본자료구조;
/*
 * 2장 - 정수 배열 정렬
 */

//교재 67 - 실습 2-5
//2번 실습
import java.util.Random;
import java.util.Arrays;

public class train_실습2_5정수배열정렬 {
	
	private static void inputData(int[] arr) {
		if (arr == null) {
			return;
		}
		Random rd = new Random(11);
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rd.nextInt(10,60)+1;
		}
	}
	
	private static void showData(String str, int[] arr) {
		if (str == null || str.isEmpty() || arr == null) {
			return;
		}
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
	
	public static void main(String[] args) {
		
		int []data = new int[10];
		inputData(data); //난수를 10 ~ 60 사이에 생성
		showData("난수 입력", data);
		/*
		sortData(data);
		showData("정렬후", data);
		*/
		reverse(data);//역순으로 재배치 - 정렬 아님 
		showData("역순 재배치", data);

		reverseSort(data);//역순으로 재배치 - 정렬 아님 
		showData("역순 정렬후", data);
		sortData(data);
		int realData[] = {5, 15, 99};
		for (int newData: realData) {
			int []result = insertData(data, newData);//입력 실수보다 큰 숫자를 우측으로 이동
			System.out.print("\n\n"+ newData+ " : ");
			showData("실수 삽입후", result);
			data = result;
		}
	}
	
	/*
	 * 난이도가 매우 높은 알고리즘 구현
	 * 정렬된 기존 배열에 임의 값을 추가하는 알고리즘 > 새 배열의 크기는 기존 배열보다 +1로 만들고 기존 배열을 copy할 때
	 * 삽입된 값이 중간에 들어가는 알고리즘 구현하기
	 * O(n) 알고리즘으로 구현 
	 */
	private static int[] insertData(int []data, int value) {//insert되는 실수 값이 insert될 위치를 찾아 보다 큰 값은 우측으로 이동
		if ( data == null) {
			return null;
		}
		int newData[] = new int[data.length+1];
		int cnt = 0; // 위치
		
		for (int i : data) {
			if (i - value < 0) {
				cnt++;
			} 
		}
		for (int j = 0; j < newData.length; j++) {
			if (j < cnt) {
				newData[j] = data[j];
			} else if (j == cnt) {
				newData[j] = value;
			} else {
				newData[j] = data[j-1];
			}
		}
		
		return newData;
	}
	private static void reverse(int[] data) {
		if (data == null) {
			return;
		}
		int[] newData = new int[data.length];
		
		for (int i = data.length-1; i >= 0; i--) {
			newData[data.length-1-i] = data[i];
		}
		for (int j = 0; j <data.length; j++) {
			data[j] = newData[j];
		}
	}
	
	private static void sortData(int[] data) {
		if (data == null) {
			return;
		}
		Arrays.sort(data);
	}
	
	private static void reverseSort(int[] data) {
		if (data == null) {
			return;
		}
		int[] newData = new int[data.length];
		Arrays.sort(data);
		
		for (int i = data.length-1; i >= 0; i--) {
			newData[data.length-1-i] = data[i];
		}
		for (int j = 0; j <data.length; j++) {
			data[j] = newData[j];
		}
		
	}


}
