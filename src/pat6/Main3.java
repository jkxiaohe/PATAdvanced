package pat6;

import java.util.Scanner;

public class Main3 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] d = new int[n];
		int[] data = new int[10001];
		for(int i=0;i<n;i++){
			d[i] = in.nextInt();
			data[d[i]]++;
		}
		for(int i=0;i<d.length;i++){
			if(data[d[i]] == 1){
				System.out.println(d[i]);
				return;
			}
		}
		System.out.println("None");
	}
	
}
