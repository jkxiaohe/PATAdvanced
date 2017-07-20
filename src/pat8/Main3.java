package pat8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 此题需要注意的特殊地方：
 * 1.链表可能不只一个，此时只需将第一个进行排序即可；
   2.链表为空时 需要输出“0 -1”。
 */
public class Main3 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();  //链表节点个数
		int firadd = in.nextInt();   //头结点的地址
		//nodes数组存放用户开始输入的链表的所有节点对象，数组下标用来存放刚开始节点的地址
		Node[] nodes = new Node[1000001];  
		Node[] sorted = new Node[N];  //存放拍好序后的数组，只需要排N个节点即可
		
		//循环接收N个节点对象，根据用户输入的地址address,将其值和下一节点地址分别存入数组中
		for(int i=0;i<N;++i){
			int add = in.nextInt();
			nodes[add] = new Node();
			nodes[add].key = in.nextInt();
			nodes[add].next = in.nextInt();
		}
		
		//特殊情况：0个节点值
		if(firadd == -1){
			System.out.println("0 -1");
			return;
		}
		
		int tag;
		int add = firadd;
		//从首地址开始，循环将链表放入到sorted数组中，add用来记录nodes数组中顺序节点的地址
		//其中最后一个节点存放的地址为0，无关紧要
		for(tag = 0;add != -1;tag++){
			sorted[tag] = new Node();
			sorted[tag].key = nodes[add].key;
			sorted[tag].address = add;
			sorted[tag].next = nodes[add].next;
			add = nodes[add].next;
		}
		
		
		//根据指定的排序方法对sorted中的数据进行排序
		//由于输入的链表可能不止一个，故只需要对第一个链表中的所有节点排序即可，tag代表了第一个链表尾节点的坐标
		Arrays.sort(sorted, 0,tag,new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.key - o2.key;
			}
		});
//		Arrays.sort(sorted, 0, tag);
		
		//当前节点的next是后一个节点的address
		for(int i=0;i<tag-1;i++){
			sorted[i].next = sorted[i+1].address;
		}
		System.out.printf("%d %05d\n",tag,sorted[0].address);
		//循环输出即可，最后一个节点单独输出，其next为-1
		for(int i=0;i<tag-1;++i){
			System.out.printf("%05d %d %05d\n",sorted[i].address,sorted[i].key,sorted[i].next);
		}
		System.out.printf("%05d %d -1",sorted[tag-1].address,sorted[tag-1].key);
		
	}
	
	//类对象，存储链表的每一个节点
	static class Node{
		int address;
		int key;
		int next;
	}
	
}
