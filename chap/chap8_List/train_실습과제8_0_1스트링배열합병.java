package chap8_List;
import java.util.ArrayList;
/*
 * 8장: 스트링 배열 합병 정렬
 */
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class train_실습과제8_0_1스트링배열합병 {
	
		static void showList(String msg, String[] strList) {
			System.out.println(msg);
			
			for (int i = 0; i < strList.length; i++) {
				System.out.print(strList[i]);
				if (i < strList.length - 1) {
					System.out.print(" + ");
				} else {
					System.out.print(" ");
				}
				
			}
			System.out.println();
			
		}
		

		static void merge(String[] strL, int lefta, int righta, int leftb, int rightb ) {
			String temp[] = new String[rightb - lefta+ 1];

			int i = lefta;
			int j = leftb;
			int k = 0;
			
			while (i <= righta && j <= rightb) {
		        if (strL[i].compareTo(strL[j]) <= 0) {
		            temp[k++] = strL[i++];
		        } else {
		            temp[k++] = strL[j++];
		        }
		    }			
			
			while (i <= righta) {
				temp[k++] = strL[i++];
			}
			
			while (j <= rightb) {
				temp[k++] = strL[j++];
			}
			
			System.arraycopy(temp, 0, strL, lefta, rightb - lefta+ 1);
			
		}

		static void MergeSort(String[] strL, int left, int right) {
			if (left >= right) return;            
		    int mid = left + (right - left) / 2;  
			MergeSort(strL, left, mid);
			MergeSort(strL, mid+1, right);
			merge(strL, left, mid, mid+1, right);
			return;
		}
		
		@SuppressWarnings("rawtypes")
		static String[] mergeList(String[] strL1, String[] strL2) {
			String[] result = new String[strL1.length + strL2.length];
			
			/*
			int j = 0;
			
			for (int i = 0; i < result.length; i++) {
				if (i < strL1.length) {
					result[i] = strL1[i];
				} else {
					result[i] = strL2[j++];
				}
			}
			*/
			List<String> list1 = new ArrayList(Arrays.asList(strL1));
			Collection<? extends String> list2 = new ArrayList(Arrays.asList(strL2));
			list1.addAll(list2);
			String[] result1 = list1.toArray(new String[0]);

			MergeSort(result1, 0, result1.length-1);
			
			return result1;
		}
	
	    public static void main(String[] args) {
		String[] s1 = {"홍길동", "강감찬", "을지문덕", "계백", "김유신", "최치원" };
		String[] s2 = {"독도", "울릉도", "한산도", "영도", "오륙도", "동백섬"};
		Arrays.sort(s1);
		Arrays.sort(s2);
		
		showList("s1배열 = ", s1);
		showList("s2배열 = ", s2);
	
		String[] s3 = mergeList(s1,s2);//정렬된 s1[], s2[]을 합병하여 다시 정렬된 결과를 만드는 함수 구현
		showList("스트링 배열 s3 = s1 + s2:: ", s3);
	}
}
