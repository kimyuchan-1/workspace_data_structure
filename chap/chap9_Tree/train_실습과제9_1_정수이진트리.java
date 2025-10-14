package chap9_Tree;
/*
 * 9장 tree
 * 1. 난수를 생성하여 binary search tree를 만든다 - insert()함수: 삽입되는 x가 root보다 작으면 left, 크면 right child로 이동
 * 2. 임의 숫자 x를 delete: x가 leaf node, one child node, two child nodes를 가질 수 있다
 * 3. stack을 이용한  non-recursive inorder 알고리즘
 * 4. queue를 사용한 level order 알고리즘의 구현
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
/*
 * stack, queue 라이브러리 사용
 * 
 */
class TreeNode5 {
	TreeNode5 LeftChild;
	int data;
	TreeNode5 RightChild;
	
	public TreeNode5() {
		LeftChild = RightChild = null;
	}
	
	public TreeNode5(int x) {
		data = x;
		LeftChild = RightChild = null;
	}
}


class Tree5 {
	TreeNode5 root;

	Tree5() {
		root = null;
	}
	/*
	 * inorderSucc()는 current 노드 다음에 방문할 노드를 찾는다
	 * inorder traversal를 이해하는 것이 필요하다 
	 * 트리에서 delete 구현시에 사용된다 
	 */
	TreeNode5 inorderSucc(TreeNode5 current) {
		TreeNode5 temp = current.RightChild;
		if (current.RightChild != null)
			while (temp.LeftChild != null)
				temp = temp.LeftChild;
		System.out.println("inordersucc:: temp.data = "+temp.data);
		return temp;
	}

	boolean isLeafNode(TreeNode5 current) {//current 가 leaf node 인지 조사 

	}

	void inorder() {//main에서 호출되는 driver function
		inorder(root);
	}

	void preorder() {//main에서 호출되는 driver function
		preorder(root);
	}

	void postorder() {//main에서 호출되는 driver function
		postorder(root);
	}
	/*
	 * 주어진 노드 x, left child를 Tl, right child를 Tr이라 할 때
	 * inorder: Tl - x - Tr 순서로 방문
	 * preorder: x - Tl - Tr 순서로 방문
	 * postorder: Tl - Tr - x 순서로 방문
	 */
	void inorder(TreeNode5 CurrentNode) {//inorder traversal을 구현하는 recursive function - workhorse라고 부름
		if (CurrentNode != null) {
			inorder(CurrentNode.LeftChild);
			System.out.print(" " + CurrentNode.data);
			inorder(CurrentNode.RightChild);
		}
	}

	void preorder(TreeNode5 CurrentNode) {//preorder traversal을 구현하는 recursive function - workhorse라고 부름
		if (CurrentNode != null) {
			System.out.print(CurrentNode.data + " ");
			preorder(CurrentNode.LeftChild);
			preorder(CurrentNode.RightChild);
		}
	}

	void postorder(TreeNode5 CurrentNode) {//postorder traversal을 구현하는 recursive function - workhorse라고 부름
		if (CurrentNode != null) {
			postorder(CurrentNode.LeftChild);
			postorder(CurrentNode.RightChild);
			System.out.print(CurrentNode.data + " ");
		}
	}

