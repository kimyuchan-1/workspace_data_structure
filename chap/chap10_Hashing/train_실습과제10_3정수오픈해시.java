package chap10_Hashing;

//오픈 주소법에 의한 해시의 사용 예
/*
 * int[] table = new int[3];
	// 메모리상 자동으로 [0, 0, 0] 초기화됨
	System.out.println(table[0]);  // 출력: 0

 * Bucket[] table = new Bucket[3];
	// 현재 상태: [null, null, null] ← 참조만 만들었고 객체는 없음
	// new Bucket[3]는 단지 **"Bucket 객체를 담을 수 있는 그릇 3개 만들기"**이지,
		실제 new Bucket()은 한 번도 호출되지 않았기 때문
		new Bucket[3]는 객체를 만드는 것이 아니라,
 		**객체를 담을 수 있는 참조 공간(=포인터 자리)**만 만들어요.

		각 요소는 초기에 **null**로 설정되며,

		new Bucket()으로 직접 객체를 만들어서 각각 넣어줘야 실제 객체
	System.out.println(table[0]);  // 출력: null
	// table[0].getValue(); → NullPointerException 발생!

 */
import java.util.Scanner;

//오픈 주소법에 의한 해시

class OpenHash2 {

//--- 버킷의 상태 ---//
	enum Status {
		OCCUPIED, EMPTY, DELETED
	} // {데이터 저장, 비어있음, 삭제 완료}

//--- 버킷 ---//
	static class Bucket {
		private int data; // 데이터
		private Status stat; // 상태
		
		public Bucket() {
			stat = Status.EMPTY;
		}
		
		public Bucket(int key) {
			data = key;
			stat = Status.OCCUPIED;
		}
	}

	private int size; // 해시 테이블의 크기
	private Bucket[] table; // 해시 테이블

//--- 생성자(constructor) ---//
	public OpenHash2(int size) {
		try {
			table = new Bucket[size];// Bucket 객체에 대한 "참조"를 저장할 공간만 만드는 것
			/*
			 * 객체 3개를 만들지 않음. Bucket 객체를 담을 수 있는 참조 공간 3개짜리 배열을 만든다
			 *
			 * // 메모리 상태: table[0] = null table[1] = null table[2] = null
			 */
			for (int i = 0; i < size; i++)
				table[i] = new Bucket();// 실제로 각 인덱스에 Bucket 객체를 생성해서 넣는 과정
			this.size = size;
		} catch (OutOfMemoryError e) { // 테이블을 생성할 수 없음
			this.size = 0;
		}
	}

	// --- 해시값을 구함 ---//
	public int hashValue(int key) {
		return (key * key * 131) % size;
	}

	// --- 재해시값을 구함 ---//
	public int rehashValue(int hash) {
		return (hash + 1) % size;
	}

	// --- 키값 key를 갖는 버킷 검색 ---//
	private Bucket searchNode(int key) {
		int kHash = hashValue(key);
		Bucket curr = table[kHash];
		
		
	}

	// --- 키값이 key인 요소를 검색(데이터를 반환)---//
	public int search(int key) {

	}

	// --- 키값이 key인 데이터를 data의 요소로 추가 ---//
	public int add(int key) {
		int kHash = hashValue(key);
		
	}

	// --- 키값이 key인 요소를 삭제 ---//
	public int remove(int key) {

	}

	// --- 해시 테이블을 덤프(dump) ---//
	public void dump() {

	}
}

public class train_실습과제10_3정수오픈해시 {
//--- 메뉴 열거형 ---//
	enum Menu {
		ADD("추가"), REMOVE("삭제"), SEARCH("검색"), DUMP("표시"), TERMINATE("종료");

		private final String message; // 표시할 문자열

		static Menu MenuAt(int idx) { // 순서가 idx번째인 열거를 반환
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}

		Menu(String string) { // 생성자(constructor)
			message = string;
		}

		String getMessage() { // 표시할 문자열을 반환
			return message;
		}
	}

//--- 메뉴 선택 ---//
	static Menu SelectMenu(Scanner stdIn) {
		int key;
		do {
			for (Menu m : Menu.values())
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
			System.out.print(" : ");
			key = stdIn.nextInt();
		} while (key < Menu.ADD.ordinal() || key > Menu.TERMINATE.ordinal());

		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Menu menu; // 메뉴
		int result = 0, val = 0;
		final int count = 8;
		Scanner stdIn = new Scanner(System.in);
		OpenHash2 hash = new OpenHash2(13);
		do {
			switch (menu = SelectMenu(stdIn)) {
			case ADD: // 추가
				int[] input = new int[count];
				for (int ix = 0; ix < count; ix++) {
					double d = Math.random();
					input[ix] = (int) (d * 20);
					System.out.print(" " + input[ix]);
				}
				for (int i = 0; i < count; i++) {
					int k = hash.add(input[i]);
					switch (k) {
					case 1:
						System.out.printf("(%d) -> ", input[i]);
						System.out.println("그 키값은 이미 등록되어 있습니다.");
						break;
					case 2:
						System.out.println("해시 테이블이 가득 찼습니다.");
						break;
					}
				}
				break;

			case REMOVE: // 삭제
				System.out.println("Search Value:: ");
				val = stdIn.nextInt();
				result = hash.remove(val);
				if (result != 0)
					System.out.println(" 검색 데이터가 존재한다");
				else
					System.out.println(" 검색 데이터가 없음");
				System.out.println();
				break;

			case SEARCH: // 검색
				System.out.println("Search Value:: ");
				val = stdIn.nextInt();
				result = hash.search(val);
				if (result != 0)
					System.out.println(" 검색 데이터가 존재한다");
				else
					System.out.println(" 검색 데이터가 없음");
				System.out.println();
				break;

			case DUMP: // 표시
				hash.dump();
				break;
			}
		} while (menu != Menu.TERMINATE);
	}
}
