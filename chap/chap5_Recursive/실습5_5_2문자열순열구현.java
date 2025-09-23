package chap5_Recursive;

public class 실습5_5_2문자열순열구현 {

    // 주어진 문자 배열의 순열을 생성하는 함수
    public static void permutate(char[] arr, int index) {
        // index가 배열의 끝에 도달했을 때, 현재 순열을 출력
        if (index == arr.length - 1) {
            System.out.println(String.valueOf(arr));
            return;
        }

        // index부터 배열 끝까지 반복하며 순열 생성
        for (int i = index; i < arr.length; i++) {
            // 요소 교환
            swap(arr, index, i);
            // 다음 단계로 이동 (재귀 호출)
            permutate(arr, index + 1);
            // 원래 배열 상태로 복원 (백트래킹)
            swap(arr, index, i);
        }
    }

    // 두 요소를 교환하는 함수
    public static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        // 문자열을 문자 배열로 변환
        String l = "abcde";
        char[] arr = l.toCharArray();

        // 순열 생성 및 출력
        permutate(arr, 0);
    }
}
