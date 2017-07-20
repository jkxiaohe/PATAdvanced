package pat1;

import java.util.HashMap;
import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); //城市数目
		int k = in.nextInt(); //路径数目
		String start = in.next(); //起始城市
		int[] routes = new int[n];  //到达每个点的路由数量
		int[] costs = new int[n];  //点与点之间的路费
		int[] hpns = new int[n]; 
		int[] steps = new int [n]; //每一条路径的节点数
		int[] parent = new int[n]; //父节点
		int[][] M = new int[n][n]; //使用矩阵存储两个城市之间的价钱
		//map中存储城市的名字及其对应的下标
		HashMap<String,Integer> index = new HashMap<String,Integer>();
		boolean[] visited = new boolean[n];
		int[] h = new int[n];  //每个城市的幸福值
		String[] names = new String[n]; //按下标存储每个城市的名字
		index.put(start, 0); //首先将起始城市加入到map中，其下标为0
		names[0] = start; //第一个城市的名字为起始城市
		routes[0] = 1;
		parent[0] =  -1;
		index.put(start, 0);
		names[0] = start;
		
		//迭代n-1次，将城市的名字及幸福值录入
		for(int i=1;i<n;++i){
			costs[i] = Integer.MAX_VALUE;
			hpns[i] = Integer.MIN_VALUE;
			names[i] = in.next();  //录入城市名字
			index.put(names[i], i);  //将城市名字及其下标录入
			h[i] = in.nextInt();  //记录对应下标的幸福值
		}
		int end = index.get("ROM"); //取出终结点的下标值
		
		//迭代k行，记录每条路径的价钱
		for(int i=0;i<k;++i){
			int s = index.get(in.next()); //根据用户输入的城市名，到map中对应城市的下标值
			int e = index.get(in.next());
			//s:起始城市下标，e:终点城市下标,cost:使用临时变量存储消耗的金钱数目
			int cost = in.nextInt();
			M[s][e] = cost;
			M[e][s] = cost;
		}
		
		for(int t=0;t<n;t++){
			int v = -1;
			for(int i=0;i<n;i++){
				if(!visited[i] && ((v<0) || (costs[i]<costs[v]))){
					v = i;
				}
			}
			visited[v] = true;
			for(int i=0;i<n;++i){
				if(!visited[i] && M[v][i]!=0){
					int cost = costs[v] + M[v][i];
					int happy = hpns[v] + h[i];
					int step = steps[v] + 1;
					boolean flag = false;
					if(cost < costs[i]){
						costs[i] = cost;
						routes[i] = routes[v];
						flag = true;
					}else if(cost == costs[i]){
						routes[i] += routes[v];
						if(happy>hpns[i]){
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
		int j = total-1;
		while(end != -1){
			result[j--] = names[end];
			end = parent[end];
		}
		for(int i=0;i<result.length-1;++i){
			System.out.print(result[i]+"->");
		}
		System.out.println(result[total-1]);
		
	}
}
