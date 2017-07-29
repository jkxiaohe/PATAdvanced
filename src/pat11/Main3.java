package pat11;

import java.util.*;

public class Main3 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int p = in.nextInt();
		int[] data = new int[N];
		for(int i=0;i<N;i++){
			data[i] = in.nextInt();
		}
		
		Arrays.sort(data);
		
		int maxNum = 0;
		for(int i=0;i<N;i++){
			while((i+maxNum)<N && data[i+maxNum]<=p*data[i]){
				maxNum++;
			}
		}
		System.out.println(maxNum);
	}
	
}
