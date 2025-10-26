package chap8_List;

public class CircularLinkedList{
	Node head;
	
	public CircularLinkedList() {
		head = null;
	}
	
	public void insertSorted(int id) {
		
		Node newNode = new Node(id);
		
		if (head == null) {
			head = newNode;
			head.next = head;
			return;
		} 
		
		Node prev = null;
		Node curr = head; 
		
		while (curr.next != head && curr.id < id) {
			prev = curr;
			curr = curr.next;
		}
		
		if (curr == head && id < curr.id) {
			Node tail = head;
			while (tail.next != head) {
				tail = tail.next;
			}
			tail.next = newNode;
			newNode.next = head;
			head = newNode;
		} else if (curr.next == head && curr.id < id) {
			curr.next = newNode;
			newNode.next = head;
		} else {
			newNode.next = curr;
			if (prev != null) {
				prev.next = newNode;
			}
		}
	}
	
	public void showList() {
		if (head == null) {
			System.out.println("Empty");
			return;
		}
		
		Node temp = head;
		
		
		while (true) {
	        System.out.print(temp);
	        if (temp.next == head) {
	        		break;
	        }
	        System.out.print(", ");
	        temp = temp.next;
	    }
		System.out.println();
	}
	
	public int size() {		
		if (head == null) {
			return 0;
		}
		Node cnt = head.next;
		int n = 1;
		
		while (cnt != head) {
			n++;
			cnt = cnt.next;
		}
		return n;
	}
	
	public int solveJosephus(int k) {
		if (head == null || k <= 0) {
			throw new IllegalArgumentException();
		}
		
		if (head.next == head) {
			return head.id;
		}
		
		Node curr = head;
		Node prev = head;
		
		while (prev.next != head) {
			prev = prev.next;
		}
		
		while (size() > 1) {
			for (int i = 1; i < k; i++) {
				prev = curr;
				curr = curr.next;
			}
			System.out.print(curr.id);
			if (size() > 2) {
				System.out.print(", ");
			}
			
			prev.next = curr.next;
			
			if (curr == head) {
				head = curr.next;
			}
			curr = prev.next;
		}
		System.out.println();
		
		head = curr;
		head.next = head;
		return curr.id;
		
	}
	
	
}
