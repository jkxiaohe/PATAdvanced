package pat11;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 1062
 * @author gljg
 * 思路：对接受到的2个字符串进行字典序排序，然后对s2进行挨个取出，判断该字符是不是在s1中，如果在，公共字符common++，并将s1的当前位置置为#，代表
 *    当前位置已经比较过了，不会再比较了
 */
public class Main8 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		char[] s1 = in.nextLine().toCharArray();
		char[] s2 = in.nextLine().toCharArray();
		Arrays.sort(s1);  
		Arrays.sort(s2);
		
		/**
		 * common:代表2个字符串中都包含的字符；
		 * i从0开始，遍历完s2的每个字符，判断每一个是不是在s1中都有记录；
		 * j从0开始，在s1里面查找是否包含s2中的该字符，如果包含，common++,并退出查找，否则会出错，并且将当前该位置上的字符
		 *    设为一个不可能的字符，表示已经被统计过了，以后不能再统计该位置了。
		 */
		int common = 0;
		for(int i=0;i<s2.length;i++){
			for(int j=0;j<s1.length;j++){
				if(s1[j] == s2[i]){
					s1[j] = '#';
					common++;
					break;
				}
			}
		}
		
		int len = s2.length - common;
		if(len == 0){
			System.out.println("Yes " + (s1.length-common));
		}else{
			System.out.println("No " + len);
		}
		
	}
	
}
