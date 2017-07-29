package pat10;

import java.util.Scanner;

public class Main10 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int[] data = new int[100000];
		String[] next = new String[100000];
		String[] list = new String[100000];
		String[] end = new String[100000];
		
		int n,k,i,temp;
		String first;
		first = in.next();
		n = in.nextInt();
		k = in.nextInt();
		
		for(i=0;i<n;i++){
			temp = in.nextInt();
			data[temp] = in.nextInt();
			next[temp] = in.next();
		}
		in.close();
		
		int len = 0;
		while(Integer.parseInt(first) != -1){
			list[len++] = first;
			first = next[Integer.parseInt(first)];
		}
		
		for(i=0;i<len;i++){
			end[i] = list[i];
		}
		
		for(i=0;i<(len-len%k);i++){
			end[i] = list[(i/k)*k + (k-i%k-1)];
		}
		
		for(i=0;i<len-1;i++){
			System.out.println(end[i] + " " + data[Integer.parseInt(end[i])] + " " + end[i+1]);
		}
		System.out.println(end[len-1] + " " + data[Integer.parseInt(end[len-1])] + " -1");
		
	}
	
}
