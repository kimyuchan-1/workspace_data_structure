package chap3_검색;

public class train_실습3_9객체comparable구현 {

}

/*
 * 문제 : Comparable 구현 
다음과 같은 정보를 저장하는 Student 클래스를 정의하고, 고정된 학생 배열을 학번(sid) 기준으로 정렬한 뒤,
탐색 대상 학생 객체 배열에 대해 Arrays.binarySearch()를 이용하여 해당 학번을 가진 학생이 배열에 존재하는지 판단하는 프로그램을 작성하시오.

✅ 구현 조건
Student 클래스는 다음과 같은 멤버 변수와 메서드를 반드시 포함해야 한다:

String sid; (학번)

String sname; (이름)

String dept; (학과)

public Student(String sid, String sname, String dept) 생성자

public int compareTo(Student other) 메서드
→ sid 기준 오름차순 비교

public String toString() 메서드
→ 출력 형식은 "S002, 철수, Physics"

Student 클래스는 Comparable<Student> 인터페이스를 구현해야 한다.

메인 메서드에서 다음 학생 배열을 정의한다 (정렬 전 상태):

Student[] students = {
    new Student("S001", "영희", "Math"),
    new Student("S003", "민수", "Computer"),
    new Student("S002", "철수", "Physics"),
    new Student("S005", "지영", "Biology"),
    new Student("S004", "준호", "Chemistry")
};

show(Student[] arr) 메서드를 작성하여 확장형 for문으로 배열 내용을 출력한다.
출력 시 "=== 정렬 전 학생 목록 ==="을 먼저 출력한다.

위 배열을 **Arrays.sort()**로 정렬한 후, 다음과 같은 탐색 대상 객체 배열을 정의하고 반복문으로 탐색한다:

Student[] targets = {
    new Student("S002", "철수", "Physics"),
    new Student("S006", "홍길동", "Law"),
    new Student("S004", "준호", "Chemistry")
};

위 배열을 확장형 for문으로 순회하면서 Arrays.binarySearch()를 통해 각 객체를 탐색하고 결과를 출력한다.
출력 형식은 다음 중 하나로 한다:

"찾은 학생: S002, 철수, Physics"

"학번 S006인 학생은 존재하지 않습니다."

=== 정렬 전 학생 목록 ===
S001, 영희, Math
S003, 민수, Computer
S002, 철수, Physics
S005, 지영, Biology
S004, 준호, Chemistry

=== 이진 탐색 결과 ===
찾은 학생: S002, 철수, Physics
학번 S006인 학생은 존재하지 않습니다.
찾은 학생: S004, 준호, Chemistry

 */
	