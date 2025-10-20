package chap6_Sorting;

class Term implements Comparable<Term>{
    double coef;           // 계수
    int    exp;            // 지수
    Term link;
    
    Term() {}
    
    //--- 생성자(constructor) ---//
    Term(double coef, int exp) {
        this.coef = coef;  
        this.exp = exp; 
        link = null;
    }
    
	@Override
	public String toString() {

		double doubleCoef = Math.round(coef*10000);
		return doubleCoef/10000  + "x**" + exp;

	}

	@Override
	public int compareTo(Term o) {
		if (this.exp != o.exp) {
			return o.exp - this.exp;
		} 
		return Double.compare(o.coef, this.coef);
	}

}