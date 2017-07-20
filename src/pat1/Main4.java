package pat1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main4 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); //供应链中的总成员数目
		double p = in.nextDouble(); //供应的价格
		double r = in.nextDouble()/100; //价格增长率
		Node[] nodes = new Node[n];   //保存每个节点的上一个节点对象，parent代表其父节点对象，i代表节点的编号
		Node root = null;  //根节点parent值为-1，需要特别指定
		for(int i=0;i<n;++i){
			int parent = in.nextInt();
			nodes[i] = new Node(parent);
			if(parent==-1){
				root = nodes[i];
			}
		}
		
		//将所有的parent节点添加到其对应位置i的node节点上去
		for(int i=0;i<n;++i){
			if(nodes[i].parent!=-1){
				nodes[nodes[i].parent].addChild(nodes[i]);
			}
		}
		int d = -1; //d代表的是层次深度，及每一层次的经销商或零售商
	    int retailers = 0;  //代表最后一层的零售商个数
	    Queue<Node> queue = new LinkedList<Node>();  //使用队列存储每一层次的所有经销商（或零售商）
	    queue.add(root);  //首先将根节点插入队列，根节点自为一层，队列长度为1，并且通过根节点可以向下取出所有的子节点对象
	    while(!queue.isEmpty()){ //只要当前队列所在的层次仍然有节点对象，就继续向下广度遍历
	    	d++;  //每多便利一层，节点深度便+1
	    	int size = queue.size();  //当前队列的大小，实际上代表了该层次上的所有经销商
	    	retailers = size;   //赋值
	    	while(size--!=0){   //遍历该层次上的每一个节点对象
	    		Node top = queue.poll();  //挨个取出队列中的节点值，对该节点下的所有子节点加入到队列中，代表了下一层次所用有的节点数
	    		for(int i=0;i<top.count;++i){
	    			queue.add(top.getChild(i));  
	    		}
	    	}
	    }
		p *= Math.pow(1+r, d);  //p=P*(1.01)d次方，d为层次深度
		System.out.printf("%.2f %d\n",p,retailers);
		
	}
	//新建一个节点对象，用于存储每个成员的信息
	private static class Node{
		int parent;
		int count;
		ArrayList<Node> children;
		public Node(int parent) {
			this.parent = parent;
			this.children = new ArrayList<Node>();
		}
		public void addChild(Node node){
			children.add(node);
			count++;
		}
		public Node getChild(int i){
			if(i>=children.size()){
				return null;
			}
			return children.get(i);
		}
	}
}
