package pat2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main1 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); //所有成员
		int m = in.nextInt(); //所有有孩子的成员
		Node[] nodes = new Node[n+1]; //n+1的原因是根节点的编号从1开始
		//接收m行数据，这M个人都为有孩子对象的节点
		for(int i=0;i<m;++i){
			int id = in.nextInt();  //该对象的编号
			int k = in.nextInt();  //孩子节点的数量 
			Node node = new Node(id, k);  //使用一个类来保存每个节点对象的值
			//将用户后面输入的K个孩子节点全部放入到node的children数组中，保存了该节点下的所有孩子对象
			for(int j=0;j<k;++j){
				node.addChild(in.nextInt());
			}
			nodes[id] = node;   //这里应该用id来表示对应节点的下标，不能用i，i只是用来计数的
		}
		int generation = 0; //代数
		int size = 0; //保存每一代的总人数
		int maxs = -1;  //保存最大人数
		int maxg = -1;  //保存最大人数对应的树的层次
		//使用队列来保存每一代的节点，并负责取出该行节点下的所有子节点
		Queue<Node> queue = new LinkedList<Node>(); 
		queue.add(nodes[1]);  //首先将根节点加入队列中，通过根节点可以向下深度搜索出所有的节点
		while(!queue.isEmpty()){
			generation++; //当前遍历的是树的第一层
			size = queue.size(); //当前层的所有节点
			if(size > maxs){  //如果有比保存的maxs更大的树的层次存在，那么更新
				maxs = size;
				maxg = generation;
			}
			if(maxs > n/2){  //作用：当只有根节点时的情况
				break;
			}
			while(size-- != 0){
				Node top = queue.poll();  //当整个树只有一个根节点时，调用poll方法会报错
				while(top!=null && !top.isEmpty()){
					queue.add(nodes[top.getChild()]);
				}
			}
		}
		System.out.println(maxs + " " + maxg);
	}
	
}
//节点对象，用于描述树中的每一个家庭对象
//id：代表了改成员的编号； children:代表了改成员对象下的所有孩子，使用数组来保存； 
//pos:用来统计孩子节点的数量
class Node{
	int id;
	int[] children;
	int pos = 0;
	//根据用户传入的成员编号和孩子数量分别为id和数组赋值
	public Node(int id,int k){
		this.id = id;
		this.children = new int[k];
	}
	//添加孩子节点时，pos顺便自加，代表了孩子的数量
	public void addChild(int id){
		children[pos++] = id;
	}
	//从该节点下的最后一个孩子开始取，zhi'dao3取完
	public int getChild(){
		return children[--pos];
	}
	//判断该节点下有没有孩子
	public boolean isEmpty(){
		return pos == 0;
	}
}
