package pat4;

import java.util.Scanner;

public class Main2 {

	private static final int[] W = {100000000,17,29};
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String[] a = in.next().split("\\.");
		String[] b = in.next().split("\\.");
		String[] result = new String[3];
		int f = 0;
		for(int i=2;i>=0;i--){
			int r = Integer.parseInt(a[i]) + Integer.parseInt(b[i]) + f;
			f = r/W[i];
			r = r%W[i];
			result[i] = String.valueOf(r);
		}
		for(int i=0;i<2;i++){
			System.out.print(result[i] + ".");
		}
		System.out.println(result[2]);
	}
	
	
}
