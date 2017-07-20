package pat7;

import java.util.Scanner;

/**
 * ASCII字符表总共有256个字符，其中128个位可见字符，即键盘上可以敲出的字符；另128个位扩展字符，不可见。
 */
public class Main9 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		char[] s = in.nextLine().toCharArray();
		char[] p = in.nextLine().toCharArray();
		
		int[] asc = new int[128];
		
		for(int i=0;i<p.length;i++){
			asc[p[i]]++;
		}
		
		for(int i=0;i<s.length;i++){
			if(asc[s[i]] == 0)
				System.out.print(s[i]);
		}
		
	}
	
}
