package chap1_기본알고리즘;

import java.util.Arrays;
import java.util.StringTokenizer;

public class train_실습1_6_스트링토큰_과제 {
/*
 * StringTokenizer(input), countTokens(), hasMoreTokens(), nextToken()
 * parseDouble(stringArray[i])
 */
	public static String[] extractSortStrings(String msg) {
		
		String[] strA = msg.split(" ");
		Arrays.sort(strA);
		return strA;
		
		/*
		StringTokenizer st = new StringTokenizer(msg, " ");
		String[] strA = new String[st.countTokens()];
		for (int i = 0; i < strA.length; i++) {
			strA[i] = st.nextToken();
		}
		return strA;
		*/
	}
	
	public static double[] convertSortToDouble(String[] strA) {
		
		double[] dbA = new double[strA.length];
		for (int i = 0; i < strA.length; i++) {
			dbA[i] = Double.parseDouble(strA[i]);
		}
		Arrays.sort(dbA);
		return dbA;
		
	}
	public static void printStringArray(String[] strA) {
		/*
		for (String s : strA) {
			System.out.print(s+" ");
		}
		System.out.println();
		*/
		String str = Arrays.toString(strA);
		StringTokenizer st = new StringTokenizer(str);
		
		while (st.hasMoreTokens()) {
			System.out.print(st.nextToken()+" ");
		}
		System.out.println();
	}
	public static void printDoubleArray(double[] dbA) {
		/*
		for (Double d : dbA) {
			System.out.print(d+" ");
		}
		System.out.println();
		*/
		String db = Arrays.toString(dbA);
		StringTokenizer st = new StringTokenizer(db);
		
		while (st.hasMoreTokens()) {
			System.out.print(st.nextToken()+" ");
		}
		System.out.println();
	}

  
    public static void main(String[] args) {
        String msg = "3.24 3.34156 1141.56 214. 0.0314156 54.12f";
        
        // 실수를 문자열로 추출하고 정렬 후 출력
        String[] sortedStringArray = extractSortStrings(msg);
        System.out.println("정렬 스트링 배열:");
        printStringArray(sortedStringArray);
        
        // 문자열 배열을 실수 배열로 변환하고 정렬 후 출력
        double[] sortedDoubleArray = convertSortToDouble(sortedStringArray);
        System.out.println("정렬 실수 배열:");
        printDoubleArray(sortedDoubleArray);
    }
}
