package pat7;

import java.util.Scanner;

public class Main8 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s1 = in.nextLine();
		char[] s2 = in.nextLine().toCharArray();
		for(int i=0;i<s2.length;i++){
			s1 = s1.replace(s2[i]+"", "");
		}
		System.out.println(s1);
	}
	
}
