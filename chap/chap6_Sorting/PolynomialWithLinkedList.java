package chap6_Sorting;

public class PolynomialWithLinkedList {
	
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
		
		Polynomial fPoly = new Polynomial(f);
		
		Term[] g = {
		         new Term(1.5, 1),
		         new Term(2.5, 2),
		         new Term(3.3, 3),
		         new Term(4.0, 0),
		         new Term(2.2, 4),
		         new Term(3.1, 5),
		         new Term(3.8, 6),
		     };
		
		Polynomial gPoly = new Polynomial(g);

		fPoly.ShowPolynomial("다항식 x = ");
		gPoly.ShowPolynomial("다항식 y = ");
		
		fPoly.MergeSort(0, fPoly.size());
		gPoly.MergeSort(0, gPoly.size());
		
		fPoly.ShowPolynomial("정렬후 다항식 x = ");
		gPoly.ShowPolynomial("정렬후 다항식 y = ");
		
		Term[] h = new Term[20];
		
		for (int i = 0; i < h.length; i++) {
			h[i] = new Term();
		}
		
		Polynomial hPoly = new Polynomial(h);
			
	/*
		int hTerms = AddPolynomial(f,g,h);//다항식 덧셈 z = x + y
		ShowPolynomial("덧셈후 다항식 z = ", h, hTerms);

		
		hTerms = MultiplyPolynomial(f,g,h);//다항식 곱셈 z = x * y
		MergeSort(h, 0, hTerms); // 배열 x를 퀵정렬
		ShowPolynomial("곱셈후 다항식 z = ", h, hTerms);
		double result = EvaluatePolynomial(h, hTerms, 1);//다항식 값 계산 함수 z(10) 값 계산한다 
		System.out.println(" result = " + result );
		*/
	}

}
