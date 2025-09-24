package chap2_기본자료구조;
/*
 * 2장 제출 과제 
 * Comparable 인터페이스의 구현 
 * 5번 실습 - 2장 실습 2-10를 수정하여 객체 배열의 정렬 구현
 */
import java.util.Arrays;

//5번 실습 - 2장 실습 2-14를 수정하여 객체 배열의 정렬 구현
class PhyscData2 implements Comparable<PhyscData2>{
	private String name;
	private int height;
	private double vision;
	
	// 생성자
	public PhyscData2() {}
	
	public PhyscData2(String name, int height, double vision) {
		this.name = name;
		this.height = height;
		this.vision = vision;
	}
	
	// getter
	public String getName() {
		return name;
	}
	public int getHeight() {
		return height;
	}
	public double getVision() {
		return vision;
	}
	
	// compareTo() 구현
	public int compareTo(PhyscData2 p) {
		
		int result = 0;
		
		if (this.name.compareTo(p.getName()) < 0) {
			result = -1;
		} else if (this.name.compareTo(p.getName()) == 0) {
			if (((Integer)(this.height)).compareTo(p.getHeight()) < 0) {
				result = -1;
			} else if (((Integer)(this.height)).compareTo(p.getHeight()) == 0) {
				if (((Double)(this.vision)).compareTo(p.getVision()) < 0) {
					result = -1;
				} else if (((Double)(this.vision)).compareTo(p.getVision()) == 0) {
					result = 0;
				} else {
					result = 1;
				}
			} else {
				result = 1;
			}
		} else {
			result = 1;
		}
		
		return result;
	}
	

}
public class train_실습2_14_1객체배열정렬 {
	
	private static void sortData(PhyscData2[] data) {
		if (data.length == 0) {
			return;
		}
		for (int i = 0; i < data.length; i++) {
			for (int j = i; j < data.length; j++) {
				if (data[i].compareTo(data[j]) > 0) {
					swap(data,i,j);
				}
			}
		}
		
	}
	
	private static void swap(PhyscData2[] data, int i, int j) {
		if (data.length == 0 || i == j) {
			return;
		}
		
		PhyscData2 temp = data[i];
		data[i] = data[j];
		data[j] = temp;
		 
	}
	
	private static void showData(String msg, PhyscData2[] data) {
		if (msg == null || msg.isEmpty() || data.length == 0) {
			return;
		}
		System.out.println(msg);
		for (int i = 0; i < data.length; i++) {
			System.out.println("이름 : "+data[i].getName()+", 키 : "+data[i].getHeight()+", 시력 : "+data[i].getVision());
		}
		System.out.println();
	}
	
	private static int binarySearch(PhyscData2[] data, String str) {
		if (data.length == 0 || str == null || str.isEmpty()) {
			return -1;
		}
		
		int head = 0;
		int tail = data.length-1;
		while (head <= tail) {
			int mid = (head + tail) /2;
			if (data[mid].getName().compareTo(str) == 0) {
				return mid;
			} else if (data[mid].getName().compareTo(str) < 0){
				head = mid + 1;
			} else {
				tail = mid - 1;
			}
		}
		return -1;
	}
	
	private static PhyscData2[] insertObject(PhyscData2[] data, PhyscData2 p) {
		if (p == null) {
			return data;
		}
		
		PhyscData2[] newData = new PhyscData2[data.length+1];
		int cnt = 0;
		
		for (int i = 0; i < data.length; i++) {
			if (data[i].compareTo(p) < 0) {
				cnt++;
			} else {
				break;
			}
		}
		
		for (int i = 0; i < newData.length; i++) {
			if (i < cnt) {
				newData[i] = data[i];
			} else if (i == cnt) {
				newData[i] = p;
			} else {
				newData[i] = data[i-1];
			}
		}
		
		
		return newData;
	}
	
	

	public static void main(String[] args) {
		PhyscData2[] data = {
				new PhyscData2("홍길동", 152, 0.3),
				new PhyscData2("홍동", 164, 1.3),
				new PhyscData2("홍길동", 152, 0.7),
				new PhyscData2("김홍길동", 172, 0.3),
				new PhyscData2("이길동", 182, 0.6),
				new PhyscData2("박길동", 167, 0.2),
				new PhyscData2("최길동", 169, 0.5)
		};
		showData("정렬전",data);
		sortData(data);
		showData("정렬후", data);
		
		Arrays.sort(data);//compareTo()가 필요하다 
		showData("Arrays.sort() 실행후", data);
		
		int resultIndex = binarySearch(data, "이길동");
		if (resultIndex >= 0)
			System.out.println("이길동이 존재하면 인덱스 = "+resultIndex);
		else
			System.out.println("사이다가 존재하지 않는다");
		
		PhyscData2[] newData= insertObject(data, new PhyscData2("소주다", 179, 1.5));
		//배열의 사이즈를 1개 증가시킨후 insert되는 객체 보다 큰 값들은 우측으로 이동, 사이즈가 증가된 객체 배열을 리턴
		showData("삽입후", newData);
	}

}
