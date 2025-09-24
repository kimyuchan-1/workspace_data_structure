package chap3_검색;

/*
 * 3장 3번 실습과제 - 객체 배열의 정렬과 이진검색 - Comparable 구현
 * 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
 * 함수(메소드) 전체를 작성하는 훈련 
 * 
 * Comparable Interface
 * 
 * public interface Comparable<T> {
 *     int compareTo(T o);
 * }
 */

/*
 * 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
 * 함수(메소드) 전체를 작성하는 훈련 
 * 
 * binarySearch(T[], T key)는 API에서 직접 지원하지 않음.
 * 하지만 binarySearch(Comparable<T>[] a, T key)를 사용할 수 있음.
 * 배열이 Comparable<T>을 구현하는 객체라면 정상적으로 동작함.
 * 만약 비교 기준을 다르게 설정하고 싶다면 binarySearch(T[], T key, Comparator<? super T> c)를 사용해야 함
 * 
 * Arrays.sort(Object[])가 존재하는 이유 > 
 * 배열의 원소가 Comparable을 구현하면, Object[] 배열에서도 정렬 가능
 * String[], Integer[], Double[] 등 Comparable을 구현한 기본 래퍼 클래스의 배열을 정렬하는 데 유용
 * 
 *  Object[] numbers = {5, 2, 9, 1, 3}; // Integer 배열 (Integer는 Comparable<Integer> 구현)
 *  Arrays.sort(numbers); // 정상 작동
 *  
 *  Arrays.sort(T[]) (제네릭 버전)
 *  제네릭 배열 T[]을 정렬할 때 더 적합한 방식.
 *  T가 Comparable<T>를 구현했을 경우, 자동으로 compareTo()가 호출됨.
 *  T[] 배열의 원소가 Comparable<T>를 구현하면 Arrays.sort(T[])를 사용할 수 있음.
 */
import java.util.Arrays;
import java.util.Comparator;

//5번 실습 - 2장 실습 2-14를 수정하여 객체 배열의 정렬 구현
class Fruit5 {
	String name;
	int price;
	String expire;
	
