package pat7;

import java.util.Arrays;
import java.util.Scanner;

public class Main6 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int[] a = new int[n];
		for(int i=0;i<n;++i){
			a[i] = in.nextInt();
		}
		
		Arrays.sort(a);
		boolean mark = false;
		for(int i=0,j=n-1;i<j;){
			if(a[i]+a[j] == m){
				System.out.println(a[i] + " " + a[j]);
				mark = true;
				break;
			}
			if(a[i]+a[j] < m){
			    ++i;
			}else{
				--j;
			}
		}
		if(!mark){
			System.out.println("No Solution");
		}
	}
	
}
