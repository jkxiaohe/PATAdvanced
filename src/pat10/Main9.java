package pat10;

import java.util.Scanner;

/**
 * @author gljg
 * 题意解析：根据指定的数字K，对链表的数字进行反转。
 *      链表中的每个节点Node由3部分组成：address data next
 * 思路：使用数组存放每个地址对应的data和next,这样子需要2个，
 *     另外使用一个数组存放排好序的节点，这是第3个数组，并且题目所给的节点不一定都在列表内部，所以要单独使用一个数值存放链表的长度；
 *     在将链表按照K进行反转，在反转的过程中，由于后面的节点插入到前面，所以可能会覆盖原来的节点，造成值丢失，所以又使用了一个数组end;
 *     在反转的过程中，查找位置：
 *        (i/k)*k + (4-i%k-1):i为当前遍历的数字，k为反转的大小，i/k为当前反转轮数的首地址，4-1-i%k：为当前该轮内遍历的第几个数字；
 *        i<len-len%k:len为节点总长，len%k为不够一轮反转的节点个数，相减后，即为所有需要反转的节点数。
 *
 */
public class Main9 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int start = in.nextInt();
		int N = in.nextInt();
		int K = in.nextInt();
		
		int[] data = new int[100000];
		int[] next = new int[100000];
		int[] list = new int[N];
		int[] end = new int[N];
		
		for(int i=0;i<N;i++){
			int address = in.nextInt();
			data[address] = in.nextInt();
			next[address] = in.nextInt();
		}
		
		//将排好序后的链表放入到List中
		int index = 0;
		while(start != -1){
			list[index++] = start;
			start = next[start];
		}
		
		for(int i=0;i<index;i++){
			end[i] = list[i];
		}
		
		//按照k值反转
		for(int i=0;i<(index-index%K);i++){
			end[i] = list[(i/K)*K+(4-i%K-1)];
		}
		
		//输出结果
		for(int i=0;i<index-1;i++){
			System.out.printf("%05d %d %05d\n",end[i],data[end[i]],data[end[i]],end[i+1]);
		}
		System.out.printf("%05d %d -1\n",end[index-1],data[end[index-1]]);
	}
	
}
