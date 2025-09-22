package chap1_기본알고리즘;

/*
* 문자열에서 연속해서 반복되는 문자를 해당 문자와 반복 횟수로 압축요. 
* 단, 압축한 문자열이 원래 문자열보다 작아야 한다.
* 
* 입력: "aabcccccaaa"
* 압축 결과: "a2b1c5a3" (단, 길이 비교 후 결정)
*
* StringBuilder:
* 내부 버퍼를 사용하여 문자열을 직접 수정할 수 있다.
* 객체 자체를 변경하기 때문에 반복적인 문자열 조작에 효율적
*/
public class train_실습1장_과제2문자열압축 {
	
	public static String compress(String s) {
		// 조기 종료
		if (s == null || s.isEmpty()) {
			return s;
		}
		
		StringBuilder sb = new StringBuilder("");
		String result = "";
		int cnt = 0;
		for (int i = 0; i < s.length(); i++) {
			if (i == 0) {
				sb.append(s.charAt(i));
				cnt++;
			} else if (i > 0 && i < s.length()-1) {
				if (s.charAt(i) == s.charAt(i-1)) {
					cnt++;
				} else {
					sb.append(cnt);
					sb.append(s.charAt(i));
					cnt = 1;
				}
			} else {
				if (s.charAt(i) == s.charAt(i-1)) {
					sb.append(++cnt);
				} else {
					sb.append(cnt);
					sb.append(s.charAt(i));
					sb.append(1);
				}
				
			}
			
		}	
		result = sb.toString();
		return result;
	}
	
    public static void main(String[] args) {
        String input = "aabcccccbbb";
        System.out.println("압축 결과: " + compress(input));
    }
}
