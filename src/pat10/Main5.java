package pat10;

import java.util.Arrays;
import java.util.Scanner;

//1054
public class Main5 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String num = in.next();
		int result = 0;
		while(result != 6174){
			int left=0,right=0;
			char[] s = create(num);
			char[] l = new char[4];
			Arrays.sort(s);
			for(int i=3;i>=0;i--){
				left = left*10 + s[i]-'0';
				right = right*10 + s[3-i]-'0';
				l[i] = s[3-i];
			}
			if(left == right){
				System.out.println(new String(l) + " - " + new String(s) + " = 0000");
				return;
			}
			result = left-right;
			System.out.println(new String(l) + " - " + new String(s) + " = " + result);
			num = "" + result;
		}
		
	}
	
	private static char[] create(String s){
		char[] c = new char[4];
		for(int i=0;i<c.length;i++){
			if(i>=s.length()){
				c[i] = '0';
			}else{
				c[i] = s.charAt(i);
			}
		}
		return c;
	}
	
}
