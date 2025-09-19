package chap2_기본자료구조;

//입력받은 10진수를 2진수 ~ 36진수로 기수 변환하여 출력

import java.util.Scanner;
public class train_실습2_12기수변환교재코드 {

	//입력받은 10진수를 2진수 ~ 36진수로 기수 변환하여 출력

	 //--- 정숫값 decimal를 radix 진수로 변환하여 배열 toNumber에 아랫자리부터 넣어 두고 자릿수를 반환 ---//
	 private static int cardConv(int decimal, int radix, char[] toNumber) {
	     int digits = 0;                        // 변환 뒤 자릿수
	     String dchar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	     do {
	         toNumber[digits++] = dchar.charAt(decimal % radix);    // r로 나눈 나머지를 저장
	         decimal /= radix;
	     } while (decimal != 0);

	     for (int i = 0; i < digits / 2; i++) {    // 배열 d의 숫자 문자열을 역순 정렬
	         char t = toNumber[i];
	         toNumber[i] = toNumber[digits - i - 1];
	         toNumber[digits - i - 1] = t;
	     }

	     return digits;
	 }

	 public static void main(String[] args) {
	     @SuppressWarnings("resource")
		 Scanner stdIn = new Scanner(System.in);
	     int decimal;                       // 변환할 정수
	     int toRadix;                       // 기수
	     int toRadixDigits;                      // 변환 뒤 자릿수
	     int retry;                    // 다시 한 번?
	     char[] toNumber = new char[32];    // 변환 후 각 자리의 숫자를 넣어 두는 문자 배열

	     System.out.println("10진수를 기수 변환합니다.");
	     do {
	         do {
	             System.out.print("변환하는 음이 아닌 정수: ");
	             decimal = stdIn.nextInt();
	         } while (decimal < 0);

	         do {
	             System.out.print("어떤 진수로 변환할까요?(2-36): ");
	             toRadix = stdIn.nextInt();
	         } while (toRadix < 2 || toRadix > 36);

	         toRadixDigits = cardConv(decimal, toRadix, toNumber); // decimal를 toRadix 진수로 변환

	         System.out.print(toRadix + "진수로 ");
	         for (int i = 0; i < toRadixDigits; i++)// 순서대로 표시
	             System.out.print(toNumber[i]);
	         System.out.println("입니다.");

	         System.out.print("다시 한 번 할까요? (1…예/0…아니오): ");
	         retry = stdIn.nextInt();
	     } while (retry == 1);
	 } 
	}	
	