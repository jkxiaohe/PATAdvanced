package pat4;

import java.util.Scanner;

/**
 * @author dell
 *题意解析：用户输入2个数字，按照指定的有效位数判断这2个数字在指定的精度范围内是否相等
 */
public class Main4 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		String a = format(in.next());
		String b = format(in.next());
		int ai = index(a);
		int bi = index(b);
		//强数字的格式操作全部放入getResult函数中，接收3个参数：数字，有效位数，类别标记
		a = getResult(a,n,ai);
		b = getResult(b,n,bi);
		if(a.equals(b))
			System.out.print("YES" + a);
		else
			System.out.print("NO" + a + b);
		
	}
	
	/**
	 * 这里主要的难点在于指数的确定：
	 * 首先，当n>1时，无论有效位怎样变化，都是向左移动固定的整数位，L中存放的是小数点的下标位置，由于下标从0开始，L即为需要向左移动的位数，反过来即是10的幂，
	 *     由于j始终不会为0，r中存放的便是它的初始值0，此时l-r永远>=0,re就是小数点左边的位数
	 * 同理，>1时，不管有没有小数部分，向左移动的位数都是确定的，假设此时就是一个整数，那移动的位数就是该数的长度，re默认存储的Index对应的便是该数字的长度
	 * 最后，<1时，即为0.xxx的格式时，l记录的是小数点的下标位置，而r记录的是小数点右边连续的最后一个0的位置，l-r即为小数点需要向右移动的位数，也就是10的
	 *     幂值，并且l始终<r,所以求出的值肯定<0,会更新re幂值
	 */
	private static String getResult(String s,int n,int index){
		char[] c = new char[n];  //c字符数组存放最后的有效数字
		int l = 0;  //存放小数点左边的整数位，当不包含小数位时，默认为0
		int r = 0;  //存放小数点右边的位数，
		int j = 0;
		for(int i=0;i<s.length() && j<n;i++){
			if(s.charAt(i) != '.'){
				if(j==0 && s.charAt(i)=='0')
					continue;
				if(j == 0)
					r = i-1;
				c[j++] = s.charAt(i);
			}else if(s.charAt(i) == '.'){
				l = i;
			}
		}
		while(j<n)
			c[j++] = '0';
		int re = index;
		if(l-r < 0)
			re = l-r;
		return " 0." + new String(c) + "*10^" + re;
		
	}
	
	/**
	 * 用户输入的数字可以分类3中情况
	 * 1.不包含小数的整数，此时返回整数的长度
	 * 2.包含小数的整数，此时返回小数点的位置
	 * 3.包含小数，且<1,比如0.123,此时返回标记0
	 */
	private static int index(String s){
		//indexOf，如果存在指定的字符，返回该字符的下标；如果不存在，返回-1
		int i = s.indexOf('.');  
		if(s.charAt(0) == '0'){ 
			i = 0;
		}else if(i == -1){
			i = s.length();
		}
		return i;
	}
	/**
	 * 为什么要有这个方法？因为测试用例中有一个为
	 * 测试用例:  5 00000001 1
	 * 为了防止这种变态的数据，需要将它前面无效的0全部都去掉，而对于其他那些正常的数，应该原样返回
	 */
	private static String format(String s){
		if(s.charAt(0) != '0')
			return s;
		int i = 1;
		for(;i<s.length();i++){
			if(s.charAt(i) != '0')
				break;
		}
		if(i == 1)
			return s;
		return s.substring(i);
	}
	
}
