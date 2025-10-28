package chap9_Tree;

import java.util.Scanner;

public class TreeWithObject {

	enum Menu {
		Add("삽입"), Delete("삭제"), Search("검색"), InorderPrint("정렬출력"), LevelorderPrint("레벨별출력"),
		StackInorderPrint("스택정렬출력"), PreorderPrint("prefix출력"), PostorderPrint("postfix출력"), Exit("종료");

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

	// --- 메뉴 선택 ---//
	static Menu SelectMenu(Scanner stdIn) {
		int key;
		do {
			for (Menu m : Menu.values())
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
			System.out.print(" : ");
			key = stdIn.nextInt();
		} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());

		return Menu.MenuAt(key);
	}

	@SuppressWarnings("incomplete-switch")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Tree t = new Tree();
		Menu menu; // 메뉴
		SimpleObject so;
		boolean result = false;
		do {
			switch (menu = SelectMenu(sc)) {
			case Add: //
				SimpleObject[] sox = {new SimpleObject("33", "ee"), 
									  new SimpleObject("55", "tt"),
									  new SimpleObject("22", "ww"), 
									  new SimpleObject("66", "yy"), 
									  new SimpleObject("21", "wq"),
									  new SimpleObject("44", "aa"),
									  new SimpleObject("11", "bb")};
				for (SimpleObject soz : sox) {
					result = t.add(soz, SimpleObject.NO_ORDER);
					if (result) {
						System.out.println("트리 노드 추가 성공");
					} else {
						System.out.println("트리 노드 추가 실패");
					}
				}
				break;

			case Delete: // 임의 정수 삭제
				so = new SimpleObject("44","aa");
				result = t.delete(so, SimpleObject.NO_ORDER);
				if (!result)
					System.out.println("삭제 값 = " + so + " 데이터가 없습니다.");
				else
					System.out.println("삭제 값 = " + so + " 데이터가 존재하여 삭제했습니다.");
				break;

			case Search: // 노드 검색
				so = new SimpleObject("44","aa");
				
				result = t.search(so, SimpleObject.NO_ORDER);
				if (!result)
					System.out.println("검색 값 = " + so + " 데이터가 없습니다.");
				else
					System.out.println("검색 값 = " + so + " 데이터가 존재합니다.");
				break;

			case InorderPrint: // 전체 노드를 키값의 오름차순으로 표시
				t.inorder();
				System.out.println();
				// t.NonrecInorder();
				break;
			case LevelorderPrint: // 전체 노드를 키값의 오름차순으로 표시
				t.levelOrder();
				System.out.println();
				// t.NonrecInorder();
				break;
			case StackInorderPrint: // 전체 노드를 키값의 오름차순으로 표시
				t.NonrecInorder();
				break;
			case PreorderPrint:// prefix 출력
				t.preorder();
				System.out.println();
				break;
			case PostorderPrint:// postfix로 출력
				t.postorder();
				System.out.println();
				break;
			}
		} while (menu != Menu.Exit);
	}

}
