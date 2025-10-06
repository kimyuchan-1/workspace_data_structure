package chap4_스택과큐;
/*
 * 실습 4_6번 객체 원형 큐를 배열로 구현
 * 교재 148 실습 4_3은 정수 원형 큐를 배열로 구현한 코드임 > 객체 버젼으로 구현
 */

import java.util.Random;
import java.util.Scanner;

/*
* Queue of ArrayList of Point
*/
class Point3 implements Comparable<Point3>{
	private int ix;
	private int iy;
	
	

	public int getIx() {
		return ix;
	}

	public int getIy() {
		return iy;
	}

	public Point3(int ix, int iy) {
		super();
		this.ix = ix;
		this.iy = iy;
	}
	
	@Override
	public String toString() {
		return "x : "+ix+", y : "+iy;
	}

	@Override
	public int compareTo(Point3 o) {
		if (((Integer)this.getIx()).compareTo(o.getIx()) < 0) {
			return -1;
		} else if (((Integer)this.getIx()).compareTo(o.getIx()) == 0 ) {
			if (((Integer)this.getIy()).compareTo(o.getIy()) < 0) {
				return -1;
			} else if (((Integer)this.getIy()).compareTo(o.getIy()) == 0) {
				return 0;
			} else {
				return 1;
			}
		} else {
			return 1;
		}
	}
	
}

//int형 고정 길이 큐
class objectQueue2 {
  private Point3[] que;
	private int capacity; // 큐의 크기
	private int front; // 맨 처음 요소 커서
	private int rear; // 맨 끝 요소 커서
	private boolean isEmptyTag;


//--- 실행시 예외: 큐가 비어있음 ---//
	@SuppressWarnings("serial")
	public class EmptyQueueException extends RuntimeException {
		public EmptyQueueException(String msg) {
			super(msg);
		}
	}

//--- 실행시 예외: 큐가 가득 찼음 ---//
	@SuppressWarnings("serial")
	public class OverflowQueueException extends RuntimeException {
		public OverflowQueueException(String msg) {
			super(msg);
		}
	}

//--- 생성자(constructor) ---//
	public objectQueue2(int maxlen) {
		capacity = maxlen;
		que = new Point3[capacity];
		front = rear = 0;
		isEmptyTag = true;
	}

//--- 큐에 데이터를 인큐 ---//
	public int enque(Point3 x) throws OverflowQueueException {
		if (front == rear && isEmptyTag == false) {
			throw new OverflowQueueException("Queue Overflow");
		} else {
			que[rear] = x;
			rear = (rear + 1) % capacity;
			isEmptyTag = false;
			return 1;
		}
		
	}

//--- 큐에서 데이터를 디큐 ---//
	public Point3 deque() throws EmptyQueueException {
		if (front == rear && isEmptyTag == true) {
			throw new EmptyQueueException("Queue is Empty");
		} else {
			Point3 result = que[front];
			que[front] = null;
			front = (front + 1) % capacity;
			if (front == rear) {
				isEmptyTag = true;
			}
			return result;
		}
	}

//--- 큐에서 데이터를 피크(프런트 데이터를 들여다봄) ---//
	public Point3 peek() throws EmptyQueueException {
		if (front == rear && isEmptyTag == true) {
			throw new EmptyQueueException("Queue is Empty");
		} else {
			Point3 result = que[front];
			return result;
		}
	}

//--- 큐를 비움 ---//
	public void clear() throws EmptyQueueException {
		if (front == rear && isEmptyTag == true) {
			throw new EmptyQueueException("Queue is Empty");
		} else {
			int n = size();
			for (int i = 0; i < n ; i++) {
				int idx = (front + i ) % capacity;
				que[idx] = null;
			}
			front = rear = 0;
			isEmptyTag = true;
		}
		
	}
	

//--- 큐에서 x를 검색하여 인덱스(찾지 못하면 –1)를 반환 ---//
	public int indexOf(Point3 x) {
		int n = size();
		for (int i = 0; i < n; i++) {
			int idx = (front + i) % capacity;
			if (que[idx].compareTo(x) == 0) {
				return idx;
			}
		}
		return -1;
	}

	//--- 큐의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;
	}

//--- 큐에 쌓여 있는 데이터 개수를 반환 ---//
	public int size() {
		if (front == rear) {
			return isEmptyTag == true ? 0 : capacity;
		} else if (front < rear) {
			return rear-front;
		} else {
			return capacity - (front-rear);
		}
	}

//--- 큐가 비어있는가? ---//
	public boolean isEmpty() {
		if (front == rear && isEmptyTag == true) {
			return true;
		} else {
			return false;
		}
	}

//--- 큐가 가득 찼는가? ---//
	public boolean isFull() {
		if (front == rear && isEmptyTag == false) {
			return true;
		} else {
			return false;
		}
	}

//--- 큐 안의 모든 데이터를 프런트 → 리어 순으로 출력 ---//
	public void dump() {
		int n = size();
		System.out.println("-".repeat(20));
		for (int i = 0; i < n; i++) {
			int idx = (front + i) % capacity;
			System.out.println(que[idx].toString());
		}
		System.out.println("-".repeat(20));
	}
}
public class train_실습4_3_3객체선형큐_배열 {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		objectQueue2 oq = new objectQueue2(4); // 최대 64개를 인큐할 수 있는 큐
		Random random = new Random();
		int rndx = 0, rndy = 0;
		Point3 p = null;
		while (true) {
			System.out.println(" "); // 메뉴 구분을 위한 빈 행 추가
			System.out.printf("현재 데이터 개수: %d / %d\n", oq.size(), oq.getCapacity());
			System.out.print("(1)인큐　(2)디큐　(3)피크　(4)덤프　(5)클리어 (0)종료: ");
			int menu = stdIn.nextInt();
			switch (menu) {
			case 1: // 인큐

				rndx = random.nextInt(20);

				rndy = random.nextInt(20);
				System.out.print("입력데이터: (" + rndx + ", " + rndy + ")");
				p = new Point3(rndx,rndy);
				try {
					oq.enque(p);
				} catch(objectQueue2.OverflowQueueException e) {
					System.out.println("stack이 가득찼있습니다.");
				}
				break;

			case 2: // 디큐
				try {
					p = oq.deque();
					System.out.println("디큐한 데이터는 " + p + "입니다.");
				} catch (objectQueue2.EmptyQueueException e) {
					System.out.println("큐가 비어 있습니다.");
				}
				break;

			case 3: // 피크
				try {
					p = oq.peek();
					System.out.println("피크한 데이터는 " + p + "입니다.");
				} catch (objectQueue2.EmptyQueueException e) {
					System.out.println("큐가 비어 있습니다.");
				}
				break;

			case 4: {// 덤프
				oq.dump();
				break;
			}	
			case 5: {
				try {
					oq.clear();
					System.out.println("큐를 비웠습니다.");
				} catch (IntQueue3.EmptyIntQueue3Exception e) {
					System.out.println("이미 비어 있습니다.");
				}
				break;
			}
			case 0: {
				System.out.println("종료합니다.");
				stdIn.close();
				return;
			}

			default:
				break;
			}
		}
	}
}