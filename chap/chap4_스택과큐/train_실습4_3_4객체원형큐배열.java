package chap4_스택과큐;
/*
 * 실습 6번: 원형 큐로서 큐에 Point 객체를 배열로 저장
 * 실습4_3_2 정수 원형 큐 배열을 객체 버젼으로 구현하는 것임
 * /*
 * num 변수를 사용하지 않고 front == rear 일 때 queue가 full인지 empty 인지를 판단
 * 큐에서는 예외 클래스를 만드는 연습
 */

/*
 * 실습4_7번 
 * 원형 큐로서 큐에 Point 객체를 저장
 * class CircularQueue의 필드는 QUEUE_SIZE, que,	front, rear, isEmptyTag 변수만 사용
 */

import java.util.Random;
import java.util.Scanner;

class Point5 implements Comparable<Point5> {
	private int ix;
	private int iy;
	
	
	public Point5(int ix, int iy) {
		super();
		this.ix = ix;
		this.iy = iy;
	}


	public int getIx() {
		return ix;
	}

	public int getIy() {
		return iy;
	}

	@Override
	public String toString() {
		return "("+ix+", "+iy+")";
	}
	
	@Override
	public int compareTo(Point5 o) {
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


class CircularQueue {
	static int QUEUE_SIZE = 0;
	Point5[] que;//배열로 객체원형 큐 구현
	int front, rear;
	boolean isEmptyTag;
	//--- 실행시 예외: 큐가 비어있음 ---//
	@SuppressWarnings("serial")
	public class EmptyQueueException extends RuntimeException {
//추가
		public EmptyQueueException(String msg) {
			super(msg);
		}
	}

	//--- 실행시 예외: 큐가 가득 찼음 ---//
	@SuppressWarnings("serial")
	public class OverflowQueueException extends RuntimeException {
//추가
		public OverflowQueueException(String msg) {
			super(msg);
		}
	}
	
	public CircularQueue(int count) {
		QUEUE_SIZE = count;
		que = new Point5[QUEUE_SIZE];
		front = rear = 0;
		isEmptyTag = true;
	}
	
	void push(Point5 it) throws OverflowQueueException{
		if(isFull()) {
			throw new OverflowQueueException("Queue Overflow");
		} else {
			que[rear] = it;
			rear = (rear + 1) % QUEUE_SIZE;
			isEmptyTag = false;
		}
//추가
	}

	Point5 pop() throws EmptyQueueException{
		if(isEmpty()) {
			throw new EmptyQueueException("Queue is Empty");
		} else {
			Point5 result = que[front];
			que[front] = null;
			front = (front + 1) % QUEUE_SIZE;
			return result;
		}
//추가

	}

	 void clear() throws EmptyQueueException{
		if(isEmpty()) {
			throw new EmptyQueueException("Queue is Empty");
		} else {
			int n = size();
			for (int i = 0; i < n; i++) {
				int idx = (front + i) % QUEUE_SIZE;
				que[idx] = null;
			}
			front = rear = 0;
			isEmptyTag = true;
		}
//추가
	}

//--- 큐의 크기를 반환 ---//
	public int getCapacity() {
//추가
		return QUEUE_SIZE;
	}

//--- 큐에 쌓여 있는 데이터 개수를 반환 ---//
	public int size() {//front, rear를 사용하여 갯수를 size로 계산
//추가
		if (front == rear) {
			return isEmptyTag == true ? 0 : QUEUE_SIZE;
		} else if (front < rear) {
			return rear - front;
		} else {
			return QUEUE_SIZE - (front - rear);
		}
	}
	//--- 원형 큐가 비어있는가? --- 수정 필요//
	public boolean isEmpty() {
//추가
		if (front == rear && isEmptyTag == true) {
			return true;
		} else {
			return false;
		}
	}

//--- 원형 큐가 가득 찼는가? --- 수정 필요//
	public boolean isFull() {
//추가
		if (front == rear && isEmptyTag == false) {
			return true;
		} else {
			return false;
		}
	}

	public void dump() throws EmptyQueueException{
		if (isEmpty())
			throw new EmptyQueueException("Queue is Empty");
		else {
//추가
			int n = size();
			System.out.println("-".repeat(20));
			for (int i = 0; i < n; i++) {
				int idx = (front + i) % QUEUE_SIZE;
				System.out.println(que[idx]);
			}
			System.out.println("-".repeat(20));
		}
	}
	
	public Point5 peek() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException("Queue is Empty");
		} else {
			return que[front];
		}

//추가
	}
}

public class train_실습4_3_4객체원형큐배열 {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		CircularQueue oq = new CircularQueue(4); // 최대 4개를 인큐할 수 있는 큐
		Random random = new Random();
		int rndx = 0, rndy = 0;
		Point5 p = null;
		while (true) {
			System.out.println(" "); // 메뉴 구분을 위한 빈 행 추가
			System.out.printf("현재 데이터 개수: %d / %d\n", oq.size(), oq.getCapacity());
			System.out.print("(1)인큐　(2)디큐　(3)피크　(4)덤프　(5) clear  (0)종료: ");
			int menu = stdIn.nextInt();
			if (menu == 0)
				break;
			switch (menu) {
			case 1: // 인큐
	
				rndx = random.nextInt(20);
	
				rndy = random.nextInt(20);
				System.out.print("입력데이터: (" + rndx + ", " + rndy + ")");
				p = new Point5(rndx,rndy);
				try {
					oq.push(p);
					System.out.println("push: size() = "+ oq.size());
				} catch(CircularQueue.OverflowQueueException e) {
					System.out.println("queue이 full입니다." + e.getMessage());
					e.printStackTrace();
				}
				break;
	
			case 2: // 디큐
				try {
					p = oq.pop();
					System.out.println("pop : "+p+"size() = "+ oq.size());
				} catch (CircularQueue.EmptyQueueException e) {
					System.out.println("queue이 비어있습니다." + e.getMessage());
					e.printStackTrace();
				}
				break;
	
			case 3: // 피크
				try {
					p = oq.peek();
					System.out.println("피크한 데이터는 " + p + "입니다.");
				} catch (CircularQueue.EmptyQueueException e) {
					System.out.println("queue이 비어있습니다." + e.getMessage());
					e.printStackTrace();
				}
				break;
			case 4: // 덤프
				try {
					oq.dump();
				} catch (CircularQueue.EmptyQueueException e) {
					System.out.println("queue이 비어있습니다." + e.getMessage());
					e.printStackTrace();
				}
				break;
			case 5: //clear
				try {
					oq.clear();
				} catch (CircularQueue.EmptyQueueException e) {
					System.out.println("queue이 비어있습니다." + e.getMessage());
					e.printStackTrace();
				}
				break;
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
	

