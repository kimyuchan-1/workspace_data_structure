package chap2_기본자료구조;

import java.util.Arrays;

/*
 * 2장 실습과제4 - 스트링 배열 정렬
 * 정렬된 배열에 insert하면 중간에 끼워 넣으면 큰 값들은 이동해야 하고 크기를 1 증가 처리가 필요 
 */

//compareTo() => 인터페이스 comparable 학습
public class train_실습2_14스트링배열정렬 {
	
	private static void showData(String msg, String[] data) {
		if (msg == null || msg.isEmpty() || data.length == 0) {
			return;
		}
		System.out.print(msg+" : [");
		for (String s : data) {
			if (s == data[data.length-1]) {
				System.out.println(s+"]");
				break;
			}
			System.out.print(s+", ");
		}
		
	}
	
	private static void sortData(String[] data) {
		if (data.length == 0) {
			return;
		}
		
		for (int i = 0; i < data.length; i++) {
			for (int j = i+1; j < data.length; j ++) {
				if (data[i].compareTo(data[j]) > 0) {
					swap(data, i, j);
				}
			}
		}
		
	}
	
	private static void swap(String[] data, int i, int j) {
		
		if (data.length == 0 || i == j) {
			return;
		}
		
		String temp = data[i];
		data[j] = data[i];
		data[i] = temp;
	}
	
	private static String[] insertString(String[] data, String str) {
		
		if (str == null || str.isEmpty()) {
			return data;
		}
		
		String[] newData = new String[data.length+1];
		int cnt = 0;
		
		for (String s : data) {
			if (s.compareTo(str) < 0) {
				cnt++;
			} else {
				break;
			}
		}
		
		for (int i = 0; i < newData.length; i++) {
			if (i < cnt) {
				newData[i] = data[i];
			} else if (i == cnt) {
				newData[i] = str;
			} else {
				newData[i] = data[i-1];
			}
		}
		
		
		
		return newData;
	}
	
	public static void main(String[] args) {
		String []data = {"apple","grape","persimmon", "pear","blueberry", "strawberry", "melon", "oriental melon"};

		showData("정렬전", data); // ["apple", "grape", ..]
		//Arrays.sort(data); // Internally uses String.compareTo(), 동적 바인딩
		Arrays.sort(data, (s1,s2)->s1.compareTo(s2));
		sortData(data);
		showData("정렬후", data);
		String[] newData = insertString(data, "banana");
		showData("삽입후 크기가 증가된 정렬 배열", newData);
		
	}

}
