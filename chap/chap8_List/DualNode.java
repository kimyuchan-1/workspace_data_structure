package chap8_List;

public class DualNode {
	SimpleObject data; // 데이터
	DualNode llink; // 좌측포인터(앞쪽 노드에 대한 참조)
	DualNode rlink; // 우측포인터(뒤쪽 노드에 대한 참조)
	
	public DualNode() {}
	
	public DualNode(SimpleObject obj) {
		data = obj;
		llink = new DualNode();
		rlink = new DualNode();
	}
}
