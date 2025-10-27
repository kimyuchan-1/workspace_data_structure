package chap6_Sorting;

//heap의 full, empty에 대한 예외처리 구현 권장 
import java.util.Random;

interface MaXHeap {
	public void insert(int x);

	public int deleteMax();
}

class Heap implements MaXHeap {
	final int maxSize = 100;
	private int[] heap;
	private int n; // MaxHeap의 현재 입력된 element 개수
	private int heapSize; // Maximum allowable size of MaxHeap

	public Heap(int sz) {
		if (sz > maxSize) {
			throw new IllegalArgumentException();
		}
		heapSize = sz;
		n = 0;
		heap = new int[heapSize];
	}

	public void display() {// heap 배열을 출력한다. 배열 인덱스와 heap[]의 값을 출력한다.
		int i;
		
		System.out.println("힙 배열 상태: ");
		for (i = 1; i <=n; i++) {
			System.out.println("["+i+"]: "+heap[i]);
		}
		System.out.println();
	}

	@Override
	public void insert(int x) {// max heap이 되도록 insert한다. 삽입후 complete binary tree가 유지되어야 한다.
		int i;
		
		if (n == heapSize) {
			heapFull();
			return;
		}
		n++;
		
		for (i = n; i > 1;) {
			int parent = i / 2;
			if (x <= heap[parent]) {
				break;
			} else {
				heap[i] = heap[parent];
				i = parent;
			}
		}
		
		heap[i] = x;
	}

	@Override
	public int deleteMax() {// heap에서 가장 큰 값을 삭제하여 리턴한다.
		int x;
		int i,j;
		if (n == 0) {
			heapEmpty();
			int elm = 0;
			return elm;
		}
		x = heap[1];
		int temp = heap[n];
		n--;
		
		// 마지막 원소를 적절한 위치로 내려보냄
		i = 1;
		j = 2;
		
		while(j <= n) {
			// 오른쪽이 존재하고 더 크면 오른쪽 선택
			if (j < n && heap[j] < heap[j+1]) {
				j++;
			}
			// 마지막 원소가 지식보다 크거나 같으면 종료
			if (temp >= heap[j]) {
				break;
			}
			// 자식을 위로 올림
			heap[i] = heap[j];
			i = j;
			j = 2 * i;
		}
		heap[i] = temp;

		return x;
	}

	private void heapEmpty() {
		System.out.println("Heap Empty");
	}

	private void heapFull() {
		System.out.println("Heap Full");
	}
}

public class train_실습과제6_4_heap_정렬 {
	static void showData(int[] d) {
		for (int i = 1; i < d.length; i++) {
			System.out.print(d[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Random rnd = new Random(42);

		Heap heap = new Heap(20);
		final int count = 10;// 난수 생성 갯수
		int[] x = new int[count + 1];// x[0]은 사용하지 않으므로 11개 정수 배열을 생성한다
		int[] sorted = new int[count];// heap을 사용하여 정렬

		System.out.println("== 1단계: 난수 생성 및 힙 삽입 ==");
		for (int i = 1; i <= count; i++) {
			x[i] = rnd.nextInt(100);
		}
		showData(x);

		for (int i = 1; i <= count; i++) {
			heap.insert(x[i]);
			System.out.println(x[i]+ "삽입");
		}
		System.out.println("삽입 완료\n");
		
		System.out.println("== 2단계: 힙 상태 출력 ==");
		heap.display();

		System.out.println("== 3단계: 힙 정렬 ==");
		for (int i = 0; i < count; i++) {
			sorted[i] = heap.deleteMax();
			System.out.println("삭제된 최대값: "+sorted[i]);
		}
		System.out.println("정렬 완료\n");
		
		System.out.println("== 4단계: 확인 ==");
		for (int i = 0; i < count; i++) {
			System.out.println("["+i+"]: "+sorted[i]+" ");
		}
		System.out.println();
		
		
		System.out.println("== 종료 ==");

	}
}
