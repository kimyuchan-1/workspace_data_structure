package chap2_기본자료구조.sol;

import java.util.Arrays;
import java.util.Comparator;

class PhyscData2 implements Comparable<PhyscData2>  {
	private String name;
	private int height;
	private double vision;
	
	public PhyscData2(String name, int height, double vision) {
		super();
		this.name = name;
		this.height = height;
		this.vision = vision;
	}	

	public String getName() {
		return name;
	}

	public int getHeight() {
		return height;
	}

	public double getVision() {
		return vision;
	}


	@Override
	public String toString() {
		return "PhyscData2 [name=" + name + ", height=" + height + ", vision=" + vision + "]";
	}

	@Override
	public int compareTo(PhyscData2 o) {
		return this.name.compareTo(o.name);
	}	
	
}

public class train_실습2_14_1객체배열정렬_풀이 {

	private static void showData(String msg, PhyscData2[] data) {
		System.out.printf(msg + "\n");
		System.out.printf("-".repeat(35) + "\n");
		for(PhyscData2 d : data) {
			System.out.printf("%-10s\t%d\t%.6f\n", d.getName(), d.getHeight(), d.getVision());
		}
		System.out.println();
	}
	
	private static void sortData(PhyscData2[] data) {
		int n = data.length;
		for(int i = 0; i < n-1; i++) { // 선행 
			for(int j = 0; j < n-1-i; j++) { // 후행
				if(data[j].compareTo(data[j+1]) > 0) {
					PhyscData2 temp = data[j];
					data[j] = data[j+1];
					data[j+1] = temp;
				}
			}
		}
	}
	
	private static int binarySearch(PhyscData2[] data, String name) {
		int left = 0;
		int right = data.length - 1;
		while (left <= right) {
			int mid = (left + right) / 2;
			int cmp = data[mid].getName().compareTo(name);
			if (cmp == 0) {
				return mid;
			} else if (cmp < 0) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}		
		return -1;
	}
	
	private static PhyscData2[] insertObject(PhyscData2[] data, PhyscData2 obj) {
		int n = data.length;
		PhyscData2[] newData = new PhyscData2[n + 1];
		int insertIdx = n;
		
		for (int i = 0; i < n; i++) {
			if(data[i].getName().compareTo(obj.getName()) > 0) {
				insertIdx = i;
				break;
			}
		}
		
		for (int i = 0; i < insertIdx;  i++) {
			newData[i] = data[i];
		}
		
		newData[insertIdx] = obj;
		
		for (int i = insertIdx; i < n; i++) {
			newData[i+1] = data[i];
		}
		
		return newData;
	}


	
	public static void main(String[] args) {
		PhyscData2[] data = {
				new PhyscData2("홍길동", 162, 0.3),
				new PhyscData2("홍동", 164, 1.3),
				new PhyscData2("홍길", 152, 0.7),
				new PhyscData2("김홍길동", 172, 0.3),
				new PhyscData2("이길동", 182, 0.6),
				new PhyscData2("박길동", 167, 0.2),
				new PhyscData2("최길동", 169, 0.5),
		};
		showData("정렬전", data);
		sortData(data);
		showData("정렬후", data);
		 
		Comparator<PhyscData2> heightComparator = (a, b) -> Integer.compare(a.getHeight(), b.getHeight());
		Arrays.sort(data, heightComparator);
		showData("Arrays.sort(키) 실행후", data);
		
		Comparator<PhyscData2> visionComparator = (a, b) -> Double.compare(a.getVision(), b.getVision());
		Arrays.sort(data, visionComparator);
		showData("Arrays.sort(시력) 실행후", data);
		
		showData("정렬전", data);
		sortData(data);
		
		String name = "이길동";
		int resultIndex = binarySearch(data, name);
		if (resultIndex >= 0)
			System.out.println(name + "이(가) 존재하면 인덱스 = "+resultIndex);
		else
			System.out.println(name + "이(가) 존재하지 않는다");
		
		PhyscData2[] newData= insertObject(data, new PhyscData2("소주다", 179, 1.5));
		//배열의 사이즈를 1개 증가시킨후 insert되는 객체 보다 큰 값들은 우측으로 이동, 사이즈가 증가된 객체 배열을 리턴
		showData("삽입후", newData);
	}


}
