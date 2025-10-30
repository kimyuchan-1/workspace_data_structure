package Lv0;

public class solve181888 {
	
	private static int[] solve(int[] numList, int n) {
		int[] tempList = new int[numList.length % n == 0 ? numList.length/n : numList.length/n + 1];
		int idx = 0;
		
		for (int i = 0; i < numList.length; i = i+n) {
            tempList[idx] = numList[i];
			idx++;
        }
		
		return tempList;
	}

	public static void main(String[] args) {
		int[] numList = {4, 2, 6, 1, 7, 6};
		int[] result = solve(numList, 2);
		for (int i : result) {
			System.out.print(i+" ");
		}
		
	}

}
