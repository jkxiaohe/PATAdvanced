package pat7;

import java.util.Scanner;

/**
 *题意解析：求解1-N之间包含1的数字的个数
 *     可以将该解分为2个部分，(before)d(after)
 *     before代表d之前的部分，即高位有多少位；
 *     after代表d之后的部分，低位有多少位；
 *     d为当前判断的位置
 */
public class Main7 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n, answer = 0;
		n = in.nextInt();
		//初始时，after为0位，before就是从个位数开始的，所以此时w=1,后面after主键增加，w*10
		for (int w = 1, after = 0; n != 0; w *= 10) {
			int before = n / 10;  //计算出去个位数后剩余的数字
			if (n % 10 == 1) {  //判断当前个位数上的数字是不是1,如果是1的话+1
				answer += after + 1;  
			}
			after += n % 10 * w; 
			answer += (before + ((n % 10 > 1) ? 1 : 0)) * w;
			n = before;
		}
		System.out.println(answer);
	}

}
