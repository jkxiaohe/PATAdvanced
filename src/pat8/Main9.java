package pat8;

import java.util.Scanner;

/**
 * @author gljg
 * 题意解析：将用户输入的一串字符串，按序输出一个U行的字符串，该U行的字符串要尽可能的接近正方形
 *       并且该U型图标分为了3个部分，U的左侧为n1,U的右侧为n3,U的底部为n2,这3者之间是没有重叠关系的
 *   思路：将字符串的长度/3,该值即为左侧(n1)和右侧(n3)的长度，字符串剩下的中间的内容全部都分给n2,
 *       这样子即使字符串序列有多余的字符，也不会超过2个，左右不会对称，将多的一个放入到底部即可
 *       然后先将n1和n3输出，最后一行输出n2即可
 *       但是当n时3的整数倍时，此时情况有所变化，如果继续按照上述方法，将会在侧边上更加接近正方形，而题目的要求是在底边上更加接近正方形
 *       所以，当N为3的整数倍时，seg--
 *
 */
public class Main9 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s = in.next();
		int len = s.length();
		int seg = len/3;
		if(len%3 == 0)
			seg--;
		String s1 = s.substring(0, seg);
		String s2 = s.substring(seg,len - seg);
		String s3 = s.substring(len - seg);
		
		int sl = s2.length()-2;
		String gap = "";
		boolean flag = len%3 != 0 ? true : false; 
		while(sl-- != 0)
			gap += " ";
		for(int i=0;i<seg;i++){
			System.out.println(s1.charAt(i) + gap + s3.charAt(seg-i-1));
			
		}
		System.out.println(s2);
	}
	
}
