package pat8;

import java.util.Scanner;

public class Main7 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int m = in.nextInt();
		int n = in.nextInt();
		int t = 0;
		int ans = -1;
		
		for(int i=0;i<m*n;i++){
			int temp = in.nextInt();
			if(t == 0){
				ans = temp;
			}
			if(temp == ans){
				t++;
			}else{
				t--;
			}
		}
		System.out.println(ans);
	}
	
}
