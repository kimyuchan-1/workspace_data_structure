package chap1_기본알고리즘;

/*
 * 문자 단위, 줄단위로 읽는 파일
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class train_실습1_7_파일데이터_과제 {

    // 파일을 읽어 각 라인을 공백으로 분리하여 정렬하는 함수
    public static String[] readSortFromFile(String filename) {
    	// 저장할 데이터
        String data = "12 1 131 2 23 99";
        String data2 = "312 32 8 22 7";
        // 파일에 데이터 쓰기
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(data);  // 데이터를 파일에 씀
            writer.newLine();    // 줄 바꿈
            writer.write(data2); 
            writer.newLine();    // 줄 바꿈
            System.out.println("파일이 성공적으로 생성되고 데이터가 저장되었습니다.");
        } catch (IOException e) {
            System.out.println("파일 쓰기 중 오류 발생: " + e.getMessage());
        }
        
        StringBuilder sb = new StringBuilder();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
        	//used for reading character data
            String line;
            while ((line = br.readLine()) != null) {//줄단위로 read한다 
            	System.out.println("line=" +line);
                sb.append(line).append(" ");  // 라인을 읽어서 StringBuilder에 추가
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 공백으로 구분된 서브스트링을 배열로 변환하고 정렬
        String[] stringArray = sb.toString().trim().split(" ");
        Arrays.sort(stringArray);
        return stringArray;  // 정렬된 배열 반환
    }
    
    public static void printStringArray(String[] strA) {
    	for (String s : strA) {
    		System.out.print(s+" ");
    	}
    	System.out.println();
    }
    
    public static int[] convertSortToInt(String[] strA) {
    	int[] intA = new int[strA.length];
    	for (int i = 0; i < strA.length; i++) {
    		intA[i] = Integer.parseInt(strA[i]);
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
        String filename = "data.txt";  // 파일 이름 설정 (실제 파일 경로로 변경)

        // 파일에서 읽어온 문자열 배열을 정렬 후 출력
        String[] sortedStringArray = readSortFromFile(filename);
        System.out.println("Sorted String Array from File:");
        printStringArray(sortedStringArray);
        
        // 문자열 배열을 정수 배열로 변환하고 정렬 후 출력
        int[] sortedIntArray = convertSortToInt(sortedStringArray);
        System.out.println("Sorted Integer Array:");
        printIntArray(sortedIntArray);
    }
}
