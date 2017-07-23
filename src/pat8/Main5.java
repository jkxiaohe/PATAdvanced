package pat8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * 题意解析：从树中找出某一条路径，其权重和==给定的权重值
 * 
 */
public class Main5 {

	static final int maxn = 101;  //使用一个常量来标识数组范围的大小
	static int n,m,s;    //记录第一行输入的3个数字
	static Node[] node = new Node[maxn];  //使用数组来存储每一个节点
	static int[] path = new int[maxn];     //path用来存储路径，它存储满足给定权重值的路径的所有编号
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		s = in.nextInt();
		
		//第2行输入的每个权重对应第i个节点，依次将每个节点的权重值放入到node数组中
		for(int i=0;i<n;i++){
			node[i] = new Node();
			node[i].weight = in.nextInt();
		}
		
		//录入每个节点的子节点
		int id,k,child;
		for(int i=0;i<m;i++){
			id = in.nextInt();
			k = in.nextInt();
			//循环接收当前节点编号id的k个孩子节点编号，将子节点的编号都放入到child数组中
			for(int j=0;j<k;j++){
				child = in.nextInt();
				node[id].child.add(child);
			}
			//对于每个节点，都对它的子节点进行一个排序，排序的规则是按照每个子节点的权重值由大到小进行排序
			//这样的目的是在使用DFS进行深度遍历时，第一个找到的一定是所有子节点中权重值最大的那一个
			Collections.sort(node[i].child,new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					return  node[o2].weight - node[o1].weight;
				}
			});
		}
	    
		path[0] = 0;  //path数组用来记录路径，首节点一定是从根开始的
		//使用DFS算法进行查找，0是当前查找到的节点的下标位置，
		//numNode记录当前路径中的节点有多少个，用于查找完成后输出路径时的判断
		//sum,当前路径上这些节点的权重和
		DFS(0,1,node[0].weight);  
	}
	
	static void DFS(int index,int numNode,int sum){
		if(sum > s) return;  //如果当前路径的权重和已经>s了，说明该路径不满足要求，退出
		//如果当前路径权重和==s,判断最后一个节点还有没有孩子节点，如果有，说明该路径没有走完，不满足要求
		//如果该路径已经结束了，那么将numNode个节点的权重值依次输出，路径上的节点编号都存储在path数组中，从path数组中取出后从node数组找出其权重值
		if(sum == s){
			if(node[index].child.size()!=0)return;
			for(int i=0;i<numNode-1;i++){
				System.out.printf("%d ",node[path[i]].weight);
			}
			System.out.printf("%d\n",node[path[numNode-1]].weight);
			return;
		}
		//如果当前路径的权重和<s,那么遍历当前节点下的子节点，注意是深度遍历，path记录遍历的节点的编号，numNode+1说名节点的数量又增加了
		for(int i=0;i<node[index].child.size();i++){
			int child = node[index].child.get(i);
			path[numNode] = child;
			DFS(child,numNode+1,sum+node[child].weight);
		}
		
	}
	
	//节点对象，节点存储的内容包括：
	//1.权重值，当前节点的权重是多少；2.child作为list集合，存储当前节点的所有孩子节点，它存储的是叶子节点的编号，而不是权重
	static class Node{
		int weight;
		List<Integer> child = new ArrayList<Integer>();
	}
	
}
