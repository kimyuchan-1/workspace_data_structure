package chap4_스택과큐;

import java.util.Random;
import java.util.Scanner;

class WorkSheet {
	private String wName;
	private int wTime;
	
	public WorkSheet(String wName, int wTime) {
		this.wName = wName;
		this.wTime = wTime;
	}

	public String getWName() {
		return wName;
	}

	public int getWTime() {
		return wTime;
	}
	public void setWTime(int time) {
		wTime = wTime - time;
		if (wTime < 0) {
			wTime = 0;
		}
	}
}

class workSpace {
	private int spaceSize;
	private int front;
	private int rear;
	private int timeSlot;
	private boolean isEmptyTag;
	private WorkSheet[] works;
	
	@SuppressWarnings("serial")
	public class EmptyQueueException extends RuntimeException {
		public EmptyQueueException(String msg) {
			super(msg);
		}
	}

	@SuppressWarnings("serial")
	public class OverflowQueueException extends RuntimeException {
		public OverflowQueueException(String msg) {
			super(msg);
		}
	}
	
	public workSpace(int workNum, int timeSlot) {
		spaceSize = workNum;
		this.timeSlot = timeSlot;
		front = rear = 0;
		works = new WorkSheet[spaceSize];
		isEmptyTag = true;
	}
	
	public void inque(WorkSheet ws) throws OverflowQueueException {
		if (isFull()) {
			throw new OverflowQueueException("Workspace is overflow");
		} else {
			works[rear] = ws;
			rear = (rear + 1) % spaceSize;
			isEmptyTag = false;
			ws.setWTime(timeSlot);
		}
	}
	
	public WorkSheet deque(WorkSheet ws) throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException("Workspace is empty");
		} else {
			WorkSheet result = works[front];
			works[front] = null;
			front = (front + 1) % spaceSize;
			return result;
		}
	}
	
	private boolean isFull() {
		return (front == rear && isEmptyTag == false);
	}
	
	private boolean isEmpty() {
		return (front == rear && isEmptyTag == true);
	}
	
	
}

public class train_실습4_5_작업대기시간_시뮬레이션 {
	/*
	문제 예시: timeslot 기반의 작업 scheduling 시스템
	문제 설명:
	어느 회사에서는 여러 작업이 동시에 들어오며, 각 작업의 처리 시간은 난수로 배정된다. 
	각 작업은 주어진 time slot 단위로 나눠서 처리되며, 처리 중인 작업은 완료되지 않았더라도 
	타임슬롯이 끝나면 큐의 마지막에 다시 대기해야 한다. 순서대로 돌아가며 타임슬롯을 할당하여 
	작업을 처리하는 이 시스템에서, 작업이 끝나면 큐에서 제거되고 완료된 시간을 출력한다, 
	그렇지 않으면 다음 타임슬롯에 다시 처리될 때까지 대기열의 끝으로 이동합니다.

	조건:
	각 작업에는 고유의 이름과 남은 작업 시간이 주어집니다.
	타임슬롯(T)의 크기가 주어집니다.
	각 작업은 타임슬롯 단위로 처리되며, 만약 작업이 완료되지 않으면 
	남은 시간이 줄어들고 큐의 끝으로 이동합니다.
	작업이 완료되면 큐에서 제거됩니다.
	모든 작업이 완료될 때까지 반복적으로 처리합니다.

	입력 형식:
	첫 번째 줄에 타임슬롯 크기 T가 주어집니다.
	두 번째 줄에는 각 작업의 이름과 남은 시간이 (작업 이름, 시간)의 형식으로 주어집니다.

	출력 형식:
	각 타임슬롯에서 처리된 작업의 이름과 남은 시간을 출력합니다.
	작업이 완료된 경우 "작업 완료"를 출력합니다.

	제약 조건:
	1 ≤ T ≤ 10
	1 ≤ 작업의 개수 ≤ 100
	각 작업의 남은 시간은 1 이상 100 이하입니다.
	*/
	
	
	
	public static void main(String args[]) {
		Random rd = new Random();
		Scanner sc = new Scanner(System.in);
		
		int workNum = rd.nextInt(5)+1;
		
		System.out.print("타임슬롯의 크기를 입력해주세요(1 ≤ T ≤ 10): ");
		int timeSlot = sc.nextInt();
		workSpace wSpace = new workSpace(workNum, timeSlot);
		System.out.println();
		
		for (int i = 0; i < workNum; i++) {
			System.out.print("작업 이름과 작업의 남은 시간(작업 이름, 시간)을 입력해주세요: ");
			String[] input = sc.nextLine().split(",");
			WorkSheet ws = new WorkSheet(input[0], Integer.parseInt(input[1]));
			wSpace.inque(ws);
			System.out.println();
		}
		
		

		
	}
	
	
}
