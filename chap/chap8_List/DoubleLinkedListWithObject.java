package chap8_List;

public class DoubleLinkedListWithObject {

	public static void main(String[] args) {
		
		DoubleLinkedList lst1 = new DoubleLinkedList();
		DoubleLinkedList lst2 = new DoubleLinkedList();
		DoubleLinkedList lst3 = new DoubleLinkedList();
		DoubleLinkedList lst4 = new DoubleLinkedList();
		
		System.out.println("== 1. 리스트 추가 (lst1) ==");
		SimpleObject[] simpleObjects = new SimpleObject[10];
		makeSimpleObjects(simpleObjects);
		for (int i = 0; i < simpleObjects.length; i++) {
			System.out.println(simpleObjects[i]);
			lst1.add(simpleObjects[i], SimpleObject.NO_ORDER);
		}
		System.out.println();
		
		System.out.println("== 2. 리스트 출력 (lst1) ==");
		lst1.show();
		System.out.println();
		
		System.out.println("== 3. 리스트 검색 (lst1) ==");
		SimpleObject so = new SimpleObject();
		so.no = "s18";
		boolean result = lst1.search(so, SimpleObject.NO_ORDER);
		
		if (!result) {
			System.out.println("검색 값 = " + so.no + " 데이터가 없습니다.");
		} else {
			System.out.println("검색 값 = " + so.no + " 데이터가 존재합니다.");
		}
		System.out.println();
		
		System.out.println("== 4. 리스트 삭제 (lst1) ==");
		SimpleObject so1 = new SimpleObject();
		so1.no = "s8";
		lst1.delete(so1, SimpleObject.NO_ORDER);
		System.out.println("== 확인 ==");
		lst1.show();
		System.out.println();
		
		System.out.println("== 5. 리스트 병합 (lst1, lst2, lst3) ==");
		SimpleObject[] simpleObjects2 = new SimpleObject[10];
		makeSimpleObjects2(simpleObjects2);
		for (int i = 0; i < simpleObjects2.length; i++) {
			lst2.add(simpleObjects2[i], SimpleObject.NO_ORDER);
		}
		System.out.println("== lst2 ==");
		lst2.show();
		System.out.println();
		
		lst3 = lst1.merge_NewList(lst2, SimpleObject.NO_NAME_ORDER);
		System.out.println("== 리스트 병합 확인 ==");
		lst3.show();
		System.out.println();
		
		System.out.println("== 6. 제자리 병합 (lst1, lst2) ==");
		lst1.merge_InPlace(lst2, SimpleObject.NO_NAME_ORDER);
		
		System.out.println("== 제자리 병합 확인 ==");
		lst1.show();
		System.out.println();
		
	}

	static void makeSimpleObjects(SimpleObject[] simpleobjects) {
		simpleobjects[0] = new SimpleObject("s8", "hong", "240618");
		simpleobjects[1] = new SimpleObject("s2", "kim", "240619");
		simpleobjects[2] = new SimpleObject("s3", "lee", "240601");
		simpleobjects[3] = new SimpleObject("s1", "park", "240621");
		simpleobjects[4] = new SimpleObject("s4", "choi", "240622");
		simpleobjects[5] = new SimpleObject("s6", "jung", "240611");
		simpleobjects[6] = new SimpleObject("s7", "kang", "240624");
		simpleobjects[7] = new SimpleObject("s5", "jo", "240615");
		simpleobjects[8] = new SimpleObject("s19", "oh", "240606");
		simpleobjects[9] = new SimpleObject("s10", "jang", "240607");

	}

	static void makeSimpleObjects2(SimpleObject[] simpleobjects) {
		simpleobjects[0] = new SimpleObject("s5", "song", "240608");
		simpleobjects[1] = new SimpleObject("s2", "Lim", "240609");
		simpleobjects[2] = new SimpleObject("s3", "kee", "240601");
		simpleobjects[3] = new SimpleObject("s1", "park", "240611");
		simpleobjects[4] = new SimpleObject("s8", "choo", "240612");
		simpleobjects[5] = new SimpleObject("s9", "jong", "240618");
		simpleobjects[6] = new SimpleObject("s4", "jang", "240614");
		simpleobjects[7] = new SimpleObject("s7", "go", "240605");
		simpleobjects[8] = new SimpleObject("s11", "na", "240616");
		simpleobjects[9] = new SimpleObject("s10", "you", "240617");

	}

	static void makeSimpleObjects3(SimpleObject[] simpleobjects) {
		simpleobjects[0] = new SimpleObject("s5", "song", "240608");
		simpleobjects[1] = new SimpleObject("s2", "Lim", "240609");
		simpleobjects[2] = new SimpleObject("s3", "kee", "240601");
		simpleobjects[3] = new SimpleObject("s1", "park", "240611");
		simpleobjects[4] = new SimpleObject("s8", "choo", "240612");
		simpleobjects[5] = new SimpleObject("s9", "jong", "240618");
		simpleobjects[6] = new SimpleObject("s4", "jang", "240614");
		simpleobjects[7] = new SimpleObject("s7", "go", "240605");
		simpleobjects[8] = new SimpleObject("s11", "na", "240616");
		simpleobjects[9] = new SimpleObject("s10", "you", "240617");

	}
}
