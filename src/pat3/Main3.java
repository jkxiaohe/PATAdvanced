package pat3;

import java.util.Scanner;

//求n个字符串最长的公共后缀字符串长度
public class Main3 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		in.nextLine();
		String[] sentence = new String[n];
		for(int i=0;i<n;i++)
			sentence[i] = in.nextLine();
		
		//template和l用来存储第一个字符串的内容和长度
		//tmp和length用来存储后面每个字符串的内容和长度
		//m用来记录后缀的长度，当后缀的长度大于某个字符串的长度时，便不再比较
		int m = 0;
		String tmp;   
		String template = sentence[0];
		int l = template.length();
		boolean same = true;  //判断所有字符串指定位置上的后缀字符是否相同
		while(same && m++<l){  //m从后缀的第一个字符开始逐渐增大
			for(int i=1;i<n;i++){   //对n个字符串都进行判断
				tmp = sentence[i];  
				int length = tmp.length();
				//如果m大于任一个字符串的总长度，或者有某一个字符串的后缀和其它的字符串不同，都将same置为false，不会再比较后缀字符了
				if(m>tmp.length() || tmp.charAt(length-m) != template.charAt(l-m)){
					same = false;
					break;
				}
			}
		}
		if(m == 1)
			System.out.println("nai");
		else
			System.out.println(template.substring(l-m+1));
	}
}
