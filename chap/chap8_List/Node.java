package chap8_List;

public class Node {
	int id;
	Node next;
	
	public Node(int id) {
		
		this.id = id;
		this.next = null;
	}
	
	@Override
	public String toString() {
		return Integer.toString(id);
	}

}
