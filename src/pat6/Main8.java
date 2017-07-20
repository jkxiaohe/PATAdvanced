package pat6;

import java.util.Scanner;

/**
 * 采用二分法的思想
 * sum数组中的每一个值都记录的是前面所有数字的和
 */
public class Main8 {

	static int index;
	static int[] sum;
	static int pay;
	static int N;
	
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		pay = in.nextInt();
		
		index = 0;
		sum = new int[N+1];
		//接收用户输入的所有数字，并将这些数字累加求和，即后一个数字是前面所有数字相加之和，
		//题意是求解>=sum的一串数字，将这些每串数字的和累加到后一个去，可以通过减去前面的数字从而求得中间某些连续数字的和
		for(int i=1;i<=N;i++){
			sum[i] = in.nextInt();
			sum[i] += sum[i-1];
		}
		
		int minSum = 0;
		boolean equal = false;
		String res = null;
		for(int i=1;i<=N;i++){
			int temp = find(i);
			if(temp == pay){
				System.out.println(i+"-"+index);
				equal = true;
			}
			if(!equal && minSum > temp){
				minSum = temp;
				res = new String(i+"-"+index);
			}
		}
		if(!equal)
			System.out.println(res);
		
	}
	
	static int find(int i){
		int left = i;
		int right = N;
		while(left < right){
			int mid = (left+right) / 2;
			if(sum[mid] - sum[i-1] >= pay){
				right = mid;
			}else{
				left = mid + 1;
			}
			index = right;
		}
		return sum[right] - sum[i-1];
	}
	
}