	public Fruit5(String name, int price, String expire) {
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

class FruitName2 implements Comparator<Fruit5> {
	@Override
	public int compare(Fruit5 a, Fruit5 b) {
		return a.getName().compareTo(b.getName());
	}
	
}

class FruitPrice2 implements Comparator<Fruit5> {
	@Override
	public int compare(Fruit5 a, Fruit5 b) {
		return ((Integer)(a.getPrice())).compareTo(b.getPrice());
	}
	
}


public class train_실습3_6_1객체배열탐색_Comparable {
	private static void showData(String msg, Fruit5[] arr) {
		if (msg == null || msg.isEmpty() || arr == null || arr.length == 0) {
			return;
		}
		
		System.out.println(msg);
		for (Fruit5 f : arr) {
			System.out.println("과일명 : "+f.getName()+", 가격 : "+f.getPrice()+", 유통기한 : "+f.getExpire());
		}
		System.out.println();
	}
	
	private static void reverse(Fruit5[] arr) {
		if (arr == null || arr.length == 0) {
			return;
		}
		
		Fruit5[] temp = new Fruit5[arr.length];
		
		for (int i = arr.length-1; i >= 0; i--) {
			temp[arr.length-1-i] = arr[i];
		}
		for (int j = 0; j <arr.length; j++) {
			arr[j] = temp[j];
		}
		
	}
	
	private static void sortData(Fruit5[] arr, Comparator<Fruit5> c) {
		if (arr == null || c == null || arr.length == 0) {
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = i+1; j < arr.length; j++) {
				if (c.compare(arr[i], arr[j]) > 0) {
					swap(arr,i,j);
				}
			}
		}
	}
	
	private static void swap(Fruit5[] arr, int i, int j) {
		if (arr == null || i == j || arr.length == 0) {
			return;
		}
		
		Fruit5 temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	private static int binarySearch(Fruit5[] arr, Fruit5 newFruit, Comparator<Fruit5> c) {
		if (arr == null || arr.length == 0 || newFruit == null || c == null) {
			return -1;
		}
		
		int head = 0;
		int tail = arr.length -1;
		
		while (head <= tail) {
			int mid = (head + tail) / 2;
			if (c.compare(arr[mid], newFruit) == 0) {
				return mid;
			} else if (c.compare(arr[mid], newFruit) < 0) {
				head = mid + 1;
			} else {
				tail = mid - 1;
			}
		}
		return -1;
	}
	
	private static void sortData2(Fruit5[] arr, Comparator<Fruit5> c) {
		if (arr == null || arr.length == 0 || c == null) {
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = i+1; j < arr.length; j++) {
				if (c.compare(arr[i], arr[j]) > 0) {
					swap(arr,i,j);
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		Fruit5[] arr = {new Fruit5("사과", 200, "2023-5-8"), 
				new Fruit5("감", 500, "2023-6-8"),
				new Fruit5("대추", 200, "2023-7-8"), 
				new Fruit5("복숭아", 50, "2023-5-18"), 
				new Fruit5("수박", 880, "2023-5-28"),
				new Fruit5("산딸기", 10, "2023-9-8") };
		
		System.out.println("\n정렬전 객체 배열: ");
		showData("정렬전 객체", arr);
		
		FruitName2 cc = new FruitName2();
//		FruitPrice2 cp = new FruitPrice2();
		
		System.out.println("\n comparator cc 객체를 사용:: ");
		Arrays.sort(arr, cc); // Arrays.sort(T[]a, Comparator<? super T> c) 사용
		showData("Arrays.sort(arr, cc) 정렬 후", arr);
		
		reverse(arr);
		showData("역순 재배치 후", arr);
		
		sortData(arr, cc);
		showData("sortData(arr,cc) 실행후", arr);
		
		// 람다식은 익명클래스 + 익명 객체이다 - Comparator 객체를 사용하려면 compare() 메소드를 구현해야 하는 데 이것이 람다식이다
		//compare()를 구현해야 하므로 (a,b)이다 왜 람다식에서 parameter가 2개인지 이유를 이해 필요 
		//
		Comparator<Fruit5> cc_price2 = (Fruit5 a, Fruit5 b) -> {
			return ((Integer)(a.getPrice())).compareTo(b.getPrice());
		};
		
		Arrays.sort(arr, cc_price2); // 람다식으로 만들어진 객체를 사용
		showData("람다식 변수 cc_price2을 사용한 Arrays.sort(arr, cc) 정렬 후", arr);
		
		Arrays.sort(arr, (a, b) -> a.getPrice() - b.getPrice());// Fruit5에 compareTo()가 있어도 람다식 우선 적용
		showData("람다식: (a, b) -> a.getPrice() - b.getPrice()을 사용한 Arrays.sort(arr, cc) 정렬 후", arr);

		System.out.println("\n익명클래스 객체로 정렬(가격)후 객체 배열: ");
		Arrays.sort(arr, new Comparator<Fruit5>() {//여기서 comparator가 필요한 이유를 알아야 
			//
			@Override
			public int compare(Fruit5 a, Fruit5 b) {
				return ((Integer)(a.getPrice())).compareTo(b.getPrice());
			}
		});
		showData("익명 클래스 객체를 사용한 정렬(가격):", arr);
		
		System.out.println("\ncomparator 정렬(이름)후 객체 배열: ");
		
	
		Comparator<Fruit5> cc_name = new Comparator<Fruit5>() {// 익명클래스 사용
			//
			@Override
			public int compare(Fruit5 a, Fruit5 b) {
				return a.name.compareTo(b.name);
			}
		};
		Comparator<Fruit5> cc_price = new Comparator<Fruit5>() {// 익명클래스 사용
			//
			@Override
			public int compare(Fruit5 a, Fruit5 b) {
				return ((Integer)(a.getPrice())).compareTo(b.getPrice());
			}

		};
		Arrays.sort(arr, cc_name);
		showData("comparator 객체를 사용한 정렬:", arr);

		Fruit5 newFruit5 = new Fruit5("수박", 880, "2023-5-18");
		/*
		 * 교재 115 Arrays.binarySearch에 의한 검색
		 */
		int result3Index = Arrays.binarySearch(arr, newFruit5, cc_name);
		System.out.println("\nArrays.binarySearch([수박,880,2023-5-18]) 조회결과::" + result3Index);
		
		result3Index = binarySearch(arr, newFruit5, cc_name);
		System.out.println("\nbinarySearch([수박,880,2023-5-18]) 조회결과::" + result3Index);

		sortData2(arr, cc_price);
		System.out.println("\ncomparator 정렬(가격)후 객체 배열: ");
		showData("comparator를 사용한 정렬후:", arr);
		
		result3Index = Arrays.binarySearch(arr, newFruit5, cc_price);
		System.out.println("\nArrays.binarySearch([수박,880,2023-5-18]) 조회결과::" + result3Index);
		
		result3Index = binarySearch(arr, newFruit5, cc_price);
		System.out.println("\nbinarySearch() 조회결과::" + result3Index);
	
	}
	
	

}
