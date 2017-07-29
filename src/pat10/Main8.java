package pat10;

import java.util.Scanner;

/**
 * @author gljg
 * 题意解析:根据科学记数法的输出形式，判断数中几个特殊字符的下标位置，将数字的各部分进行分割，然后依据指数的大小进行移位输出即可，
 *       需要注意的是：当位数不够时，用0来填充。
 */
public class Main8 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s = in.next();
		String first = s.charAt(0)+"";
		System.out.print(first.equals("-") ? first : "");
        
	    int e = s.indexOf("E");
//	    int point = s.indexOf(".");
//		String left = s.substring(3, e);
//		String right = s.substring(point+1,e-1);
	    String right = s.substring(3, e);
		String eRight = s.substring(e+2);
		char mark = s.charAt(e+1);
		
		if(mark == '+'){
			int move = Integer.parseInt(eRight);
			if(right.length() < move){
				System.out.print(s.charAt(1) + right);
				int len = move - right.length();
				while(len-- != 0){
					System.out.print("0");
				}
				System.out.println();
			}else{
				String pleft = right.substring(3, move);
				String pright = right.substring(move);
				System.out.println(s.charAt(1) + pleft + "." + pright);
			}
		}
		else{
			String tmp = "";
			int radix = Integer.parseInt(eRight)-1;
			while(radix-- !=0){
				tmp += "0";
			}
			System.out.println("0." + tmp  + s.charAt(1) + right);
		}
		
	}
	
}
