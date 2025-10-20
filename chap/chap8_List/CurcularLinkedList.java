package chap8_List;

public class CurcularLinkedList{
	Node head;
	
	public CurcularLinkedList() {
		head = null;
	}
	
	public void insertSorted(int id) {
		
		Node newNode = new Node(id);
		
		// 첫번째 insert
		if (head == null) {
			head = newNode;
			head.next = head;
			return;
		} 
		
		Node tail = head;
		while (tail != head) {
			tail = tail.next;
		}
		
		Node prev = tail;
		Node curr = head; 
		
		while (curr != tail) {
			// id 값 비교
			if (newNode.id < curr.id) {
				// 제일 앞에 삽입
				if (curr == head) {
					newNode.next = curr;
					head = newNode;
					tail.next = head;
					return;
				// 중간 삽입
				} else {
					newNode.next = curr;
					prev.next = newNode;
					return;
				}
			// 다음 노드로 넘어감
			} else {
				prev = curr;
				curr = curr.next;
			}
			
			if (prev != head) {
				prev.next = newNode;
				tail = newNode;
				newNode.next = head;
				return;
			}
		}		
		
	}
	
	public void showList() {
		Node temp = head;
		while (temp != null) {
			System.out.print(temp);
			if (temp.next != null) {
				System.out.print(", ");
			}
			temp = temp.next;
		}
	}
	
	public void delete() {
		
	}
	
	public void solveJosephus(int size, int n) {
		int cursor = 0;
		Node temp = head;
		
		while (temp.next != null) {
			
		}
		
	}
	
	
}