	void NonrecInorder()//void Tree5::inorder(TreeNode5 *CurrentNode)와 비교
	//stack을 사용한 inorder 출력
	//non-recursive 코드를 이해하는 학습 필요
	{
		ObjectStack5 s = new ObjectStack5(20);
		TreeNode5 CurrentNode = root;
		while (true) {
			while (CurrentNode != null) {
				s.push(CurrentNode);
				CurrentNode = CurrentNode.LeftChild;
			}
			if (!s.isEmpty()) {
				try {
					CurrentNode = s.pop();
				} catch (Chap9_Tree.ObjectStack5.EmptyGenericStackException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(" " + CurrentNode.data);
				CurrentNode = CurrentNode.RightChild;
			}
			else break;  
		}
	}
	void levelOrder() //level 별로 출력한다. level이 증가하면 다음줄에 출력한다 >> 선택 사항 
	//난이도: 최상급 구현 
	// queue를 사용한 구현
	{
		ObjectQueue5 q = new ObjectQueue5(20);
		Queue<Integer> que = new LinkedList<>();
		
	}

	boolean insert(int x) {// binary search tree를 만드는 입력 : left subtree < 노드 x < right subtree
		//inorder traversal시에 정렬된 결과가 나와야 한다
		
		TreeNode5 newNode = new TreeNode5(x);
		TreeNode5 p = root;
		TreeNode5 q = null;
		boolean tag = false;
		
		if (p == null) {
			root = newNode;
			return true;
		} 
		while (p != null) {
			if (p.data > x) {
				q = p;
				p = p.LeftChild;
				tag = true;
			} else {
				q = p;
				p = p.RightChild;
				tag = false;
			}
			
		}
		if (tag) {
			q.LeftChild = newNode;
		} else {
			q.RightChild = newNode;
		}
		return true;
		
	}

	boolean delete(int num) {//binary search tree에서 임의 값을 갖는 노드를 찾아 삭제한다.
		//난이도 중상
		//삭제 대상이 leaf node인 경우, non-leaf node로 구분하여 구현한다 
		TreeNode5 p = root, q = null, parent = null;
		int branchMode = 0; // 1은 left, 2는 right
		
		return false;

	}

	boolean search(int num) {//num 값을 binary search tree에서 검색
		TreeNode5 p = root;
		
	}
}

public class train_실습과제9_1_정수이진트리 {
	enum Menu {
		Add("삽입"), Delete("삭제"), Search("검색"), InorderPrint("정렬출력"), 
		LevelorderPrint("레벨별출력"), StackInorderPrint("스택정렬출력"), 
		PreorderPrint("prefix출력"), PostorderPrint("postfix출력"), Exit("종료");

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

	public static void main(String[] args) {
		Random rand = new Random();
		Scanner stdIn = new Scanner(System.in);
		Tree5 t = new Tree5();
		Menu menu; // 메뉴
		int count = 7;
		int num;
		boolean result;
		do {
			switch (menu = SelectMenu(stdIn)) {
			case Add: // 
				int[] input = new int[count];
				for (int ix = 0; ix < count; ix++) {
					input[ix] = rand.nextInt(50);
				}
				for (int n: input)
					System.out.print(n + " ");
				for (int i = 0; i < count; i++) {
					if (!t.insert(input[i]))
						System.out.println("Insert Duplicated data");
				}
				break;

			case Delete: //임의 정수 삭제
				System.out.println("삭제할 데이터:: ");
				num = stdIn.nextInt();
				if (t.delete(num))
					System.out.println("삭제 데이터 = " + num + " 성공");
				else
					System.out.println("삭제 실패");
				;
				break;

			case Search: // 노드 검색
				System.out.println("검색할 데이터:: ");

				num = stdIn.nextInt();
				result = t.search(num);
				if (result)
					System.out.println(" 데이터 = " + num + "존재합니다.");
				else
					System.out.println("해당 데이터가 없습니다.");
				break;

			case InorderPrint: // 전체 노드를 키값의 오름차순으로 표시
				t.inorder();
				System.out.println();
				//t.NonrecInorder();
				break;
			case LevelorderPrint: // 
				t.levelOrder();
				System.out.println();
				//t.NonrecInorder();
				break;
			case StackInorderPrint: // 전체 노드를 키값의 오름차순으로 표시
				t.NonrecInorder();
				break;
			case PreorderPrint://prefix 출력
				t.preorder();
				System.out.println();
				break;
			case PostorderPrint://postfix로 출력
				t.postorder();
				System.out.println();
				break;
			}
		} while (menu != Menu.Exit);
		stdIn.close();
	}
}
