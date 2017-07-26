package pat9;

import java.util.Scanner;

/**
 * 牛客秋招模拟编程题1
 * @author dell
 *
 */
public class Main4 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s = in.next();
		int half = s.length()/2-1;
		
		while(true){
			String s1 = s.substring(0, half);
			String s2 = s.substring(half,half*2);
			if(s1.equals(s2)){
				System.out.println(half*2);
				return;
			}else{
				half--;
			}
		}
		
		
		
	}
	
}
