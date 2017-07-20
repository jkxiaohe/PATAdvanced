package pat2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main3 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String headAddress = in.next();  //接收头结点的编号
		int n = in.nextInt();  //节点个数
		Map<String,Node> map = new HashMap<String,Node>();  //使用HashMap存储，格式为：地址，节点对象
		Set<Integer> set = new HashSet<Integer>();  //Set集合用来判断value值是否已经存在
		//遍历n次，查询出所有的节点对象，将接收到的数据全部保存到节点中，并将节点放入map集合中
		for(int i=0;i<n;++i){ 
			String address = in.next();
			int val = in.nextInt();
			String nextAddress = in.next();
			map.put(address, new Node(address, val, nextAddress));
		}
		/**
		 * 接下来的过程便是构造两个链表，分别存放两个结果值，分别是：保留下来的链表和移除的链表 ,因此在这里构造了2个节点对象，用来保存2个链表的头结点
		 * head是保存的链表，cur对应head的临时节点对象，用来将它的后续节点对象补充上去
		 * head2是移除的链表，cur2对应head2的临时节点对象，用来将它的后续移除的节点对象保存上去
		 */
		Node head = map.get(headAddress); //head节点存放结果列表的头结点
		set.add(Math.abs(head.val));  //set将头结点的value及时存入，用于后续判断是否重复
		Node head2 = new Node();  //head2节点是用来存放移除节点的头结点的
		Node cur = head;  
		Node cur2 = head2;
		String nextAddress = head.nextAddress;   //通过nextAddress是否为-1判断其是否到达链表的终点
		while(!nextAddress.equals("-1")){
			Node next = map.get(nextAddress);  
			//首先获取到下一个节点对象，再通过Set对象的add方法将其值加入到hashSet集合中，如果已存在，返回false;如果没有，返回true
			if(set.add(Math.abs(next.val))){
				//当加入成功时，说明该节点应该被保留下来，将它放入上一个节点的next上，并更新当前结点到下一个
				cur.next = next;
				cur = next;
			}else{
				//当加入失败时，说明该节点应该被移除
				cur2.next = next;
				cur2 = next;
			}
			//更新地址为下一个
			nextAddress = next.nextAddress;
		}
	    print(head);
	    print(head2.next);
	}
	
	private static void print(Node head){
		while(head!=null){
			System.out.print(head.address + " " + head.val + " ");
			//通过head.next对象是否为null,判断其有没有下一个节点，是输入-1还是下一个对象
			if(head.next == null){
				System.out.println(-1);
				break;
			}else{
				System.out.println(head.next.address);
			}
			head = head.next;
		}
	}
	
	//每一行的输入或输出格式为：地址：值：下一个节点的地址 ，在这里，next用来保存下一个节点对象
	private static class Node{
		String address;
		int val;
		String nextAddress;
		Node next;
		public Node(){}
		public Node(String address,int val,String nextAddress){
			this.address = address;
			this.val = val;
			this.nextAddress = nextAddress;
		}
	}
}

