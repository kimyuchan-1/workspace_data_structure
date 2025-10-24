package chap11_Graph;

class Sets3 {
	public Sets3(int sz) {
		n = sz;
		parent = new int[n + 1]; // Don't want to use parent[0]
		for (int i = 1; i <= n; i++)
			parent[i] = -1; // 0 for Simple versions
	}

	public void display() {

		for (int i = 1; i <= n; i++)
			System.out.print(" " + i);
		System.out.println();
		for (int i = 1; i <= n; i++)
			System.out.print(" " + parent[i]);
		System.out.println();
	}

	public void SimpleUnion(int i, int j)
	// Replace the disjoint sets with roots i and j, i != j with their union
	{
		// i,j는 임의 노드

	}

	public int SimpleFind(int i)
	// Find the root of the tree containing element i
	{

	}

	public void WeightedUnion(int i, int j) {
	// Union sets with roots i and j, i != j, using the weighting rule.
	// parent[i]=-count[i] and parent[i]=-count[i].
	}


	public int Getter() {
		return n;
	}
	int[] parent;
	private int n;
}
public class train_실습과제11_0SetUnion {
	static boolean IsCycle(Sets3 s, int i, int j) {

	}
	static int HowManySets(Sets3 s) {
		int count = 0;

	}
	public static void main(String[] args) {

		Sets3 s1 = new Sets3(13);
		s1.SimpleUnion(0, 5);s1.SimpleUnion(12, 8);
		s1.SimpleUnion(7, 1);s1.SimpleUnion(12, 10);
		s1.SimpleUnion(2, 3);
		s1.SimpleUnion(4, 5);
		s1.SimpleUnion(6, 7);
		s1.SimpleUnion(4, 2);
		s1.SimpleUnion(5, 7);
		s1.SimpleUnion(9, 11);
		s1.SimpleUnion(13, 9);
		s1.display();
		int n1 = s1.SimpleFind(5);
		int n2 = s1.SimpleFind(7);
		System.out.println("5의 parent = " + n1 + "  7의 parent = " + n2);
		
		if (IsCycle(s1, 7,9))
			System.out.println("7과 9는 같은 집합이다");
		else
			System.out.println("7과 9는 다른 집합이다");	
		System.out.println("세트의 갯수는 " + HowManySets(s1));
		s1.WeightedUnion(1, 2);
		s1.WeightedUnion(3, 4);
		s1.WeightedUnion(5, 6);
		s1.WeightedUnion(7, 8);
		s1.display();

	}
}