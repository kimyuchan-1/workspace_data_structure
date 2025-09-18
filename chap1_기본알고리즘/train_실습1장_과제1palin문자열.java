package chap1_기본알고리즘;

/*
* 문자열이 palindrome(앞뒤로 동일하게 읽히는지)인지를 판별
* Palindrome은 앞에서부터 읽으나 뒤에서부터 읽으나 같은 
단어, 문장, 숫자, 문자열 등을 뜻하는 영어 단어이며, 
한국어로는 **회문(回文)**이라고 합니다. 
'기러기', '동교동', '소주 만병만 주소' 등이 한국어 회문의 예시이며, 
영어로는 'civic', 'madam', 'radar' 등이 팰린드롬에 해당
*/
public class train_실습1장_과제1palin문자열 {

	private static boolean isPalindrome(String s) { // method의 시그니처
		// 조기 탈출 (Early stopping/exit)
		if (s == null) {
			return false;
		}
		boolean result;
		String reverse = "";
		
		for (int i = s.length()-1; i>=0; i--) {
			reverse = reverse + s.charAt(i);
		}
		//System.out.println(reverse);
		
		if (s.equals(reverse)) {
			result = true;
		} else {
			result = false;
		}
		
		return result;
		/*
		int len = s.length();
		for (int i = 0; i < len / 2; i++) {
			if (s.charAt(i) != s.charAt(len - i - 1) ) {
				return false;
			}
		}
		*/
	}
	
    public static void main(String[] args) {
    	// 테스트 케이스로 사용할 예제 문자열들
        String[] testStrings = {
            "racecar", // 팰린드롬
            "level",   // 팰린드롬
            "tattarrattat",  // 팰린드롬, 12글자
            "detartrated",    // 팰린드롬, 11글자
            "redivider" ,      // 팰린드롬, 9글자
            "hello",   // 팰린드롬 아님
            "java"     // 팰린드롬 아님
        };

        // 각 문자열에 대해 팰린드롬 여부 출력
        for (String s : testStrings) {
            System.out.println(s + " -> " + (isPalindrome(s) ? "팰린드롬" : "팰린드롬 아님"));
        }

    }
}
