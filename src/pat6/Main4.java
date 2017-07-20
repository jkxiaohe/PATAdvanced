package pat6;

import java.util.Scanner;

public class Main4 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		in.nextLine();
		String[] cards = in.nextLine().split(" ");
		int[] res = new int[55];
		for(int i=1;i<55;i++){
			res[i] = i;
		}
		for(int i=0;i<n;i++){
		    boolean[] flag = new boolean[55];
			for(int j=1;j<55;j++){
				if(!flag[j]){
					int index = Integer.parseInt(cards[j-1]);
					int tmp = res[index];
					res[index] = res[j];
					res[j] = tmp;
					flag[j] = flag[index] = true;
				}
			}
		}
		for(int i=1;i<55;i++){
			switch(res[i]/13){
			case 0:
				System.out.print("S" + res[i] + " ");
				break;
			case 1:
				System.out.print("H" + (res[i]-13) + " ");
				break;
			case 2:
				System.out.print("C" + (res[i]-26) + " ");
				break;
			case 3:
				System.out.print("D" + (res[i]-39) + " ");
				break;
			case 4:
				System.out.print("J" + (res[i]-52) + " ");
				
			}
		}
		
		
	}
	
}
