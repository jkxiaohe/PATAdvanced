package pat10;

import java.util.*;

//1057
public class Main12 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int first = in.nextInt();
		int n = in.nextInt();
		int k = in.nextInt();
		
		Map<Integer,Node> map = new HashMap<Integer,Node>();
		List<Node> list = new ArrayList<Node>();
		while(n!=0){
			int adr = in.nextInt();
			int val = in.nextInt();
			int next = in.nextInt();
			map.put(adr, new Node(adr,val,next));
			n--;
		}
		in.close();
		
		//按照链表顺序取出节点
		while(first != -1){
			Node node = map.get(first);
			list.add(node);
			first = node.next;
		}
		
		//反转
		for(int i=k;i<=list.size();i+=k){
			int l = i-k;
			int r = i-1;
			while(l<r){
				Node t = list.get(l);
				list.set(l, list.get(r));
				list.set(r, t);
				l++;
				r--;
			}
		}
		
		//输出
		for(int i=0;i<list.size()-1;i++){
			String str = "" + list.get(i).address;
			String str2 = "" + list.get(i+1).address;
			while(str.length()<5)
				str = "0" + str;
			while(str2.length()<5)
				str2 = "0" + str2;
			System.out.println(str + " " + list.get(i).value + " " + str2);
		}
		String str = "" + list.get(list.size()-1).address;
		while(str.length()<5)
			str = "0" + str;
		System.out.println(str + " " + list.get(list.size()-1).value + " -1");
 		
	}
	
	static class Node{
		int address;
		int value;
		int next;
		Node(int addr,int v,int ne){
			address = addr;
			value = v;
			next = ne;
		}
	}
}
