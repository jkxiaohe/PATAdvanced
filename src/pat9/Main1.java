package pat9;

import java.util.Scanner;

/**
 *  题意解析：根据输入的2个字符串，求后缀相同的部分的开始的地址；
 *  思路：由于一个地址只存一个字符，所以可以用地址唯一的表示一个字符;
 *     这样，后缀的字符序列相同，就变成了后缀字符序列对应的地址是相同的;
 *     在比较的过程中，2个字符串必须长度要相等，这样在比较他们的后缀是否相同的时候才能够保证他们所在的末尾位置是相同的，
 *     对于比较长的那个字符串，可以直接把长的部分截去，不影响和另一个字符串的比较，因为另一个字符串更短，要用它去和长字符串的尾部序列去比较;
 *     
 *     每个链表节点的格式为：address data next
 *     可以直接在int数组中下表存放address,值为next
 */
public class Main1 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int[] add = new int[100000];  
		int x, y, z;
		x = in.nextInt();
		y = in.nextInt();
		z = in.nextInt();
		while (z-- != 0) {
			int where = in.nextInt();
			in.next();  //字符不需要存储，以为地址address和key是一一对应的
			add[where] = in.nextInt();
		}

		int lenx = 0;   //lenx记录第一个字符串的长度
		for (int i = x; i != -1; ++lenx, i = add[i])
			;
		int leny = 0;   //leny记录第二个字符串的长度
		for (int i = y; i != -1; ++leny, i = add[i])
			;

		//找出比较长的那个字符串，并将首节点的下标移动到相等的那个位置，由于lenx和leny的长度不确定，所以需要进行判断，
		//判断出来后，将start的位置移动到2个字符串长度相等的位置处
		for (int i = lenx - leny; i > 0; --i, x = add[x])
			;
		for (int i = leny - lenx; i > 0; --i, y = add[y])
			;

		//从2个字符串长度相等的位置开始比较，只要没有到达字符串的尾部，并且这2个字符串的当前地址不想等(说明对应的字符是不相同的)
		//一旦它们的地址相等，即x==y,那么他们对应的next也是相等的，后面就全部都相等了，此时应该退出，x(或y)记录了相同处的起始地址
		for (; (x >= 0) && (y >= 0) && (x != y); x = add[x], y = add[y])
			;
		
		if (x >= 0) {
			System.out.printf("%05d\n", x);
		} else {
			System.out.println("-1");
		}

	}

}
