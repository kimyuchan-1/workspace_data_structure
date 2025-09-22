package chap2_기본자료구조;

/*
 * 어떤 정수를 나누어 떨어지게 만드는 수를 약수(divisor)
 * N % d == 0을 만족하는 d를 N의 약수
 * 
 * 소수(prime number)는 1과 자기 자신만을 약수로 가지는 수
 * 약수가 1과 자기 자신 두 개뿐인 자연수를 소수(즉 약수가 2개일 것)
 * 
 * 0은 모든 수로 나누어지므로 소수가 아님.
 * 1은 약수가 하나뿐이라 소수가 될 수 없음.
 * 2는 약수가 두 개(1과 자기 자신)뿐이므로 소수이며, 유일한 짝수 소수
 */
public class train_실습2_7소수나열 {

    // 0 이상 n 이하의 소수 개수를 반환하는 함수
    private static int primeCount(int n, int []data) {
        if (n < 2 || data == null) {
        	return 0; // 0과 1은 소수가 아님
        }
        
        int count = 0;
        int j = 0;
        for (int i = 2; i <= n; i++) { // 2부터 n까지 검사
            if (isPrime(i)) {
            	data[j++] = i;
                count++;
            }
        }
        return count;
    }

    // 소수 판별 함수
    /*
     * 48의 약수는 1, 2, 3, 4, 6, 8, 12, 16, 24, 48
     * i * i <= 48 조건을 만족하려면 i는 6까지 실행하면 충분, 성능 계산 - time complexity O(n): n이 성능을 좌우
     * i <= (48 % i)를 만족하는 것은 6까지이다 
     */
    private static boolean isPrime(int num) {
        if (num < 2) {
        	return false;
        }
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
    // 최대 공약수(GCD) 계산 함수 (유클리드 호제법 사용)
    /*
     * 두 수 a와 b(a > b)가 있을 때, 
     * a를 b로 나눈 나머지를 이용하여 재귀적으로 GCD를 구할 수 있음.
     * 나머지가 0이 될 때, 나누는 수가 최대 공약수(GCD).
     * 
     * 예제: GCD(56, 98) 구하기
     * 98 % 56 = 42 → GCD(56, 42)
     * 56 % 42 = 14 → GCD(42, 14)
     * 42 % 14 = 0 → GCD(14, 0), 즉 최대 공약수는 14.
     */
    private static int gcd(int a, int b) {
    	if (a == 0 || b == 0) {
    		return 0;
    	}
        while (b != 0) { // a = 56, b = 98/ a = 98, b = 56/ a = 56, b = 42/ a = 42, b = 14
            int temp = b; // temp = 98/ temp = 56/ temp = 42/ temp = 14
            b = a % b; // b = 56/ b = 42/ b = 14/ b = 0
            a = temp; // a = 98/ a = 56/ a = 42/ a = 14
        }
        return a;
    }
    // 최소 공배수(LCM) 계산 함수
    /*
     * GCD(최대 공약수)와의 관계
     * LCM(a, b) = (a × b) / GCD(a, b)
     */
    private static int lcm(int a, int b) {
    	if (a == 0 || b == 0) {
    		return 0;
    	}
    	
        return (a * b) / gcd(a, b); // LCM 공식: (a * b) / GCD(a, b)
    }
    public static void main(String[] args) {
    	for (int i = 0; i < 15; i++) {
    		System.out.println(i + " = " + (isPrime(i)?"소수":"소수가 아니다"));
    	}
    	int[] primeArr = new int[100];
        for (int i = 0; i <= 10; i++) {
        	int num = primeCount(i, primeArr);
            System.out.print("\n0부터 " + i + "까지의 소수 개수: " + num + ":: ");
            if (num > 0)
            	for (int j =0; j<num; j++)
            		System.out.print(primeArr[j] + " ");
        }
        int num1 = 56, num2 = 98;
        System.out.println("\n\n" + num1 + "와 " + num2 + "의 최대 공약수: " + gcd(num1, num2));
        
        int num3 = 12, num4 = 18;
        System.out.println("\n\n" + num3 + "과 " + num4 + "의 최소 공배수: " + lcm(num3, num4));
    }
	
}
