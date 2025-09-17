package chap1_기본알고리즘;

import java.util.Arrays;

public class train_실습1_5_숫자변환_과제 {
/*
 * split(" ")
 * parseInt(stringArray[i])
 */
	public static String[] splitSortString(String input) {
		String[] strA = input.split(" ");
		Arrays.sort(strA);
		return strA;
	}
	
	public static void printStringArray(String[] strA) {
		for (String s : strA) {
			System.out.print(s+" ");
		}
		System.out.println();
	}
	
	public static int[] convertSortToIntArray(String[] input) {
		int[] intA = new int[input.length];
		
		for (int i = 0; i < input.length; i++) {
			intA[i] = Integer.parseInt(input[i]);
		}
		Arrays.sort(intA);
		
		return intA;
	}
	
	public static void printIntArray(int[] intA) {
		for (int i : intA) {
			System.out.print(i+" ");
		}
		System.out.println();
	}

    public static void main(String[] args) {
        String input = "12 111 911 921 94 23 214 222";
        
        // 문자열 배열 정렬 및 출력
        String[] sortedStringArray = splitSortString(input);
        System.out.println("Sorted String Array:");
        printStringArray(sortedStringArray);
        
        // 문자열 배열을 정수 배열로 변환 및 정렬 후 출력
        int[] sortedIntArray = convertSortToIntArray(sortedStringArray);
        System.out.println("Sorted Integer Array:");
        printIntArray(sortedIntArray);
    }
}
