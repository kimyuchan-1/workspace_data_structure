package Lv0;

public class solve181939 {
	
	private static int solve(int a, int b) {
		if (a < 1 || a >= 10000 || b < 1 || b >= 10000) {
            return -1;
        }
        int tempA = Integer.parseInt((String.valueOf(a))+((String.valueOf(b))));
        int tempB = Integer.parseInt((String.valueOf(b))+((String.valueOf(a))));
        
        if (tempA >= tempB) {
            return tempA;
        } else {
            return tempB;
        }
	}

	public static void main(String[] args) {
		System.out.println(solve(9, 91));

	}

}
