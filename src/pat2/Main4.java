package pat2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main4 {
	
	static Map<String,Node> map = new HashMap<String,Node>();
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String headAddress = in.next();
		int n = in.nextInt();
		
		Set<Integer> set = new HashSet<Integer>();
		for(int i=0;i<n;++i){ 
			String address = in.next();
			int val = in.nextInt();
			String nextAddress = in.next();
			map.put(address, new Node(address, val, nextAddress));
		}
		Node head = map.get(headAddress);
		set.add(Math.abs(head.val));
		Node head2 = new Node();
		Node cur = head;
		Node cur2 = head2;
		String nextAddress = head.nextAddress;
		while(!nextAddress.equals("-1")){
			Node next = map.get(nextAddress);  
			if(set.add(Math.abs(next.val))){
				cur.nextAddress = next.address;
				cur = map.get(next.address);
			}else{
				cur2.nextAddress = next.address;
				cur2 = map.get(next.address);
			}
			nextAddress = next.nextAddress;
		}
		cur.nextAddress = "-1";
	    print(head);
	    print(map.get(head2.nextAddress));
	}
	
	private static void print(Node head){
		while(head!=null){
			System.out.print(head.address + " " + head.val + " ");
			if(head.nextAddress.equals("-1")){
				System.out.println(-1);
				break;
			}else{
				System.out.println(head.nextAddress);
			}
			head = map.get(head.nextAddress);
		}
	}
	private static class Node{
		String address;
		int val;
		String nextAddress;
		public Node(){}
		public Node(String address,int val,String nextAddress){
			this.address = address;
			this.val = val;
			this.nextAddress = nextAddress;
		}
	}
}

