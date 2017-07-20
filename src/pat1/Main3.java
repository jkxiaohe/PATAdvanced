package pat1;

import java.util.HashMap;
import java.util.Scanner;

public class Main3 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); //记录城市的数量
		int k = in.nextInt(); //记录总共的路径数
		String start = in.next(); //记录开始的节点
		int[] costs = new int[n];   //用于记录到达每个城市所需要花费的总费用
		int[] hpns = new int[n];  //记录到达每个城市的幸福值
		int[] steps = new int[n];  //记录所经过的城市数
		int[] routes = new int[n];  //记录到达每个节点的所有路径数
		int[] parent = new int[n]; //记录每个节点的上一个，用于遍历输出路线
		int[][] M = new int[n][n]; //记录每2个城市之间的花费
		HashMap<String,Integer> index = new HashMap<String,Integer>(); //记录每个城市及其对应的下标
		boolean[] visited = new boolean[n]; //标记该节点有没有被广度搜索遍历过
		int[] h = new int[n]; //根据城市对应的下标记录每个城市对应的幸福值
		String[] names = new String[n];  //根据城市的下标记录每个城市的名字
		index.put(start, 0); //将开始城市的下标置为0
		routes[0]=1; //后面会用迭代的方式来赋值，此处用来初始化一个值
		parent[0]=-1; //开始节点的上一个节点，该数组是为了能够记录子节点的父节点
		names[0]=start; 
		
		//接受n-1行的数据：城市名：幸福值
		for(int i=1;i<n;++i){
			//初始化costs数组的值为无限大，用来在后面比较城市的路费时要用到，Integer.MAX_VALUE说明该城市未被访问过，被置为无穷大
			costs[i] = Integer.MAX_VALUE;
			//用来说明到达某个城市所得到的幸福值，Integer.MIN_VALUE：说明在没有到达该城市前，该城市的幸福值无限的小
			hpns[i] = Integer.MIN_VALUE;
			names[i] = in.next(); //将城市的名字记录到names数组中
			index.put(names[i], i);  //将城市的名字及其对应的下标记录到map集合中
			h[i] = in.nextInt();  //记录城市下标对应的幸福值
		}
		int end = index.get("ROM"); //找出终点城市对应的下标
		
		//接下来又K行数据，使用下标来记录每2个城市之间的话费
		for(int i=0;i<k;++i){
			int s = index.get(in.next());  
			int e = index.get(in.next());
			int cost = in.nextInt();
			M[s][e] = cost;
			M[e][s] = cost;
		}
		
		//遍历所有的城市，求得所有城市的路径数，花费，个数
		for(int t=0;t<n;t++){
			int v = -1; //广度遍历时，v代表广度搜索遍历的根，-1代表根值为空
			for(int i=0;i<n;++i){
				//!visited[i]：如果当前结点未被访问过，即根节点没有进行过广度搜索
				//v<0 ： 说明处于初始化状态
				//costs[i] < costs[v] : 默认情况下，那些没有被访问过的节点值为无穷大，而那些已经被访问过的节点会有一个具体的值，并且该值肯定小于那些未访问过的节点
				//所以将i给了v，相当于将未访问过的城市的下标值给了v(未广度遍历过的根节点交给v)
				if(!visited[i] && ((v<0) || (costs[i] < costs[v]))){
					v=i;
				}
			}
			visited[v] = true;
			//v:从该根节点开始，向下进行广度搜索遍历
			for(int i=0;i<n;++i){
				//!visited[i]:如果该节点为进行过广度搜索
				//M[v][i]:2个城市之间有路径，==0说明没有路径
				if(!visited[i] && M[v][i]!=0){
					int cost = costs[v] + M[v][i]; //costs[0]默认为0，M[v][i]代表了2个节点间需要的花费
					int happy = hpns[v] + h[i]; //该城市的幸福值加上前面城市的幸福值
					int step = steps[v] + 1; //当前城市+1 及前面城市的个数steps[v]
					boolean flag = false; //用来判断用不用更新路径数routes和花费costs
					//1.costs[i] :当前城市未访问过，为无穷大，要更新当前城市的cost值
					//2.cost[i] : 当前城市已经被访问过（ROM），判断有没有比它更小的花费，如果有的话就更新
					if(cost < costs[i]){
						costs[i] = cost; //更新到达当前城市的花费
						routes[i] = routes[v]; //到达该城市的路线+1
						flag = true; //说明有过更新
					}else if(cost==costs[i]){
						//说明到达ROM的路径中存在相同的花费
						//将可选的路径数加上原来可选的总路径数，不能是简单的加1，因为前面的路径数并不一定为1
						routes[i] += routes[v];
						//判断不同路径总的幸福值关系
						if(happy > hpns[i]){
							flag = true;
						}else if(happy==hpns[i] && step<steps[i]){
							flag = true;
						}
					}
					if(flag){
						costs[i] = cost;
						hpns[i] = happy;
						steps[i] = step;
						parent[i] = v;
					}
				}
			}
		}
		int total = steps[end];
		int happiness = hpns[end];
		int avg = happiness/total;
		total++;
		System.out.println(routes[end]+" "+costs[end]+" "+happiness+" "+avg);
		String[] result = new String[total];
		int j=total-1;
		while(end!=-1){
			result[j--]=names[end];
			end = parent[end];
		}
		for(int i=0;i<result.length-1;++i){
			System.out.print(result[i]+"->");
		}
		System.out.println(result[total-1]);
	}
}
