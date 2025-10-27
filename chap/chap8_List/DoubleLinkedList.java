package chap8_List;

import java.util.Comparator;

public class DoubleLinkedList {
	private DualNode first; // 머리 포인터(참조하는 곳은 더미노드)

	// --- 생성자(constructor) ---//
	public DoubleLinkedList() {
		first = new DualNode(); // dummy(first) 노드를 생성
		first.rlink = first;
		first.llink = first;
	}

	// --- 리스트가 비어있는가? ---//
	public boolean isEmpty() {
		return first.rlink == first;
	}

	// --- 노드를 검색 ---//
	public boolean search(SimpleObject obj, Comparator<? super SimpleObject> c) {
		if (isEmpty()) {
			System.out.println("Empty");
			return false;
		}
		
		DualNode temp = first.rlink;
		
		while (temp != first) {
			if (c.compare(temp.data, obj) == 0) {
				return true;
			}
			temp = temp.rlink;
		}
		
		return false;

	}

	// --- 전체 노드 표시 ---//
	public void show() {
		if (isEmpty()) {
			System.out.println("Empty");
			return;
		}

		DualNode temp = first.rlink;

		while (temp != first) {
			System.out.print(temp.data + " -> ");
			temp = temp.rlink;
		}
		System.out.println("처음으로");

	}

	// --- 올림차순으로 정렬이 되도록 insert ---//
	public void add(SimpleObject obj, Comparator<? super SimpleObject> c) {
		DualNode newNode = new DualNode(obj);

		DualNode curr = first.rlink;
		DualNode prev = first;

		while (curr != first && c.compare(curr.data, newNode.data) < 0) {

			prev = curr;
			curr = curr.rlink;
		}

		newNode.rlink = curr;
		curr.llink = newNode;
		prev.rlink = newNode;
		newNode.llink = prev;


	}

	// --- list에 삭제할 데이터가 있으면 해당 노드를 삭제 ---//
	public void delete(SimpleObject obj, Comparator<? super SimpleObject> c) {
		if (isEmpty()) {
			System.out.println("Empty");
			return;
		}
		
		if (!search(obj,c)) {
			System.out.println("삭제할 항목이 존재하지 않습니다.");
			return;
		}
		
		DualNode curr = first.rlink;
		DualNode prev = first;
		
		while (curr != first) {
			if (c.compare(curr.data, obj) == 0) {
				DualNode temp = curr.rlink;
				prev.rlink = temp;
				temp.llink = prev;
				System.out.println(curr.data + " 삭제 완료");
				return;
			}
			prev = curr;
			curr = curr.rlink;
		}
		
	}

	public DoubleLinkedList merge_NewList(DoubleLinkedList lst, Comparator<SimpleObject> cc) {
		// l3 = l1.merge(l2); 실행하도록 리턴 값이 리스트임
		// l.add(객체)를 사용하여 구현
		// 기존 리스트의 노드를 변경하지 않고 새로운 리스트의 노드들을 생성하여 구현
		
		DoubleLinkedList newList = new DoubleLinkedList();
		
		if (this.first == null) {
			return lst;
		}
		
		if (lst.first == null) {
			return this;
		}
		
		if (this.first == null && lst.first == null) {
			return newList;
		}
		
		DualNode ac = this.first.rlink;
		DualNode bc = lst.first.rlink;
		
		while (ac != this.first) {
			newList.add(ac.data, cc);
			ac = ac.rlink;
		}
		
		while (bc != lst.first) {
			newList.add(bc.data, cc);
			bc = bc.rlink;
		}
		
		return newList;

	}

	void merge_InPlace(DoubleLinkedList b, Comparator<SimpleObject> cc) {
		/*
		 * 연결리스트 a,b에 대하여 a = a + b merge하는 알고리즘 구현으로 in-place 방식으로 합병/이것은 새로운 노드를 만들지
		 * 않고 합병하는 알고리즘 구현 난이도 등급: 최상급 회원번호에 대하여 a = (3, 5, 7), b = (2,4,8,9)이면 a =
		 * (2,3,4,5,8,9)가 되도록 구현하는 코드
		 */
		
		if (this.first == null) {
			this.first = b.first;
			return;
		}
		
		if (b.first == null) {
			return;
		}
		
		if (this.first == null || b.first == null) {
			System.out.println("두 연결리스트가 비었습니다.");
			return;
		}
		
		DualNode p = this.first.rlink, q = b.first.rlink;
		DualNode temp = null;
		
		while (p != this.first && q != b.first) {
			if (p.data == null || q.data == null) {
				break;
			}
			
			if (cc.compare(p.data, q.data)<=0) {
				temp = p.rlink;
				p.rlink = q;
				q.llink = p;
				p = temp;
			}
			if (cc.compare(q.data, p.data) < 0) {
				temp = q.rlink;
				q.rlink = p;
				p.llink = q;
				q = temp;
			}
		}
		
		if (q != b.first) {
			p.rlink = q;
			q.llink = p;
			
			while (q != b.first) {
				q = q.rlink;
			}
			
			q.rlink = this.first;
			this.first.llink = q;
			
		}
		
		

	}
}
