package chap3_검색;

import java.util.Arrays;
import java.util.Comparator;

/*
 * 3장 과제2 : 객체 배열 정렬/검색 - 람다식사용
 * 
* Comparator를 사용하는 방법
* MyComparator implements Comparator<>
* MyComparator myComparator = new MyComparator();
* 
* Arrays.sort(array, myComparator);
* Collections.sort(list, new MyComparator());
*/

class Fruit4 {
	String name;
	int price;
	String expire;
	
	public Fruit4(String name, int price, String expire) {
		this.name = name;
		this.price = price;
		this.expire = expire;
	}
	
	public String getName() {
		return this.name;
	}
	public int getPrice() {
		return this.price;
	}
	
	public String getExpire() {
		return this.expire;
	}

}
//교재 123~129 페이지 참조하여 구현
class FruitName implements Comparator<Fruit4>{
	@Override
	public int compare(Fruit4 f1, Fruit4 f2) {
		return f1.getName().compareTo(f2.getName());
	}
	
}
class FruitPrice implements Comparator<Fruit4>{
	@Override
	public int compare(Fruit4 f1, Fruit4 f2) {
		return ((Integer)(f1.getPrice())).compareTo(f2.getPrice());
	}
	
}

public class train_실습3_8_1객체배열정렬검색_람다식 {
	
	private static void showData(String msg, Fruit4[] arr) {
		if (arr == null || arr.length == 0 || msg == null || msg.isEmpty()) {
			return;
		}
		
		System.out.println(msg);
		for (Fruit4 f : arr) {
			System.out.println("과일명 : "+f.getName()+", 가격 : "+f.getPrice()+", 유통기한 : "+ f.getExpire());
		}
		System.out.println();
		
	}
	
	private static void swap(Fruit4[] arr, int i, int j) {
		Fruit4 temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	private static void sortData(Fruit4[] arr, Comparator<Fruit4> c) {
		if (arr == null || arr.length == 0 || c == null) {
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j < arr.length; j++) {
				if (c.compare(arr[i], arr[j]) > 0) {
					swap(arr,i,j);
				}
			}
		}
		
	}
	
	private static int binarySearch(Fruit4[] arr, Fruit4 key, Comparator<Fruit4> c) {
		if (arr == null || arr.length == 0 || c == null || key == null) {
			return -1;
		}
		
		int head = 0;
		int tail = arr.length-1;
		
		while (head <= tail) {
			int mid = (head + tail) / 2;
			if (c.compare(arr[mid], key) == 0) {
				return mid;
			} else if (c.compare(arr[mid], key) < 0) {
				head = mid + 1;
			} else {
				tail = mid - 1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {

		Fruit4[] arr = {new Fruit4("사과", 200, "2023-5-8"), 
				new Fruit4("감", 500, "2023-6-8"),
				new Fruit4("대추", 200, "2023-7-8"), 
				new Fruit4("복숭아", 50, "2023-5-18"), 
				new Fruit4("수박", 880, "2023-5-28"),
				new Fruit4("산딸기", 10, "2023-9-8") };
		
		System.out.println("\n정렬전 객체 배열: ");
		showData("정렬전 객체", arr);
		
		FruitName cc = new FruitName();
		System.out.println("\n comparator cc 객체를 사용:: ");
		Arrays.sort(arr, cc);
		showData("Arrays.sort(arr, cc) Name 정렬 후", arr);
		
		sortData(arr, new FruitPrice());
		showData("Arrays.sort(arr, cc)  Price 실행후", arr);
		
		// 람다식은 익명클래스 + 익명 객체이다
		Comparator<Fruit4> cc_expire = (Fruit4 a, Fruit4 b) -> {
			return a.getExpire().compareTo(b.getExpire());
		};
		Arrays.sort(arr, cc_expire); // 람다식으로 만들어진 객체를 사용
		showData("람다식 변수 cc_expire을 사용한 Arrays.sort(arr, cc) 정렬 후", arr);
		
		Arrays.sort(arr, (a,b) -> ((Integer)(a.getPrice())).compareTo(b.getPrice())); 
		showData("람다식: (a, b) -> a.getPrice() - b.getPrice()을 사용한 Arrays.sort(arr, cc) 정렬 후", arr);

		System.out.println("\n익명클래스 객체로 정렬(가격)후 객체 배열: ");
		Arrays.sort(arr, new Comparator<Fruit4>() {
			@Override
			public int compare(Fruit4 a, Fruit4 b) {
				return ((Integer)(a.getPrice())).compareTo(b.getPrice());
			}
		});
		System.out.println("\ncomparator 정렬(이름)후 객체 배열: ");
		showData("name comparator - 익명 객체를 사용한 정렬:", arr);
		
		//익명 클래스를 사용한 comparator 객체
		Comparator<Fruit4> cc_name = new Comparator<Fruit4>() {// 익명클래스 사용
			@Override
			public int compare(Fruit4 a, Fruit4 b) {
				return a.getName().compareTo(b.getName());
			}
		};
		
		Comparator<Fruit4> cc_price = new Comparator<Fruit4>() {
			@Override
			public int compare(Fruit4 a, Fruit4 b) {
				return ((Integer)(a.getPrice())).compareTo(b.getPrice());
			}
			
		};

		Fruit4 newFruit4 = new Fruit4("수박", 880, "2023-5-18");
		/*
		 * 교재 115 Arrays.binarySearch에 의한 검색
		 */
		int result3Index = Arrays.binarySearch(arr, newFruit4, cc_name);
		System.out.println("\nArrays.binarySearch([수박,880,2023-5-18]) 조회결과::" + result3Index);
		
		result3Index = binarySearch(arr, newFruit4, cc_price);//교재 113 binSearch() 함수를 고쳐서 구현 
		System.out.println("\nbinarySearch([수박,880,2023-5-18]) 조회결과::" + result3Index);

		sortData(arr, cc_price);
		System.out.println("\ncomparator 정렬(가격)후 객체 배열: ");
		showData("comparator를 사용한 정렬후:", arr);	
	}
}

