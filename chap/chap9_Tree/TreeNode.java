package chap9_Tree;

public class TreeNode {
	TreeNode LeftChild;
	SimpleObject data;
	TreeNode RightChild;

	public TreeNode() {
		LeftChild = RightChild = null;
	}

	TreeNode(SimpleObject so) {
		data = so;
		LeftChild = RightChild = null;
	}
}
