package pat1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main1 {
	
	//首先定义全局变量，用于在多个方法体中使用
	private static int mintime = Integer.MAX_VALUE;  //记录最短时间
	private static List<Integer> path = new ArrayList<Integer>();  //记录最短路径
	private static List<Integer> result;   //记录最后路径结果
	private static int[] C;  //记录每个节点的车辆数目
	private static int send;  //记录pbmc中心所要发送的车辆数
	private static int collect;  //需要从其他车站收集的车辆数
	private static int cmax;  //容量
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		cmax = in.nextInt();  //接收最大容量
		int n = in.nextInt();  //车站的数目
		int sp = in.nextInt();  //出问题的车站编号
		int m = in.nextInt();  //总路径条数
		C = new int[n+1];  //使用数组记录每个车站拥有的车辆数目
		int[][] M = new int[n+1][n+1]; //使用一个数组矩阵记录每2个节点的时间
		boolean[] visited = new boolean[n+1]; //记录该节点有没有被访问过，即通过
		//使用一个循环记录每个车站的车辆数
		for(int i=1;i<=n;++i){
			C[i] = in.nextInt();
		}
		//迭代M次，记录每一次的路径所需要的时间
		for(int i=0;i<m;++i){
			int start = in.nextInt();
			int end = in.nextInt();
			int time = in.nextInt();
			//将获取到的时间记录到矩阵中，并且由于矩阵的对称性，要在对称的一方也记录相应的时间
			M[start][end] = time;
			M[end][start] = time;
		}
		//数据全部接收完毕后，使用深度优先遍历搜索树遍历所有的结点，获取每次遍历的时间值和路径
		dfn(M,visited,0,0,0,sp);
		System.out.print(send + " ");
		//由于最后需要留有一个空格，所以嘴一个单独输出
		for(int i=0;i<result.size()-1;++i){
			System.out.print(result.get(i) + "->");
		}
		System.out.print(result.get(result.size()-1) + " ");
		System.out.println(collect);
	}
	
	/**
	 * 
	 * @param M    每条路径所消耗的时间
	 * @param visited  树中的该节点有没有被访问过
	 * @param from  从哪个节点开始访问
	 * @param cur  当前开始的节点
	 * @param time   从开始到现在的节点所消耗的时间
	 * @param sp  出问题的节点所在的地方
	 */
	public static void dfn(int[][] M,boolean[] visited,int from,int cur,int time,int sp){
		path.add(cur); //将走过的节点全部都加入到路径中
		visited[cur] = true; //将走过的节点标记为true
		time += M[from][cur]; //将走过的路径所消耗的时间进行累加
		if(cur == sp){
			//如果现在已经走到了出问题的节点处
			int s=0,c=0;  //s:PBMC所要送出的车辆数，c:PBMC要从各个车站收集的车辆数
			//i从1开始是因为：0代表的是PBMC,并且此时的path中存储了到达问题节点处的路径上的所有结点
			for(int i=1;i<path.size();++i){
				int index = path.get(i); //挨个获取走过的节点（但是不包括PBMC）
				//接下来要对每个节点处所存储的车辆是否处于（Perfect）状态进行判断
				if(C[index] > cmax/2){
					//当车站存储的车辆比完美状态多时
					c += C[index] - cmax/2;
				}else{
					//当车站存储的车辆比完美状态少时，分2种情况:
					//1:虽然少，但是可以从c中的获取的数目满足达到完美状态的要求
					//2:虽然少，即便c中的车辆也无法满足要求，此时需要 从PBMC中心送出车辆
					if((cmax/2 - C[index]) < c){
						//为了满足该车站的完美状态需求，从c中取出相应的车辆
						c -= (cmax/2 - C[index]);
					}else{
						s += (cmax/2 - C[index]) - c;
						c = 0;
					}
				}
			}
			//根据指定的规则选择出最佳的路径
			if(time < mintime){
				mintime = time;  //更新最短时间
				result = new ArrayList<Integer>(path); //将最优路径记录到result中
				send = s; //记录PBMC所要送出的车辆
				collect = c; //带回的车辆
			}else if(time==mintime){
				//当时间相同时，根据指标：send,collect选择
				if(s < send){
					result = new ArrayList<Integer>(path);
					send = s;
					collect = c;
				}else if(s==send && c < collect){
					result = new ArrayList<Integer>(path);
					send = s;
					collect = c;
				}
			}
		}else{
			//如果仍然没有走到出问题的节点处，继续向下搜索遍历树
			for(int i=1;i<M[cur].length;++i){
				if(M[cur][i]!=0 && !visited[i]){
					dfn(M,visited,cur,i,time,sp);
				}
			}
		}
		//当上面的递归循环结束时，意味着树的一条路线已经搜索完毕，接下来再从树根开始，便利另一条路线
		path.remove(path.size()-1);  //留下树根节点
		visited[cur] = false; //重置树根节点的访问标记
	}
	
}
