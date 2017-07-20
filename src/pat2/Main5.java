package pat2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main5 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String headAddress = in.next();
		int n = in.nextInt();
		Map<String,Node> map = new HashMap<String,Node>();
		Set<Integer> set = new HashSet<Integer>();
		for(int i=0;i<n;++i){
			String address = in.next();
			int val = in.nextInt();
			String nextAddress = in.next();
			map.put(address, new Node(address,val,nextAddress));
		}
		Node head = map.get(headAddress);
		Node head2 = new Node();
		set.add(Math.abs(head.val));
		Node cur = head;
		Node cur2 = head2;
		String nextAddress = head.nextAddress;
		while(!nextAddress.equals("-1")){
			Node next = map.get(nextAddress);
			if(set.add(Math.abs(next.val))){
				cur.next = next;
				cur = next;
			}else{
				cur2.next = next;
				cur2 = next;
			}
			nextAddress = next.nextAddress;
		}
		while(head!=null){
			System.out.print(head.address+" "+head.val+" ");
			if(head.next==null){
				System.out.println(-1);
				break;
			}else{
				System.out.println(head.next.address);
			}
			head = head.next;
		}
		head2 = head2.next;
		while(head2!=null){
			System.out.print(head2.address+" "+head2.val+" ");
			if(head2.next==null){
				System.out.println(-1);
				break;
			}else{
				System.out.println(head2.next.address);
			}
			head2 = head2.next;
		}
	}
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
