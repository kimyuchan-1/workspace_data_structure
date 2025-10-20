package chap6_Sorting;
/*
 * 6장 구현과제3
 */

public class train_실습과제6_3_Merge정렬다항식 {

	// --- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
	static void merge(Term[] a, int lefta, int righta, int leftb, int rightb ) {
		 //body를 지우고 작성 훈련 연습이 도움이 된다 
		Term temp[] = new Term[rightb - lefta + 1];
		//구현코드
		int i = lefta;
		int j = leftb;
		int k = 0;
		
		while (i <= righta && j <= rightb) {
	        if (a[i].compareTo(a[j]) <= 0) {
	            temp[k++] = a[i++];
	        } else {
	            temp[k++] = a[j++];
	        }
	    }			
		
		while (i <= righta) {
			temp[k++] = a[i++];
		}
		
		while (j <= rightb) {
			temp[k++] = a[j++];
		}
		
		System.arraycopy(temp, 0, a, lefta, rightb - lefta+ 1);
		
	}

	// --- 퀵 정렬(비재귀 버전)---//
	static void MergeSort(Term[] a, int left, int right) {
		if (left >= right) return;            
	    int mid = left + (right - left) / 2;  
		MergeSort(a, left, mid);
		MergeSort(a, mid+1, right);
		merge(a, left, mid, mid+1, right);
		return;
	}
	static void ShowPolynomial(String str, Term[] x, int count) {
		//str 변수는 다항식 이름으로 스트링이다
		//count가 -1이면 다항식 x의 length로 계산하고 -1이면 count가 다항식 항의 숫자이다 
		//정렬후 다항식 x = 2.5x**7  + 3.8x**5  + 3.1x**4  + 1.5x**3  + 3.3x**2  + 4.0x**1  + 2.2x**0 
		System.out.print(str);
		
		int length = (count == -1) ? x.length : count;
		//구현코드
		for (int i = 0; i < length; i++) {
			if ( i > 0 && x[i].coef > 0) {
				System.out.print(" + ");
			} else if (x[i].coef < 0) {
				System.out.print(" ");
			}
			System.out.print(x[i].toString());
		}
		System.out.println();
		
	}
	static int AddPolynomial(Term[]x,Term[]y,Term[]z) {
		//z = x + y, 다항식 덧셈 결과를 z로 주고 z의 항의 수 terms을 리턴한다 
		int p=0, q=0, r=0;
		//구현코드
		while (p < x.length && q < y.length) {
			if (x[p].exp > y[q].exp) {
				z[r++] = new Term(x[p].coef, x[p].exp);
				p++;
			} else if (x[p].exp < y[q].exp) {
				z[r++] = new Term(y[q].coef, y[q].exp);
				q++;
			} else {
				double coefSum = x[p].coef + y[q].coef;
				if (coefSum != 0) {
					z[r++] = new Term(coefSum, x[p].exp);
				}
				p++;
				q++;
			}
		}
		
		while (p < x.length) {
			z[r++] = new Term(x[p].coef, x[p].exp);
			p++;
		}
		
		while (q < y.length) {
			z[r++] = new Term(y[q].coef, y[q].exp);
			q++;
		}
		
		return r;
		
	}
	static int addTerm(Term[]z, Term term, int terms) {
		//다항식 z에 새로운 항 term을 추가한다. 지수가 같은 항이 있으면 계수만 합한다
		//추가된 항의 수를 count하여 terms으로 리턴한다.
		//구현코드
		for (int i = 0; i < terms; i++) {
	        if (z[i].exp == term.exp) {
	        	
	            double newCoef = z[i].coef + term.coef;
	            
	            if (newCoef == 0) {
	                for (int j = i + 1; j < terms; j++) {
	                	z[j - 1] = z[j];
	                }
	                z[--terms] = null;
	                
	            } else {
	                z[i] = new Term(newCoef, z[i].exp);
	            }
	            return terms;
	        }
	    }
		
		z[terms] = term;
		
		return ++terms;
			
	}
	static int MultiplyPolynomial(Term[]x,Term[]y,Term[]z) {
		//z = x * y, 다항식 z의 항의 수는 terms으로 리턴한다 
		//terms = addTerm(z, term, terms);사용하여 곱셈항을 추가한다.
		
		int p=x.length,q=y.length,r=x.length*y.length;	
		
		Term[] temp = new Term[r];
		
		int terms = 0;
		
		
		//구현코드
		for (int i = 0; i < p; i++) {
			for (int j = 0; j < q; j++) {
				terms = addTerm(temp, new Term(x[i].coef * y[j].coef, x[i].exp + y[j].exp), terms);
			}
		}
		
		System.arraycopy(temp, 0, z, 0, terms);

		return terms;
	}
	static double EvaluatePolynomial(Term[]z, int zTerms, int value) {
		//zTerms는 다항식 z의 항의 수, value는 f(x)를 계산하기 위한 x 값
		//다항식 계산 결과를 double로 리턴한다 
		double result = 0.0;
		
		//구현 코드
		for (int i = 0; i < zTerms; i++) {
			if (z[i].exp == 0) {
				result += z[i].coef;
			} else {
				int zExp = 1;
				for (int j = 0; j < z[i].exp; j++) {
					zExp *= value;
				}
				result += (z[i].coef * zExp);
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		
		Term[] f = {
		         new Term(1.5, 3),
		         new Term(2.5, 7),
		         new Term(3.3, 2),
		         new Term(4.0, 1),
		         new Term(2.2, 0),
		         new Term(3.1, 4),
		         new Term(3.8, 5),
		     };
		
		Term[] g = {
		         new Term(1.5, 1),
		         new Term(2.5, 2),
		         new Term(3.3, 3),
		         new Term(4.0, 0),
		         new Term(2.2, 4),
		         new Term(3.1, 5),
		         new Term(3.8, 6),
		     };
		
		int nx = f.length;

		ShowPolynomial("다항식 x = ", f, nx);
		ShowPolynomial("다항식 y = ", g, -1);
		MergeSort(f, 0, f.length - 1); // 배열 f를 퀵정렬
		MergeSort(g, 0, g.length - 1); // 배열 g를 퀵정렬
		ShowPolynomial("정렬후 다항식 x = ", f, -1);
		ShowPolynomial("정렬후 다항식 y = ", g, -1);
		
		Term[] h = new Term[20];
		
		for (int i =0; i < h.length; i++)
			h[i] = new Term();
	
		int hTerms = AddPolynomial(f,g,h);//다항식 덧셈 z = x + y
		ShowPolynomial("덧셈후 다항식 z = ", h, hTerms);
		
		hTerms = MultiplyPolynomial(f,g,h);//다항식 곱셈 z = x * y
		MergeSort(h, 0, hTerms); // 배열 x를 퀵정렬
		ShowPolynomial("곱셈후 다항식 z = ", h, hTerms);
		double result = EvaluatePolynomial(h, hTerms, 1);//다항식 값 계산 함수 z(10) 값 계산한다 
		System.out.println(" result = " + result );
		
	}
}
