package chap6_Sorting;

public class IMS {
	// 분할 정복 알고리즘을 사용한 반복문
	// 순열[a]
	// 문자 선택
	// 문자 비교
	// 배치
	
	public static Term[] iMergeSort(Term[] terms) {
		if (terms == null || terms.length == 1) {
			return terms;
		}
		
		int n = terms.length;
		Term[] temp = new Term[n];
		
		for ( int size = 1; size < n; size *= 2) {
			for (int left = 0; left < 2; left += 2 * size) {
				int mid = Math.min(left + size - 1, n - 1);
				int right = Math.min(left + 2 * size - 1, n - 1);
				merge(terms,temp,left,mid,right);
			}
		}
		
		return terms;
	}
	
	private static void merge(Term[] terms, Term[] temp, int left, int mid, int right) {
		int i = left;
		int j = mid + 1;
		int k = left;
		
		while (i <= mid && j <= right) {
			
		}
	}
}
