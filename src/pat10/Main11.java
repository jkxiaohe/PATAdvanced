package pat10;

import java.util.Scanner;

/**
 *  思路：使用一个二维数组来存放一个节点的所有信息，
 *     第一维用来存放每个节点的地址，第二维可以指定大小，如果为N,对应的address处即可存放N个信息；
 *     将链表的address按照顺序放入到sort数组中，
 *     按照反转数字K将sort数组中存放的地址进行反转，
 *     最后在输出的时候，根据地址到二维数组中找对应的值就行了。
 */
public class Main11 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int[][] nodes = new int[100000][2];
		int start = in.nextInt();
		int N = in.nextInt();
		int K = in.nextInt();
		int[] sort = new int[N];
		
		for(int i=0;i<N;i++){
			int address = in.nextInt();
			nodes[address][0] = in.nextInt();
			nodes[address][1] = in.nextInt();
		}
		int index = 0;
		while(start != -1){
			sort[index++] = start;
			start = nodes[start][1];
		}
		
		for(int i=0;i<index-index%K;){
			int temp = sort[i];
			sort[i] = sort[i/K*K + 4-1-i%K];
			sort[i/K*K + 4-1-i%K] = temp;
			if(i%K == 0){
				i++;
			}else if(i%K ==1){
				i+=3;
			}
		}
		
		for(int i=0;i<index-1;i++){
			System.out.printf("%05d %d %05d\n",sort[i],nodes[sort[i]][0],sort[i+1]);
		}
//		System.out.printf("05d% %d -1",sort[index-1],nodes[sort[index-1]][0]);
		System.out.printf("%05d %d -1",sort[index-1],nodes[sort[index-1]][0]);
	}
	
}
