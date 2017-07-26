package pat9;

import java.util.Scanner;

import java.util.Scanner;

/**
 * 牛客秋招第3题
 * @author gljg
 */
public class Main5 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		
		int nums = (int) Math.pow(2, n);
		int count = 0;
		for(int i=0;i<nums;i++){
			boolean flag = true;
			int sum = 0;
			for(int j=1;j<=i+1;j++){
				if(n%j == 0){
					sum += Math.pow(2, j-1);
				}
			}
			if(sum == i){
				count++;
			}
			
		}
		System.out.println(count % 1000000007);
		
		
	}
	
}
