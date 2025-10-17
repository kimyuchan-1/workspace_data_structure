package chap6_Sorting;


import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("serial")
public class Polynomial extends LinkedList<Term>{
	Term first;
	
	public Polynomial(Term[] temp) {
		
		for (int i = 0; i < temp.length; i++) {
			if (i == 0) {
				first = temp[0];
			} else {
				temp[i-1].link = temp[i];
			}
		}
	}
	
	// --- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
	public void merge(int lefta, int righta, int leftb, int rightb) {
		// body를 지우고 작성 훈련 연습이 도움이 된다
		// 구현코드
		Term slow = get(lefta);
		Term fast = get(lefta).link;
		
		while (fast != null && slow.link != null) {
			
		}
		
	}

	// --- 퀵 정렬(비재귀 버전)---//
	public void MergeSort(int left, int right) {
		
		int mid = (left+right)/2;
		
		if (left == right) {
			return;
		}
		
		MergeSort(left, mid);
		MergeSort(mid+1, right);
		merge(left, mid, mid+1, right);
		return;
	}

	public void ShowPolynomial(String msg) {
		//str 변수는 다항식 이름으로 스트링이다
		//count가 -1이면 다항식 x의 length로 계산하고 -1이면 count가 다항식 항의 숫자이다 
		//정렬후 다항식 x = 2.5x**7  + 3.8x**5  + 3.1x**4  + 1.5x**3  + 3.3x**2  + 4.0x**1  + 2.2x**0 
		Term tmp = first;
		
		System.out.println(msg);
		
		while (tmp != null) {
			
			System.out.print(tmp.toString());
			
			if (tmp.link != null) {
				System.out.print(" + ");
			} else {
				System.out.print(" ");
			}
			
			tmp = tmp.link;
			
		}
		
		System.out.println();
		
	}
	
	public int AddPolynomial(Term[]x,Term[]y,Term[]z) {
		//z = x + y, 다항식 덧셈 결과를 z로 주고 z의 항의 수 terms을 리턴한다 
		int p=0,q=0,r=0;
		int terms = 0;
		//구현코드
		return terms;
		
	}
	public int addTerm(Term[]z, Term term, int terms) {
		//다항식 z에 새로운 항 term을 추가한다. 지수가 같은 항이 있으면 계수만 합한다
		//추가된 항의 수를 count하여 terms으로 리턴한다.
		//구현코드
		return ++terms;
			
	}
	public int MultiplyPolynomial(Term[]x,Term[]y,Term[]z) {
		//z = x * y, 다항식 z의 항의 수는 terms으로 리턴한다 
		//terms = addTerm(z, term, terms);사용하여 곱셈항을 추가한다.
		int p=0,q=0,r=0;
		int terms = 0;
		//구현코드
		return terms;
	}
	public double EvaluatePolynomial(Term[]z, int zTerms, int value) {
		//zTerms는 다항식 z의 항의 수, value는 f(x)를 계산하기 위한 x 값
		//다항식 계산 결과를 double로 리턴한다 
		double result = 0.0;
		//구현 코드
		return result;
	}
	
}
