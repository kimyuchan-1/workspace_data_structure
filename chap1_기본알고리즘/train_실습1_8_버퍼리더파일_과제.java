package chap1_기본알고리즘;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class train_실습1_8_버퍼리더파일_과제 {

    // 파일을 읽어 각 라인을 공백으로 분리하여 정렬하는 함수
    public static String[] readSortFromFile(String filename) {
    	// 저장할 데이터
        String data = "12 1 131 2 23\n";
        String data2 = " 312 32 8 22 7\n";
        String data3 =  "11 111 9 91 911 23 24 222\n";
        // 파일에 데이터 쓰기
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(data);  // 데이터를 파일에 씀
            writer.write(data2); 
            writer.write(data3); 
            System.out.println("파일이 성공적으로 생성되고 데이터가 저장되었습니다.");
        } catch (IOException e) {
            System.out.println("파일 쓰기 중 오류 발생: " + e.getMessage());
        }
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(filename));
        		//to read byte data more efficiently by using a buffer.
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
             
            byte[] buf = new byte[1024];
            int len;

            // 파일의 내용을 ByteArrayOutputStream에 읽기
            while ((len = in.read(buf)) != -1) {//버퍼 크기보다 파일이 작으면 1번 읽는다 > 다음은 -1이므로 종료
            	//to read up to 1024 bytes at a time
                out.write(buf, 0, len);//buf를 out에 출력한다 
            }

            // ByteArrayOutputStream의 내용을 문자열로 변환
            String fileContent = out.toString("UTF-8");
            // 공백으로 구분된 서브스트링을 배열로 변환하고 정렬
            String[] stringArray = fileContent.trim().split("\\s+");
            Arrays.sort(stringArray);  // 정렬
            return stringArray;  // 정렬된 배열 반환
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0];  // 오류 발생 시 빈 배열 반환
        }
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
        String filename = "data2.txt";  // 파일 이름 설정 (실제 파일 경로로 변경)

        // 파일에서 읽어온 문자열 배열을 정렬 후 출력
        String[] sortedStringArray = readSortFromFile(filename);
        System.out.println("파일에서 읽은 스트링의 정렬:");
        printStringArray(sortedStringArray);
        
        // 문자열 배열을 정수 배열로 변환하고 정렬 후 출력
        int[] sortedIntArray = convertSortToInt(sortedStringArray);
        System.out.println("정렬된 정수 배열:");
        printIntArray(sortedIntArray);
    }
}
/*
* a.txt 파일 내용은 "11 111 9 91 911 23 24 222"를 포함한다 > 3줄 이상 데이터가 포함할 때 구현한다 
*/