package chap9_Tree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Tree {
	TreeNode root;

	Tree() {
		root = null;
	}

	TreeNode inorderSucc(TreeNode current) {
		// 주어진 노드에 대한 inorder traversal 방문시에 다음에 방문할 노드(successor)를 찾는 알고리즘
		TreeNode temp = current.RightChild;
		if (current.RightChild != null) {
			while (temp.LeftChild != null) {
				temp = temp.LeftChild;
			}
		}
		return temp;
	}

	TreeNode findParent(TreeNode current, Comparator<? super SimpleObject> c) {
		// 주어진 노드의 parent node를 찾는 알고리즘
		if (!search(current.data, c)) {
			System.out.println("해당 node는 트리에 존재하지 않습니다.");
			SimpleObject temp = new SimpleObject(null, null);
			return new TreeNode(temp);
		}
		
		TreeNode p = root;
		TreeNode q = null;
		
		while (c.compare(p.data, current.data) != 0) {
			if (c.compare(p.data, current.data) < 0) {
				q = p;
				p = p.RightChild;
			} else {
				q = p;
				p = p.LeftChild;
			}
		}
		
		return q;
	}

	boolean isLeafNode(TreeNode current) {
		// 주어진 노드가 leaf node인지 검사
		if (current.LeftChild == null && current.RightChild == null && search(current.data, SimpleObject.NO_ORDER)) {
			return true;
		}
		
		return false;
	}

	void inorder() {
		inorder(root);
	}

	void preorder() {
		preorder(root);
	}

	void postorder() {
		postorder(root);
	}

	void inorder(TreeNode CurrentNode) {
		if (CurrentNode != null) {
			inorder(CurrentNode.LeftChild);
			System.out.print(" " + CurrentNode.data);
			inorder(CurrentNode.RightChild);
		}
	}

	void preorder(TreeNode CurrentNode) {
		if (CurrentNode != null) {
			System.out.print(CurrentNode.data + " ");
			preorder(CurrentNode.LeftChild);
			preorder(CurrentNode.RightChild);
		}
	}

	void postorder(TreeNode CurrentNode) {
		if (CurrentNode != null) {
			postorder(CurrentNode.LeftChild);
			postorder(CurrentNode.RightChild);
			System.out.print(CurrentNode.data + " ");
		}
	}

	public boolean add(SimpleObject obj, Comparator<? super SimpleObject> c) {
		// inorder로 출력시에 정렬이 되도록 입력: binary search tree를 구현
		// left subtree < x < right subtree
		TreeNode p = root;
		//TreeNode q = null;
		
		TreeNode newNode = new TreeNode(obj);
		
		if (p == null) {
			root = newNode;
			return true;
		}
		
		while (p != null) {
			if (c.compare(p.data, obj) == 0) {
				return false;
			} else if (c.compare(p.data, obj) > 0) {
				if (p.LeftChild == null) {
					p.LeftChild = newNode;
					return true;
				} else {
					p = p.LeftChild;
				}
			} else {
				if (p.RightChild == null) {
					p.RightChild = newNode;
					return true;
				} else {
					p = p.RightChild;
				}
			}
		}
		
		return false;

	}

	public boolean delete(SimpleObject obj, Comparator<? super SimpleObject> c) {
		// 주어진 객체 obj를 포함한 노드를 찾아 삭제하는 알고리즘
		// 난이도: 최상급 중에서 최상급
		TreeNode p = root;
		TreeNode q = null;
		boolean isLeft = true;
		
		if (!search(obj,c)) {
			System.out.println("삭제할 node가 트리에 존재하지 않습니다.");
			return false;
		}
		
		if (p == null) {
			System.out.println("트리가 존재하지 않습니다.");
			return false;
		}
		
		while (p != null) {
			if (c.compare(p.data, obj) == 0) {
				break;
			} else {
				q = p;
				if (c.compare(p.data, obj) < 0) {
					isLeft = false;
					p = p.RightChild;
				} else {
					isLeft = true;
					p = p.LeftChild;
				}
			}
		}
		
		if (isLeafNode(p)) {
			if (p == root) {
				root = null;
			} else if (isLeft) {
				q.LeftChild = null;
			} else {
				q.RightChild = null;
			}
		} else if (p.LeftChild == null || p.RightChild == null) {
			TreeNode child = p.LeftChild != null ? p.LeftChild : p.RightChild;
			if (p == root) {
				root = child;
			} else if (isLeft) {
				q.LeftChild = child;
			} else {
				q.RightChild = child;
			}
		} else {
			TreeNode succ = inorderSucc(p);
			SimpleObject succData = succ.data;
			delete(succData, c);
			p.data = succData;
		}
		return true;
	}

	boolean search(SimpleObject obj, Comparator<? super SimpleObject> c) {
		// 주어진 객체 obj를 갖는 노드를 찾는 문제
		TreeNode p = root;
		
		if (p == null) {
			return false;
		}
		
		while (p != null) {
			if (c.compare(p.data, obj) == 0) {
				return true;
			} else if (c.compare(p.data, obj) < 0) {
				p = p.LeftChild;
			} else {
				p = p.RightChild;
			}
		}
		
		return false;
	}

	void levelOrder()
	// root 부터 level별로 출력 : root는 level 1, level 2는 다음줄에 => 같은 level이면 같은 줄에 출력하게 한다
	{
		Queue<TreeNode> q = new LinkedList<>();
		TreeNode CurrentNode = root;

	}

	void NonrecInorder()// void Tree::inorder(TreeNode *CurrentNode)와 비교
	// stack을 이용하여 inorder traversal하는 알고리즘 구현
	{
		Stack<TreeNode> s = new Stack<>();
		TreeNode CurrentNode = root;
		while (true) {
			while (CurrentNode != null) {
				s.push(CurrentNode);
				CurrentNode = CurrentNode.LeftChild;
			}
			if (!s.isEmpty()) {
				try {
					CurrentNode = s.pop();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(" " + CurrentNode.data);
				CurrentNode = CurrentNode.RightChild;
			} else
				break;
		}
	}
}
